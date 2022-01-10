import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class Solver {
	
	private Graph graph;
	
	private int S; //scale factor
	private int W; //the width of the labyrinth
	private int L; //length of the labyrinth
	private String pathOfVICTORY; //what type of square is it
    private ArrayList<String> B; //Holds all the stuff for the labyrinth
    
    private int K1;
    private int K2;
    
    private int entrance;
    private int exit;

	public Solver(String inputFile) throws LabyrinthException, GraphException {
		graph = null;
		try {
			Scanner input = new Scanner(new File(inputFile));
			readInputFile(input);
			input.close();
		} catch (FileNotFoundException e) {
			throw new LabyrinthException(inputFile);
		}
	}

	private void readInputFile(Scanner input) throws GraphException {
		S = Integer.parseInt(input.nextLine());
		W = Integer.parseInt(input.nextLine());
		L = Integer.parseInt(input.nextLine());
		int numNodes = W * L;
		graph = new Graph(numNodes);
		
		B = new ArrayList<>();
        K1 = Integer.parseInt(input.nextLine());
        K2 = Integer.parseInt(input.nextLine());
		
		int lRange = L * 2 - 1;
		int wRange = W * 2 - 1;
		
		char[][] laby = new char[lRange][wRange];
		for (char[] labyChar : laby) {
			String line = input.nextLine();
			for (int j = 0; j < labyChar.length; j++) {
				labyChar[j] = line.charAt(j);
			}
		}
		//laby (of lab2)
		//[[e,-,o,-,o,m,o,-,o],
		//[|,*,|,*,B,*,B,*,R],
		//[o,-,o,b,o,b,o,-,o],
		//[B,*,|,*,|,*,B,*,M],
		//[o,-,o,b,o,b,x,-,o]]

		//[[e,-,o,-,o,m,o,-,o], [|,*,|,*,B,*,B,*,R], [o,-,o,b,o,b,o,-,o], [B,*,|,*,|,*,B,*,M], [o,-,o,b,o,b,x,-,o]]


		//EMILY START HERE

		int name = 0;
		
		for (int i = 0; i < laby.length; i += 1) {
			char[] tempArray = laby[i];
			for (int j = 0; j < tempArray.length; j += 1) {
				Node node = graph.getNode(name);
				if (laby[i][j] == 'e') {
					entrance = name;
				}
				else if (laby[i][j] == 'x') {
					exit = name;
				}
				
				if (i < lRange - 1) {
                    Node down = graph.getNode(name);
                    int yIndex = i+1;
                    while(laby[yIndex][j] != '|' && yIndex < L) {
						yIndex++;
					}

                    if (Character.isLetter(laby[yIndex][j]) && yIndex < L) {
                        graph.insertEdge(node, down, laby[yIndex][j]);
                    }
                }
				
				if (j < wRange - 1) {
                    Node right = graph.getNode(name);
					int xIndex = j+1;
					while(laby[i][xIndex] != '-' && xIndex < W) {
						xIndex++;
					}

                    if (Character.isLetter(laby[i][xIndex] ) && xIndex < W) {
                        graph.insertEdge(node, right, laby[i][xIndex]);
                    }
                }
				
				name++;
			//EMILY END HERE
			}
		}
	}
	
	public Graph getGraph() throws GraphException {
		if (graph == null) {
			throw new GraphException("The Graph does not exist");
		}
		return graph;
	}
	
	public Iterator solve() throws GraphException {
		for (int i = 0; i < W * L; i += 1) {
			graph.getNode(i).setMark(false);
		}
		
		pathOfVICTORY = "";
		
		Stack<Node> path = new Stack<>();
		
		Node u = graph.getNode(entrance);
		u.setMark(true);
		
		path.push(u);
		
		if (dfs(path)) {
			return path.iterator();
		}
		return null;
	}
		
	
	private boolean dfs(Stack<Node> path) throws GraphException{
		Node u = path.peek();
		Iterator<Edge> iter = (Iterator<Edge>)graph.incidentEdges(u);
		if (iter == null) {
			return false;
		}
		while (iter.hasNext()) {
			Node n = iter.next().secondEndpoint();
			if (!n.getMark()) {
				n.setMark(true);
				try {
					Edge tempPath = graph.getEdge(u, n);
					
					int pathType = tempPath.getType();
					
					if (B.contains(pathType)) {
						if (pathOfVICTORY.isEmpty()) {
							pathOfVICTORY = Integer.toString(pathType);
						}
						else {
							if (!pathOfVICTORY.equals(pathType)) {
								return false;
							}
						}
					}
				}
				catch (GraphException ignored) {}
				path.push(n);
				if (n.getName() == exit) {
					return true;
				}
				if (dfs(path)) {
					return true;
				}
				
				path.pop();
			}
		}
		return false;
	}
}
