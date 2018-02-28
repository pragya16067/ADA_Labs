// NAME : PRAGYA PRAKASH
// ROLL NO. : 2016067
// SECTION_GROUP : A-3
// Lab 10(Saturday group)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
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

public class dfs {
	public static int cyclic=0;
	
	public static void DFS(GNode[] l,char[] color) {
		
		for(int i=0; i<color.length;i++)
		{
			color[i]='w';
		}
		
		for(int i=0; i<l.length; i++)
		{
			if(color[i]=='w' && cyclic==0)
				DFS_Visit(i,l,color);
		}
	}
	
	public static void DFS_Visit(int index, GNode[] l,char[] color) {
		GNode u=l[index];
		color[index]='g';
		
		//System.out.println(cyclic);
		//System.out.println("u="+u.getData()+" "+u.getColor());
		
		while(u.getLink()!=null)
		{
			GNode v=u.getLink();
			//System.out.print(v.getColor());
			if(color[v.getData()-1]=='w')
			{
				//System.out.println("v="+v.getData()+" "+v.getColor());
				DFS_Visit(v.getData()-1,l,color);
			}
			if(color[v.getData()-1]=='g' )
			{
				
				cyclic=1;
				//return;
			}
			u=u.getLink();
			//System.out.println("u="+u.getData()+" "+u.getColor());
		}
		color[index]='b';
		
	}

	public static void main(String[] args) throws IOException{
		Reader.init(System.in);
		int N=Reader.nextInt();
		int D=Reader.nextInt();
		
		char[] Color=new char[N];
		GNode[] AdjList=new GNode[N];
		for(int i=0; i<N;i++)
		{
			GNode nptr=new GNode(i+1);
			AdjList[i]=nptr;
		}
		
		while (D-- > 0)
		{
			int x=Reader.nextInt();
			int y=Reader.nextInt();
			for(int i=0; i<N;i++)
			{
				if(AdjList[i].getData()==y)
				{
					GNode n=AdjList[i];
					n.insert(x);
				}
			}
		}
		//Now we have the complete adjacency list
		
		/*
		//To display the adjacency list
		for(int i=0; i<N;i++)
		{
			GNode n=AdjList[i];
			n.display();
		}*/
		
		long startTime_x = System.currentTimeMillis();
		
		DFS(AdjList,Color);
		
		if(cyclic==0) //No cycle is detected during DFS of the graph
		{
			for(int i=0; i<N;i++)
			{
				if(AdjList[i].getLink()==null)
				{
					System.out.print(AdjList[i].getData()+" ");
				}
			}
		}
		else //A cycle is present i.e. deadlock occurs
		{
			System.out.println("-1");
		}
		
		long endTime_x = System.currentTimeMillis();
		double TimeElapsed_x= (endTime_x-startTime_x)/1000.0; //finds time in seconds
		//System.out.println(TimeElapsed_x);

	}

}
