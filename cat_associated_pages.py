''' Create a new text file that contain the name of all pages that belongs  to particular category
	(Using binary Search)
	Note:- bfsTree.txt is a text file corresponding to id = 881000
	'''

import pickle
import io
import pickle

#------------------- Binary Search Function------------------------
def binary_search(a, id):
   
    first = 0
    last = len(a) - 1

    while first <= last:									
        i = (first + last) / 2

        if a[i] == id:
            return  True
        elif a[i] > id:
            last = i - 1
        elif a[i] < id:
            first = i + 1
        else:
            return False

if __name__ == '__main__':

	p_filename = 'pages_cat_dict.p'							# id = pages, values = category
	catname_to_id = "category_dic.p"						# id = category_Name, values = category_Id
	cat_depth_List = 'indian_sportsmen.p'					# id = categorgy_id , values = depth of category, this file is created by cate_tree.py


	with open(p_filename, 'rb') as fp:
		page_cat_dict = pickle.load(fp)						# page_cat_dict represnts entire dictionary

	page_cat_id = list(page_cat_dict.keys())				# page_cat_id stores the id of dictionary 

	with open(catname_to_id, 'rb') as cti_fp:
	    cat_to_id_dict = pickle.load(cti_fp)				# cat_to_id_dict  represent enitre  dictionary

	with open(cat_depth_List, 'rb') as cList_fp:	
	    cat_depth_dict = pickle.load(cList_fp)				#cat_depth_dict represnt entire dictionary

	cat_depth_dic_id = list(cat_depth_dict.keys())			# cat_depth_dic_id stores the id of dictionary
	cat_depth_dic_id.sort()

	fp=open("indian_sport_cat_page_find.txt",'wb')			# create a new file to store the name of pages that belongs to particular category

	for i in page_cat_id:
		vector = False
		
		query_category = page_cat_dict[i]					# create the list of category that belongs to particular page

		for j in query_category:
			if(binary_search(cat_depth_dic_id,cat_to_id_dict[j]) and vector== False):	#cat_to_id_dict[j] gives the id of category name corresponding to j
				#if vector == False:
					print(i)
					fp.write(i+'\n')					#write the page name in a text file
					vector= True
					break
			

	fp.close()												#close the text file									


