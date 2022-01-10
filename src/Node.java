//exie5
//251035713
//CS2210 Assign 5
public class Node {
	private int name;
	private boolean marked = false;
	
	public Node(int nodeName){
		//assign node the given name and keep marked false
		name = nodeName;
	}
	
	public void setMark(boolean mark){
		//assign node the given boolean value
		marked = mark;
	}
	
	public boolean getMark() {
		//return the boolean marked value
		return marked;
	}
	
	public int getName() {
		//return the integer name value
		return name;
	}
	
	public boolean equals(Node otherNode) {
		//if the name of this node is the same as the name of the other specified node
		if (name == otherNode.getName()) {
			//nodes are equal, return true
			return true;
		}
		else {
			//otherwise return false, nodes are not equal
			return false;
		}
	}
}
