package daytwointern;
import java.util.*;
public class generics 
{
		public static void main(String[] args) 
		{
		     ArrayList<Integer> l = new ArrayList();//generics
		     l.add(10);
		     l.add(20);
		     l.add(25);
		     l.add(1,25);//duplicates allowed(inserting)
		     
		     /*Iterator itr = l.iterator();
		     while(itr.hasNext())
		     {
		         System.out.println(itr.next());
		     }*/
		     
		     for(int i : l)
		     {
		         System.out.println(i);
		     }
			//System.out.println("Hello World");
		}
}
