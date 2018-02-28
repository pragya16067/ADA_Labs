import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//		NAME : PRAGYA PRAKASH
//		ROLL NO. : 2016067

//		Reference for Dijkstra Algorithm : Class notes and GeeksForGeeks


public class Lab2b {
	static int[][] G;
	static int AdjMatrix[][];
	
	public static int getMinDist(int[] dist, boolean[] set)
	{
		int min=Integer.MAX_VALUE;
		int index=0;
		
		for(int v=1; v<dist.length; v++)
		{
			if(set[v]==false && dist[v]<=min && dist[v]!=-1)
			{
				min=dist[v];
				index=v;
			}
		}
		return index;
	}
	
	public static void Dijkstra(int V) {
		
		int[] Dist=new int[V+1];
		boolean[] ShortSet = new boolean[V+1];
		
		for (int i=0; i<V+1; i++)
        {
            Dist[i] = Integer.MAX_VALUE;
            ShortSet[i] = false;
        }
 
        // Distance of source vertex from itself is always 0
        Dist[1] = 0;
        
        for(int i=0; i<V; i++)
        {
        	int u = getMinDist(Dist, ShortSet);
        	//System.out.println("MinDistance picked is "+u);
            
        	//This vertex's shortest length path has been calculated
            ShortSet[u] = true;
 
            //Update distances of all neighbours of u
            for (int v=1; v<V+1; v++)
            {
                if (!ShortSet[v] && AdjMatrix[u][v]!=-1 && Dist[u] != Integer.MAX_VALUE && Dist[u]+AdjMatrix[u][v] < Dist[v])
                    Dist[v] = Dist[u] + AdjMatrix[u][v];
            }
        }
        
        //for(int i=1; i<=V; i++)
        for(int i=1; i<=V; i++)
        {
        	System.out.print(Dist[i] +" ");
        }
        System.out.println(Dist[V]+G[1][1]);
		
	}
		
	public static void main(String[] args) throws IOException {
		Reader.init(System.in);
		int T=Reader.nextInt();
		while(T-- > 0)
		{
			int R=Reader.nextInt();
			int C=Reader.nextInt();
			
			G=new int[R+1][C+1];
			
			for(int i=1; i<=R; i++)
			{
				for(int j=1; j<=C; j++)
				{
					 
					int d=Reader.nextInt();
					G[i][j] = d;
				}
			}
			int size= (R*C) +1;
			AdjMatrix= new int[size][size];
			
			for(int i=0; i<size; i++)
			{
				for (int j=0; j<size; j++)
				{
					AdjMatrix[i][j]=-1;
				}
			}
			
			//Now code to make the adjacency Mtrix
			
			for(int i=1; i<=R; i++)
			{
				for(int j=1; j<=C; j++)
				{
					//int w = G[i][j];
					int pos= (i-1)*C + j;
					//neighbour1
					int pos1 = (i-1)*C + j-1;
					if(i>0 && i<=R && j>1 && j<=C )
						AdjMatrix[pos][pos1] = G[i][j-1];
					//neighbour2
					pos1 = (i-1)*C + j+1;
					if(i>0 && i<=R && j>=1 && j<C )
						AdjMatrix[pos][pos1] = G[i][j+1];
					pos1 = (i-2)*C + j;
					if(i>1 && i<=R && j>0 && j<=C )
						AdjMatrix[pos][pos1] = G[i-1][j];
					//neighbour4
					pos1 = (i)*C + j;
					if(i>0 && i<R && j>0 && j<=C )
						AdjMatrix[pos][pos1] = G[i+1][j];
				}
			}
			
			for(int i=1; i<size; i++)
			{
				for (int j=1; j<size; j++)
				{
					System.out.print(AdjMatrix[i][j]+" ");
				}
				System.out.println();
			}
			
			Dijkstra(size-1);
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