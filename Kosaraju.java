import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

class GNode {
	
	public int data;
	public GNode link;
	//public String color;
	
	public GNode() { 		
		// a simple GNode
		data = 0; 
		link = null;
		//color="White";
	}
	
	public GNode(int n) {
		// a GNode with a given value
		data = n; 
		link = null; 
		//color="White";
	}

	public GNode(int value, GNode ptr) {
		//a GNode with given value and reference
		data=value;
		link=ptr; 
		//color="White";
	}
	
	public int getData() {
		return this.data;
	}
	
	
	
	public GNode getLink() {
		return this.link;
	}
	
	public void setLink(GNode n) {
		this.link=n;
	}
	
	
	
	public void setData(int n) {
		this.data=n;
	}
	
	public void insert(int value) {
		GNode nptr=new GNode(value,this.getLink());
		//nptr.setLink(this.getLink());
		this.setLink(nptr);
	}
	
	public void display() {
		GNode n=this;
		do
		{
			System.out.print(n.getData()+" ");
			n=n.getLink();
		}while(n!=null);
		
		System.out.println();
	}
}

public class Kosaraju {
	static boolean[] mark;
	//
	//	Reference for Kosaraju Algorithm : Class notes and GeeksForGeeks
	//
	
	//public static int cyclic=0;

	
	public static void DFS_Visit(int index, GNode[] l,boolean[] mark) {
		
		GNode u=l[index];
		mark[index]=true;
		System.out.print(index+" ");
		
		while(u.getLink()!=null)
		{
			GNode v=u.getLink();
			
			if(!mark[v.getData()])
			{
				//System.out.println("v="+v.getData()+" "+v.getColor());
				DFS_Visit(v.getData(),l,mark);
			}
			u=u.getLink();
			//System.out.println("u="+u.getData()+" "+u.getColor());
		}
		//color[index]='b';
		
	}
	
	 public static void getOrder(int v, boolean mark[], GNode[] AdjList, Stack<Integer> s)
	    {
	        // Mark the current node as visited
	        mark[v] = true;
	        GNode u = AdjList[v];
	        
	        // Recur for all the vertices adjacent to this vertex, if unvisited
	        while(u.getLink()!=null)
			{
				GNode x=u.getLink();
	            if(!mark[x.getData()])
	                getOrder(x.getData(), mark, AdjList, s);
	            u=u.getLink();
	        }
	 
	        // All vertices reachable from v are processed by now,
	        // push v to Stack
	        s.push(new Integer(v));
	    }

	public static GNode[] getTranspose(GNode[] AdjList) {
		GNode[] newAdjL = new GNode[AdjList.length];
		for(int i=0; i<AdjList.length;i++)
		{
			GNode nptr=new GNode(i);
			newAdjL[i]=nptr;
		}
		
		for(int i=0; i<AdjList.length; i++)
		{
				GNode curr = AdjList[i];
				int head=curr.getData();
				
				curr = curr.getLink();
				//System.out.println(curr.getData());
				while(curr!=null)
				{
					newAdjL[curr.getData()].insert(head);
					//System.out.println(curr.getData());
					curr = curr.getLink();
				}
		}
		
		/*for(int i=0; i<AdjList.length;i++)
		{
			GNode n=newAdjL[i];
			n.display();
		}*/
		return newAdjL;
	}
	
	
	
	public static void getAllSCC (GNode[] G, int V) {
		Stack<Integer> s= new Stack<Integer> ();
		mark = new boolean[V];
		
		for(int v=0; v<V; v++)
		{//mark all vertices as unvisited
			mark[v] = false;
		}
		
		//fill vertices in stack order acc to their finish times
		for(int i=0; i<V; i++)
		{
			if(mark[i]==false)
				getOrder(i, mark, G, s);
		}
		//System.out.println(Arrays.toString(s.toArray()));
		
		
		//Reverse the graph
		GNode[] G2= getTranspose(G);
		
		//For second DFS again mark vertices as not visited
		for(int v=0; v<V; v++)
		{
			mark[v] = false;
		}
		
		//Now to process according to order on stack
		while(!s.isEmpty())
		{
			int v = s.pop(); //pop out the top vertex
			if(mark[v] != true) //if it hasn't been visited, means it is a vertex of a new component
			{
				DFS_Visit(v,G2,mark);
				System.out.println();
			}
		}
		
		return;
	}
	
	public static void main(String[] args) throws IOException {
		Reader.init(System.in);
		int T=Reader.nextInt();
		while(T-- > 0)
		{
			int N=Reader.nextInt();
			int D=Reader.nextInt();
			
			GNode[] AdjList=new GNode[N];
			for(int i=0; i<N;i++)
			{
				GNode nptr=new GNode(i);
				AdjList[i]=nptr;
			}
			
			while (D-- > 0)
			{
				int x=Reader.nextInt();
				int y=Reader.nextInt();
				GNode n=AdjList[x];
				n.insert(y);
			}
			//Now we have the complete adjacency list
			
			
			//To display the adjacency list
			/*for(int i=0; i<N;i++)
			{
				GNode n=AdjList[i];
				n.display();
			}
			*/
			getAllSCC(AdjList,N);
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

