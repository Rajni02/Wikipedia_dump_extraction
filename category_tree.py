''' create a category tree upto fourth level using BFS Traversal'''

import networkx as nx
from collections import deque
import pickle


if __name__ == '__main__':

	cat_dic_file = "category_dic.p"									# id = category_Name, values = category_Id				
	cat_dic_rev_file = "category_dic_rev.p"							# id = category_Id, values = category_Name
	path ="category_graph.txt"										# store the node and edge of the graph
	g=nx.Graph()   
	G = nx.read_edgelist(path, create_using=g, nodetype=int)		# read a text file and create a graph

	print("Enter the id")												
	id = int(input())												# user insert the id to create the category tree for that particular Id 

	n = g.neighbors(id)												# find all the adjacent node of the id node
	print (list(n))

	with open(cat_dic_rev_file, 'rb') as cdr_fp:
	    data = pickle.load(cdr_fp)									# data represent the entire cat_dic_rev dictionary 

 
 # -----------Code to print the name of parent category and their child category ----------------------

	'''deque = deque()
	deque.append(id)
	j=0
	while(len(deque)<300):
		k = list(deque)[j]
		j =j+1
		for i in g.neighbors(k):
			deque.append(i)


	while deque: 
		k = deque.popleft()
		print ("Parent is : ",k, data[k])
		print("Children adjacent to parents id: ",k)
		for x in g.neighbors(k):
			print (x,data[int(x)])'''

	#------------------ Find Diameter of a graph-----------
	'''print('Diameter of graph is')								#  Create a bfs tree		
	diameter = nx.diameter(g)
	print(diameter)'''



	f=nx.bfs_tree(g,id)												#  Create a bfs tree		

	print(len(f))													# no. of node in bfs tree
	print(nx.number_connected_components(g))						# no. of connected component in BFS tree

	print(nx.single_source_shortest_path_length(f,id,4))			# Print name of all node upto 5 level in bfs tree
	k = nx.single_source_shortest_path_length(f,id,4)

	with open('indian_sportsmen.p', 'wb') as fp:					# Create a pickle file to store the bfs Tree upto 4th node
		pickle.dump(k,fp, protocol= pickle.HIGHEST_PROTOCOL)





