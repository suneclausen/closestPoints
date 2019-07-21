# Closest Points Algorithm
This algorithm is based on the paper "Divide-and-conquer in multidimensional space" by Bentley and Shamos, 1976. The algorithm runs in O(n lg n) or O(n lg^{k-1} n), k = dimension, depending on the choice of appraoch for solving the problem.  


## The implementation
The code have been implemented in Java in a compositional design where all the vriable behaviour/strategies have been put under interfaces and the relevant implementation made in a subpackage. For each dimension there will be a facotory, which will contain all the needed strategies for finding the closest pair of points in the given dimension. 

The main component/class is the /mainComponents/ClosestPairLogicImpl.java, where the main algorithm runs and uses the different factories given. 

If you want to copy the code and run the algotihm in an implementation not yet supported you would have to implement the different strategies and get them from a factory. 

For testing purposes a verification algorithm have been developed in /mainComponents/Utility.java#verifyAlgorithm(...). 
This will run in a given amount of iterations and for each iteration create a random set of points, run the bruteforce, run the closestPairAlgorithm and check if the bruteforce and the algorithm got the same result. This can be used to verify further implementations. 

## The two different approaches to the problem
### The slow approach
This apprach has it basis in the idea of projecting points into lower dimension until the base case of 2- and 1-dimension is reached. For 2D You start out bu having a sorted set of points as input. This set is then divided into two subsets on the median point of the x axis. This is done recursively until there is only 2 or 3 points. Then these are returned and points of interest in each of the subset of the dividing line is compared against each other. The points of interst are points within a distance \delta of the dividing line. These points are then projected onto the dividing line and we have it in 1-dimensional setting where all points are just compared to hte one next to eachother.  

This is the basic logic for when having two dimension, but ff, however, you are in e.g. 3 dimension then the comparing of points works a bit differently. You would then have to find these points of interest and project them down onto the dividing hyperplane (since it is in 3D). This would then yield the problem to be considered in a 2-dimensional setting. The idea is then to use the 2D implementation to solve the problem. 

This is the idea and the same approach could be used for 4D, where you would use the 3D implementation that uses the 2D implementation. 

It gives the overall running time of O(n lg^{k-1} n), k = dimension

### The fast approach
This approach will no matter the dimension end up running in O(n lg n). 
... TBA ... 
