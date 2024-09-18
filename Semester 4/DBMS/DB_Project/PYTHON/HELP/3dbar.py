from mpl_toolkits.mplot3d import axes3d
import matplotlib.pyplot as plt
import numpy as np

xVal = ['Category 1','Category 1','Category 1','Category 2','Category 2','Category 2','Category 2','Category 3','Category 3','Category 3','Category 3']
yVal = [5,10,15,20,10,15,20,5,10,15,20]
zVal = [10,9,8,7,6,7,8,9,10,11,12]

# Initializing Figure
fig = plt.figure()
ax1 = fig.add_subplot(111, projection='3d')
ax1.set_facecolor((1.0, 1.0, 1.0))
# Creating a dictionary from categories to x-axis coordinates
xCategories = xVal
i=0
xDict = {}
x=[]
for category in xCategories:
  if category not in xDict:
    xDict[category]=i
    x.append(i)
    i+=1
  else:
    x.append(xDict[category])
# Defining the starting position of each bar (x is already defined)
z = np.zeros(len(x))
# Defining the length/width/height of each bar.
dx = np.ones(len(x))*0.1
dy = np.ones(len(x))
dz = zVal
ax1.bar3d(x, yVal, z, dx, dy, dz)
ax1.set_zlim([0, max(zVal)])
plt.xticks(range(len(xDict.values())), xDict.keys())
plt.show()

