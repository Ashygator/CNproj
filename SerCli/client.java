import java.lang.*;
import java.io.*;
import java.net.*;

class client {
    public static void main(String args[]) {
	String outputLine;
	byte[] bytesIn = new byte[32];
	try {
	    Socket skt = new Socket("128.227.248.209", 1234); // lin116-15
	    BufferedReader in = new BufferedReader(new InputStreamReader(skt.getInputStream()));	    
	    InputStream inS = skt.getInputStream();	    	   
	    System.out.print("Ready to Receive\n");
	    inS.read(bytesIn, 0, 32);
	    String str = new String(bytesIn, "UTF-8");
	    System.out.print("Header :" + str);
	    while ((outputLine = in.readLine()) != null) {		
		System.out.println(outputLine); // Read one line and output it
                if (outputLine.equals("Bye."))
                    break;
            }
	    in.close();
	}
	catch(Exception e) {
	    System.out.print("Whoops! It didn't work!\n");
	}
    }
}