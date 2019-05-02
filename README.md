
Augusto de Oliveira Batista				CS2334-10				May 2, 2019

Project Description:
 
Without any file provided the project requires the creation of graphical interface to display the hamming distance of 
a given station from all the other given in Mesonet.txt. However, the projet requires the application to be run using 
a GUI which has the several fuctions: display all the stations whitin a given hamming distance from the selected 
station in the combobox. 

Aproach:

The implementation of the project was broken into two parts:
	1. The implentatation of the components and organization with layout managers
	2. The implementaion of the funcionalities such was the calculation of hamming distance and the stations which have
	the same hamming distance as given as an input in the slider.


* Despite many layout managers options to create the layout given in the projet guidelines, the most effective layout
style for the project choosed was GridBagLayout due to the stretching of the components when added to other layout
managers. 

* Next, the implmentation for the functions of the program were implmented using Hamming Dist class from project1 in
which already had the following methods and fields to support the operations required by the program.

* The action listeners for the components in the the GraphicalHammingDist required that the HammindDist class to gather
all the station given in mesonet.txt which were used to setup the JComboBox (as a drop down menu) with all the stations
 in the file. Then, calcHammingDist was used to get all the distances from the mesoner.txt given the selected station 
 in the menu. Which was also used to print all the station with a certain given distance from station.
