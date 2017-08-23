import numpy as np 
import matplotlib.pyplot as plt
import random

# Cost function
def cost(m, x, y, z):
	err=0.0
	for i in range(0, m):
		err += ((hypothesis(i, x, z) - y[i])**2)
	err = err/(2*m)	
	return float(err)

# Hypothesis
def hypothesis(i, x, z):
	h =  z[0]*1+z[1]*x[i]
	return h 

# Generate linear function
def graph_gen(low, high, size):
	g=np.arange(size, dtype=float)
	for i in range(0 , size):
		g[i] = i + random.uniform(low,high)
	return g

# Input used for tranning
#x=np.array([0,1,2,3,4,5])
x=np.arange(100,dtype=float)

# Output used for tranning
#y=np.array([0,1,2,3,4,5])
y=np.random.normal(5,1,100)
#y=graph_gen(0,2,100)

# Initiaize theta
z=np.array([0,0])

# Range of theta value
theta_range=50

# Size of rows or Columns
theta_sz=2*theta_range+1 #1 for including 0 value

# Number of input elements 
m=x.size

# Results of Squared Error
result_squared_err = np.ones(((2*theta_range)+1, (2*theta_range)+1), dtype=float)

# calculate squared error in order to find minimal squared error value 
for z0 in range(-theta_range ,theta_range+1):
	z[0] = z0
	for z1 in range(-theta_range ,theta_range+1):
		z[1] = z1
		result_squared_err[z0+theta_range][z1+theta_range] = cost(m, x, y, z)

# Find the position of minimal squared error
pos_theta = np.unravel_index(result_squared_err.argmin(),result_squared_err.shape)

# Transfrom to one-dimensional arrays
result_squared_err_1d = result_squared_err.reshape((1,theta_sz*theta_sz))

print "***** Mininal Squared Error *****"
print "---------------------------------"
print "Position of theta : %s           " %(str(pos_theta))
print "---------------------------------"
print "minimal squared error   : %f"  %(result_squared_err.min())
print "Theta(%d,%d)            : %f" %(pos_theta [0]-theta_range, pos_theta [1]-theta_range, result_squared_err[pos_theta [0]][pos_theta [1]])

#print result_squared_err 
#print result_squared_err_1d

# Plot original data
plt.plot(x,y,'rs')

# Plot linear regression function 
x_grph=np.arange(m);
plt.plot(x_grph, 1*(pos_theta [0]-theta_range)+x_grph*(pos_theta [1]-theta_range), 'b-')

# Plot squared error
# new_row = ((row+1)*row_sz)-(row_sz-(col+1)))
#row_sz = 2*theta_range+1
#px = ((pos_theta [0]+1)*row_sz)-(row_sz-(pos_theta [1]+1))
#plt.plot(result_squared_err_1d[0], 'go', px,result_squared_err.min(),'rs')

# Display Graph
plt.axis([0.,100.,0.,100.])
plt.show()

