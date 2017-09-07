package assignment1;

/**
 * For optimzing purpose, enabling trace back from the Node to binary heap item directly
 * @author Brad Lee
 *
 */
public interface BinHeapInfo
{
    public void setHeapIndex(int index);
    public int getHeapIndex();
}
