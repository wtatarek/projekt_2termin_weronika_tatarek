package oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class Animation extends JFrame implements ActionListener {
    LinkedList<Grass> grasses=new LinkedList();
    LinkedList<Animal> animals=new LinkedList();
    JFrame frame= new JFrame();
    //JButton button= new JButton("otwórz nowa symulację, z tymi samymi parametrami");
    public Animation(LinkedList<Animal> animals, LinkedList<Grass> grasses){
        this.animals=animals;
        this.grasses=grasses;
    }
    public void openAnimation(){
        //JFrame frame= new JFrame();
        frame.setTitle("animacja");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(900,700);
        frame.setVisible(true);
        frame.getContentPane().setBackground(new Color(0x123456));
    }
    public void run(){
        JPanel animationPanel=new JPanel();
        animationPanel.setBackground(Color.green);
        animationPanel.setBounds(0,0,700,700);

        JPanel statisticsPanel=new JPanel();
        statisticsPanel.setBackground(Color.blue);
        statisticsPanel.setBounds(700,0,900,700);


        frame.add(animationPanel);
        frame.add(statisticsPanel);



        JLabel animationLabel=new JLabel();
        animationPanel.add(animationLabel);
       // animationLabel.add(button);
        animationLabel.setHorizontalAlignment(JLabel.CENTER);
        animationLabel.setVerticalAlignment(JLabel.CENTER);

        JLabel statisticsLabel=new JLabel();
        statisticsPanel.add(statisticsLabel);
        statisticsLabel.setHorizontalAlignment(JLabel.CENTER);
        statisticsLabel.setVerticalAlignment(JLabel.CENTER);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
