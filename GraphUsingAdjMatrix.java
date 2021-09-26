package graphs;
import java.awt.BorderLayout;
import java.util.*;
/*
* Graph and its algorithms implementation using Adjacency matrix.
* GraphUsingAdjMatrix ,BFS ,DFS ,Detect cycle in a Graph ,Topological Sorting ,Dijkstra's algorithm ,Bellman-Ford algorithm ,Floyd-Warshall algorithm ,
* Kosaraju's algorithm, Shortest path using BFS.
*/
public class GraphUsingAdjMatrix {
    int[][] adjmatrix;
    public GraphUsingAdjMatrix(int n) {
        adjmatrix=new int[n][n];
    }
    
    public void addEdgeToMatrix(int i,int j,int weight){
        adjmatrix[i][j]=weight;
    }
    
   public void addEdge(int i,int j){
       adjmatrix[i][j]=1;
   }
    
    public boolean containsCycle(){
        return false;
    }
    
    public void DFS(boolean[] visited,int i){
        visited[i]=true;
        System.out.print(i+" ");
        for(int j=0;j<adjmatrix[i].length;j++){
            if(adjmatrix[i][j]==1 && !visited[j]){
                DFS(visited, j);
            }
        }
    }
    
    public void BFS(boolean[] visit,int i){
        System.out.println("BFS of a given Graph: ");
        visit[i]=true;
        Queue<Integer> queue=new LinkedList<>();
        queue.add(i);
        while(!queue.isEmpty()){
            int e=queue.poll();
            System.out.print(e+" ");
            for(int j=0;j<adjmatrix[e].length;j++){
                if(adjmatrix[e][j]==1 && !visit[j]){
                    visit[j]=true;
                    queue.add(j);
                }
            }
        }
        System.out.println();
    }
    
    public void distanceUsingBFS(int start,int dest,int V){
        boolean[] visit=new boolean[V];
        visit[start]=true;
        int[] dist=new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Queue<Integer> q=new LinkedList();
        dist[0]=0;
        q.add(start);
        while(!q.isEmpty()){
            int u=q.poll();
            for(int i=0;i<adjmatrix[u].length;i++){
                if(adjmatrix[u][i]==1 && !visit[i]){
                    visit[i]=true;
                    dist[i]=dist[u]+1;
                    q.add(i);
                }
            }
        }
        System.out.println("Shortest path is: "+dist[dest]);
    }
    
    public void topologicalSorting(boolean[] visited,Stack<Integer> stack,int i){
        /*Topologivcal sorting will work for DAG(Directed Acyclic Graph) only.
        Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices 
        such that for every directed edge u v, vertex u comes before v in the ordering. 
        Topological Sorting for a graph is not possible if the graph is not a DAG.*/
        visited[i]=true;
        for(int j=0;j<adjmatrix[i].length;j++){
            if(adjmatrix[i][j]==1 && !visited[j]){
                topologicalSorting(visited, stack, j);
            }
        }
        stack.push(i);
    }
    
    public void topoSort(int n){
        if(isCyclic(n)){
            return;
        }
        Stack<Integer> stack=new Stack<>();
        boolean[] visited=new boolean[adjmatrix.length];
        for(int i=0;i<visited.length;i++){
            visited[i]=false;
        }
        for(int i=0;i<adjmatrix.length;i++){
            if(!visited[i]){
                topologicalSorting(visited,stack,i);
            }
        }
        System.out.println("Topological ordering of the given graph is:");
        while(!stack.isEmpty()){
            System.out.print(stack.pop()+" ");
        }System.out.println();
    }
    
    public int minKey(int[] key,boolean[] mstSet){
        int min_in=-1,min=Integer.MAX_VALUE;
        for(int i=0;i<key.length;i++){
            if(!mstSet[i] && key[i]<min){
                min_in=i;
                min=key[i];
            }
        }
        return min_in;
    }
    
    public void primsMST(int n){
        int[] parent=new int[n];
        boolean mstSet[]=new boolean[n];        
        int[] key=new int[n];
        for(int i=0;i<n;i++){
            key[i]=Integer.MAX_VALUE;
            mstSet[i]=false;
        }
        parent[0]=-1;
        key[0]=0;
        for(int i=0;i<n;i++){
            int u=minKey(key,mstSet);
            mstSet[u]=true;
            for(int v=0;v<n;v++){
                if(adjmatrix[u][v]!=0 && !mstSet[v] && adjmatrix[u][v]<key[v]){
                    parent[v]=u;
                    key[v]=adjmatrix[u][v];
                }
            }
        }
        System.out.println("Prim's Algorithm for MST:");
        for(int i=1;i<n;i++){
            System.out.println(parent[i]+"--->"+i+"= "+adjmatrix[i][parent[i]]);
        }
    }
    
    public boolean isCyclic(int n){
        boolean[] visited=new boolean[n];
        boolean[] reStack=new boolean[n];
        for(int i=0;i<n;i++){
            if(detectCycle(i,visited,reStack)){
                return true;
            }
        }
        return false;
    }
    
    public boolean detectCycle(int v,boolean[] visited,boolean[] reStack){
        if(reStack[v]){
            return true;
        }
        if(visited[v]){
            return false;
        }
        visited[v]=true;
        reStack[v]=true;
        for(int j=0;j<adjmatrix[v].length;j++){
            if(adjmatrix[v][j]==1 && detectCycle(j,visited,reStack)){
                return true;
            }
        }
        reStack[v]=false;
        return false;
    }
    
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        sc.nextLine();
        GraphUsingAdjMatrix d=new GraphUsingAdjMatrix(n);
        String s=sc.nextLine();
        String[] ss=s.split(" ");       
        for(int i=0;i<ss.length-1;){
            int u=Integer.parseInt(ss[i]);
            int v=Integer.parseInt(ss[i+1]);i+=2;
            //int w=Integer.parseInt(ss[i+2]);i+=3;
            d.addEdge(u, v);
        }
        System.out.println("Directed Graph(Adjacency matrix):");
        System.out.print("    ");
         for(int i=0;i<n;i++){
             if(i<n-1){
                 System.out.print(i+"--");
             }
             else{
                 System.out.print(i);
             }
        }System.out.println();
        for(int i=0;i<n;i++){
            System.out.print(i+"-->");
            for(int j=0;j<n;j++){
                System.out.print(d.adjmatrix[i][j]+"  ");
            }System.out.println();
        }
       d.topoSort(n);
    }
}
