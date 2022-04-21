package FileHandling;
import java.util.*;
import java.io.*;
public class files 
{
	public static void main(String[] args)throws IOException
	{
		//Scanner sc = new Scanner(System.in);
		
		//BUFFEREDREADER:
		/*InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		int n = Integer.parseInt(br.readLine());
		//parseInt is static method so we using wrapper class name Integer to access it.
		//br.readLine(); throws IOException.
		System.out.println(n);*/
		
		File f = new File("demo.txt");
		//File f = new File("tilt.txt");
		//boolean present = f.exists();
		//System.out.println(present);//false - file not get created
		FileOutputStream fout = new FileOutputStream(f);//file get created
		//present = f.exists();
		//System.out.println(present);// true - because already file get created
		DataOutputStream dout = new DataOutputStream(fout);
		dout.writeUTF("hii...");
		
		FileInputStream fin = new FileInputStream(f);
		DataInputStream din = new DataInputStream(fin);
		String str = din.readUTF();
		
		System.out.println(str);
		
	}
}
