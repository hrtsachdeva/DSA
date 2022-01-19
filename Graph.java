import java.util.Iterator;
import java.util.LinkedList;


public class Graph{
    int V;
    LinkedList<Integer> adj[];

    Graph(int v){
        this.V = v;
        this.adj = new LinkedList[v];

        for(int i=0;i<v;i++){
            this.adj[i]= new LinkedList<Integer>();
        }
    }

    public void addEdge(int v, int w){
        this.adj[v].add(w);
    }

    public void bfs(int s){
        boolean visited[] = new boolean[V];
        LinkedList<Integer> queue = new LinkedList<Integer>();


        visited[s] =true;
        queue.add(s);

        while(queue.size()!=0){
            s = queue.poll();

            System.out.println(s);
            Iterator<Integer> itr = adj[s].listIterator();

            while(itr.hasNext()){
                int n = itr.next();
                if(!visited[n]){
                    visited[n]= true;
                    queue.add(n);
                }
            }
        }
        
    }

    public void dfsUtil(int s, boolean[] visited){
        visited[s]= true;
        System.out.println(s);

        Iterator<Integer> i = adj[s].listIterator();

        while(i.hasNext()){
            int j = i.next();
            if(!visited[j]){
                dfsUtil(j, visited);
            }
        }


    }

    public void dfs(int s){
        boolean[] visited = new boolean[this.V];

       

        dfsUtil(s,visited);
    }
    

    public static void main(String[] args) {
        Graph g = new Graph(5);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);



        g.bfs(2);

        g.dfs(2);


    }
}