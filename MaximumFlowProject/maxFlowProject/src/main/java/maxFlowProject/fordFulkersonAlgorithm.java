package maxFlowProject;

import java.util.ArrayList;
import java.util.LinkedList;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.view.Viewer;

public class fordFulkersonAlgorithm extends Thread {

	int graph[][];
	int source;
	int target;
	int graphSize;

	public fordFulkersonAlgorithm(int graph[][], int s, int t, int size) {
		this.graph = graph;
		this.source = s;
		this.target = t;
		this.graphSize = size;
	}

	@Override
	public void run() {

		try {
			System.out.println(fordFulkerson(graph, source, graphSize - 1, graphSize));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void dfs(int[][] rGraph, int s, boolean[] visited) {
		visited[s] = true;
		for (int i = 0; i < rGraph.length; i++) {
			if (rGraph[s][i] > 0 && !visited[i]) {
				dfs(rGraph, i, visited);
			}
		}
	}

	// source'dan ��kan kayna��n sink'e kadar gidip gidemedi�ini kontrol edebilmek
	// i�in bfs algoritmas�
	static boolean bfs(int rGraph[][], int s, int t, int parent[], int size, int startGraph[][]) throws InterruptedException {
		
		// �izim i�lemi i�in ayarlamalar
	
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		Graph graphDraw = new MultiGraph("Max Flow Algorithm");
		graphDraw.setStrict(false);
		graphDraw.setAutoCreate(true);
		Viewer viewer = graphDraw.display();

		
		// ��z�mden �nce ba�lang�� graph�n� �izdirme i�lemi
		for (int i = 0; i < startGraph.length; i++) {
			for (int j = 0; j < startGraph[i].length; j++) {
				if (startGraph[i][j] > 0) {
					String string1 = String.format("%d%d", i, j);
					String string2 = String.format("%d", i);
					String string3 = String.format("%d", j);

					graphDraw.addEdge(string1, string2, string3);

					// graphDraw.addAttribute("ui.label", graphDraw.getEdgeCount());
				}
			}
		}

		graphDraw.addAttribute("ui.stylesheet",
				"node{size:50px; fill-color: orange;} edge { size:7px; fill-color: grey; }");

		for (Node node : graphDraw) {
			node.addAttribute("ui.label", node.getId());

		}

		for (int i = 0; i < graphDraw.getNodeCount(); i++) {

			if (s == Integer.parseInt(graphDraw.getNode(i).getId())) {
				graphDraw.getNode(i).addAttribute("ui.style", "size:70px; fill-color: red;");
			} else if (graphDraw.getNodeCount() - 1 == Integer.parseInt(graphDraw.getNode(i).getId())) {
				graphDraw.getNode(i).addAttribute("ui.style", "size:70px; fill-color: rgb(0,100,255);");
			}
		}
	
		

		boolean maxFlowFound = false;

		boolean visited[] = new boolean[size];

		for (int i = 0; i < size; ++i) {
			visited[i] = false;
		}

		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(s);
		visited[s] = true;
		parent[s] = -1;
		
		Thread.sleep(1200);
		// BFS d�ng�s�
		while (queue.size() != 0) {
			s = queue.poll();

			for (int v = 0; v < size; v++) {
				if (visited[v] == false && rGraph[s][v] > 0) {
					queue.add(v);
					parent[v] = s;
					visited[v] = true;
					Thread.sleep(750);
					for (int k = 0; k < graphDraw.getEdgeCount(); k++) {
						if(s == Integer.parseInt(graphDraw.getEdge(k).getNode0().getId()) && v == Integer.parseInt(graphDraw.getEdge(k).getNode1().getId())) {
							graphDraw.getEdge(k).setAttribute("ui.style", "fill-color: rgb(0,100,255);");
						}
						
					}
					
				}
			}
		}

		if (visited[t]) {
			maxFlowFound = true;
		}

		
		return (maxFlowFound);
	}

	// max flowu bulabilmek i�in ford fulkerson algoritmas�
	static int fordFulkerson(int graph[][], int s, int t, int size) throws InterruptedException {

		// �izim i�lemi i�in ayarlamalar
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		Graph graphDraw = new MultiGraph("Max Flow Algorithm");
		graphDraw.setStrict(false);
		graphDraw.setAutoCreate(true);
		graphDraw.display();

		// ��z�mden �nce ba�lang�� graph�n� �izdirme i�lemi
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[i].length; j++) {
				if (graph[i][j] > 0) {
					String string1 = String.format("%d%d", i, j);
					String string2 = String.format("%d", i);
					String string3 = String.format("%d", j);

					graphDraw.addEdge(string1, string2, string3);

					// graphDraw.addAttribute("ui.label", graphDraw.getEdgeCount());
				}
			}
		}

		graphDraw.addAttribute("ui.stylesheet",
				"node{size:50px; fill-color: orange;} edge { size:7px; fill-color: grey; }");

		for (Node node : graphDraw) {
			node.addAttribute("ui.label", node.getId());

		}

		for (int i = 0; i < graphDraw.getNodeCount(); i++) {

			if (s == Integer.parseInt(graphDraw.getNode(i).getId())) {
				graphDraw.getNode(i).addAttribute("ui.style", "size:70px; fill-color: red;");
			} else if (graphDraw.getNodeCount() - 1 == Integer.parseInt(graphDraw.getNode(i).getId())) {
				graphDraw.getNode(i).addAttribute("ui.style", "size:70px; fill-color: rgb(0,100,255);");
			}
		}

		// ba�lang��taki edge de�erlerini atama i�lemi
		// edge de�erlerini grapha atama i�lemi
		for (int k = 0; k < graphDraw.getEdgeCount(); k++) {
			int m = Integer.parseInt(graphDraw.getEdge(k).getNode0().getId());
			int n = Integer.parseInt(graphDraw.getEdge(k).getNode1().getId());
			String string3 = String.format("%d/%d", 0, graph[m][n]);

			graphDraw.getEdge(k).addAttribute("ui.label", string3);

			// System.out.println(graphDraw.getEdge(i).getLabel("20"));
		}

		// ------------------------------------------------------------------------------------------------------------

		// ��z�m i�in de�i�kenler
		int u, v;
		int rGraph[][] = new int[size][size];
		int whichPaths[][] = new int[size][size];
		ArrayList<Integer> startNode = new ArrayList<Integer>();
		ArrayList<Integer> endNode = new ArrayList<Integer>();

		// max flowun hangi yollar� kullanarak akt���n� g�sterecek olan graph
		int findPathGraph[][] = new int[size][size];

		// Reisudal graph olu�turma i�lemi
		for (u = 0; u < size; u++) {
			for (v = 0; v < size; v++) {
				rGraph[u][v] = graph[u][v];
			}
		}

		for (int i = 0; i < whichPaths.length; i++) {
			for (int j = 0; j < whichPaths[i].length; j++) {
				whichPaths[i][j] = 0;
			}
		}

		int parent[] = new int[size];

		int max_flow = 0;

		Thread.sleep(2500);
		
		
		while (bfs(rGraph, s, t, parent, size,graph)) {
			int path_flow = Integer.MAX_VALUE;
			for (v = t; v != s; v = parent[v]) {
				u = parent[v];
				path_flow = Math.min(path_flow, rGraph[u][v]);
			}

			for (v = t; v != s; v = parent[v]) { 
				u = parent[v];
				rGraph[u][v] -= path_flow;
				rGraph[v][u] += path_flow;
				Thread.sleep(1500);
				for (int k = 0; k < graphDraw.getEdgeCount(); k++) {
					if(u == Integer.parseInt(graphDraw.getEdge(k).getNode0().getId()) && v == Integer.parseInt(graphDraw.getEdge(k).getNode1().getId())) {
						graphDraw.getEdge(k).setAttribute("ui.style", "fill-color: green;");
						String string3 = String.format("%d/%d", rGraph[v][u], graph[u][v]);
						graphDraw.getEdge(k).addAttribute("ui.label", string3);
					}
					
				}
				
				whichPaths[u][v] = 1;
				whichPaths[v][u] = 1;
			}
			
			
			Thread.sleep(3500);
			for (int k = 0; k < graphDraw.getEdgeCount(); k++) {
					graphDraw.getEdge(k).setAttribute("ui.style", "fill-color: grey;");
			}

			Thread.sleep(1700);
			max_flow += path_flow;

		}

		// Hangi yollar�n kullan�ld���n� bulma i�lemi
		for (int i = 0; i < findPathGraph.length; i++) {
			for (int j = 0; j < findPathGraph[i].length; j++) {
				findPathGraph[i][j] = rGraph[i][j];
			}
		}

		int maxFlow = 0;

		// rGraphda kullan�lmayan kenarlar�n de�erini s�f�rlama i�lemi
		for (int i = 0; i < findPathGraph.length; i++) {
			for (int j = 0; j < findPathGraph[i].length; j++) {
				if (whichPaths[i][j] == 0) {
					findPathGraph[i][j] = 0;
				}
			}
		}
		
		System.out.println("start graph: ");
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph.length; j++) {
				System.out.print(graph[i][j] + " ");
			}
			System.out.println();
		}
		
		// Maximum ak�� s�ras�nda hangi yollar�n kullan�lm�� oldu�unu g�steren graph
		// modeli
		System.out.println("findPathGraph: ");
		for (int i = 0; i < findPathGraph.length; i++) {
			for (int j = 0; j < findPathGraph[i].length; j++) {
				System.out.print(findPathGraph[i][j] + " ");
			}
			System.out.println();

		}
		
		Thread.sleep(3500);
		for (int k = 0; k < graphDraw.getEdgeCount(); k++) {
			int m = Integer.parseInt(graphDraw.getEdge(k).getNode0().getId());
			int n = Integer.parseInt(graphDraw.getEdge(k).getNode1().getId());
			//Thread.sleep(700);

			if (findPathGraph[n][m] != 0 || findPathGraph[m][n] != 0) {
				graphDraw.getEdge(k).setAttribute("ui.style", "fill-color: green;");
				String string3 = String.format("%d/%d", findPathGraph[n][m], graph [m][n]);
				graphDraw.getEdge(k).addAttribute("ui.label", string3);
			}

			// System.out.println(graphDraw.getEdge(i).getLabel("20"));
		}

		//drawfoundPathGraph(graph, findPathGraph,s);

		System.out.println("rGraph: ");
		for (int i = 0; i < rGraph.length; i++) {
			for (int j = 0; j < rGraph[i].length; j++) {
				System.out.print(rGraph[i][j] + " ");
			}
			System.out.println();
		}

		boolean[] isVisited = new boolean[graph.length];
		dfs(rGraph, s, isVisited);

		System.out.println("Min cut ile kesilen yollar: ");
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph.length; j++) {
				if (graph[i][j] > 0 && isVisited[i] && !isVisited[j]) {
					System.out.println(i + " - " + j);
					startNode.add(i);
					endNode.add(j);
				}
			}
		}

		// drawMinCut(startNode, endNode, graph, findPathGraph);
		System.out.println("Max flow: ");
		return max_flow;

	}


}
