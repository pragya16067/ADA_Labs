import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;


//		NAME : PRAGYA PRAKASH
//		ROLL NO. : 2016067

//		References : Class notes and GeeksForGeeks


class lift {
	static int start;
	static int stop;
	
	public lift(int s1, int s2) {
		start = s1;
		stop = s2;
	}
	
	@Override
	public boolean equals(Object s) {
		lift l = (lift) s;
		if(this.start == l.start && this.stop==l.stop) {
			return true;
		}
		else
			return false;
	}
	
}
public class Lab3b {
	static int AdjMatrix[][];
	static int V;
	static int Parent[];
	static HashSet<lift> h = new HashSet<lift> ();
	//static ArrayList<ArrayList> AllPaths = new ArrayList<ArrayList> ();


	/*
	// Prints all paths from
    // 's' to 'd'
    public static void printAllPaths() 
    {
        boolean[] isVisited = new boolean[V+1];
        ArrayList<Integer> pathList = new ArrayList<>();
         
        //add source to path[]
        pathList.add(1);
         
        //Call recursive utility
        printAllPathsUtil(1, V, isVisited, pathList);
   
    }
 
    // A recursive function to print
    // all paths from 'u' to 'd'.
    // isVisited[] keeps track of
    // vertices in current path.
    // localPathList<> stores actual
    // vertices in the current path
    private static void printAllPathsUtil(Integer u, Integer d, boolean[] isVisited, ArrayList<Integer> localPathList) {
         
        // Mark the current node
        isVisited[u] = true;
         
        if (u.equals(d)) 
        {
            System.out.println(localPathList);
            AllPaths.add(localPathList);
        }
         
        // Recur for all the vertices
        // adjacent to current vertex
        for(int v=1; v<=V; v++)
		{
			if(AdjMatrix[u][v] > 0)
			{
	            if (!isVisited[v])
	            {
	                // store current node 
	                // in path[]
	                localPathList.add(v);
	                printAllPathsUtil(v, d, isVisited, localPathList);
	                 
	                // remove current node
	                // in path[]
	                localPathList.remove(v);
	            }
			}
		}
        
        // Mark the current node
        isVisited[u] = false;
    }
    
    static int FindMinWaitingTime() {
    	int min = Integer.MAX_VALUE;
    	for(ArrayList<Integer> l : AllPaths) {
    		//calculation of waiting time of path
    		int t=0;
    		int prev=1;
    		for(int i=1; i<l.size(); i++) {
    			
    			int t2 = l.get(i);
    			int t1 = (l.get(i) - prev + 1) * 5;
    			prev= l.get(i);
    		}
    			
    	}
    }*/
 
	
	public static int getMinTime(int[] Time, boolean[] set)
	{
		int min=Integer.MAX_VALUE;
		int index=0;
		
		for(int v=1; v<Time.length; v++)
		{
			if(set[v]==false && Time[v]<=min)
			{
				min=Time[v];
				index=v;
			}
		}
		return index;
	}
	
	public static void Dijkstra(int V) {
		int t;
		int[] Dist=new int[V+1];
		int[] Time= new int[V+1];
		boolean[] ShortSet = new boolean[V+1];
		
		for (int i=0; i<V+1; i++)
        {
            Dist[i] = Integer.MAX_VALUE;
            Time[i] = Integer.MAX_VALUE;
            ShortSet[i] = false;
        }
 
        // Distance of source vertex from itself is always 0
        Dist[1] = 0;
        Time[1] = 0;
        
        for(int i=1; i<=V; i++)
        {
        	int u = getMinTime(Time, ShortSet);
        	//System.out.println("MinDistance picked is "+u);
            
        	//This vertex's shortest length path has been calculated
            ShortSet[u] = true;
            
            
            //Update distances of all neighbours of u
            for (int v=1; v<V+1; v++)
            {
            	t = (Integer.max(u,v) - Integer.min(u,v) + 1 ) * 5; //time taken to actually traverse the floors
            	//We must also add the waiting time
                if(u != 1 )
                {
                	//if u was 1, then no waiting time
                	int factor1 = (Integer.max(u,v) - Integer.min(u,v) + 1);
                	int factor2 = Time[u] / 5;
                	//to find the starting pt of lift
                	lift l1 = new lift(u,v);
                	int pos; //to store current position of lift
                	if(h.contains(l1)) //u is starting point
                	{
                		System.out.println("lift Starting at "+u);
                		int x=factor2/factor1;
                		if(x%2 == 0)//we are at u 
            			{
            				pos = u + factor2 - (x*factor1);
            			}
                		else//we are at v
            			{
            				pos = v + factor2 - (x*factor1);
            			}
                	}
                	else
                	{
                		//v is starting point
                		int x=factor2/factor1;
                		if(x%2 != 0)//we are at u
            			{
            				pos = u + factor2 - (x*factor1);
            			}
                		else//we are at v
            			{
            				pos = v + factor2 - (x*factor1);
            			}
                    	
                	}
                	int waitingtime = (Integer.max(u,pos) - Integer.min(u,pos) + 1 ) * 5;
                	t = t+waitingtime;
                }
                if (!ShortSet[v] && AdjMatrix[u][v]!=0 && Time[u] != Integer.MAX_VALUE && Time[u]+t < Time[v]) {
                    Time[v] = Time[v] + t;
                }
            }
        }
        
        //for(int i=1; i<=V; i++)
        	System.out.println(Time[V]);
		
	}
	
	public static void main(String[] args) throws IOException {
		Reader.init(System.in);
		int u=Reader.nextInt(); //number of levels
		int n=Reader.nextInt(); //number of lifts
		int fi1;
		int fi2;
		AdjMatrix = new int[u+1][u+1];
		
		while(n-- > 0)
		{
			fi1 = Reader.nextInt();
			fi2 = Reader.nextInt();
			lift l= new lift(fi1, fi2);
			h.add(l);
			AdjMatrix[fi1][fi2] = 1;
			AdjMatrix[fi2][fi1] = 1;
		}
		
		Dijkstra(u);
		
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