//		NAME : PRAGYA PRAKASH
//		ROLL NO. : 2016067

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Lab1a {
	public static String str;
	public static char[] newstr;
	public static int valid=1;
	
	public static void rec(int prev, int curr, int next, int ptr) {
		if(next>=str.length() || curr >= str.length() || prev >= str.length())
		{
			return;
		}
		else
		{
			int dist=(int) str.charAt(next) - (int)str.charAt(curr);
			if( dist <= 3 && dist>= -3)
			{
				if(prev==-1) {
					newstr[ptr]='a';
					newstr[ptr+1]='a';
				}
				else if(str.charAt(prev)== 'a')
				{
					newstr[ptr]='a';
					newstr[ptr+1]='a';
				}
				else
				{
					newstr[ptr]='b';
					newstr[ptr+1]='b';
				}
			}
			else
			{
				if(dist > 0) //ascending order
				{
					newstr[ptr]='a';
					newstr[ptr+1]='b';
				}
				else
				{
					newstr[ptr]='b';
					newstr[ptr+1]='a';
				}
					
			}
		}
		rec(curr, next, next+1,ptr+2);
	}
	
	public static void check(int prev, int curr) {
		//System.out.println(curr);
		if(curr >= newstr.length)
		{
			return;
		}
		if(newstr[prev]=='a')
		{
			if(newstr[curr] == 'a')
			{
				check(curr, curr+1);
			}
			else
			{
				if((curr+1)<newstr.length && newstr[curr+1]=='b')
				{
					check(curr+1,curr+2);
				}
				else
				{
					valid=0;
					return;
				}
			}
		}
		else
		{
			if(newstr[curr]=='a')
			{
				check(curr, curr+1);
			}
			else
			{
				valid=0;
				return;
			}
		}
		
	}

	public static void main(String[] args) throws IOException {
		Reader.init(System.in);
		
		int T=Reader.nextInt();
		while(T-- > 0)
		{
			String s=Reader.reader.readLine();
			str=s.toLowerCase();
			
			int len=(str.length()/2)*(str.length()-1);
			if(str.length()==2)
			{
				len=2;
			}
			
			newstr=new char[len];
			rec(-1,0,1,0);
			
			/*for(int i=0; i<len; i++)
			{
				System.out.print(newstr[i]);
			}
			System.out.println();*/
			
			check(0,1);
			if(valid==0)
			{
				System.out.println("Invalid");
				valid=1;
			}
			else
			{
				System.out.println("Valid");
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