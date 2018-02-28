//		NAME : PRAGYA PRAKASH
//		ROLL NO. : 2016067

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Lab1b {
	public static int M;
	
	public static int numhrs(int n, int m) {
		//System.out.println(n);
		//System.out.println(m);
		if(n==0)
		{
			return m;
		}
		else
		{
			int hrs = m+1; //new hour has started
			
			if(hrs % M==0)
			{
				//add a new pen and subtract a pen
				return numhrs(n,hrs);
			}
			else
			{
				//subtract a pen
				return numhrs(n-1,hrs);
			}
		}
		
		
	}

	public static void main(String[] args) throws IOException {
		Reader.init(System.in);
		
		int T=Reader.nextInt();
		while(T-- > 0) {
			int n=Reader.nextInt();
			int m=Reader.nextInt();
			M=m;
			if(M==1) //can never sleep
			{
				System.out.println("No Sleep");
			}
			else
			{
				int h=numhrs(n,0);
				System.out.println(h);
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