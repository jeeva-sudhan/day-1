package FileHandling;
import java.util.*;
import java.io.*;
public class file3 
{
	public static void main(String[] args)throws IOException
	{
		/*FOLDER CREATION:
		File f = new File("C:\\Users\\Jeeva S\\eclipse-workspace\\weekend1\\test");
		boolean present = f.exists();
		System.out.println(present);
		f.mkdir();// to create folder
		present = f.exists();
		System.out.println(present);*/
		
		/*SUBFOLDER CREATION:
		File f = new File("C:\\Users\\Jeeva S\\eclipse-workspace\\weekend1\\test\\raja\\rani");
		boolean present = f.exists();
		System.out.println(present1);
		f.mkdirs();// to create subfolders
		present1 = f.exists();
		System.out.println(present);*/
		
		/*FILE CREATION:
		File f = new File("C:\\Users\\Jeeva S\\eclipse-workspace\\weekend1\\test\\tilt.txt");
		boolean present = f.exists();
		System.out.println(present);
		boolean created = f.createNewFile();//required to handle IOException
		
		//createNewFile -> method create the file and if it able to create the file it return true.
		System.out.println(created);*/
		
		/*FILE DELETION:
		File f = new  File("C:\\Users\\Jeeva S\\eclipse-workspace\\weekend1\\test\\tilt.txt");
		f.delete();//to delete the file
		boolean present = f.exists();
		System.out.println(present);*/
		
		/*RENAMING FILE:
		File f = new File("C:\\Users\\Jeeva S\\eclipse-workspace\\weekend1\\test\\tilt.txt");
		f.createNewFile();
		File rename = new File("C:\\Users\\Jeeva S\\eclipse-workspace\\weekend1\\test\\temp.txt");
		boolean renameStatus = f.renameTo(rename);//to rename file
		System.out.println(renameStatus);
		
		//PRINTING NAME OF THE FILE:
		System.out.println(f.getName());
		System.out.println(rename.getName());
		
		//PROPERTIES OF FILE:
		System.out.println(rename.canExecute());
		System.out.println(rename.canRead());
		System.out.println(rename.canWrite());*/
		
		/*PRINTING ALL FILES AND FOLDERS PRESENT IN DIRECTORY:
		File f = new File("C:\\Users\\Jeeva S\\eclipse-workspace\\weekend1");
		String[] allfilesfolderslist = f.list();// f.list() gives us all the files & folders present in weekend1
		for(String allfilesfolders : allfilesfolderslist)
		{
			System.out.println(allfiles);
		}*/
		
		/*PRINTING ONLY FILES & ONLY FOLDERS:
		File f = new File("C:\\Users\\Jeeva S\\eclipse-workspace\\weekend1");
		File[] allfilesfolderslist = f.listFiles();//***listFiles()***
		for(File allfilesfolders : allfilesfolderslist)
		{
			if(allfilesfolders.isFile())//to print only files
				System.out.println(allfilesfolders);
			
			if(allfilesfolders.isDirectory())//to print only folders
				System.out.println(allfilesfolders);
		}*/
		
		/*LISTING ONLY .txt FILES
		File f = new File("C:\\Users\\Jeeva S\\eclipse-workspace\\weekend1");
		File[] allfilesfolderslist = f.listFiles();//***listFiles()***
		for(File allfilesfolders : allfilesfolderslist)
		{
			if(allfilesfolders.isFile())//to print only files
			{
				String fileName = allfilesfolders.getName();
				int dotindex = fileName.lastIndexOf(".");
				String extension = fileName.substring(dotindex+1);
				
				//if(extension.equals("txt"))//only .txt file get printed
					//System.out.println(allfilesfolders);
				
				//if(extension.equals("html"))//only .html file get printed
					//System.out.println(allfilesfolders);
				
				//length() -> give the file size in bytes
				//1kilobyte = 1000byte.
				
				if(allfilesfolders.length() < 1000)//printing files based on its size
					System.out.println(fileName + "size in bytes: " + allfilesfolders.length());
			}
		}*/
		
		/*DELETING PARTICULAR EXTENSION FILE IN DIRECTORY(FOLDER):
		File f = new File("C:\\Users\\Jeeva S\\eclipse-workspace\\weekend1\\test");
		File[] allfilesfolderslist = f.listFiles();//***listFiles()***
		for(File allfilesfolders : allfilesfolderslist)
		{
			if(allfilesfolders.isFile())//to print only files
			{
				String fileName = allfilesfolders.getName();
				int dotindex = fileName.lastIndexOf(".");
				String extension = fileName.substring(dotindex+1);
				
				if(extension.equals("txt"))//only .txt file allowed
					allfilesfolders.delete();//.txt files in test folder get deleted [eg:here temp.txt file get deleted]
					
			}
		}*/
		
		/*FILEWRITING:
		File f = new File("C:\\Users\\Jeeva S\\eclipse-workspace\\weekend1\\test\\writing.txt");
		f.createNewFile();
		FileWriter writer = new FileWriter(f); // OR FileWriter writer = new FileWriter("C:\\Users\\Jeeva S\\eclipse-workspace\\weekend1\\test\\writing.txt");
		//writer.write(65);// stored as A in file(ascii value) so we stored in string format in below write
		writer.write("65");
		writer.write("\nhey,hii..");
		//FileWriter writer = new FileWriter(f,true); // this "true" will say to append the previous data stored in file.
		writer.flush();//to push all data's into file that we written using write.
		writer.close();*/
		
		/*FILEREADING:
		FileReader reader = new FileReader(f);
		int output = reader.read();
		//read() will return integer(ascii value of given character) it will return -1 after it readen the last value.
		while(output != -1)
		{
			//System.out.println(output);// it will return ascii value of character in file so we do type casting
			System.out.print((char)output);//print not println -> to print output in single line.
			output = reader.read();//to read next character
		}*/
		
		/*count of characters in file:
		//filereader reads character by character
		FileReader reader = new FileReader(f);
		char[] ch = new char[(int)f.length()];//length() return size in long so we need to do typecasting to int because long not allowed
		System.out.println("character count: " + ch.length);
		//System.out.println("character count: " + ch.length + (int)f.length());//ch.length & (int)f.length() both are same
		reader.read(ch);//we must read the character to print the character because after reading the characters only we can know how many characters in it.
		for(char ch1 : ch)
		{
			System.out.print(ch1);
		}*/
		
		//BUFFERED READER & BUFFERED WRITER:
		File f = new File("C:\\Users\\Jeeva S\\eclipse-workspace\\weekend1\\test\\writing.txt");
		//f.createNewFile();
				
		//buffered writer constructor looks for writer object so we want to give writer object or child of writer object so we giving child class filewriter object inside constructor of bufferedwriter.
		//buffered reader constructor looks for reader object so we want to give reader object or child of reader object so we giving child class filereader object inside constructor of bufferedreader.
		//filereader reads character by character
		//bufferedreader reads line by line
		//readLine() return string
				
		FileWriter fwriter = new FileWriter(f,true);
		BufferedWriter bwriter = new BufferedWriter(fwriter);
	    //bwriter.write(67);//stores as ascii value in  file.
	    /*bwriter.newLine();
	    bwriter.write("TAMIL");
	    bwriter.newLine();
	    bwriter.write("ENGLISH");
	    bwriter.flush();
	    bwriter.close();*/
			    
	    FileReader freader = new FileReader(f);
		BufferedReader breader = new BufferedReader(freader);
		int linecount = 0;
		int sentencecount = 0;
		int wordcount = 0;
		int charactercount = 0;
		String line;
		//split("[]") returns string array
		while((line = breader.readLine()) != null)
		{
			String[] sentence = line.split("[.]");
			//split() return string array
			sentencecount += sentence.length;
			String[] words = line.split(" ");
			wordcount += words.length;
			charactercount += line.length();//breader.readLine() return each line in file  which means string so we used length().
			//System.out.println("number of sentence: " + sentencecount);
			linecount++;
			System.out.println(line);
			}
			    
			/*OR
		    String line = breader.newLine();
			while(line != null)
			{
			    linecount++;
			    System.out.println(line);
			     line = breader.readerLine();
			}*/
			    
			System.out.println("number of lines: " + linecount);
			System.out.println("number of sentence: " + sentencecount);
			System.out.println("number of words: " + wordcount);
			System.out.println("number of characters: " + charactercount);
		
	}
}
