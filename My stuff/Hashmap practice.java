/*
Hash Function: Use the integer given.
Compression Function: Mod the hashcode by the table capacity.
Table Capacity: 5
Resize Information: Max LF is 0.67. Resize to 2 * (old backing table's length) + 1 if necessary.

Add 55, 2110, 4, 133, 322, 91, 78, 51 in that order.

Draw the final HashMap using the following notation:

0 -> 322
1 -> 
2 -> 
3 -> 
4 -> 4
5 -> 51
6 ->
7 -> 
8 ->
9 -> 55
10 -> 78
11 ->
12 ->
13 ->
14 ->
15 ->
16 ->
17 -> 2110
18 -> 133
19 ->
20 ->
21 ->
22 -> 91

The first resize operation occurs during the put call for 133. 
The table is first resized from capacity 5 to capacity 11, 
then 133 is added.

The second resize operation occurs during the put call for 51. 
The table is first resized from capacity 11 to 23, then 51 is added.