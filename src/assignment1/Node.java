package assignment1;

import java.util.ArrayList;

/**
 * this is not generic node implementation.
 * it contains methods and attributes for DijkstraAlgorithm algorithm use
 * @author Brad Lee
 *
 */
public class Node implements Comparable<Node>, BinHeapInfo
{
    // maximum length between nodes
    public static final int INFINITY = Integer.MAX_VALUE;

    // unique id for each node
    private int id;
    
    // total distance from source to this node
    private int distance;
    
    // for trace back purpose to get the path detail
    private Node prevNode;
    
    // have a binary heap position of this node
    private int priorityQueueIndex;

    // neighboring edges are in this list
    private ArrayList<Edge> edges;

    public Node(int id)
    {
        this.id = id;
        edges = new ArrayList<Edge>();
        this.distance = INFINITY;
        this.prevNode = null;
    }

    public int getDistance() 
    { 
        return distance; 
    }

    public void setDistance(int distance) 
    { 
        this.distance = distance; 
    }

    public Node getPrevNode() 
    { 
        return prevNode; 
    }

    public void setPrevNode(Node node) 
    { 
        this.prevNode = node; 
    }

    public ArrayList<Edge> getEdges() 
    { 
        return edges; 
    }

    public void addEdge(Edge edge)
    {
        edges.add(edge);
    }

    @Override
    public void setHeapIndex(int index)
    {
        this.priorityQueueIndex = index;   
    }

    @Override
    public int getHeapIndex()
    {
        return priorityQueueIndex;   
    }

    /**
     * it compares total path length
     */
    @Override
    public int compareTo(Node node)
    {
        return Integer.compare(distance, node.distance);
    }
}
