import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Lab3a {
	
	public static void GaleShapley(int N, int[][] mPref, int[][] wPref) {
		boolean[] mFree = new boolean[N];
		int[] wPartner = new int[N];
		int[] mPartner = new int[N];
		int freeMenCtr = N;
		
		//initialise all partners to -1 and all men as free
		for(int i=0; i<N; i++)
		{
			mFree[i] = true;
			wPartner[i] = -1;
			mPartner[i] = -1;
		}
		
		while(freeMenCtr > 0)
		{
			//Pick the first free man
			int m;
			for(m=1; m<=N; m++)
			{
				if(mFree[m-1])
					break;
			}
			
			//Go over all women according to m's preference
			for(int i=0; i<N && mFree[m-1]; i++)
			{
				int w=mPref[m-1][i] - 1;
				
				//If this women is free, then engage her with m
				if(wPartner[w] == -1)
				{
					wPartner[w] = m;
					mPartner[m-1] = w+1;
					mFree[m-1] = false;
					freeMenCtr--;
				}
				
				else
				{
					int m2=wPartner[w];
					//to check if women w prefers m2 over m or not
					for(int p=0; p<N; p++)
					{
						if(wPref[w][p]==m2)
						{
							//No change in pairing and number of free men
							break;
						}
						if(wPref[w][p]==m)
						{
							//m is preferred over m2, so change pairing
							wPartner[w] = m;
							mPartner[m-1] = w+1;
							mFree[m2-1]=true;
							mFree[m-1] = false;
							break;
						}
					}
					
				}
			}
		}
		
		for(int j=0; j<N; j++)
		{
			System.out.println((j+1)+" "+mPartner[j]);
		}
	}

	public static void main(String[] args) throws IOException {
		Reader.init(System.in);
		int T=Reader.nextInt();
		int N;
		int[][] mPref;
		int[][] wPref;
		String s;
		String[] s2;
		
		while(T-- > 0) {
			N = Reader.nextInt();
			mPref = new int[N][N];
			wPref = new int[N][N];
			
			
			//Add values according to the input data
			for(int i=0; i<N; i++)
			{
				//women preferences
				s=Reader.reader.readLine();
				s2=s.split(" ");
				int wNo = Integer.parseInt(s2[0])-1;
				for(int j=0; j<N; j++)
				{	wPref[wNo][j]= Integer.parseInt(s2[j+1]);	}
			}
			
			for(int i=0; i<N; i++)
			{
				//men preferences
				s=Reader.reader.readLine();
				s2=s.split(" ");
				int mNo = Integer.parseInt(s2[0])-1;
				for(int j=0; j<N; j++)
				{	mPref[mNo][j]= Integer.parseInt(s2[j+1]);	}
			}
			
			/*print to check if data is ok
			for(int i=0; i<N; i++)
			{
				for(int j=0; j<N; j++)
				{
					System.out.print(mPref[i][j] + " ");
				}
				System.out.println();
			}*/
			
			GaleShapley(N, mPref, wPref);
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
