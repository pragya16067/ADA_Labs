import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//References for Maximum Bipartite Matching Algorithm : Class notes and GeeksForGeeks

class point {
	double x;
	double y;
	
	public point(double a, double b) {
		x=a;
		y=b;
	}
	
	public double getDistance(point p2) {
		double d = ((x - p2.x)*(x - p2.x)) + ((y - p2.y)*(y - p2.y));
		return Math.sqrt(d);
		
	}
	
}

public class Lab5b {
	
	// p is number of squirrels and q is number of holes
	static int  p;
	static int q;
	static int vt;
	static int bpGraph[][];
	
	
	static boolean canReachHole(int u, boolean[] seen, int MatchR[]) {
		//Try every hole, one by one
		for(int v=0; v< q; v++)
		{
			//if distance can be reached in time, and v is not visited
			if(!seen[v] && bpGraph[u][v] == 1)
			{
				seen[v] = true;
				// If hole 'v' is not assigned to a squirrel OR
	            // previously assigned squirrel for hole v (which
	            // is matchR[v]) has an alternate hole available.
	            // Since v is marked as visited in the above line,
	            // matchR[v] in the following recursive call will
	            // not get hole 'v' again
	            if (MatchR[v] < 0 || canReachHole(MatchR[v], seen, MatchR))
	            {
	                MatchR[v] = u;
	               //System.out.println(MatchR[v]);
	                return true;
	            }
			}
		}
		return false;
	}
	 
	   
	 
	// Returns maximum number of matching from M to N
	static int maxMatching()
	{
	    // An array to keep track of the applicants assigned to
	    // jobs. The value of matchR[i] is the applicant number
	    // assigned to job i, the value -1 indicates nobody is
	    // assigned.
	        int matchR[] = new int[q];
	 
	        // Initially all jobs are available
	        for(int i=0; i<q; ++i)
	            matchR[i] = -1;
	 
	        int result = 0; // Count of holes assigned to squirrels
	        
	        for (int u = 0; u < p; u++)
	        {
	        	// Mark all jobs as not seen for next applicant.
	            boolean seen[] = new boolean[q] ;
	            for(int i=0; i<q; ++i)
	                seen[i] = false;
	 
	            // Find if the applicant 'u' can get a job
	            if (canReachHole(u, seen, matchR))
	            	result++;
	        }
	    return result;
	}
	
	public static void main(String[] args) throws IOException {
		Reader.init(System.in);
		p= Reader.nextInt();
		q= Reader.nextInt();
		int t= Reader.nextInt();
		int v= Reader.nextInt();
		point[] squirrels = new point[p];
		point[] holes = new point[q];
		bpGraph = new int[p][q];
		
		vt = v*t;
		
		for(int i=0; i<p; i++) 
		{
			double a=Reader.nextDouble();
			double b=Reader.nextDouble();
			point x = new point(a,b);
			squirrels[i] = x;
		}
		
		for(int i=0; i<q; i++) 
		{
			double a=Reader.nextDouble();
			double b=Reader.nextDouble();
			point x = new point(a,b);
			holes[i] = x;
		}
		
		for(int i=0; i<squirrels.length; i++)
		{
			for(int k=0; k<holes.length; k++)
			{
				//System.out.println(squirrels[i].getDistance(holes[k]));
				if(squirrels[i].getDistance(holes[k]) <= vt)
				{
					bpGraph[i][k] = 1;
				}
			}
		}
		
		/*for(int i=0; i<squirrels.length; i++)
		{
			for(int k=0; k<holes.length; k++)
			{
				System.out.print(bpGraph[i][k]);			
			}System.out.println();
		}*/
		
		int n= maxMatching();
		System.out.println(p-n);
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