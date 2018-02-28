import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//		NAME : PRAGYA PRAKASH
//		ROLL NO. : 2016067


class Node1 {
	
	public Lab2b1 data;
	public Node1 link;
	
	public Node1(Lab2b1 value, Node1 ptr) {
		//a Node1 with given value and reference
		data=value;
		link=ptr; 
	}
	
	public Lab2b1 getData() {
		return this.data;
	}
	
	public Node1 getLink() {
		return this.link;
	}
	
	public void setLink(Node1 n) {
		this.link=n;
	}
	
	public void setData(Lab2b1 n) {
		this.data=n;
	}
}
	

class queue     {
    protected Node1 front ;
    protected Node1 rear;
    public int size ;
    
    public queue() {    
    	rear = null;  
    	front = null;     
    	size = 0;  
    }
    
    public boolean isEmpty() {   
    	return rear == null;       	
    }
    
    public int getSize() { 
    	return size;    
    }
    
    public void enqueue (Lab2b1 val)  {
        Node1 nptr = new Node1(val, null);
        if (rear == null)  
              front = nptr;
        else
              rear.setLink(nptr);
        rear = nptr;
        size++;
   }
    
    public Lab2b1 dequeue ()  {
        Lab2b1 d = front.getData();
        front = front.getLink();
        if (front == null)
              rear = null;
        size--;
        return d;
       
   }
    
    public Lab2b1 peek ()  {
        Lab2b1 d = front.getData();
        return d;             
   }


}

public class Lab2b1 {
	public int data;
	public int x;
	public int y;
	public int distance;
	
	public Lab2b1(int d,int x, int j) {
		this.data=d;
		this.x=x;
		this.y=y;
		this.distance = -1;
	}
	
	public void setDist(int d) {
		this.distance = d;
	}
	
	
	static Lab2b1 G[][];
	static int R;
	static int C;

	public static Lab2b1[] getNeighbours(Lab2b1[][] G, int i, int j) {
		int max1=R;
		int max2=C;
		if(i!=1 && i!=max1 && j!=1 && j!=max2)
		{
			Lab2b1[] n={G[i-1][j],G[i+1][j],G[i][j-1],G[i][j+1]};
			return n;
		}
		else if(i==1 && j==1) //the first corner
		{
			Lab2b1[] n={G[i][j+1],G[i+1][j]};
			return n;
		}
		else if(i==1 && j==max2)
		{
			Lab2b1[] n={G[i][j-1],G[i+1][j]};
			return n;
		}
		else if(i==max1 && j==1)
		{
			Lab2b1[] n={G[i][j+1],G[i-1][j]};
			return n;
		}
		else if(i==max1 && j==max2)
		{
			Lab2b1[] n={G[i][j-1],G[i-1][j]};
			return n;
		}
		else if(i==1 && j!=max2 && j!=1)
		{
			Lab2b1[] n={G[i][j-1],G[i][j+1],G[i+1][j]};
			return n;
		}
		else if(i==max1 && j!=max2 && j!=1)
		{
			Lab2b1[] n={G[i][j-1],G[i][j+1],G[i-1][j]};
			return n;
		}
		else if(j==1 && i!=max1 && i!=1)
		{
			Lab2b1[] n={G[i][j+1],G[i-1][j],G[i+1][j]};
			return n;
		}
		else //the condition for j=max and i neither 0 nor max
		{
			Lab2b1[] n={G[i-1][j],G[i+1][j],G[i][j-1]};
			return n;
		}
		
	}
	
	public static void DistanceToAllCells(Lab2b1[][] G,int i, int j) {
		queue q= new queue();
		Lab2b1 c0=G[i][j];
		c0.setDistance(0);
		q.enqueue(c0);
		while(!(q.isEmpty()))
		{
			Lab2b1 c=q.dequeue();
			Lab2b1[] N=getNeighbours(G,c.x,c.y);
			for (int a=0; a<N.length; a++) 
			{//for each neighbour of x, choose the one with the least weight
				Lab2b1 b=N[a];
				if(b.blocked!=0) //to check if block is not an obstacle
				{
					if(b.getDistance()==-1)
					{
						b.setDistance(c.getDistance()+1);
						q.enqueue(b);
					}
				}
			}
		}
		
	}

	
	public static void main(String[] args) throws IOException {
		Reader.init(System.in);
		int T=Reader.nextInt();
		while(T-- > 0)
		{
			int R=Reader.nextInt();
			int C=Reader.nextInt();
			
			G=new Lab2b[R+1][C+1];
			
			for(int i=1; i<=R; i++)
			{
				for(int j=1; j<=C; j++)
				{
					 
					int d=Reader.nextInt();
					G[i][j] = new Lab2b(d, i, j);
				}
			}
		}
	}
	
}

/** Class for buffered reading int and double values */
class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /** call this method to initialize reader for InputStream */
    static void init(InputStream input) {
        reader = new BufferedReader(
                     new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    /** get next word */
    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                   reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }
    
    static long nextLong() throws IOException {
        return Long.parseLong( next() );
    }
	
    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
}

