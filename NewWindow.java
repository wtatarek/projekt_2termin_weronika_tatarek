package oop;



import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

public class NewWindow extends JFrame implements MouseListener {
    LinkedList<Grass> grasses=new LinkedList();
    LinkedList<Animal> animals=new LinkedList();
    JFrame frame = new JFrame();
    IWorldMap map;
    JLabel animationLabel=new JLabel(" ");
    JLabel statisticsLabel=new JLabel(" ");
    ImageIcon icon=new ImageIcon("zw.png");;
    MyPanel panel;
    Global g=new Global();
    boolean magic=false;
    JLabel animalLabel;
    boolean magicHappened=false;
    String side="left";
    //int startstop=0;
    NewWindow(LinkedList<Animal> animals, LinkedList<Grass> grasses,IWorldMap map,boolean magic,String side){
        panel=new MyPanel(animals,grasses,map,frame,magic,side);
        this.magic=magic;
        frame.add(panel);
        frame.pack();
        this.animals=animals;
        this.side=side;
        this.grasses=grasses;
        this.map=map;
        icon= new ImageIcon("zw.png");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900,700);
        frame.setLayout(null);
        frame.setVisible(true);


        JPanel animationPanel=new JPanel();
        animationPanel.setBackground(Color.green);
        animationPanel.setBounds(0,0,700,700);

        JPanel statisticsPanel=new JPanel();
        statisticsPanel.setBackground(Color.blue);
        statisticsPanel.setBounds(700,0,900,700);



       /* frame.add(animationPanel);
        frame.add(statisticsPanel);
        frame.getContentPane().setBackground(new Color(0x123456));*/

        JLabel animationLabel=new JLabel("elo ");
       // animationPanel.add(animationLabel);
        animationLabel.setHorizontalAlignment(JLabel.CENTER);
        animationLabel.setVerticalAlignment(JLabel.CENTER);
        animationLabel.setBounds(0,0,700,700);

        JLabel statisticsLabel=new JLabel(" grimes");
        //statisticsPanel.add(statisticsLabel);
        statisticsLabel.setHorizontalAlignment(JLabel.CENTER);
        statisticsLabel.setVerticalAlignment(JLabel.CENTER);
        statisticsLabel.setBounds(700,0,900,700);

        //animationLabel.setIcon(icon);
        animationLabel.setOpaque(true);
        statisticsLabel.setOpaque(true);
        animationLabel.setVisible(true);
        animationLabel.setIcon(icon);
        animationLabel.addMouseListener(this);

        frame.add(animationPanel);
        frame.add(statisticsPanel);
       // frame.getContentPane().setBackground(new Color(0x123456));
        frame.getContentPane().setBackground(new Color(0x008000));
        animationPanel.add(animationLabel);
        statisticsPanel.add(statisticsLabel);
        frame.add(animationLabel);
        frame.add(statisticsLabel);

    }

    public void changeWindow(LinkedList<Animal> animals, LinkedList<Grass> grasses){
        System.out.println("changeWindow");
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        Point pt=new Point(e.getLocationOnScreen());
     //   System.out.print(pt.x+"      .      "+pt.y+ "\n");

        JLabel animalLabel=new JLabel(" grimes");
        for (Animal a : animals){
            Vector2d v = a.getPosition();
           // System.out.print(v.x + " " + v.y + "\n");
            if(v.getY()>=0 && v.getX()>=0)
                if((pt.x>=10+10*v.getX()) && (pt.x<=10+10*v.getX()+10) && (pt.y>=30+10*v.getY()) && (pt.y<=30+10*v.getY()+10)){

                    panel.updateAnimal(a);
                    break;
                }

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
