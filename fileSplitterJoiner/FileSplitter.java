import java.io.FileInputStream;
import java.io.FileOutputStream;

/* Takes two parameters as input : First the path to the file being split
   Second : The name that should be given to the split files
   Alwats create the splits in ./temp directory
*/

class FileSplitter {

  public static void main(String args[]) throws Exception {
    int SPLIT_SIZE = 16384;
    FileInputStream fis = new FileInputStream(args[0]);
    String padding;
    byte buffer[] = new byte[SPLIT_SIZE];

    int count = 10000;
    while (true) {
      int i = fis.read(buffer, 0, SPLIT_SIZE);
      if (i == -1)
        break;

      String filename = "temp/" + args[1] +  count;
      FileOutputStream fos = new FileOutputStream(filename);
      fos.write(buffer, 0, i);
      fos.flush();
      fos.close();

      ++count;
    }
  }
}

   