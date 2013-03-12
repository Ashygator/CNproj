
import java.
public class DataMessage implements RequiredConstants {
	
	private byte[] len = null;
	private byte[] type = null;
	private byte[] payload = null;
	private String messageLength;
	private String messageType;
	private int dataLength = DATA_MESSAGE_TYPE;

	public DataMessage() {}
	
	public DataMessage(String Msg_type, byte[] Payload) {
		// call get set 
		
	}
	
	public void setMsgLen(byte[] MsgLen) {
		this.len = MsgLen;
	}
	public byte[] getMsgLen() {
		return this.len;
	}
	
	public void setMsgType(byte[] MsgType) {
		this.type = MsgType;
	}
	public byte[] getMsgType() {
		return 
	}
	
	public void setMsgPayload() {
		
	}
	public byte[] getMsgPayload() {
		
	}
	
	
	
	
}
