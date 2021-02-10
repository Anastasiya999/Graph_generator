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

public class UndirectedGraphController {
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
    public Label degree_label;
    public Label valueOfdegreeLab;
    public TextField degree_value;
    public TextArea neighborsArea;
    public TextField neighbors;
    public Label lGetNeighb;
    private UndirectedGraph graph;
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
        // graph.addNode(new Node("hello",new Vector((float)x1,(float)y1)));

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
        graph=new UndirectedGraph(canvas.getWidth(),canvas.getHeight(),canvas.getGraphicsContext2D());
        numberLabel.setText(vertices.getText());
        vertices.setText("");
        v_value.setText("");
        u_value.setText("");
        edge_value.setText("");
        neighbors.setText("");
        neighborsArea.setText("");
    }
    public void init(MainController mainController) {
        main = mainController;
    }


    public void onEnter2(ActionEvent actionEvent) {
        valueOfdegreeLab.setText("");
        Integer degree=graph.getDegreeOfNode(graph.getNodeByValue(degree_value.getText()));
        valueOfdegreeLab.setText(degree.toString());
    }

    public void getNeigbors(ActionEvent actionEvent) {
        

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
