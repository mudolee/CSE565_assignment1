package assignment1;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.Reporter;
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
    
    private void printPath(Node dest)
    {
    	String path = "";
    	while (dest != null)
    	{
    		path = dest.toString() + path;
    		dest = dest.getPrevNode();
    	}
    	System.out.println(path);
    }
    
    /**
     * network size: many nodes
     * link status : connected
     * source to destination : connected
     * expected shortest path : 161
     */
    @Test (priority = 70, description="33 nodes, connected, expects 161")
    public void testCrowdedNetwork() 
    {
        addPath(32, 33, 54);
        addPath(33, 24, 41);
        addPath(24, 26, 65);
        addPath(25, 23, 12);
        addPath(24, 25, 16);
        addPath(33, 25, 13);
        addPath(31, 32, 12);
        addPath(29, 33, 14);
        addPath(28, 25, 47);
        addPath(27, 26, 29);
        addPath(21, 23, 11);
        addPath(22, 23, 17);
        addPath(31, 30, 65);
        addPath(29, 30, 77);
        addPath(29, 28, 15);
        addPath(28, 27, 16);
        addPath(27, 21, 95);
        addPath(22, 9, 70);
        addPath(10, 22, 9);
        addPath(22, 11, 19);
        addPath(11, 21, 10);
        addPath(27, 20, 17);
        addPath(19, 20, 18);
        addPath(18, 19, 24);
        addPath(18, 28, 47);
        addPath(17, 31, 31);
        addPath(13, 30, 94);
        addPath(13, 12, 8);
        addPath(12, 11, 9);
        addPath(11, 10, 6);
        addPath(9, 8, 31);
        addPath(10, 8, 24);
        addPath(7, 8, 6);
        addPath(11, 7, 25);
        addPath(6, 7, 24);
        addPath(12, 6, 56);
        addPath(14, 13, 7);
        addPath(16, 17, 7);
        addPath(16, 14, 16);
        addPath(15, 16, 14);
        addPath(15, 4, 3);
        addPath(4, 5, 4);
        addPath(5, 14, 6);
        addPath(6, 5, 20);
        addPath(6, 1, 31);
        addPath(1, 5, 5);
        addPath(2, 1, 12);
        addPath(2, 5, 4);
        addPath(4, 3, 9);
        addPath(3, 2, 7);
        
        int distance = dijkstra.findShortestDistance(graph, nodeMap.get(1), nodeMap.get(19));
        System.out.printf("Distance = %d\n", distance);
        printPath(nodeMap.get(19));
        Assert.assertEquals(distance, 161);
    }
    
    /**
     * network size: 14 nodes
     * link status : connected
     * source to destination : connected
     * expected shortest path : 91
     */
    @Test (priority = 60, description="14 nodes, connected, expects 91")
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
        printPath(nodeMap.get(4));
        Assert.assertEquals(distance, 91);
    }
    
    /**
     * network size: 7 nodes
     * link status : disconnected
     * source to destination : disconnected
     * expected shortest path : INFINITY
     */
    @Test (priority = 50, description="7 nodes, disconnected, expects infinity")
    public void testSmallDiscNetwork() 
    {
        addPath(11, 3, 88);
        addPath(3, 9, 21);
        addPath(10, 4, 29);
        addPath(4, 1, 6);
        addPath(5, 1, 55);
        
        int distance = dijkstra.findShortestDistance(graph, nodeMap.get(11), nodeMap.get(4));
        System.out.printf("Distance = %d\n", distance);
        printPath(nodeMap.get(4));
        Assert.assertEquals(distance, Node.INFINITY);
    }
    
    /**
     * network size: 5 nodes
     * link status : all connected
     * source to destination : connected
     * expected shortest path : 87
     */
    @Test (priority = 40, description="5 nodes, connected, expects 87")
    public void testSmallNetwork() 
    {
        addPath(1, 2, 10);
        addPath(3, 5, 12);
        addPath(4, 2, 77);
        addPath(2, 3, 2);
        
        int distance = dijkstra.findShortestDistance(graph, nodeMap.get(1), nodeMap.get(4));
        System.out.printf("Distance = %d\n", distance);
        printPath(nodeMap.get(4));
        Assert.assertEquals(distance, 87);
    }
    
    /**
     * network size: 2 node
     * link status : all connected
     * source to destination : connected
     * expected shortest path : 33
     */
    @Test (priority = 30, description="2 nodes, connected, expects 33")
    public void testTwoNetwork() 
    {
        addPath(7, 2, 33);
        
        int distance = dijkstra.findShortestDistance(graph, nodeMap.get(2), nodeMap.get(7));
        System.out.printf("Distance = %d\n", distance);
        printPath(nodeMap.get(7));
        Assert.assertEquals(distance, 33);
    }
    
    /**
     * network size: 1 node
     * link status : all connected
     * source to destination : same node
     * expected shortest path : 0
     */
    @Test (priority = 20, description="1 nodes, source = destination, expects 0")
    public void testOneNetwork() 
    {
        Node node = new Node(14);
        graph.addNode(node);
        
        // no edge available
        
        int distance = dijkstra.findShortestDistance(graph, node, node);
        System.out.printf("Distance = %d\n", distance);
        printPath(node);
        Assert.assertEquals(distance, 0);
    }
    
    /**
     * network size: 1 node
     * link status : all connected
     * source to destination : destination N/A
     * expected shortest path : INFINITY
     */
    @Test (priority = 16, description="1 nodes, destination = null, expects infinity")
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
    @Test (priority = 15, description="1 nodes, source = null, expects infinity")
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
    @Test (priority = 10, description="0 nodes, expects infinity")
    public void testEmptyNetwork() 
    {
        int distance = dijkstra.findShortestDistance(graph, null, null);
        System.out.printf("Distance = %d\n", distance);
        Assert.assertEquals(distance, Node.INFINITY);
    }
}
