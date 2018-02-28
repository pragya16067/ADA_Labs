// NAME : PRAGYA PRAKASH
// ROLL NO. : 2016067
// SECTION_GROUP : A-3
// Lab 11(Saturday group)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class edge {
	
	public int cost;
	public int u;
	public int v;
	
	public edge(int[] c, int e) {
		cost=e;
		u=c[0];
		v=c[1];
	}
	
}


public class KruskalsAlgo {
	public static int[] parent;   //the array which contains the set information
	public static int MinCost=0;
	
	public static int Find(int i) {
		int j = i, k;
		if (parent[i] < 0) 
		{
			return i; 		/* i is the leader */
		}
		while (parent[j] > 0)
		{
			j = parent[j]; 	/* j is the leader */
		}
		while (parent[i] != j) 
		{
			k = parent[i]; 
			parent[i] = j; 
			i = k;
		 /* if i is not directly pointing to j, then make i point to j,
		and look at the next element on the path from i to j */
		}
		return j;
	}
	
	public static void Union( int i, int j) {

		if (parent[i] < parent[j]) {
			 parent[i] = parent[i]+parent[j];
			 parent[j] = i;
		}
		else {
			 parent[j] = parent[i]+parent[j];
			 parent[i] = j;
		}
			 return;			
	}
	
	public static void sort_edges(edge[] edges) {
		edge[] aux=new edge[edges.length];
		int n=edges.length;
		mergesort(edges,aux,0,n-1);
	}
	
	private static void mergesort(edge[] Arr,edge[] aux,int low, int high) {
		 if (low < high) {

		 int middle = (low + high) / 2;

		 mergesort(Arr,aux,low, middle);

		 mergesort(Arr,aux,middle + 1, high);
		 // Combine both the sorted sub arrays
		 merge(Arr,aux,low, middle, high);
		 }
	}
	
	private static void merge(edge[] Arr,edge[] aux,int low, int middle, int high) {
		 // Copy contents of A into aux array
		 for (int i = low; i <= high; i++) {
		 aux[i] = Arr[i];
		 }
		 int i = low;
		 int j = middle + 1;
		 int k = low;
		 
		 
		 // Copy the smallest element from either sub arrays into A
		 while (i <= middle && j <= high) {
			 if (aux[i].cost <= aux[j].cost) {
				 	Arr[k] = aux[i];
				 	i++;} 
			 else {
				 	Arr[k] = aux[j];
				 	j++;}
			 k++;
		 }
		
		// Copy the rest of the elements
		 while (i <= middle) {
			 Arr[k] = aux[i];
			 k++;
			 i++;
		 }
		 
		 while (j <= high) {
			 Arr[k] = aux[j];
			 k++;
			 j++;
		 }
	}
	
	
	
	public static void main(String[] args) throws IOException {
		Reader.init(System.in);
		int C=Reader.nextInt();
		int R=Reader.nextInt();
		int R2=R;
		

		//set for each vertex
		parent=new int[C+1];
		for(int i=0;i<=C;i++)
		{
			parent[i]=-1;
			//System.out.print(A[i]+" ");
		}
		
		edge[] edges=new edge[R];
		//to read all edges and store in an array of edges
		while (R2-- > 0)
		{
			int c1=Reader.nextInt();
			int c2=Reader.nextInt();
			int wt=Reader.nextInt();
			int[] arr={c1,c2};
			edge x=new edge(arr, wt);
			edges[R2]=x;
		}
		

		//set of safe edges
		edge[] E=new edge[R];
		int end_ptrE=0;
		
		//the compulsory edge
		int cc1=Reader.nextInt();
		int cc2=Reader.nextInt();
		
		long startTime_x = System.currentTimeMillis();
		
		Union(Find(cc1),Find(cc2));
		
		for(int i=0; i<edges.length; i++)
		{
			edge e=edges[i];
			int u=e.u;
			int v=e.v;
			if(u==cc1 & v==cc2)
			{
				E[end_ptrE++]=e;
				MinCost+=e.cost;
			}
		}

		
		//set of edges sorted on the basis of their weights
		sort_edges(edges);
		
		//To Print Sorted Array of edges 
		/*for(int i=0; i<edges.length; i++)
		{
			edge e=edges[i];
			System.out.println(e.u + " " + e.v);
		}*/
		
		
		//Kruskal's Algorithm		
		for(int i=0; i<edges.length; i++)
		{
			edge e=edges[i];
			int u=e.u;
			//System.out.print(u);
			int v=e.v;
			//System.out.println(v);
			//System.out.println(Find(u)+" "+Find(v));
			if(Find(u)!=Find(v))
			{
				E[end_ptrE++]=e;
				MinCost+=e.cost;
				Union(Find(u),Find(v));
			}
			
		}
		
		//Now we have the set of safe edges, E
		/*For printing E
		for(int i=0; i<end_ptrE; i++)
		{
			
			edge e=E[i];
			System.out.println(e.u + " " + e.v);
		}*/
		
		System.out.println(MinCost);
		
		long endTime_x = System.currentTimeMillis();
		double TimeElapsed_x= (endTime_x-startTime_x)/1000.0; //finds time in seconds
		//System.out.println(TimeElapsed_x);

	}

}

