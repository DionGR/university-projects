# SSD300


## Tutorials
- [Introduction to code](notebooks/code_introduction.ipynb).
- [Running tensorboard to visualize graphs](tutorials/tensorboard.md).


## Install
Follow the installation instructions from previous assignments.
Then, install specific packages with

```
pip install -r requirements.txt
```

## Dataset visualization

We have also created a script visualizing images with annotations. To run the script, do 

```
python -m dataset_exploration.save_images_with_annotations
```

By default, the script will print the 500 first train images in the dataset, but it is possible to change this by changing the parameters in the `main` function in the script.

## Qualitative performance assessment

To check how the model is performing on real images, check out the `performance assessment` folder. Run the test script by doing:

```
python -m performance_assessment.save_comparison_images <config_file>
```

If you for example want to use the config file `configs/ssd300.py`, the command becomes:

```
python -m performance_assessment.save_comparison_images configs/ssd300.py
```

This script comes with several extra flags. If you for example want to check the output on the 500 first train images, you can run:

```
python -m performance_assessment.save_comparison_images configs/ssd300.py --train -n 1000
```


## Bencharking the data loader
The file `benchmark_data_loading.py` will automatically load your training dataset and benchmark how fast it is.
At the end, it will print out the number of images per second.

```
python benchmark_data_loading.py configs/ssd300.py
```
