package oop;

/*import agh.Vector2d;*/

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


public class Main {
    public static void main(String [] args) { //psvm
    args=new String[]{"1","1"};
    try {
        IWorldMap map;
        //args="5 5 5 5 5".split(" ") ;
        args = new String[]{"1", "1", "1","1"};
        //int[] tab=new int[args.length];
       // for(int i=0;i<args.length;i++){
            //tab[i]=Integer.parseInt(args[i]);
            //switch(args[i]):

       // }
        MoveDirection[] a = OptionsParser.parse(args);
        map = new GrassField();
        Vector2d maximum=map.getMax();
        int length=maximum.getX();
        //System.out.print("+"+length+"+");
        int width=maximum.getY();
       int animalAount=(int) (0.2*length*width);

        for(int i=0;i<=animalAount;i++)
        {
            Vector2d v=map.wylosujVector();
            int [] genes=new int[32];
            genes=map.wylosujGenotyp(genes);
            map.place(new Animal(map,v,(float) 5,genes));
        }
       // int [] genes=new int[32];
       // Vector2d v=new Vector2d(0,0);
       // map.place(new Animal(map,v,(float) 5,genes));
        ///LaunchPage launchPage=new LaunchPage();

        ((GrassField) map).startAnimation(map);


      /*  while(true) {
            MoveDirection [] directions=map.skretIPrzemieszczenie();
            map.run(directions);
        //((GrassField) map).runAnimation(animation);
        }*/
    } catch (IllegalArgumentException bad){
        System.err.println(bad.getMessage());

    }
    }
}