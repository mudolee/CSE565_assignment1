package assignment1;

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
    public Node getOtherEnd(Node node)
    {
        if (node == srcNode)
            return dstNode;
        else
            return srcNode;
    }
}

