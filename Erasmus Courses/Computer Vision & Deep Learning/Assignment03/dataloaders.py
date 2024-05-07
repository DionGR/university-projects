from torchvision import transforms, datasets
from torch.utils.data.sampler import SubsetRandomSampler
import torch
import typing
import numpy as np
import pathlib
np.random.seed(0)

mean = (0.5, 0.5, 0.5)
std = (.25, .25, .25)


def get_data_dir():
    server_dir = pathlib.Path("/work/datasets/cifar10")
    if server_dir.is_dir():
        return str(server_dir)
    return "data/cifar10"


def load_cifar10(batch_size: int, 
                 validation_fraction: float = 0.1,
                 augment=False,
                 resnet=False
                 ) -> typing.List[torch.utils.data.DataLoader]:
    # Note that transform train will apply the same transform for
    # validation!
    
    if resnet:
        mean = (0.485, 0.456, 0.406)
        std = (0.229, 0.224, 0.225)
    else:
        mean = (0.5, 0.5, 0.5)
        std = (.25, .25, .25)
            
    train_trans_list = [transforms.ToTensor(),
                        transforms.Normalize(mean, std)]
    test_trans_list = [transforms.ToTensor(),
                       transforms.Normalize(mean, std)]
    
    if resnet:
        train_trans_list.append(transforms.Resize(224))
        test_trans_list.append(transforms.Resize(224))
        
    transform_train = transforms.Compose(train_trans_list)
    transform_test = transforms.Compose(test_trans_list)
        
    data_train = datasets.CIFAR10(get_data_dir(),
                                  train=True,
                                  download=True,
                                  transform=transform_train)

    data_test = datasets.CIFAR10(get_data_dir(),
                                 train=False,
                                 download=True,
                                 transform=transform_test)
        
    if augment:
        augment_trans_list = [
            transforms.ToTensor(),
            transforms.Normalize(mean, std),
            transforms.RandomResizedCrop(32),
            transforms.ColorJitter(),
        ]
        
        if resnet:
            augment_trans_list.append(transforms.Resize(224))
        
        transform_augment_test = transforms.Compose(augment_trans_list)
        
        augmented_data = datasets.CIFAR10(get_data_dir(),
                                train=True,
                                download=True,
                                transform=transform_augment_test)
        
        mixed_train_data = torch.utils.data.ConcatDataset((data_train, augmented_data))     
        
        indices = list(range(len(data_train)))
        split_idx = int(np.floor(validation_fraction * len(data_train)))

        val_indices = np.random.choice(indices, size=split_idx, replace=False)
        train_indices = list(set(indices) - set(val_indices))   
        
        train_indices += [indice * 2 for indice in train_indices] # Get the respective augmented idxs
        
        train_sampler = SubsetRandomSampler(train_indices)
        validation_sampler = SubsetRandomSampler(val_indices)

        dataloader_train = torch.utils.data.DataLoader(mixed_train_data,
                                                    sampler=train_sampler,
                                                    batch_size=batch_size,
                                                    num_workers=2,
                                                    drop_last=True)

        dataloader_val = torch.utils.data.DataLoader(data_train,
                                                    sampler=validation_sampler,
                                                    batch_size=batch_size,
                                                    num_workers=2)

        dataloader_test = torch.utils.data.DataLoader(data_test,
                                                    batch_size=batch_size,
                                                    shuffle=False,
                                                    num_workers=2)
    else: 
        indices = list(range(len(data_train)))
        split_idx = int(np.floor(validation_fraction * len(data_train)))

        val_indices = np.random.choice(indices, size=split_idx, replace=False)
        train_indices = list(set(indices) - set(val_indices))   
        train_sampler = SubsetRandomSampler(train_indices)
        validation_sampler = SubsetRandomSampler(val_indices)

        dataloader_train = torch.utils.data.DataLoader(data_train,
                                                    sampler=train_sampler,
                                                    batch_size=batch_size,
                                                    num_workers=2,
                                                    drop_last=True)

        dataloader_val = torch.utils.data.DataLoader(data_train,
                                                    sampler=validation_sampler,
                                                    batch_size=batch_size,
                                                    num_workers=2)

        dataloader_test = torch.utils.data.DataLoader(data_test,
                                                    batch_size=batch_size,
                                                    shuffle=False,
                                                    num_workers=2)


    return dataloader_train, dataloader_val, dataloader_test
