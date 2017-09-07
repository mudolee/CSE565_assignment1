package assignment1;

import java.util.ArrayList;

/**
 * Dijkstra algorithm core procedure, it expect the network as
 *   - single line at most between two nodes
 *   - undirected path between nodes
 *   - finding shortest path between two nodes given
 * @author Brad Lee
 *
 */
public class DijkstraAlgorithm
{
    private BinHeap<Node> minQueue;
    
    public DijkstraAlgorithm()
    {
        minQueue = new BinHeap<Node>();
    }
    
    /**
     * main method for find and return the distance of shorted path
     * @param graph : network object
     * @param source : source node to start from
     * @param dest : destination node to reach
     * @return shortest distance from source to destination
     */
    public int findShortestDistance(Graph graph, Node source, Node dest)
    {
        for (Node node : graph.getNodes())
        {
            if (node == source)
                node.setDistance(0);
            else
                node.setDistance(Node.INFINITY);
            minQueue.insert(node);
        }
        
        while (!minQueue.empty())
        {
            Node minNode = minQueue.remove();
            // no further connection to remaining nodes
            if (minNode.getDistance() == Node.INFINITY)
                return Node.INFINITY;
            // found destination node return the path length
            if (minNode == dest)
                return minNode.getDistance();
            
            // loop through neighboring edges and update path distances to each node
            // same edge will be traced back but it's always not selected since
            // the path length is always longer than previously set value
            for (Edge edge : minNode.getEdges())
            {
                int newDistance = edge.getLength() + minNode.getDistance();
                Node neighbor = edge.getOtherEnd(minNode);
                if (neighbor.getDistance() > newDistance)
                {
                    neighbor.setDistance(newDistance);
                    neighbor.setPrevNode(minNode);
                    minQueue.decrease(neighbor.getHeapIndex(), neighbor);
                }
            }
        }
        // we couldn't find the path to reach the destination
        return Node.INFINITY;
    }
}
