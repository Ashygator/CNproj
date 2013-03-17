package src;
import src.RequiredConstants;


import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 
 * @author Ashish,Nitin
 * This class makes use of File Joiner &
 * File Splitter which  is used by the P2P
 * processes 
 */

public class FileOps {

	/**
	 * Function to Split the file
	 * @returns Array of bytes to fill up the 
	 * correponding bit of the file
	 */
	public byte[] FileSplitter(FileInputStream Shared_file) {

		
		String padding;
		byte buffer[] = new byte[SPLIT_SIZE];

		int count = 10000;
		while (true) {
			int i = fis.read(buffer, 0, SPLIT_SIZE);
			if (i == -1)
				break;

			String filename = "temp/" + args[1] + count;
			FileOutputStream fos = new FileOutputStream(filename);
			fos.write(buffer, 0, i);
			fos.flush();
			fos.close();

			++count;
		}

		public void FileJoiner(byte[]) {


		}
