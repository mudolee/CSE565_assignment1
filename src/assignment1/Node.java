package assignment1;

import java.util.ArrayList;

//this is not generic, specific to usage from DijkstraAlgorithm class
public class Node implements Comparable<Node>, BinHeapInfo
{
    public static final int INFINITY = Integer.MAX_VALUE;

    private int id;
    private int distance;
    private Node prevNode;
    private int priorityQueueIndex;

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

    public void sePrevNode(Node node) 
    { 
        this.prevNode = node; 
    }

    public int getEdgeSize() 
    { 
        return edges.size(); 
    }

    public int getEdgeLength(int index) 
    { 
        return edges.get(index).getLength(); 
    }

    public Node getEdgeOtherEnd(int index) 
    { 
        return edges.get(index).getOtherEnd(this); 
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

    @Override
    public int compareTo(Node node)
    {
        return Integer.compare(distance, node.distance);
    }
}
