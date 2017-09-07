package assignment1;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test cases class by using TestNG test framework
 * @author Brad Lee
 *
 */
public class TestNGDijkstraAlgorithm 
{
    private HashMap<Integer, Node> nodeMap;
    private Graph graph;
    private DijkstraAlgorithm dijkstra;
    
    @BeforeMethod
    public void prepTest()
    {
        nodeMap = new HashMap<Integer, Node>();
        graph = new Graph();
        dijkstra = new DijkstraAlgorithm();
    }
    
    private void addPath(int id1, int id2, int distance)
    {
        if (id1 == id2)
            return;
        
        Node node1 = nodeMap.get(id1);
        Node node2 = nodeMap.get(id2);
        
        if (node1 != null && node2 != null)
        {
            for (Edge edge : node1.getEdges())
            {
                if (edge.getSrcNode() == node2 || edge.getDstNode() == node2)
                    return;     // handled before
            }
        }   
        
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
    
    /**
     * network size: 7 nodes
     * link status : connected
     * source to destination : connected
     * expected shortest path : 91
     */
    @Test (priority = 50)
    public void testSmallMultiLinkNetwork() 
    {
        addPath(11, 3, 88);
        addPath(3, 9, 21);
        addPath(10, 4, 29);
        addPath(4, 1, 6);
        addPath(5, 11, 55);
        addPath(4, 3, 12);
        addPath(10, 5, 7);
        
        int distance = dijkstra.findShortestDistance(graph, nodeMap.get(11), nodeMap.get(4));
        System.out.printf("Distance = %d\n", distance);
        Assert.assertEquals(distance, 91);
    }
    
    /**
     * network size: 7 nodes
     * link status : disconnected
     * source to destination : disconnected
     * expected shortest path : INFINITY
     */
    @Test (priority = 40)
    public void testSmallDiscNetwork() 
    {
        addPath(11, 3, 88);
        addPath(3, 9, 21);
        addPath(10, 4, 29);
        addPath(4, 1, 6);
        addPath(5, 1, 55);
        
        int distance = dijkstra.findShortestDistance(graph, nodeMap.get(11), nodeMap.get(4));
        System.out.printf("Distance = %d\n", distance);
        Assert.assertEquals(distance, Node.INFINITY);
    }
    
    /**
     * network size: 5 nodes
     * link status : all connected
     * source to destination : connected
     * expected shortest path : 87
     */
    @Test (priority = 40)
    public void testSmallNetwork() 
    {
        addPath(1, 2, 10);
        addPath(3, 5, 12);
        addPath(4, 2, 77);
        addPath(2, 3, 2);
        
        int distance = dijkstra.findShortestDistance(graph, nodeMap.get(1), nodeMap.get(4));
        System.out.printf("Distance = %d\n", distance);
        Assert.assertEquals(distance, 87);
    }
    
    /**
     * network size: 2 node
     * link status : all connected
     * source to destination : connected
     * expected shortest path : 33
     */
    @Test (priority = 30)
    public void testTwoNetwork() 
    {
        addPath(7, 2, 33);
        
        int distance = dijkstra.findShortestDistance(graph, nodeMap.get(2), nodeMap.get(7));
        System.out.printf("Distance = %d\n", distance);
        Assert.assertEquals(distance, 33);
    }
    
    /**
     * network size: 1 node
     * link status : all connected
     * source to destination : same node
     * expected shortest path : 0
     */
    @Test (priority = 20)
    public void testOneNetwork() 
    {
        Node node = new Node(14);
        graph.addNode(node);
        
        // no edge available
        
        int distance = dijkstra.findShortestDistance(graph, node, node);
        System.out.printf("Distance = %d\n", distance);
        Assert.assertEquals(distance, 0);
    }
    
    /**
     * network size: 1 node
     * link status : all connected
     * source to destination : destination N/A
     * expected shortest path : INFINITY
     */
    @Test (priority = 16)
    public void testOneNetworkDestNull() 
    {
        Node node = new Node(32);
        graph.addNode(node);
        
        // no edge available
        
        int distance = dijkstra.findShortestDistance(graph, node, null);
        System.out.printf("Distance = %d\n", distance);
        Assert.assertEquals(distance, Node.INFINITY);
    }
    
    /**
     * network size: 1 node
     * link status : all connected
     * source to destination : Src N/A
     * expected shortest path : INFINITY
     */
    @Test (priority = 15)
    public void testOneNetworkSrcNull() 
    {
        Node node = new Node(6);
        graph.addNode(node);
        
        // no edge available
        
        int distance = dijkstra.findShortestDistance(graph, null, node);
        System.out.printf("Distance = %d\n", distance);
        Assert.assertEquals(distance, Node.INFINITY);
    }
    
    /**
     * network size: 0 nodes
     * link status : N/A
     * expected shortest path : INFINITY
     */
    @Test (priority = 10)
    public void testEmptyNetwork() 
    {
        int distance = dijkstra.findShortestDistance(graph, null, null);
        System.out.printf("Distance = %d\n", distance);
        Assert.assertEquals(distance, Node.INFINITY);
    }
}
