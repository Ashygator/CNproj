import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.util.Arrays;


public class FileJoiner {

  public static void main(String args[]) throws Exception {
    int SPLIT_SIZE = 16384;
    File file = new File(args[0]);
    File[] files = file.listFiles();
    Arrays.sort(files);
    
    FileOutputStream fout = new FileOutputStream("chembai_joined.mp3");  
    FileInputStream segment;
    int length;
    for (int i = 0; i < files.length; i++) {

      System.out.println("files[i].getPath() is " + files[i].getPath());
      
       segment = new FileInputStream(files[i].getPath());
      
       byte[] buff = new byte[SPLIT_SIZE];  
            while ( (length = segment.read(buff)) > 0){  
                fout.write(buff, 0, length);  
            }  

      segment.close();
    }
    fout.close();
  }
}