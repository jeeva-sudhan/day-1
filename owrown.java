package daytwointern;
import java.util.*;
public class owrown<T> 
{
	private Object[] arr;
    static int default_size = 10;
    int size = 0;
    
    owrown()
    {
        this.arr = new Object[default_size];
    }
    
    public boolean full()
    {
        if(size == arr.length)
        return true;
        
        return false;
    }
    
    public void resize()
    {
        default_size = default_size + (default_size >> 1);
        Object[] temp = new Object[default_size];
        for(int i=0;i<arr.length;i++)
        {
            temp[i] = (T)arr[i];
        }
        arr = temp; 
    }
    
    public int size()
    {
        return size;
    }
    
    public void add(int x)
    {
       boolean b =  full();
       if(b)
       {
           resize();
       }
        arr[size++] = x;
        
    }
    
    public void set(int ind,T x)
    {
        arr[ind] = x;// object = T [smaller(T) to higher(object)] --> so no typecasting required
    } 
    
    public T get(int ind)
    {
        return (T)(arr[ind]);// return type must be same 
    }
    
    
    /*public void remove(int ind) // size problem
    {
        for(int i=0;i<arr.length;i++)
        {
            if(i == ind)
                continue;
            System.out.print(arr[i] + " ");
        }
    }*/ 
    
    public T remove()
    {
        T removed_element = (T)(arr[--size]);// T = object [higher(object) to smaller(T)] --> typecasting required
        default_size -= 1; 
        for(int i=0;i<default_size;i++)
        {
            if(i == size)
                continue;
            System.out.print(arr[i] + " ");
        }
        
        return removed_element;
    }
    
    @Override //checks applicable for override or not
    public String toString()
    {
        return "Main{ data=" + Arrays.toString(arr) + "element size: " + size + "totalsize: " + default_size +'}';
    }
    
	public static void main(String[] args) 
	{
		//System.out.println("Hello World");
		//owrown m = new owrown();
		/*m.add(2);
		m.add(3);
		m.add(5);
		m.add(4);
		m.set(1,20);*/
		
		/*for(int i=0;i<14;i++)
        {
            m.add(i);
        }
        
		System.out.println(m);
		System.out.println(m.size());
		System.out.println(m.get(3));
		System.out.println("removed element" + m.remove());*/
		
		owrown<Integer> m = new owrown();
	    //m.add("sss");//error
	    
	    /*m.add(45);
	    m.add(66);
	    m.add(73);
		
		System.out.println(m);*/
		
		for(int i=0;i<12;i++)
		{
		   m.add(i); 
		}
		
        System.out.println(m);
	}
}
