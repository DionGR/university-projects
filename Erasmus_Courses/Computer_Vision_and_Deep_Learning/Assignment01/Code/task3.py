import numpy as np
import utils
import matplotlib.pyplot as plt
from task2a import pre_process_images
from trainer import BaseTrainer
from task3a import cross_entropy_loss, SoftmaxModel, one_hot_encode
np.random.seed(0)


def calculate_accuracy(X: np.ndarray, targets: np.ndarray, model: SoftmaxModel) -> float:
    """
    Args:
        X: images of shape [batch size, 785]
        targets: labels/targets of each image of shape: [batch size, 10]
        model: model of class SoftmaxModel
    Returns:
        Accuracy (float)
    """
    Y = model.forward(X)
    
    return np.mean(np.argmax(Y, axis=1) == np.argmax(targets, axis=1))


class SoftmaxTrainer(BaseTrainer):

    def train_step(self, X_batch: np.ndarray, Y_batch: np.ndarray):
        """
        Perform forward, backward and gradient descent step here.
        The function is called once for every batch (see trainer.py) to perform the train step.
        The function returns the mean loss value which is then automatically logged in our variable self.train_history.

        Args:
            X: one batch of images
            Y: one batch of labels
        Returns:
            loss value (float) on batch
        """
        Y_batch_pred = self.model.forward(X_batch)
        
        self.model.backward(X_batch, Y_batch_pred, Y_batch)
        
        self.model.w -= self.learning_rate * self.model.grad
        
        return cross_entropy_loss(Y_batch, Y_batch_pred)
        
    def validation_step(self):
        """
        Perform a validation step to evaluate the model at the current step for the validation set.
        Also calculates the current accuracy of the model on the train set.
        Returns:
            loss (float): cross entropy loss over the whole dataset
            accuracy_ (float): accuracy over the whole dataset
        Returns:
            loss value (float) on batch
            accuracy_train (float): Accuracy on train dataset
            accuracy_val (float): Accuracy on the validation dataset
        """
        # NO NEED TO CHANGE THIS FUNCTION
        logits = self.model.forward(self.X_val)
        loss = cross_entropy_loss(self.Y_val, logits)

        accuracy_train = calculate_accuracy(
            self.X_train, self.Y_train, self.model)
        accuracy_val = calculate_accuracy(
            self.X_val, self.Y_val, self.model)
        return loss, accuracy_train, accuracy_val


def main():
    # hyperparameters DO NOT CHANGE IF NOT SPECIFIED IN ASSIGNMENT TEXT
    num_epochs = 50
    learning_rate = 0.01
    batch_size = 128
    l2_reg_lambda = 0
    shuffle_dataset = True

    # Load dataset
    X_train, Y_train, X_val, Y_val = utils.load_full_mnist()
    X_train = pre_process_images(X_train)
    X_val = pre_process_images(X_val)
    Y_train = one_hot_encode(Y_train, 10)
    Y_val = one_hot_encode(Y_val, 10)

    # ANY PARTS OF THE CODE BELOW THIS CAN BE CHANGED.

    # Intialize model
    model = SoftmaxModel(l2_reg_lambda)
    
    # Train model
    trainer = SoftmaxTrainer(
        model, learning_rate, batch_size, shuffle_dataset,
        X_train, Y_train, X_val, Y_val,
    )
    train_history, val_history = trainer.train(num_epochs)

    print("Final Train Cross Entropy Loss:",
          cross_entropy_loss(Y_train, model.forward(X_train)))
    print("Final Validation Cross Entropy Loss:",
          cross_entropy_loss(Y_val, model.forward(X_val)))
    print("Final Train accuracy:", calculate_accuracy(X_train, Y_train, model))
    print("Final Validation accuracy:", calculate_accuracy(X_val, Y_val, model))

    plt.ylim([0.2, .6])
    utils.plot_loss(train_history["loss"],
                    "Training Loss", npoints_to_average=10)
    utils.plot_loss(val_history["loss"], "Validation Loss")
    plt.legend()
    plt.xlabel("Number of Training Steps")
    plt.ylabel("Cross Entropy Loss - Average")
    plt.savefig("task3b_softmax_train_loss.png")
    plt.show()

    # Plot accuracy
    plt.ylim([0.89, .93])
    utils.plot_loss(train_history["accuracy"], "Training Accuracy")
    utils.plot_loss(val_history["accuracy"], "Validation Accuracy")
    plt.xlabel("Number of Training Steps")
    plt.ylabel("Accuracy")
    plt.legend()
    plt.savefig("task3b_softmax_train_accuracy.png")
    plt.show()

        
    # Train a model with L2 regularization (task 4b)

    model_reg = SoftmaxModel(l2_reg_lambda=1.0)
    trainer = SoftmaxTrainer(
        model_reg, learning_rate, batch_size, shuffle_dataset,
        X_train, Y_train, X_val, Y_val,
    )
    train_his_reg, val_his_nonreg = trainer.train(num_epochs)
    
    def weights_to_image(model: SoftmaxModel) -> np.ndarray:
        W = model.w.T 
        
        W_no_bias = W[:, :-1] # remove bias
        
        W_imgs = W_no_bias.reshape(W_no_bias.shape[0], 28, 28) # reshape to 28x28 images
        
        return W_imgs
        
    model_nonreg_w = weights_to_image(model) 
    model_reg_w = weights_to_image(model_reg)
    
    assert model_nonreg_w.shape == model_reg_w.shape == (10, 28, 28)
    
    _, axs = plt.subplots(nrows=2, ncols=10, figsize=(20, 5))
    
    for i, _ in enumerate(model_nonreg_w):
        axs[0, i].imshow(model_nonreg_w[i], cmap="gray")
        axs[1, i].imshow(model_reg_w[i], cmap="gray")
        axs[0, i].axis("off")
        axs[1, i].axis("off")
    plt.subplots_adjust(wspace=0, hspace=0)
    
    plt.savefig("task4b_softmax_weight.png")
    plt.show()
         
    # You can finish the rest of task 4 below this point.
       
    # Plotting of accuracy for different values of lambdas (task 4c)
    lambdas = [1, .1, .01, .001]
    l2_norms = []
    val_histories_lambda = {}
    
    for reg_lambda in lambdas:
        model = SoftmaxModel(l2_reg_lambda=reg_lambda)
        
        trainer = SoftmaxTrainer(
            model, learning_rate, batch_size, shuffle_dataset,
            X_train, Y_train, X_val, Y_val,
        )
        
        _, val_his = trainer.train(num_epochs)
        
        val_histories_lambda[reg_lambda] = val_his["accuracy"]
        
        # Keep track of the l2 norm of the weights (task 4e) 
        norm_squared = np.sum(model.w**2)
        l2_norms = np.append(l2_norms, norm_squared)
        
    
    # Plot accuracy
    plt.ylim([0.7, .93])
    for reg_lambda in lambdas:
        utils.plot_loss(val_histories_lambda[reg_lambda], f"λ: {reg_lambda}")
    plt.xlabel("Number of Training Steps")
    plt.ylabel("Accuracy")
    plt.legend()
    plt.savefig("task4c_l2_reg_accuracy.png")
    plt.show()

    # Task 4e - Plotting of the l2 norm for each weight
    plt.plot(lambdas, l2_norms, label="L2 Norm of weights")
    plt.xlabel("Lambda")
    plt.ylabel("L2 Norm of Weights")
    plt.legend()
    plt.savefig("task4e_l2_reg_norms.png")
    plt.show()



if __name__ == "__main__":
    main()
