package assignment1;

import java.util.HashMap;

import org.testng.annotations.Test;

public class TestNGDijkstraAlgorithm 
{
    private void addPath(HashMap<Integer, Node> nodeMap, Graph graph, int id1, int id2, int distance)
    {
        if (id1 == id2)
            return;
        
        Node node1 = nodeMap.get(id1);
        Node node2 = nodeMap.get(id2);
        
        if (node1 != null && node2 != null)
            return;     // handled before
        
        if (node1 == null)
        {
            node1 = new Node(id1);
            nodeMap.put(id1, node1);
            graph.addNode(node1);
        }
            
        if (node2 == null)
        {
            node2 = new Node(id2);
            nodeMap.put(id2, node2);
            graph.addNode(node2);
        }
        
        // new edge needed
        Edge edge = new Edge(node1, node2, distance);
        graph.addEdge(edge);
        
        node1.addEdge(edge);
        node2.addEdge(edge);
    }
    
    @Test
    public void f() 
    {
        HashMap<Integer, Node> nodeMap = new HashMap<Integer, Node>();
        Graph graph = new Graph();
        addPath(nodeMap, graph, 1, 2, 10);
        addPath(nodeMap, graph, 3, 5, 12);
        addPath(nodeMap, graph, 4, 2, 77);
        
        Node src = nodeMap.get(1);
        Node dst = nodeMap.get(4);
        
        DijkstraAlgorithm algo = new DijkstraAlgorithm();
        int distance = algo.findShortestDistance(graph, src, dst);
        
        System.out.printf("Distance = %d", distance);
    }
}
