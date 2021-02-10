package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import javax.swing.*;
import java.awt.*;

public class DirectedGraphController {
    public Canvas canvas;
    public Label label;
    public TextField vertices;
    public Label v;
    public TextField v_value;
    public Label u;
    public TextField u_value;
    public Label edge;
    public TextField edge_value;
    public Button generateButton;
    public Button addEdge;
    public Label numberLabel;
    public TextField Outdegree_value;
    public Label valueOfOutdegreeLab;
    public Label Outdegree_label;
    public Label valueOfIndegreeLab;
    public TextField Indegree_value;
    public Label Indegree_label;
    public Label lGetNeighb;
    public TextField neighbors;
    public TextArea neighborsArea;
    private DirectedGraph graph;
    private double x1,y1;
    private double x2,y2;
    private VertexPane vertexPane;
    private MainController main;

    public void initialize() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        clear(gc);
        addEdge.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String value1=v_value.getText();
                String value2=u_value.getText();
                graph.addEdge(graph.getNodeByValue(value1),graph.getNodeByValue(value2),edge_value.getText());

            }
        });
        generateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                clear(gc);
                graph.drawGraph();
            }
        });

    }
    private void clear(GraphicsContext gc) {
        gc.setFill(Color.BLUEVIOLET);
        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        label.setText("Number of vertices:");
        label.setStyle(" -fx-background-color:rgb(179, 242, 196);-fx-font-size: 14px;-fx-font-weight: bold;");
        lGetNeighb.setStyle(" -fx-background-color:rgb(179, 242, 196);-fx-font-size: 14px;-fx-font-weight: bold;");


    }
    public void mousePressed(MouseEvent mouseEvent) {
        x1 = mouseEvent.getX();
        y1 = mouseEvent.getY();
        x2 = x1;
        y2 = y1;

        JFrame f = new JFrame("Enter value");
        vertexPane=new VertexPane(graph,new Vector((float)x1,(float)y1),f);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(150, 100);
        f.getContentPane().add(vertexPane, BorderLayout.CENTER);
        f.setVisible(true);
    }

    public void onEnter(ActionEvent actionEvent) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        clear(gc);
        graph=new DirectedGraph(canvas.getWidth(),canvas.getHeight(),canvas.getGraphicsContext2D());
        numberLabel.setText(vertices.getText());
        vertices.setText("");
        v_value.setText("");
        u_value.setText("");
        edge_value.setText("");
        Outdegree_value.setText("");
        Indegree_value.setText("");
        valueOfOutdegreeLab.setText("");
        valueOfIndegreeLab.setText("");
        neighborsArea.setText("");
        neighbors.setText("");


    }
    public void init(MainController mainController) {
        main = mainController;
    }


    public void onEnterIn(ActionEvent actionEvent) {
        valueOfIndegreeLab.setText("");
        Integer degree=graph.getInDegreeOfNode(graph.getNodeByValue(Indegree_value.getText()));
        valueOfIndegreeLab.setText(degree.toString());
    }

    public void onEnterOut(ActionEvent actionEvent) {
        valueOfOutdegreeLab.setText("");
        Integer degree=graph.getDegreeOfNode(graph.getNodeByValue(Outdegree_value.getText()));
        valueOfOutdegreeLab.setText(degree.toString());
    }

    public void getNeigbors(ActionEvent actionEvent) {
        neighborsArea.setText("");

       Node a= graph.getNodeByValue(neighbors.getText());
       String verteces=new String("");
        for (Node node:graph.getNeighbors(a)
             ) {
           verteces= verteces.concat(node.getValue());
           verteces= verteces.concat(", ");
           verteces= verteces.concat("\n");
        }

        neighborsArea.setText(verteces);
    }
}
