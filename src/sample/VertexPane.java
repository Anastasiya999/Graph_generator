package sample;

import javafx.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.awt.*;


public class VertexPane extends JPanel {
    private GraphAbstr graph;
    private String value="";
    private Vector point;
    private JTextField vertex_value;
    private JLabel label;



    public VertexPane(GraphAbstr graph,Vector point,JFrame f)
    {
        this.graph=graph;
        this.point=point;
        label=new JLabel();
        label.setText("Enter vertex value:");
        label.setFont(new Font(Font.SERIF,Font.ITALIC,14 ));
        vertex_value=new JTextField();
        setLayout(new BorderLayout());
        vertex_value.setFont(new Font(Font.SERIF,Font.BOLD,14 ));
        add(label,BorderLayout.NORTH);
        add(vertex_value, BorderLayout.CENTER);
        vertex_value.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                value = vertex_value.getText();
                graph.addNode(new Node(value,point));
                f.setVisible(false);
                f.dispose();

            }
        });


    }

}
