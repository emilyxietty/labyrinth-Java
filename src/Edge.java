//exie5
//251035713
//CS2210 Assign 5
public class Edge {
	private Node firstEndpoint, secondEndpoint;
	private int type;
	
	public Edge(Node u, Node v, int edgeType){
		this.firstEndpoint = u;
		this.secondEndpoint = v;
		this.type = edgeType;
	}
	
	public Node firstEndpoint() {
		//return the first end point node
		return firstEndpoint;
	}
	
	public Node secondEndpoint() {
		//return the second end point node
		return secondEndpoint;
	}
	
	public int getType() {
		//return the integer type of node
		return type;
	}
	
	public void setType(int newType){
		//assign new integer type value to node
		type = newType;
	}
	
	public boolean equals(Edge otherEdge){
		//return true if the first end point of this edge is equal to the first end point of the other edge
		//and the second end point of this edge is equal to the second end point of the other edge
		if (firstEndpoint.equals(otherEdge.firstEndpoint()) && secondEndpoint.equals(otherEdge.secondEndpoint())) {
			return true;
		}
		//return true if the second end point of this edge is equal to the first end point of the other edge
		//and the first end point of this edge is equal to the second end point of the other edge
		if (secondEndpoint.equals(otherEdge.firstEndpoint()) && firstEndpoint.equals(otherEdge.secondEndpoint())) {
			return true;
		}
		//otherwise the edges are not equal, return false
		return false;
	}
}
