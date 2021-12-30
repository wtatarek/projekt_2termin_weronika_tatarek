package oop;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class LaunchPage implements ActionListener {
    JFrame frame= new JFrame();
    JButton magicRightButton= new JButton("magic right button");
    JButton rightButton= new JButton("right button");
    JButton magiLeftButton= new JButton("magic left button");
    JButton leftButton= new JButton("left button");
    LinkedList<Grass> grasses=new LinkedList();
    LinkedList<Animal> animals=new LinkedList();
    IWorldMap map;

    LaunchPage(LinkedList<Animal> animals, LinkedList<Grass> grasses,IWorldMap map){

        this.animals=animals;
        this.grasses=grasses;
        this.map=map;
        magicRightButton.setBounds(100,160,200,40);
        magicRightButton.setFocusable(false);
        magicRightButton.addActionListener(this);


        rightButton.setBounds(100,210,200,40);
        rightButton.setFocusable(false);
        rightButton.addActionListener(this);


        magiLeftButton.setBounds(100,260,200,40);
        magiLeftButton.setFocusable(false);
        magiLeftButton.addActionListener(this);

        leftButton.setBounds(100,310,200,40);
        leftButton.setFocusable(false);
        leftButton.addActionListener(this);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.add(magicRightButton);
        frame.add(rightButton);
        frame.add(magiLeftButton);
        frame.add(leftButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==magicRightButton){
            NewWindow myWindow= new NewWindow(animals,grasses,map,true,"right");
            }
        if(e.getSource()==rightButton){
            NewWindow myWindow= new NewWindow(animals,grasses,map,false,"right");
        }
        if(e.getSource()==magiLeftButton){
            NewWindow myWindow= new NewWindow(animals,grasses,map,true,"left");
        }
        if(e.getSource()==leftButton){ // zgadza sie
            NewWindow myWindow= new NewWindow(animals,grasses,map,false,"left");
        }

    }


}
