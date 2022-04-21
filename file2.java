package FileHandling;
import java.util.*;
import java.io.*;
public class file2
{
	public static void main(String[] args)throws IOException//(checked Exception handling)
	{
		/*String[] names = {"AAA","BBB","CCC"};
		//writing into a file
		FileWriter f = new FileWriter("demo1.txt");
		f.write("hmm");
		BufferedWriter br = new BufferedWriter(f);
		br.write("\n" + 77);
		br.write("\nhii...hello");
		
		for(String name : names)
		{
			br.write("\n" + name);
		}
		 br.close();
		 f.close();*/
		 
		 //reading from a file
		 FileReader fr = new FileReader("C:\\Users\\Jeeva S\\eclipse-workspace\\weekend1\\demo1.txt");
		 
		 // if we single slash(\) java take it as escape sequence so we given double slash(\\) in file name(directory)
		 //escape sequence - (\n,\t,\r,\b,\a,etc.,)
		 
		 BufferedReader brr = new BufferedReader(fr);
		 String line;
		 while((line = brr.readLine()) != null)
		 {
			 System.out.println(line);
		 }
		 brr.close();
		 fr.close();
		 
		
	}
}
