
/*

1) Sorting Coding:
  a) Insertion Sort: 
    - Loop throuth i = 0 ~ length-1
    - Check anything in front of i
      is in order
    - Swap if not in order
    
  b) Merge Sort:
    - Base case: 
        arr.length <= 1   
            -> Return!!!
    - Find mid point
    - Copy right(mid~length-1) and left(0~mid-1) array
    - Call mergeSort(left)
    - Call mergeSort(right)
    - Compare things in left and right array
        if any of i or j reaches the end of the subarray
            add the rest

    c) LSD Radix
    - Make a lists of buckets using LL
    - Find the max number using one loop
    - Create a "Base"
    - Create a "mod" helper method or make sure negative num mod is working
    - While (Math.abs(max)/base > 0)
        - Loop throug the numbers: 
            int bucketNumber = (mod(arr[j] / base, 10) + 9);
        - Move the number from the bucket to the array in order
        - update the "Base" (base *= 10)

    d) kth Select
    - kthSelectHelper(k, arr, comparator, rand, 0, arr.length - 1);
    - Base case: 
        if ((end - start) < 1) {
            return arr[start];
        }
    - Calculate the random pivot
        int pivotIdx = rand.nextInt(end + 1 - start) + start;
    - Swap start with pivot
    - i = start + 1
    - j = end
    - while i and j is not crossed:
        ((j - i) >= 0)
        - Find the i that is larger than the pivot
            i++
        - Find the j that is smaller than the pivot
            j--
        - If j and i is not crossed
            swap i and j
            i++; j--;
    - Swap start and j
    - Found if k-1 == j
        - k-1 < j => Look at start to j-1
        - k-1 > j => Look at j+1 to end





2) Pattern Matching
    a) BM

    b) KMP
        - Failure Table
            - i = 0; j = 1;
            - while j < m
                - if i == j

                    FT[j] = i + 1;
                    i++
                    j++

                  else
                    if i == 0

                        FT[j] = 0
                        j++

                    else

                        i = FT[i-1]
        
        - Process
            int m = pattern.length();
            int n = text.length();
            List<Integer> list = new ArrayList<Integer>();

            if (m > n) {
                return list;
            }

            int j = 0; 
            int i = 0;

            int[] failureT = buildFailureTable(pattern, comparator);

            while (i - (j + 1) < (n - m)) {
                if (Found the same) {
                    i++;
                    j++;
                    if (j < m) {
                        continue;
                    } else {
                        list.add(i - j);
                    }
                } else if (j == 0) {
                    i++;
                    continue;
                }

                j = failureT[j - 1];
            }
        }

    c) Rabin-Karp






3) Graphs
    a) DFS
    - Create a list
    - dfsH(start, graph, list);
    - Add the starting vertex to the list
    - get adj list
    - for each vertex that is adjacent
        if it is not in the list
            dfsH(currVertex, graph, list);

    b) Dijkstra's
    - Create a PQ
    - Create a Distance Map
    - Create a visited set
    - Set all vertex's distance to inf
        for (Vertex<T> ver: graph.getVertices()) {
            distanceMap.put(ver, Integer.MAX_VALUE);
        }
    - Enqueue (start,0)
    - while (!PQ.isEmpty   &&    graph.getVertices().size() > vS.size()  )
        - Dequeue and get u

        - if u is not marked as visited
            - addn u to visited set
            - update distance map

            - for each vertex that is adjacent to u
                - if it is not in the visited set
                      // Add them to the priority Q
                    - pQ.add(new VertexDistance<T>(verAdjacent.
                        getVertex(), distance + verAdjacent.getDistance()))
    c) Prim's:
    - Create a visited set, mst edge set, PQ
    - Get the strarting vertex's adj edges
    - Enqueue all start's edge
    - Mark start as visited
    - while (!PQ.isEmpty   &&    graph.getVertices().size() > vS.size()  )
        - Dequeue edge from PQ
        - Get the end vertex(w) of the edge(start, w)
        - if u is not marked as visited
            - addn u to visited set
            - add both forward and backward edge
            - get w's adjList

            - for each edge that is adjacent to w
                - if it is not in the visited set
                      // Add them to the priority Q
                    - pQ.add(new Edge<T>(w, wAdjacent.getVertex(), 
                      wAdjacent.getDistance()));
    - Check if disconnected
        if (mstEdgeSet.size() != 2 * (graph.getVertices().size() - 1)) {
            return null;
        }






*/

/*

Insertion Sort:
** j >= j-1

*/



/*

Merge Sort

*/



/*

LSD Sort

*/

/*

kthSelect Sort

*/

/*

dfs

*/
public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {

}


/*

dijkstras

*/
public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                        Graph<T> graph) {

}

/*

prim's

*/
public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {

}


