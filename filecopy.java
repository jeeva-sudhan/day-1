package FileHandling;
import java.util.*;
import java.io.*;

//for copying image file(read- inputstream-fileinputstream) (write- outputstream-fileoutputstream)
public class filecopy 
{
	public static void main(String[] args)throws Exception
	{
		InputStream imageread = new FileInputStream("d:\\Downloads\\67512 - Jeeva.jpg");
		OutputStream imagewrite = new FileOutputStream("d:\\Downloads\\67512 - photo.jpg");
	    int content;
		while((content = imageread.read()) != -1)
	    {
	    	imagewrite.write(content);
	    }
		imagewrite.flush();
		
	}
}
