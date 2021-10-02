package graphs;
import java.awt.BorderLayout;
import java.util.*;
/*
* Graph and its algorithms implementation using Adjacency matrix.
* GraphUsingAdjMatrix ,BFS ,DFS ,Detect cycle in a Graph ,Topological Sorting ,Dijkstra's algorithm ,Bellman-Ford algorithm ,Floyd-Warshall algorithm ,
* Kosaraju's algorithm, Shortest path using BFS.
*/
class GEdge implements Comparable<GEdge>{
    int src,destin,weight;
    @Override
    public int compareTo(GEdge e1){
        return this.weight-e1.weight;
    }
}
public class GraphUsingAdjMatrix {
    int[][] adjmatrix;
    public GraphUsingAdjMatrix(int n) {
        adjmatrix=new int[n][n];
    }
    
    public GraphUsingAdjMatrix(){}
    
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
    
    public void dijktrasAlgorithm(int n){
        int[] dist=new int[n];
        boolean[] visited=new boolean[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[0]=0;
        Arrays.fill(visited,false);
        for(int i=0;i<n;i++){
            int u=minKey(dist, visited);
            visited[u]=true;
            for(int v=0;v<n;v++){
                if(!visited[v] && adjmatrix[u][v]!=0 && dist[u]!=Integer.MAX_VALUE && dist[u]+adjmatrix[u][v]<dist[v]){
                    dist[v]=dist[u]+adjmatrix[u][v];
                }
            }
        }
        for(int i=0;i<n;i++){
            System.out.println(i+"----->"+dist[i]);
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
    
    public int find(SubSet[] subSet,int v){
        if(subSet[v].parent==-1){
            subSet[v].parent=find(subSet,subSet[v].parent);
        }
        return subSet[v].parent;
    }
    
    public void union(int fromP,int toP,SubSet[] subSet){
        if(subSet[fromP].rank>subSet[toP].rank){
            subSet[toP].parent=fromP;
        }
        else if(subSet[fromP].rank<subSet[toP].rank){
            subSet[fromP].parent=toP;
        }
        else{
            subSet[fromP].parent=toP;
            subSet[toP].rank+=1;
        }
    }
    
    public void kruskalsAlgo(GEdge[] edgeList,int n){
        GEdge[] result=new GEdge[n];
        SubSet[] subSets=new SubSet[n];
        for(int i=0;i<n;i++){
            result[i]=new GEdge();
        }
        for(int i=0;i<n;i++){
            subSets[i]=new SubSet();
            subSets[i].parent=i;
            subSets[i].rank=0;
        }
        Arrays.sort(edgeList);
        int i=0,e=0;
        while(e<n-1){
            GEdge next=edgeList[i++];
            int fromP=find(subSets,next.src);
            int toP=find(subSets,next.destin);
            if(fromP!=toP){
                result[e++]=next;
                union(fromP,toP,subSets);
            }
        }
        int min=0;
        for(int j=0;j<e;j++){
            System.out.println(result[j].src+"--->"+result[j].destin+"===="+result[j].weight);
            min+=result[j].weight;
        }
        System.out.println("Minimum Cost MST is: "+min);
    }
    
    
    
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        sc.nextLine();
        GraphUsingAdjMatrix d=new GraphUsingAdjMatrix(n);
//        String ss=sc.nextLine();
//        String[] sp=ss.split(" ");
//        GEdge[] edgeList=new GEdge[n+1]; 
//        int j=0;
//        for(int i=0;i<sp.length-1;i+=3){
//            int from=Integer.parseInt(sp[i]);
//            int to=Integer.parseInt(sp[i+1]);
//            int weight=Integer.parseInt(sp[i+2]);
//            edgeList[j]=new GEdge();
//            edgeList[j].src=from;
//            edgeList[j].destin=to;
//            edgeList[j].weight=weight;j++;     
//        }
//        d.kruskalsAlgo(edgeList,n);
        String s=sc.nextLine();
        String[] ss=s.split(" ");       
        for(int i=0;i<ss.length;){
            int u=Integer.parseInt(ss[i]);
            int v=Integer.parseInt(ss[i+1]);
            int w=Integer.parseInt(ss[i+2]);i+=3;
            d.addEdgeToMatrix(u,v,w);
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
        d.dijktrasAlgorithm(n);
       //d.topoSort(n);
    }
}
