package maxFlowProject;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.spriteManager.*;
import org.graphstream.ui.view.Viewer;

public class main implements ActionListener {

	// grafýn boyutunun kaç olduðunu belirten deðiþken
	static int graphSize;
	static int graph[][];
	static int sourceNode;

	// graf oluþumu için arayüz kýsmý
	JTextField graphSizeText = new JTextField();
	JTextField nodeSourceText = new JTextField();
	JTextField nodeTargetText = new JTextField();
	JTextField capasityText = new JTextField();
	JTextField sourceText = new JTextField();
	JLabel nodeSourceLabel = new JLabel("edge source");
	JLabel nodeTargetLabel = new JLabel("edge target");
	JLabel capasityLabel = new JLabel("capasity");
	JButton graphSizeButton = new JButton("Set Node Count");
	JButton addEdgeButton = new JButton("Add Edge");
	JButton findMaxFlow = new JButton("Find Max Flow");
	JButton findMinCut = new JButton("Find Min Cut");
	JButton example1MaxFlow = new JButton("Find Example1 Graph Max Flow");
	JButton example1MinCut = new JButton("Find Example1 Graph Min Cut");
	JButton example2MaxFlow = new JButton("Find Example2 Graph Max Flow");
	JButton example2MinCut = new JButton("Find Example2 Graph Min Cut");
	JButton setSource = new JButton("Set Source Node");
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();

	public main() {

		frame.setLayout(null);
		frame.setSize(500, 500);
		frame.setTitle("GUI  Training");
		frame.setVisible(true);

		panel.setLayout(null);
		// panel.setLocation(100, 200);
		panel.setSize(500, 500);
		panel.add(graphSizeButton);
		panel.add(graphSizeText);
		panel.add(nodeSourceText);
		panel.add(nodeTargetText);
		panel.add(addEdgeButton);
		panel.add(nodeSourceLabel);
		panel.add(nodeTargetLabel);
		panel.add(capasityText);
		panel.add(capasityLabel);
		panel.add(addEdgeButton);
		panel.add(findMaxFlow);
		panel.add(findMinCut);
		panel.add(capasityText);
		panel.add(example1MaxFlow);
		panel.add(example1MinCut);
		panel.add(example2MaxFlow);
		panel.add(example2MinCut);
		panel.add(sourceText);
		panel.add(setSource);

		graphSizeText.setBounds(180, 30, 40, 25);
		graphSizeButton.setBounds(240, 20, 130, 40);
		
		

		nodeSourceLabel.setBounds(65, 100, 90, 25);
		nodeSourceText.setBounds(70, 130, 60, 25);

		nodeTargetLabel.setBounds(160, 100, 80, 25);
		nodeTargetText.setBounds(165, 130, 60, 25);

		capasityLabel.setBounds(275, 100, 60, 25);
		capasityText.setBounds(270, 130, 60, 25);

		sourceText.setBounds(180, 180, 40, 25);
		setSource.setBounds(240, 180, 130, 25);
		
		findMaxFlow.setBounds(180, 240, 130, 25);
		findMinCut.setBounds(180, 290, 130, 25);

		
		addEdgeButton.setBounds(350, 125, 100, 30);

		example1MaxFlow.setBounds(20, 340, 210, 30);
		example1MinCut.setBounds(20, 390, 210, 30);

		example2MaxFlow.setBounds(250, 340, 210, 30);
		example2MinCut.setBounds(250, 390, 210, 30);

		frame.add(panel);

		graphSizeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				graphSize = Integer.parseInt(graphSizeText.getText());
				graph = new int[graphSize][graphSize];
				for (int i = 0; i < graph.length; i++) {
					for (int j = 0; j < graph[i].length; j++) {
						graph[i][j] = 0;
					}
				}

				for (int i = 0; i < graph.length; i++) {
					for (int j = 0; j < graph[i].length; j++) {
						System.out.print(graph[i][j]);
					}
					System.out.println();
				}
				System.out.println();
				// System.out.println(graphSize);
			}
		});
		
		setSource.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sourceNode = Integer.parseInt(sourceText.getText());
			}
		});

		addEdgeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = Integer.parseInt(nodeSourceText.getText());
				int j = Integer.parseInt(nodeTargetText.getText());
				graph[i][j] = Integer.parseInt(capasityText.getText());

				for (int m = 0; m < graph.length; m++) {
					for (int n = 0; n < graph[m].length; n++) {
						System.out.print(graph[m][n] + " ");
					}
					System.out.println();
				}
				System.out.println();
			}
		});

		findMaxFlow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = sourceNode;
				fordFulkersonAlgorithm f = new fordFulkersonAlgorithm(graph, a, graph.length-1, graph.length);
				f.start();
			}
		});

		findMinCut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawMinCut d = new drawMinCut(graph, sourceNode, graphSize - 1, graph.length);
				d.start();
			}
		});

		example1MaxFlow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int graph1[][] = new int[][] { 
					{ 0, 16, 13, 0, 0, 0 },
					{ 0, 0, 10, 12, 0, 0 },
					{ 0, 4, 0, 0, 14, 0 },
					{ 0, 0, 9, 0, 0, 20 },
					{ 0, 0, 0, 7, 0, 4 },
					{ 0, 0, 0, 0, 0, 0 } };

				fordFulkersonAlgorithm f = new fordFulkersonAlgorithm(graph1, 0, graph1.length - 1, graph1.length);
				f.start();

			}
		});

		example1MinCut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int graph1[][] = new int[][] { { 0, 16, 13, 0, 0, 0 }, { 0, 0, 10, 12, 0, 0 }, { 0, 4, 0, 0, 14, 0 },
						{ 0, 0, 9, 0, 0, 20 }, { 0, 0, 0, 7, 0, 4 }, { 0, 0, 0, 0, 0, 0 } };
				drawMinCut d = new drawMinCut(graph1, 0, graph1.length - 1, graph1.length);
				d.start();

			}
		});

		example2MaxFlow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int graph1[][] = new int[][] { { 0, 3, 2, 1, 0, 0, 0 }, { 0, 0, 0, 2, 2, 0, 0 },
						{ 0, 0, 0, 2, 0, 1, 0 }, { 0, 0, 0, 0, 4, 2, 2 }, { 0, 0, 0, 0, 0, 0, 1 },
						{ 0, 0, 0, 0, 0, 0, 2 }, { 0, 0, 0, 0, 0, 0, 0 } };

				fordFulkersonAlgorithm f = new fordFulkersonAlgorithm(graph1, 0, graph1.length - 1, graph1.length);
				f.start();

			}
		});

		example2MinCut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int graph1[][] = new int[][] { { 0, 3, 2, 1, 0, 0, 0 },
					{ 0, 0, 0, 2, 2, 0, 0 },
						{ 0, 0, 0, 2, 0, 1, 0 }, { 0, 0, 0, 0, 4, 2, 2 }, { 0, 0, 0, 0, 0, 0, 1 },
						{ 0, 0, 0, 0, 0, 0, 2 }, { 0, 0, 0, 0, 0, 0, 0 } };

				drawMinCut d = new drawMinCut(graph1, 0, graph1.length - 1, graph1.length);
				d.start();

			}
		});

	}

	public static void main(String[] args) throws InterruptedException {

		new main();

		// System.out.println(fordFulkerson(graph, 0, 5, graph.length));

	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
