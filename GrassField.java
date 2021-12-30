package oop;



import javax.swing.*;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class GrassField extends AbstractWorldMap{
    int n;
    LinkedList<Grass> trawy=new LinkedList();
    int length=70;
    int width=70;
    public int getLength(){
        return this.length;
    }
    public LinkedList<Grass> getGrasses(){
        return trawy;
    }
    public void removeFromGrasses(Grass g){
        this.trawy.remove(g);
    }
    public int getWidth(){
        return this.width;
    }
    public GrassField() {
        super();
        trawy=new LinkedList();

        this.min=new Vector2d(0,0);
        this.max=new Vector2d(this.length, this.width);

        int max=Math.max(this.length,this.width);
        this.n=(int) (1.2*max);
        // zatrzymuje sie na 4 bo nie ma juz gdzie wsadzic
        int a= (int) Math.sqrt(max);
        //System.out.println(a);
        Random generator = new Random();
        // generator srodka
        while(n>0) {
            //int a2=(int) 2*a;
            int a2=20;
            int x = generator.nextInt(a2);
            int y = generator.nextInt(a2);
            //System.out.println(x+y);
            Vector2d v=new Vector2d(x+25,y+25);
            int contr=1;
            for(Grass trawa : trawy)
            {
                //System.out.println(trawa.getPosition().toString());
                if(v.equals(trawa.getPosition())){
                    //System.out.println("<3");
                    contr=0;
                    break;
                }
            }
            if(contr==1)
            {
                Grass g=new Grass(v);
                trawy.add(g);
                n--;
            }

        }
        n=(int) Math.sqrt(max)*(max/2);

        addGrasses(n);
    }

    public void addGrasses(int n){
        Random generator = new Random();
        while(n>0) {
            int x1 = generator.nextInt(this.length);
            int y1 = generator.nextInt(this.width);
            int ctr1=generator.nextInt(2);
            int ctr2=generator.nextInt(2);
            Vector2d v=new Vector2d(x1,y1);
            int contr=1;
            for(Grass trawa : trawy) {
                if(v.equals(trawa.getPosition())){
                    contr=0;
                    break;
                } }
            if(contr==1)
            {
                Grass g=new Grass(v);
                trawy.add(g);
                n--; }
        }


    }
    @Override
    public Object objectAt(Vector2d position){
        Object ret=super.objectAt(position);
        // animal
        if (ret != null) return ret;
        for(Grass i: trawy){
            Vector2d v=i.getPosition();
            if(v.equals(position)){
                return i;
            }
        }
        return null;
    }

    @Override
    public boolean isOccupied(Vector2d position){
        if(!super.isOccupied(position)){
            for(Grass i : trawy){
                Vector2d v=i.getPosition();
                if(v.equals(position)){
                    return true;
                }
            }
            return false;
        }
        return true;
    }
    public void startAnimation(IWorldMap map){

        LaunchPage launchPage=new LaunchPage(animals,trawy,map);
    }
    public void leftFive(){
        LinkedList<Animal> tmp=animals;
        for(Animal a:tmp){
            Vector2d v=this.wylosujVector();
            this.place(new Animal(this,v,(float) 5,a.getGenes()));
            System.out.print("dodano 5 zwierząt do mapy");
        }

    }

}
