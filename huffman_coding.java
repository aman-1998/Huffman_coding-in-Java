import java.util.*;														
import java.lang.*;
import java.io.*;

class huff
{
	int heap_size;
	node min1,min2;
	int[] code=new int[50];
	huff(int i)
	{
		heap_size=i;
	}
	node huffman_tree(ArrayList<node> A)
	{
		int x,y;
		A=build_heap(A);
		while(heap_size>1)
		{
			A=delete_min(A,1);
			A=delete_min(A,2);
			node temp=new node(min1,'#',min1.freq+min2.freq,min2);
			A=insert(A,temp);
		}
		return A.get(1);
	}
	void display(node root,int top)
	{
		if(root.left!=null)
		{
			code[top]=0;
			display(root.left,top+1);
		}
		if(root.right!=null)
		{
			code[top]=1;
			display(root.right,top+1);
		}
		if(root.left==null && root.right==null)
		{
			System.out.print(root.ch+" - ");
			for(int i=0;i<top;i++)
				System.out.print(code[i]);
			System.out.println();
		}
	}
	ArrayList<node> build_heap(ArrayList<node> A)
	{
		int i;
		for(i=heap_size/2;i>=1;i--)
			A=min_heapify(A,i);
		return A;
	}
	ArrayList<node> min_heapify(ArrayList<node> A,int i)
	{
		int l,r,smallest;
		node temp;
		l=2*i;
		r=2*i+1;
		if(l<=heap_size && (A.get(l).freq)<(A.get(i).freq))
			smallest=l;
		else
			smallest=i;
		if(r<=heap_size && (A.get(r).freq)<(A.get(smallest).freq))
			smallest=r;
		if(smallest!=i)
		{
			temp=A.get(i);
			A.set(i,A.get(smallest));
			A.set(smallest,temp);
			A=min_heapify(A,smallest);
		}
		return A;
	}
	ArrayList<node> delete_min(ArrayList<node> A,int flag)
	{
		if(flag==1)
		{
			min1=A.get(1);
			A.set(1,A.get(heap_size));
			heap_size--;
			A=min_heapify(A,1);
		}
		else
			min2=A.get(1);
		return A;
	}
	ArrayList<node> insert(ArrayList<node> A,node temp)
	{
		A.set(1,temp);
		A=min_heapify(A,1);
		return A;
	}
	public static void main(String args[])
	{
		char ch;
		int freq,v;
		Scanner in=new Scanner(System.in);
		ArrayList<node> A=new ArrayList<node>();
		A.add(null);
		do
		{ 
			System.out.print("Enter character and its frequency : ");
			ch=in.next().charAt(0);
			freq=in.nextInt();
			A.add(new node(null,ch,freq,null));
			System.out.print("Enter \'1\' to continue and other key to quit : ");
			v=in.nextInt();
		}
		while(v==1);
		huff x=new huff(A.size()-1);
		node root=x.huffman_tree(A);
		System.out.print("\nCodes: \n");
		x.display(root,0);
	}
}
class node
{
	node left,right;
	char ch;
	int freq;
	node(node left,char ch,int freq,node right)
	{
		this.left=left;
		this.ch=ch;
		this.freq=freq;
		this.right=right;
	}
}