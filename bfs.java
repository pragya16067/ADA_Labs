// NAME : PRAGYA PRAKASH
// ROLL NO. : 2016067
// SECTION_GROUP : A-3
// Lab 7(Saturday group)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class Node1 {
	
	public bfs data;
	public Node1 link;
	
	public Node1(bfs value, Node1 ptr) {
		//a Node1 with given value and reference
		data=value;
		link=ptr; 
	}
	
	public bfs getData() {
		return this.data;
	}
	
	public Node1 getLink() {
		return this.link;
	}
	
	public void setLink(Node1 n) {
		this.link=n;
	}
	
	public void setData(bfs n) {
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
    
    public void enqueue (bfs val)  {
        Node1 nptr = new Node1(val, null);
        if (rear == null)  
              front = nptr;
        else
              rear.setLink(nptr);
        rear = nptr;
        size++;
   }
    
    public bfs dequeue ()  {
        bfs d = front.getData();
        front = front.getLink();
        if (front == null)
              rear = null;
        size--;
        return d;
       
   }
    
    public bfs peek ()  {
        bfs d = front.getData();
        return d;             
   }


}

public class bfs {
	private int distance;
	private int blocked;
	private int k;
	private int l;
	
	public bfs(int value, int x, int y) {
		k=x;
		l=y;
		blocked=value;
		distance=-1;
	}
	
	public int getDistance() {
		return this.distance;
	}
	
	public void setDistance(int d) {
		this.distance=d;
	}

	
	public static bfs[] getNeighbours(bfs[][] G, int i, int j) {
		int max=G.length-1;
		if(i!=0 && i!=max && j!=0 && j!=max)
		{
			bfs[] n={G[i-1][j],G[i+1][j],G[i][j-1],G[i][j+1]};
			return n;
		}
		else if(i==0 && j==0)
		{
			bfs[] n={G[i][j+1],G[i+1][j]};
			return n;
		}
		else if(i==0 && j==max)
		{
			bfs[] n={G[i][j-1],G[i+1][j]};
			return n;
		}
		else if(i==max && j==0)
		{
			bfs[] n={G[i][j+1],G[i-1][j]};
			return n;
		}
		else if(i==max && j==max)
		{
			bfs[] n={G[i][j-1],G[i-1][j]};
			return n;
		}
		else if(i==0 && j!=max && j!=0)
		{
			bfs[] n={G[i][j-1],G[i][j+1],G[i+1][j]};
			return n;
		}
		else if(i==max && j!=max && j!=0)
		{
			bfs[] n={G[i][j-1],G[i][j+1],G[i-1][j]};
			return n;
		}
		else if(j==0 && i!=max && i!=0)
		{
			bfs[] n={G[i][j+1],G[i-1][j],G[i+1][j]};
			return n;
		}
		else //the condition for j=max and i neither 0 nor max
		{
			bfs[] n={G[i-1][j],G[i+1][j],G[i][j-1]};
			return n;
		}
		
	}
	
	public static void DistanceToAllCells(bfs[][] G,int i, int j) {
		queue q= new queue();
		bfs c0=G[i][j];
		c0.setDistance(0);
		q.enqueue(c0);
		while(!(q.isEmpty()))
		{
			bfs c=q.dequeue();
			bfs[] N=getNeighbours(G,c.k,c.l);
			for (int a=0; a<N.length; a++) 
			{
				bfs b=N[a];
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
	
	public static bfs[] TraceBack(bfs[][] G, bfs dest, int d) {
		bfs[] tb=new bfs[d+1];
		/*System.out.print(dest.l+" "+dest.k);
		System.out.println();*/
		tb[0]=dest;
		int i=1;
		bfs cur=dest;
		while(cur.getDistance()!=0)
		{
			bfs[] N=getNeighbours(G,cur.k,cur.l);
			for (int a=0; a<N.length; a++) 
			{
				bfs b=N[a];
				if(b.blocked!=0) //to check if block is not an obstacle
				{
					if(b.getDistance()==cur.getDistance()-1)
					{
						tb[i]=b;
						/*System.out.print(b.l+" "+b.k);
						System.out.println();*/
						cur=b;
						i++;
						break;
					}
				}
			}
		}
		return tb;
	}


	public static void main(String[] args) throws IOException {
		
		Reader1.init(System.in);
		int n=Reader1.nextInt();
		bfs[][] G = new bfs[n][n];
		int col1=Reader1.nextInt();
		int row1=Reader1.nextInt();
		int col2=Reader1.nextInt();
		int row2=Reader1.nextInt();
		
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				G[i][j]=new bfs(Reader1.nextInt(),i,j);
			}
		}
		
		long startTime_x = System.currentTimeMillis();
		
		DistanceToAllCells(G,row1,col1);
		
		bfs c=G[row2][col2];
		//System.out.println(c.getDistance());
		
		bfs[] out=TraceBack(G,c,c.getDistance());
		
		for(int i=c.getDistance(); i>=0; i--)
		{
			bfs b=out[i];
			System.out.print(b.l+" "+b.k);
			System.out.println();
		}
		
		long endTime_x = System.currentTimeMillis();
		double TimeElapsed_x= (endTime_x-startTime_x)/1000.0; //finds time in seconds
		//System.out.println(TimeElapsed_x);
	}
}


class Reader1 {
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



