
public class SideStuff {
	
	public static int numOfPreferredNeighbr;
	public static int unchokingInterval;
	public static int optUnchokingInterval;
	public static String fileName;
	public static int filesize;
	public static int pieceSize;
	
	
	public static byte[] intToByteArray(int value) 
	{
        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) 
        {
            int offset = (b.length - 1 - i) * 8;
            b[i] = (byte) ((value >>> offset) & 0xFF);
        }
        return b;
    }
	
	  public static int byteArrayToInt(byte[] b, int offset) 
	    {
	        int value = 0;
	        for (int i = 0; i < 4; i++) 
	        {
	            int shift = (4 - 1 - i) * 8;
	            value += (b[i + offset] & 0x000000FF) << shift;
	        }
	        return value;
	    }
	
}
