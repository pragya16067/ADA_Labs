import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class FordFulkerson {
	//Reference for Ford Fulkerson Algorithm : Class notes and GeeksForGeeks
	
	static int[][] ResidualG;
	static int[] Parent;
	static int V;
	static int MaxFlow;
	
	static boolean bfs(int src, int dest) {
		boolean[] mark=new boolean[V+1];
		
		for(int i=0; i<=V; i++)
		{
			mark[i]=false; //mark all vertices as not visited
		}
		
		//create a queue, enqueue source vertex and mark it as visited
		LinkedList<Integer> Q=new LinkedList<Integer> ();
		
		Q.add(src);
		mark[src] = true;
		
		while(!Q.isEmpty())
		{
			int u=Q.poll();
			for(int v=1; v<=V; v++)
			{
				if(ResidualG[u][v] > 0 && mark[v]==false)
				{
					Q.add(v);
					mark[v] = true;
					Parent[v] = u;
				}
			}
		}
		
		if(mark[dest] == false)
		{
			return false;
		}
		return true;
	}
	
	public static void findMaxFlow(int[][] G, int src, int dest) {
		ResidualG = new int[V+1][V+1];
		Parent = new int[V+1];
		MaxFlow = 0;
		
		//Initially the Residual graph is same as graph G
		for(int i=0; i<V+1; i++)
		{
			for(int j=0; j<=V; j++)
			{
				ResidualG[i][j] = G[i][j];
			}
		}
		
		//while there exists an augmenting path
		while( bfs(src, dest) )
		{
			int PathMinF = Integer.MAX_VALUE;
			
			//backtracking along the parent to find the min Flow along this path
			int u=dest;
			int p;
			while(u!=src)
			{
				p=Parent[u];
				if(ResidualG[p][u] <= PathMinF)
					PathMinF = ResidualG[p][u];
				u=p;
			}
			
			//Changing the residual graph
			u=dest;
			while(u!=src)
			{
				p=Parent[u];
				ResidualG[p][u] = ResidualG[p][u] - PathMinF; //for the forward edge, subtract min flow of the path
				ResidualG[u][p] = ResidualG[u][p] + PathMinF; //for the backward edge, add min Flow of the path
				u=p;
			}
			
			MaxFlow = MaxFlow + PathMinF ;
		}
	}

	
	public static void main(String[] args) throws IOException {
		Reader.init(System.in);
		int T=Reader.nextInt();
		while(T-- > 0)
		{
			int N = Reader.nextInt();
			int[][] G= new int[N+1][N+1];
			V=N;
			
			int E = Reader.nextInt(); //number of directed edges in graph
			while(E-- > 0)
			{
				int u= Reader.nextInt();
				int v=Reader.nextInt();
				int wt=Reader.nextInt();
				
				G[u][v] = wt;
			}
			
			findMaxFlow(G,1,N);
			System.out.println(MaxFlow);
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