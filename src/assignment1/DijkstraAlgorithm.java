package assignment1;

public class DijkstraAlgorithm
{
    private BinHeap<Node> minQueue;
    
    public DijkstraAlgorithm()
    {
        minQueue = new BinHeap<Node>();
    }
    
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
            if (minNode.getDistance() == Node.INFINITY)
                return Node.INFINITY;
            
            if (minNode == dest)
                return minNode.getDistance();
            
            int edgeSize = minNode.getEdgeSize();
            for (int i = 0; i < edgeSize; i++)
            {
                int newDistance = minNode.getEdgeLength(i) + minNode.getDistance();
                Node neighbor = minNode.getEdgeOtherEnd(i);
                if (neighbor.getDistance() > newDistance)
                {
                    neighbor.setDistance(newDistance);
                    minQueue.decrease(neighbor.getHeapIndex(), neighbor);
                }
            }
        }
        return Node.INFINITY;
    }
}
