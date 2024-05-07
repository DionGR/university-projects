from tops.config import LazyCall as L
# The line belows inherits the configuration set for the SSD300 mnist config
from .ssd300 import (
    train,
    optimizer,
    schedulers,
    loss_objective,
    model,
    backbone,
    data_train,
    data_val,
    train_cpu_transform,
    val_cpu_transform,
    gpu_transform,
    label_map
)
# We can keep all other configs the same, and only change the learning rate to a given value.
# You can now start training with the following command: python train.py configs/change_lr.py
optimizer.lr = 1