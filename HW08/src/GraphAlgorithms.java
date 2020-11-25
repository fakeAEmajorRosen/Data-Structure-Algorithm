import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;


/**
 * Your implementation of various graph algorithms.
 *
 * @author Ting-Ying Yu
 * @version 1.0
 * @userid tyu304 (i.e. gburdell3)
 * @GTID 903534212 (i.e. 900000000)
 *
 * Collaborators: I work on this assignment alone.
 *
 * Resources: I only use the course materials.
 */
public class GraphAlgorithms {

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * NOTE: You MUST implement this method recursively, or else you will lose
     * all points for this method.
     *
     * You may import/use java.util.Set, java.util.List, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
        if (start == null) {
            throw new IllegalArgumentException("Start cannot be null");
        } else if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null");
        } else if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("Start is not in the graph.");
        }
        // Create the list
        List<Vertex<T>> list = new ArrayList<>();
        // Call helper function
        dfsH(start, graph, list);

        return list;
    }

    /**
     * A helper method for dfs()
     * @param <T>   the generic typing of the data
     * @param ver     the start vertex for dfx
     * @param graph the graph to search through
     * @param list  list of vertices in visited order
     */
    private static <T> void dfsH(Vertex<T> ver, Graph<T> graph, List<Vertex<T>> list) {
        // Add vertex to list
        list.add(ver);
        // Get the adjList
        List<VertexDistance<T>> adjList = graph.getAdjList().get(ver);
        // Loop through all adjacent vertex
        for (VertexDistance<T> verAdj: adjList) {
            if (!list.contains(verAdj.getVertex())) {
                // Recursive call
                dfsH(verAdj.getVertex(), graph, list);
            }
        }
    }

    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     *
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing
     * infinity) if no path exists.
     *
     * You may import/use java.util.PriorityQueue,
     * java.util.Map, and java.util.Set and any class that
     * implements the aforementioned interfaces, as long as your use of it
     * is efficient as possible.
     *
     * You should implement the version of Dijkstra's where you use two
     * termination conditions in conjunction.
     *
     * 1) Check if all of the vertices have been visited.
     * 2) Check if the PQ is empty yet.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the Dijkstra's on (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every
     * other node in the graph
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph.
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                        Graph<T> graph) {
        if (start == null) {
            throw new IllegalArgumentException("Start cannot be null");
        } else if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null");
        } else if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("Start is not in the graph.");
        }
        // Create a Priority Queue, a distance map, and a visited set
        PriorityQueue<VertexDistance<T>> pQ = new PriorityQueue<>();
        Map<Vertex<T>, Integer> dM = new HashMap<>();
        Set<Vertex<T>> vS = new HashSet<>();

        // Set all vertex's distance to be inf first
        for (Vertex<T> ver: graph.getVertices()) {
            dM.put(ver, Integer.MAX_VALUE);
        }

        // Enqueue (start,0)
        pQ.add(new VertexDistance<T>(start, 0));

        // Stop when Priority Queue is empty and visited set is full
        while ((!pQ.isEmpty()) && (graph.getVertices().size() > vS.size())) {
            // Dequeue; get u and distance
            VertexDistance<T> v = pQ.remove();
            Vertex<T> u = v.getVertex();
            int distance = v.getDistance();

            // If u is not visited
            if (!vS.contains(u)) {
                // Mark u as visited
                vS.add(u);
                // Update distance map
                dM.put(u, distance);
                // get u's adjList
                List<VertexDistance<T>> uAdjList = graph.getAdjList().get(u);
                for (VertexDistance<T> verAdjacent: uAdjList) {
                    if (!vS.contains(verAdjacent.getVertex())) {
                        pQ.add(new VertexDistance<T>(verAdjacent.
                                getVertex(), distance + verAdjacent.getDistance()));
                    }
                }
            }


        }
        return dM;
    }

    /**
     * Runs Prim's algorithm on the given graph and returns the Minimum
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * reverse edge to the set as well. This is for testing purposes. This
     * reverse edge does not need to be the one from the graph itself; you can
     * just make a new edge object representing the reverse edge.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * You should NOT allow self-loops or parallel edges in the MST.
     *
     * You may import/use java.util.PriorityQueue, java.util.Set, and any
     * class that implements the aforementioned interface.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * The only instance of java.util.Map that you may use is the adjacency
     * list from graph. DO NOT create new instances of Map for this method
     * (storing the adjacency list in a variable is fine).
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin Prims on
     * @param graph the graph we are applying Prims to
     * @return the MST of the graph or null if there is no valid MST
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        if (start == null) {
            throw new IllegalArgumentException("Start cannot be null");
        } else if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null");
        } else if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("Start is not in the graph.");
        }
        // Initializations
        Set<Vertex<T>> vS = new HashSet<>();
        Set<Edge<T>> mstEdgeSet = new HashSet<>();
        PriorityQueue<Edge<T>> pQ = new PriorityQueue<>();

        // Get start's adj
        List<VertexDistance<T>> startAdjList = graph.getAdjList().get(start);

        // Enqueue all start's edge
        for (VertexDistance<T> verD: startAdjList) {
            pQ.add(new Edge<T>(start, verD.getVertex(), verD.getDistance()));
        }

        // Mark start as visited
        vS.add(start);

        // Stop when PQ is empty and VS is full
        while ((!pQ.isEmpty()) && (graph.getVertices().size() > vS.size())) {
            // Dequeue edge
            Edge<T> wEdge = pQ.remove();

            // Get the end vertex of the edge
            Vertex<T> w = wEdge.getV();


            // if w is not visited
            if (!vS.contains(w)) {
                // mark w as visited
                vS.add(w);

                // add both forward and backward edge
                mstEdgeSet.add(wEdge);
                mstEdgeSet.add(new Edge<T>(wEdge.getV(), wEdge.getU(), wEdge.getWeight()));

                // get w's adjList
                List<VertexDistance<T>> wAdjList = graph.getAdjList().get(w);
                for (VertexDistance<T> wAdjacent: wAdjList) {
                    if (!vS.contains(wAdjacent.getVertex())) {
                        // Enqueue w's edges
                        pQ.add(new Edge<T>(w, wAdjacent.getVertex(), wAdjacent.getDistance()));
                    }
                }

            }
        }

        // Check Disconnected
        if (mstEdgeSet.size() != 2 * (graph.getVertices().size() - 1)) {
            return null;
        }

        return mstEdgeSet;
    }
}
