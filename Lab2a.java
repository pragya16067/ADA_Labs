import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Lab2a {
	static int AdjMatrix[][];
	
	public static int getMinDist(int[] dist, boolean[] set)
	{
		int min=Integer.MAX_VALUE;
		int index=0;
		
		for(int v=1; v<dist.length; v++)
		{
			if(set[v]==false && dist[v]<=min)
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
                if (!ShortSet[v] && AdjMatrix[u][v]!=0 && Dist[u] != Integer.MAX_VALUE && Dist[u]+1 < Dist[v])
                    Dist[v] = Dist[u] + 1;
            }
        }
        
        //for(int i=1; i<=V; i++)
        System.out.println(Dist[V]);
		
	}
		
	public static void main(String[] args) throws IOException {
		Reader.init(System.in);
		int T=Reader.nextInt();
		while(T-- > 0)
		{
			int N=Reader.nextInt();
			AdjMatrix= new int[N+2][N+2];
			String s=Reader.reader.readLine();
			String[] s2=s.split(" ");
			
			for(int i=1; i<=N; i++)
			{
				int num=Integer.parseInt(s2[i-1]);
				int j=i+1;
				AdjMatrix[i][j] = 1;
				j=i+num;
				if(j>N+1 || j<1)
					continue;
				AdjMatrix[i][j] = 1;
			}
			
			/*for(int i=1; i<=N+1; i++)
			{
				for(int j=1; j<=N+1; j++)
				{
					System.out.print(AdjMatrix[i][j]+" ");
				}
				System.out.println();
			}*/
			
			Dijkstra(N+1);
			
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
