import java.lang.*;
import java.io.*;
import java.net.*;

class server {
    public static void main(String args[]) {
	String data = "I'm ready to send!";
	String inputLine;
	InputStreamReader converter = new InputStreamReader(System.in);
	BufferedReader in = new BufferedReader(converter);
	String str = "CEN2008TYHGBGHJKIU00000000001234";
	byte[] h1 = str.getBytes();

	
	try {
	    ServerSocket srvr = new ServerSocket(1234);
	    Socket skt = srvr.accept();
	    
	    System.out.print("Server has connected!\n");
	    PrintWriter out = new PrintWriter(skt.getOutputStream(), true);
	    System.out.print(data + "\n");
	    //System.out.print(h1);
	    
	    OutputStream outStrm = skt.getOutputStream(); 
	    DataOutputStream dos = new DataOutputStream(outStrm);
	    
	    
		dos.write(h1, 0, 32);
	    
	    while ((inputLine = in.readLine()) != null) {   
		out.println(inputLine);
		
		if (inputLine.equals("Bye."))
		    break;
	    }

	    out.close();
	    skt.close();
	    srvr.close();
	}
	catch(Exception e) {
	    System.out.print("Whoops! It didn't work!\n");
	}
    }
}