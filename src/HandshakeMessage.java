
import java.io.*;


public class HandshakeMessage implements RequiredConstants {

	private byte[] header = new byte[HANDSHAKE_HEADER];
	private byte[] peerID = new byte[HANDSHAKE_PEERID];
	private byte[] zeroBits = new byte[HANDSHAKE_ZEROBITS];
	private String messageHeader;
	private String messagePeerID;
	
	public HandshakeMessage() {}
	
	public HandshakeMessage(String Header, String PeerID) {
		try {
			this.messageHeader = Header;
			this.header = Header.getBytes(MSG_CHARSET_NAME);
			if (this.header.length > HANDSHAKE_HEADER)
				throw new Exception("Header is too large.");

			this.messagePeerID = PeerID;
			this.peerID = PeerID.getBytes(MSG_CHARSET_NAME);
			if (this.peerID.length > HANDSHAKE_HEADER)
				throw new Exception("Peer ID is too large.");

			this.zeroBits = "0000000000".getBytes(MSG_CHARSET_NAME);
		} catch (Exception e) {
			System.out.print(e);
		}
	}
	
	public byte[] getHeader() {
		return this.header;
	}
	
	public void setHeader() {
		
		
	}
	
	public byte[] getPeerID() {
		return this.peerID;
	}
	
	public void setPeerID() {
		
	}
	
	public byte[] getZeroBits() {
		return this.zeroBits;
	}
	
	public void setZeroBits() {
		
	}
	
	
}
