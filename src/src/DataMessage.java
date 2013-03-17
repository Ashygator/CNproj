package src;

/**
 * 
 * @author Ashish, Nitin
 * DataMessage Class to make Data Messages to the 
 * Peer processes.
 */

public class DataMessage implements RequiredConstants {
	
	private byte[] len = null;
	private byte[] type = null;
	private byte[] payload = null;
	private String messageLength;
	private int messageType;
	private int dataLength = DATA_MESSAGE_TYPE;

	public DataMessage() {}
	
/**
 *  DataMessage Constructor class
 * @param Msg_type
 * @param Payload
 * @throws Exception 
 */
	
	public DataMessage(int Type, byte[] Payload) throws Exception {
		
		try 
		{
			if (Payload == null) // Pay load is null
			{
				if (Type == MSG_TYPE_CHOKE || Type == MSG_TYPE_UNCHOKE
						|| Type == MSG_TYPE_INTERESTED
						|| Type == MSG_TYPE_NOT_INTERESTED) 
				{
					this.setMsgLen(1);
					this.payload = null;
				} 
				else 
					throw new Exception("DataMessage:: Constructor - Pay load should not be null");

			} 
			else // Pay load has some value
			{
				this.setMsgLen(Payload.length + 1);
				if (this.len.length > DATA_MESSAGE_LENGTH)
					throw new Exception("DataMessage:: Constructor - message length is too large.");
				
				this.setMsgPayload(Payload);
			}

			this.setMsgType(Type);
			if (this.getMsgType().length > DATA_MESSAGE_TYPE)
				throw new Exception("DataMessage:: Constructor - Type length is too large.");

		} catch (Exception e) {
			throw new Exception("DataMessage: Not Initialized");
		}
	}
	
	/**
	 * Class constructor overriding 
	 * 
	 * @param Type
	 *            
	 */
	public DataMessage(int Type) {

		try {

			if (Type == MSG_TYPE_CHOKE || Type == MSG_TYPE_UNCHOKE
					|| Type == MSG_TYPE_INTERESTED
					|| Type == MSG_TYPE_NOT_INTERESTED) 
			{
				this.setMsgLen(1);
				this.payload = null;
				this.setMsgLen(1);
				this.setMsgType(Type) ;
			
			} 
			else 
				throw new Exception("DataMessage:: Constructor - Wrong constructor selection.");
		} catch (Exception e) {
		}

	}
	
	/**
	 * Function to set the messageLength, len
	 * @param MsgLen
	 */
	public void setMsgLen(byte[] MsgLen) {
		
		Integer temp = SideStuff.byteArrayToInt(MsgLen,0);
		this.messageLength = temp.toString();
		this.len = MsgLen;
		this.dataLength = temp;  
		
	}
	
	/**
	 * Overriding setMsgLen() to set it with integer value
	 * @param messageLength
	 */
	
	public void setMsgLen(int messageLength) {
		this.dataLength = messageLength;
		this.messageLength = ((Integer)messageLength).toString();
		this.len = SideStuff.intToByteArray(messageLength);
	}
	
	/**
	 * getter function to return length
	 * @return
	 */
	public byte[] getMsgLen() {
		return this.len;
	}
	
	/**
	 * Function to set type of message
	 * @param MsgTypen
	 */
	public void setMsgType(byte[] MsgType) {
		this.type = MsgType;
	}
	
	/**
	 * Overriding setMsgType to set the 
	 * messageType
	 * @param MsgType
	 */
	public void setMsgType(int MsgType) {
		this.messageType = MsgType;
	}
	
	/**
	 * getter function for type
	 * @return
	 */
	public byte[] getMsgType() {
		return this.type;
	}
	
	/**
	 * Setting the payload data
	 * @param Payload
	 */
	public void setMsgPayload(byte[] Payload) {
		this.payload = Payload;
	}
	
	/**
	 * Getter for the data payload
	 * @return
	 */
	public byte[] getMsgPayload() {
		return this.payload;
	}
	
	/**
	 * Sets the message type
	 * @return
	 */
	public int getMsgTypeInt() {
		return messageType;
	}
	
	/**
	 * This function encodes the object DataMessage to a byte array for
	 * transmission
	 * 
	 * @param m - DataMessage object to be converted into byte array
	 */

	public static byte[] encodeMessage(DataMessage msg) 
	{
		byte[] msgStream = null;
		int msgType;

		try 
		{
			// Encode message type, length, pay load field
			msgType = msg.getMsgTypeInt();

			// Encode message length field
			if (msg.getMsgLen() == null)
				throw new Exception("Null Msg length");
			else if (msg.getMsgLen().length > DATA_MESSAGE_LENGTH)
				throw new Exception("Message length > 4");
			else if (msg.getMsgType() == null)
				throw new Exception("Invalid message type.");
			else if (msgType < 0 || msgType > 7)
				throw new Exception("Invalid message type.");

			if (msg.getMsgPayload() != null) {
				msgStream = new byte[DATA_MESSAGE_LENGTH + DATA_MESSAGE_TYPE + msg.getMsgPayload().length];

				System.arraycopy(msg.getMsgLen(), 0, msgStream, 0, msg.getMsgLen().length);
				System.arraycopy(msg.getMsgType(), 0, msgStream, DATA_MESSAGE_LENGTH, DATA_MESSAGE_TYPE);
				System.arraycopy(msg.getMsgPayload(), 0, msgStream, DATA_MESSAGE_LENGTH + DATA_MESSAGE_TYPE, msg.getMsgPayload().length);
			} else {
				msgStream = new byte[DATA_MESSAGE_LENGTH + DATA_MESSAGE_TYPE];

				System.arraycopy(msg.getMsgLen(), 0, msgStream, 0, msg.getMsgLen().length);
				System.arraycopy(msg.getMsgType(), 0, msgStream, DATA_MESSAGE_LENGTH, DATA_MESSAGE_TYPE);

			}

		} 
		catch (Exception e) 
		{
			// Log here
			msgStream = null;
		}

		return msgStream;
	}
	
	/**
	 * This function decodes the byte array and loads to the object DataMessage
	 * 
	 * @param PeerId - Peer ID of the receiving or sending messages
	 * @param Message - Message in byte array format
	 */
	public static DataMessage decodeMessage(byte[] Message) {

		// VAR initialization
		DataMessage msg = new DataMessage();
		byte[] msgLength = new byte[DATA_MESSAGE_LENGTH];
		byte[] msgType = new byte[DATA_MESSAGE_TYPE];
		byte[] payLoad = null;
		int len;

		try 
		{
			// Initial check
			if (Message == null)
				throw new Exception("Invalid data.");
			else if (Message.length < DATA_MESSAGE_LENGTH + DATA_MESSAGE_TYPE)
				throw new Exception("Byte array length is too small...");

			// 1. Decode the received message
			System.arraycopy(Message, 0, msgLength, 0, DATA_MESSAGE_LENGTH);
			System.arraycopy(Message, DATA_MESSAGE_LENGTH, msgType, 0, DATA_MESSAGE_TYPE);

				
			msg.setMsgLen(msgLength);
			msg.setMsgType(msgType);
			len = SideStuff.byteArrayToInt(msgLength,0);
			
			if (len > 1) 
			{
				payLoad = new byte[len-1];
				System.arraycopy(Message, DATA_MESSAGE_LENGTH + DATA_MESSAGE_TYPE,	payLoad, 0, Message.length - DATA_MESSAGE_LENGTH - DATA_MESSAGE_TYPE);
				msg.setMsgPayload(payLoad);
			}
			
			payLoad = null;
		} 
		catch (Exception e) 
		{
			//Log here
			msg = null;
		}
		return msg;
	}
	
}
