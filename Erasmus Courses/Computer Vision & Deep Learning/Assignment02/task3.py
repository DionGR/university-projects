import utils
import matplotlib.pyplot as plt
from task2a import pre_process_images, one_hot_encode, SoftmaxModel
from task2 import SoftmaxTrainer


def main():
    # hyperparameters DO NOT CHANGE IF NOT SPECIFIED IN ASSIGNMENT TEXT
    num_epochs = 50
    learning_rate = .1
    batch_size = 32
    neurons_per_layer = [64, 10]
    momentum_gamma = .9  # Task 3 hyperparameter
    shuffle_data = True

    use_improved_sigmoid = False
    use_improved_weight_init = False
    use_momentum = False
    use_relu = False

    # Load dataset
    X_train, Y_train, X_val, Y_val = utils.load_full_mnist()
    X_train = pre_process_images(X_train)
    X_val = pre_process_images(X_val)
    Y_train = one_hot_encode(Y_train, 10)
    Y_val = one_hot_encode(Y_val, 10)

    model = SoftmaxModel(
        neurons_per_layer,
        use_improved_sigmoid,
        use_improved_weight_init,
        use_relu)
    trainer = SoftmaxTrainer(
        momentum_gamma, use_momentum,
        model, learning_rate, batch_size, shuffle_data,
        X_train, Y_train, X_val, Y_val,
    )
    train_history, val_history = trainer.train(num_epochs)

    # Task 3 Testbench
    setups = [
        {'params': {'use_improved_weight_init': True, 'use_improved_sigmoid': False, 'use_momentum': False}, 'learning_rate': .1},
        {'params': {'use_improved_weight_init': True, 'use_improved_sigmoid': True, 'use_momentum': False}, 'learning_rate': .1},
        {'params': {'use_improved_weight_init': True, 'use_improved_sigmoid': True, 'use_momentum': True}, 'learning_rate': 0.02},
    ]
    
    for setup in setups:
        model = SoftmaxModel(
            neurons_per_layer,
            setup['params']['use_improved_sigmoid'],
            setup['params']['use_improved_weight_init'],
            use_relu
        )
        trainer = SoftmaxTrainer(
            momentum_gamma, setup['params']['use_momentum'],
            model, setup['learning_rate'], batch_size, shuffle_data,
            X_train, Y_train, X_val, Y_val,
        )
        setup_train_history, setup_val_history = trainer.train(num_epochs)

        plt.figure(figsize=(10, 4))
        plt.subplot(1, 2, 1)
        utils.plot_loss(
            train_history["loss"], 
            "Initial Model", npoints_to_average=10)
        utils.plot_loss(
            setup_train_history["loss"], 
            "Modified Model", npoints_to_average=10)
        plt.ylim([0, .4])
        plt.legend()
        
        plt.subplot(1, 2, 2)
        plt.ylim([0.85, .99])
        utils.plot_loss(
            val_history["accuracy"], 
            "Initial Model")
        utils.plot_loss(
            setup_val_history["accuracy"], 
            "Modified Model")
        plt.ylabel("Validation Accuracy")
        plt.legend()
        plt.savefig(f"task3_{setup['params']['use_improved_weight_init']}_{setup['params']['use_improved_sigmoid']}_{setup['params']['use_momentum']}.png")

        plt.show()    

    
if __name__ == "__main__":
    main()
