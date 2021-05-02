package bfs_shortestpath;
import java.util.*;
public class BFS_ShortestPath {
static Vector<String > InputValue = new Vector<>();
static Vector<Integer> PathCost = new Vector<>();
static class Node
{
    String key;
    int weight;
    Vector<Node >child_node = new Vector<>();
};

static Node newNode(String key, int weight)
{
    Node temp = new Node();
    temp.key = key;
    temp.weight = weight;
    return temp;
}
 
static void BFS_Node_Visit(Node root)
{
    if (root == null)
        return;
    Queue<Node > q = new LinkedList<>();
    q.add(root); 
    while (!q.isEmpty())
    {
        int n = q.size();
 
        // If this node has children
        while (n > 0)
        {
            Node p = q.peek();
            q.remove();
            if(p.key.contains("I")){        
                String s = "";
                for(int i = p.key.length() - 1; i>=0; i--){
                    s += p.key.charAt(i);
                }
                InputValue.add(s + p.weight);
            }

            for (int i = 0; i < p.child_node.size(); i++){
                p.child_node.get(i).key += p.key;
                p.child_node.get(i).weight += p.weight;
                q.add(p.child_node.get(i));
            }
            n--;
        }
         
    }
    for(String s:InputValue){
        int indexOfI = s.indexOf("I");
        String sb = s.substring(indexOfI + 1, s.length());
        int totalCost = Integer.parseInt(sb);
        PathCost.add(totalCost);
    }

    int min = PathCost.get(0);
    for(int i = 1; i < PathCost.size(); i++){
        if(min > PathCost.get(i)){ min = PathCost.get(i);}
    }
    for(String s : InputValue){
        if(s.contains(Integer.toString(min))){
           System.out.println(s);
        }
    }
        
}
 
public static void main(String[] args)
{

    Node root = newNode("A", 0);
    (root.child_node).add(newNode("B", 6));
    (root.child_node).add(newNode("C", 9));
    (root.child_node).add(newNode("D", 11));
    (root.child_node.get(0).child_node).add(newNode("E", 8));
    (root.child_node.get(1).child_node).add(newNode("F", 19));
    (root.child_node.get(1).child_node.get(0).child_node).add(newNode("I", 4));
    (root.child_node.get(1).child_node).add(newNode("G", 10));
    (root.child_node.get(2).child_node).add(newNode("H", 12));
    (root.child_node.get(2).child_node.get(0).child_node).add(newNode("I", 7));
    (root.child_node.get(2).child_node.get(0).child_node).add(newNode("J", 9));
 
    System.out.print("BFS Shortest Path is: ");
    BFS_Node_Visit(root);
}
}
