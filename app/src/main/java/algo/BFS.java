package algo;

import java.util.*;

public  class BFS {

  // A -> B,C

  // C -> []

  // B -> D

  //D -> []


    public static   void main(String[] args) {

      GraphNode A = new GraphNode('A');

      GraphNode B = new GraphNode('B');

      GraphNode C = new GraphNode('C');

      GraphNode D = new GraphNode('D');
      GraphNode E = new GraphNode('E');

      GraphNode F = new GraphNode('F');

      GraphNode G = new GraphNode('G');

      A.addChild(B);
      A.addChild(C);
      B.addChild(D);
      B.addChild(E);

      C.addChild(F);
      C.addChild(G);

      performBFS(A);

    }

  private static void performBFS(GraphNode root){

      Queue<GraphNode> toBeExplored  = new LinkedList<>();

      Set<GraphNode> visited =  new HashSet<>();

      if (root!= null) toBeExplored.add(root);

      while(!toBeExplored.isEmpty()){
          GraphNode currentNode = toBeExplored.poll();
          System.out.println(currentNode.name);

           for (GraphNode g : currentNode.getChildren()) {
              toBeExplored.add(g);

              if (!visited.contains(currentNode)) visited.add(g);


              //System.out.println(g.name);
          }

      }


  }

    static class GraphNode{
        char name ;
        ArrayList<GraphNode>  children = new ArrayList<>();

        GraphNode(char name){
            this.name =  name;
        }

        ArrayList<GraphNode> getChildren(){

            return children;
        }

        void addChild(GraphNode g){

            children.add(g);
        }
    }




}
