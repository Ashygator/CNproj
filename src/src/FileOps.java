package src;
import src.RequiredConstants;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 
 * @author Ashish,Nitin
 * This class makes use of File Joiner &
 * File Splitter which  is used by the P2P
 * processes 
 */

public class FileOps implements RequiredConstants{

	/**
	 * Function to Split the file
	 * @returns Array of bytes to fill up the 
	 * corresponding bit of the file
	 */
	public byte[] FileSplitter(FileInputStream Shared_file) {

		
		String padding;
		byte buffer[] = new byte[SPLIT_SIZE];

		int count = 10000;
	
			
			int i;
			try {
				i = Shared_file.read(buffer, 0, SPLIT_SIZE);
		
		

			String filename = "temp/" +  + count;
			FileOutputStream fos;
			fos = new FileOutputStream(filename);
			fos.write(buffer, 0, i);
			fos.flush();
			fos.close();

			
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			++count;
		
		
		return buffer;
	}
		public void FileJoiner(byte[] FileBytes) {

		
		}
}