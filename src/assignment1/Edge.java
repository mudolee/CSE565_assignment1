package assignment1;

/**
 * Edge implementation contains two node reference and distance between them
 * @author Brad Lee
 *
 */
public class Edge
{
    private Node srcNode, dstNode;
    private int length;
    public Edge(Node src, Node dst, int length)
    {
        setSrcNode(src);
        setDstNode(dst);
        setLength(length);
    }

    public int getLength()
    {
        return length;
    }
    public void setLength(int length)
    {
        this.length = length;
    }
    public Node getSrcNode()
    {
        return srcNode;
    }
    public void setSrcNode(Node srcNode)
    {
        this.srcNode = srcNode;
    }
    public Node getDstNode()
    {
        return dstNode;
    }
    public void setDstNode(Node dstNode)
    {
        this.dstNode = dstNode;
    }
    
    /**
     * returns the other ending node in this edge from the given node perspective
     * @param node : node we are working on
     * @return opposite node from current working node
     */
    public Node getOtherEnd(Node node)
    {
        if (node == srcNode)
            return dstNode;
        else
            return srcNode;
    }
}

