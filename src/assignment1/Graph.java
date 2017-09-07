package assignment1;

import java.util.ArrayList;

public class Graph
{
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;

    public Graph()
    {
        nodes = new ArrayList<Node>();
        edges = new ArrayList<Edge>();
    }
    
    public ArrayList<Node> getNodes()
    {
        return nodes;
    }
    
    public void addNode(Node node)
    {
        nodes.add(node);
    }
    
    public ArrayList<Edge> getEdges()
    {
        return edges;
    }
    
    public void addEdge(Edge edge)
    {
        edges.add(edge);
    }
}

