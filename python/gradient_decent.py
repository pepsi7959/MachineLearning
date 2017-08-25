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

# Delivetive of cost function by theta0 
def find_local_minimum_theta0(m, _input, output, theta_array):	
	err=0.0
	for i in range(0, m):
		err += (hypothesis(i, _input, theta_array) - output[i])
	err = err/m	
	return float(err)

# Delivetive of cost function by theta1
def find_local_minimum_theta1(m, _input, output, theta_array):	
	err=0.0
	for i in range(0, m):
		err += (hypothesis(i, _input, theta_array) - output[i])*_input[i]
	err = err/m	
	return float(err)

# Hypothesis
def hypothesis(i, x, z):
	h =  z[0]*1+z[1]*x[i]
	return h 

# Update theta by Gradient Descent
# Define theta0 as z0
# Define theta1 as z1
def find_local_minimum_theta(alpha, theta0, theta1, m, _input, output):
	z0 = theta0
	z1 = theta1
	t_z0 = 0.0
	t_z1 = 0.0
	r = 1 
	
	while 1 :
		print "===== Round %d =====" %(r)
		z = np.array([z0, z1])
		t_z0 = t_z0 - alpha * find_local_minimum_theta0(m, _input, output, z)	
		t_z1 = t_z1 - alpha * find_local_minimum_theta1(m, _input, output, z)	
		if z0 == t_z0 and z1 == t_z1 :
			break
		else:
			print " -> update thata0 from %.8f to %.8f" %(z0, t_z0)
			print " -> update thata1 from %.8f to %.8f" %(z1, t_z1)
			# Update Thata	
			z0 = t_z0
			z1 = t_z1
		r += 1
	return np.array([z0,z1])

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
#y=np.random.normal(5,1,100)
y=graph_gen(0,5,100)

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

# Leaning Rate
alpha = 0.00055

# Run Gradient Descent
theta_array = find_local_minimum_theta(alpha, 0.0, 0.0, m, x, y)
print "========================="
print "Gradient Descent : %f    " %(cost(m,x,y,theta_array))
print "========================="

# Plot original data
plt.plot(x,y,'rs')

# Plot linear regression function 
x_grph=np.arange(m);
plt.plot(x_grph, theta_array[0]+x_grph*(theta_array[1]), 'b-')

# Display Graph
#plt.axis([0.,100.,0.,100.])
plt.show()

