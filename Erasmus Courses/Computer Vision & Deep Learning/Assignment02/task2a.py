import numpy as np
import utils
import typing

np.random.seed(1)


def pre_process_images(X: np.ndarray):
    """
    Args:
        X: images of shape [batch size, 784] in the range (0, 255)
    Returns:
        X: images of shape [batch size, 785] normalized as described in task2a
    """
    assert X.shape[1] == 784, f"X.shape[1]: {X.shape[1]}, should be 784"
    
    mu = np.mean(X)
    std = np.std(X)
    
    X = (X - mu)/std
    X = np.concatenate([X, np.ones((X.shape[0], 1))], axis=1)   
    
    print(f"Dataset μ: {mu} and σ: {std}.")
        
    return X


def cross_entropy_loss(targets: np.ndarray, outputs: np.ndarray):
    """
    Args:
        targets: labels/targets of each image of shape: [batch size, num_classes]
        outputs: outputs of model of shape: [batch size, num_classes]
    Returns:
        Cross entropy error (float)
    """
    assert (
        targets.shape == outputs.shape
    ), f"Targets shape: {targets.shape}, outputs: {outputs.shape}"
    
    return -np.sum(targets * np.log(outputs)) / targets.shape[0]


class SoftmaxModel:

    def __init__(
        self,
        # Number of neurons per layer
        neurons_per_layer: typing.List[int],
        use_improved_sigmoid: bool,  # Task 3b hyperparameter
        use_improved_weight_init: bool,  # Task 3a hyperparameter
        use_relu: bool,  # Task 3c hyperparameter
    ):
        np.random.seed(
            1
        )  # Always reset random seed before weight init to get comparable results.
        # Define number of input nodes
        self.I = 785
        self.use_improved_sigmoid = use_improved_sigmoid
        self.use_relu = use_relu
        self.use_improved_weight_init = use_improved_weight_init

        # Define number of output nodes
        # neurons_per_layer = [64, 10] indicates that we will have two layers:
        # A hidden layer with 64 neurons and a output layer with 10 neurons.
        self.neurons_per_layer = neurons_per_layer

        # Initialize the weights
        self.ws = []
        self.zs = []
        self.outputs = []
        prev = self.I
        for size in self.neurons_per_layer:
            w_shape = (prev, size)
            print("Initializing weight to shape:", w_shape)
            if self.use_improved_weight_init:
                w = np.random.normal(0, 1/np.sqrt(prev), w_shape)
            else:
                w = np.random.uniform(-1, 1, w_shape)
            self.ws.append(w)
            self.zs.append(0)
            self.outputs.append(0)
            prev = size
        self.grads = [None for _ in range(len(self.ws))]
        
    def sigmoid(self, X: np.ndarray) -> np.ndarray:
        if self.use_improved_sigmoid:
            return self.improved_sigmoid(X)
        else:
            return self.basic_sigmoid(X)
        
    def sigmoid_df(self, X: np.ndarray) -> np.ndarray:
        if self.use_improved_sigmoid:
            return self.improved_sigmoid_df(X)
        else:
            return self.basic_sigmoid_df(X)
        
    def improved_sigmoid(self, X: np.ndarray) -> np.ndarray:
        return 1.7159 * np.tanh(2/3 * X)
    
    def improved_sigmoid_df(self, X: np.ndarray) -> np.ndarray:
        return 1.7159 * 2/3 * (1 - np.tanh(2/3 * X)**2)
        
    def basic_sigmoid(self, X: np.ndarray) -> np.ndarray:
        return 1 / (1 + np.exp(-X))
    
    def basic_sigmoid_df(self, X: np.ndarray) -> np.ndarray:
        return self.basic_sigmoid(X) * (1 - self.basic_sigmoid(X))
    
    def softmax(self, X: np.ndarray) -> np.ndarray:
        return np.exp(X)/np.sum(np.exp(X), axis=1, keepdims=True)

    def forward(self, X: np.ndarray) -> np.ndarray:
        """
        Args:
            X: images of shape [batch size, 785]
        Returns:
            y: output of model with shape [batch size, num_outputs]
        """
        for i, w in enumerate(self.ws):
            if i == 0:
                z = X @ w
            else:
                z = self.outputs[i-1] @ w
                
            self.zs[i] = z
                
            if i == len(self.ws) - 1:
                self.outputs[i] = self.softmax(z)
            else:
                self.outputs[i] = self.sigmoid(z)
                
        return self.outputs[-1]

    def backward(self, X: np.ndarray, outputs: np.ndarray, targets: np.ndarray) -> None:
        """
        Computes the gradient and saves it to the variable self.grad

        Args:
            X: images of shape [batch size, 785]
            outputs: outputs of model of shape: [batch size, num_outputs]
            targets: labels/targets of each image of shape: [batch size, num_classes]
        """
        def gradient(X: np.ndarray, delta: np.ndarray) -> np.ndarray:
            return 1/targets.shape[0] * X.T @ delta
        
        def output_delta(targets: np.ndarray, outputs: np.ndarray) -> np.ndarray:
            return -(targets - outputs)
        
        def hidden_delta(delta: np.ndarray, W: np.ndarray, z: np.ndarray) -> np.ndarray:
            return (delta @ W.T) * self.sigmoid_df(z)
        
        assert (
            targets.shape == outputs.shape
        ), f"Output shape: {outputs.shape}, targets: {targets.shape}"
        # A list of gradients.
        # For example, self.grads[0] will be the gradient for the first hidden layer
        self.grads = [None for _ in range(len(self.ws))]
        self.deltas = [None for _ in range(len(self.ws))]
                
        self.deltas[-1] = output_delta(targets, outputs)
        self.grads[-1] = gradient(self.outputs[-2], self.deltas[-1])
        
        for i in range(len(self.ws) - 1, 0, -1):
            self.deltas[i-1] = hidden_delta(self.deltas[i], self.ws[i], self.zs[i-1])
            if i == 1:
                self.grads[i-1] = gradient(X, self.deltas[i-1])
            else:
                self.grads[i-1] = gradient(self.outputs[i-2], self.deltas[i-1])
        
        for grad, w in zip(self.grads, self.ws):
            assert (
                grad.shape == w.shape
            ), f"Expected the same shape. Grad shape: {grad.shape}, w: {w.shape}."

    def zero_grad(self) -> None:
        self.grads = [None for _ in range(len(self.ws))]


def one_hot_encode(Y: np.ndarray, num_classes: int):
    """
    Args:
        Y: shape [Num examples, 1]
        num_classes: Number of classes to use for one-hot encoding
    Returns:
        Y: shape [Num examples, num classes]
    """
    one_hot = np.zeros((Y.shape[0], num_classes))
    one_hot[np.arange(Y.shape[0]), Y.T] = 1
    
    return one_hot


def gradient_approximation_test(model: SoftmaxModel, X: np.ndarray, Y: np.ndarray):
    """
    Numerical approximation for gradients. Should not be edited.
    Details about this test is given in the appendix in the assignment.
    """

    assert isinstance(X, np.ndarray) and isinstance(
        Y, np.ndarray
    ), f"X and Y should be of type np.ndarray!, got {type(X), type(Y)}"

    epsilon = 1e-3
    for layer_idx, w in enumerate(model.ws):
        for i in range(w.shape[0]):
            for j in range(w.shape[1]):
                orig = model.ws[layer_idx][i, j].copy()
                model.ws[layer_idx][i, j] = orig + epsilon
                logits = model.forward(X)
                cost1 = cross_entropy_loss(Y, logits)
                model.ws[layer_idx][i, j] = orig - epsilon
                logits = model.forward(X)
                cost2 = cross_entropy_loss(Y, logits)
                gradient_approximation = (cost1 - cost2) / (2 * epsilon)
                model.ws[layer_idx][i, j] = orig
                # Actual gradient
                logits = model.forward(X)
                model.backward(X, logits, Y)
                difference = gradient_approximation - \
                    model.grads[layer_idx][i, j]
                assert abs(difference) <= epsilon**1, (
                    f"Calculated gradient is incorrect. "
                    f"Layer IDX = {layer_idx}, i={i}, j={j}.\n"
                    f"Approximation: {gradient_approximation}, actual gradient: {model.grads[layer_idx][i, j]}\n"
                    f"If this test fails there could be errors in your cross entropy loss function, "
                    f"forward function or backward function"
                )


def main():
    # Simple test on one-hot encoding
    Y = np.zeros((1, 1), dtype=int)
    Y[0, 0] = 3
    Y = one_hot_encode(Y, 10)
    assert (
        Y[0, 3] == 1 and Y.sum() == 1
    ), f"Expected the vector to be [0,0,0,1,0,0,0,0,0,0], but got {Y}"

    X_train, Y_train, *_ = utils.load_full_mnist()
    X_train = pre_process_images(X_train)
    Y_train = one_hot_encode(Y_train, 10)
    assert (
        X_train.shape[1] == 785
    ), f"Expected X_train to have 785 elements per image. Shape was: {X_train.shape}"

    neurons_per_layer = [64, 10]
    use_improved_sigmoid = True
    use_improved_weight_init = False
    use_relu = True
    model = SoftmaxModel(
        neurons_per_layer, use_improved_sigmoid, use_improved_weight_init, use_relu
    )

    # Gradient approximation check for 100 images
    X_train = X_train[:100]
    Y_train = Y_train[:100]
    for layer_idx, w in enumerate(model.ws):
        model.ws[layer_idx] = np.random.uniform(-1, 1, size=w.shape)

    gradient_approximation_test(model, X_train, Y_train)


if __name__ == "__main__":
    main()
