//exie5
//251035713
//CS2210 Assign 5
import java.util.ArrayList;

public class Graph implements GraphADT{
	private Edge[][] matrix;
	private Node[] nodeArray;
	private int numNodes;
	
	private ArrayList<Edge> edges;
	
	public Graph(int n){
		numNodes = n;
		nodeArray = new Node[n];
		matrix = new Edge[n][n];
		
		//while the number of nodes created is smaller than the length of nodes in the array
		for (int i = 0; i < nodeArray.length; i++) {
			//create a array with the given node set at the increasing integer i
			nodeArray[i] = new Node(i);
        }
	}
	
	public void insertEdge(Node nodeu, Node nodev, int type) throws GraphException{
		//if the name of either node is greater than the node count, it is not in the matrix, and will not have an edge
		if (nodeu.getName() >= numNodes || nodev.getName() >= numNodes) {
			//throw graph exception
			throw new GraphException("1 or more nodes missing");
		}
		//if there is already an edge connecting the 2 given nodes
		else if (matrix[nodeu.getName()][nodev.getName()] != null && matrix[nodev.getName()][nodeu.getName()] != null) {
			//throw graph exception
			throw new GraphException("an edge already exists for these 2 nodes");
		}
		else {
			//otherwise, create new edge variable connecting the 2 given nodes with given type
			Edge newEdge = new Edge(nodeu, nodev, type);
			//assign edge value to the 2 nodes
			matrix[nodeu.getName()][nodev.getName()] = newEdge;
			matrix[nodev.getName()][nodeu.getName()] = newEdge;
		}
	}

	public Node getNode(int name) throws GraphException{
		//if the name is greater than the node count, it is not in the matrix
		if (name > numNodes) {
			//throw graph exception
			throw new GraphException("node with given name is not in matrix");
		}
		else {
			//otherwise it is in matrix, return the node with the name
			return nodeArray[name];
		}
	}

	public ArrayList<Edge> incidentEdges(Node u) throws GraphException{
		//create new array to collect edges
        edges = new ArrayList<Edge>();
        //create variable with name of node u
        int row = u.getName();
        //for each matrix row, check the edge
        for (Edge newEdge : matrix[row]) {
        	//if there is an edge
            if (newEdge != null) {         
            	//add edge to the edge array
                edges.add(newEdge);
            }
        }
        //if there are no edges in the edge array
        if (edges.isEmpty()) {
        	//return null
            return null;
        }
        //return the list of edges
        return edges;
	}
	
	public Edge getEdge(Node u, Node v) throws GraphException{
		//if the name of either node is greater than the node count, it is not in the matrix, and will not have an edge
		if (u.getName() > numNodes || v.getName() > numNodes) {
			//throw graph exception
			throw new GraphException("at least 1 node is not in matrix");
		}
		//if there is no edge between the two nodes
		else if (matrix[u.getName()][v.getName()] == null && matrix[v.getName()][u.getName()] == null) {
			//throw graph exception
			throw new GraphException("edge does not exist");
		}
		else {
			//otherwise return the edge connecting the 2 nodes
			return matrix[u.getName()][v.getName()];
		}
	}

	public boolean areAdjacent(Node u, Node v) throws GraphException{
		//if the name of either node is greater than the node count, it is not in the matrix, and will not have an edge
		if (u.getName() >= numNodes || v.getName() >= numNodes) {
			//throw graph exception
			throw new GraphException("at least 1 node is not in matrix");
		}
		//if there is an edge between the two nodes
		else if (matrix[u.getName()][v.getName()] != null && matrix[v.getName()][u.getName()] != null) {
			//the nodes are adjacent, return true
			return true;
		}
		else {
			//otherwise there is no edge connecting the nodes, they are not adjacent, return false
			return false;
		}
	}
}
