package oop;



import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.File;


public class MyPanel extends JPanel implements ActionListener, KeyListener, MouseListener {
    final int PANEL_WIDTH = 1100;
    final int PANEL_HEIGHT = 700;
    Timer timer;
    LinkedList<Grass> grasses = new LinkedList();
    LinkedList<Animal> animals = new LinkedList();
    public int fl=0;
    //int startstop = 0;
    IWorldMap map;
    Global g=new Global();
    JFrame frame;
    Animal a=null;
    int startNumber=0;
    String maxGene="";
    boolean drawDominatingGenes=false;
    public boolean magic=false;
    int allAnimalsWithDominantingGenes = 0;
    String side="left";

    MyPanel(LinkedList<Animal> animals, LinkedList<Grass> grasses, IWorldMap map,JFrame frame,boolean magic,String side) {
        this.animals = animals;
        this.grasses = grasses;
        this.map = map;
        this.magic=magic;
        this.frame=frame;
        this.side=side;
        frame.addKeyListener(this);
        frame.addMouseListener(this);
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        timer = new Timer(2, this);
        timer.start();
        this.maxGene= animals.get(0).genesToString();
    }




    public String dominatingGenes(){

        int count=0;
        String s="";
        for(Animal a:animals){
            if(a.ile_zwierzat_ma_geny_co_ja>=count){
                count=a.ile_zwierzat_ma_geny_co_ja;
                s=a.genesToString();


            }
        }
        return s;
    }

    public String averageEnergyLevel(){
        float allEnergy=0;
        for(Animal a:animals){
            allEnergy+=a.getEnergy();
        }
        if(animals.size()!=0){
            return String.valueOf(allEnergy/animals.size());
        }
        return "there is no animals";
    }

    public String averageNumberOfChildren(){
        int x=0;
        for(Animal a: animals){
            x+=a.numberOfChildren;
        }
        if(animals.size()!=0){
            return String.valueOf(x/animals.size());
        }
        return "0";
    }
    public void updateAnimal(Animal a){
        this.a=a;
        this.startNumber=a.numberOfChildren;
    }

    public String lifeExpectancy(){
        int numberOfDeadAnimals=map.getNumberOfDeadAnimals();
        int ageOfDeadAnimals=map.getAgeOfDeadAnimals();
        if(numberOfDeadAnimals!=0){
            return String.valueOf(ageOfDeadAnimals/numberOfDeadAnimals);
        }
        return "no dead animals";

    }
    /*public void paintAnimalAtributs(Animal a,Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setPaint(Color.black);
        g2D.setStroke(new BasicStroke(5));
        g2D.drawString( " geny zwierzęcia: ",710,270);}*/

    public void zapiszDoPliku() throws IOException {
         File f=new File("plik.txt");
         if(!f.exists())
         {
             try{
                 f.createNewFile();
                 System.out.print("Plik został utworzony");
             }catch(Exception e){
                 System.out.print(e.getMessage());
             }
         }
         if(f.canWrite()){
             try{
                 FileWriter fw = new FileWriter(f,true);
                 Formatter fm = new Formatter(fw);
             /* //   Scanner sc= new Scanner(System.in);*/
                 Scanner sf = new Scanner(f);

                // String tekst = sc.nextLine();*/
                 String tekst = "pliczek";
                 fm.format("%sm\r\n",""+fl);
                 Random generator = new Random();
                 Eras e=new Eras();
                 String s=e.getEra(generator.nextInt(13));
                 fm.format("%sm\r\n","THE ERA OF "+ s);
                 fm.format("%sm\r\n","number of animals:");
                 fm.format("%sm\r\n",String.valueOf(animals.size()));
                 fm.format("%sm\r\n","number of plants:");
                 fm.format("%sm\r\n",String.valueOf(grasses.size()));
                 fm.format("%sm\r\n","dominating genes:");
                 fm.format("%sm\r\n",""+dominatingGenes());
                 fm.format("%sm\r\n","average energy level for living animals:");
                 fm.format("%sm\r\n",averageEnergyLevel());
                 fm.format("%sm\r\n","average number of children for living animals:");
                 fm.format("%sm\r\n",averageNumberOfChildren());
                 fm.format("%sm\r\n","life expectancy for dead animals:");
                 fm.format("%sm\r\n",lifeExpectancy());
                 this.fl+=1;


                 fm.close();
                 fw.close();
               /*  while(sf.hasNextLine()){

                 } */
            //     while(sf.has)

             }catch(Exception e){
                 System.out.print(e.getMessage());
             }
         }

    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;

        g2D.setPaint(Color.black);
        g2D.setStroke(new BasicStroke(5));
        g2D.setPaint(new Color(0x008000));
        for (int i = 0; i <= 700; i += 10) {
            for (int j = 0; j <= 700; j += 10) {
                //System.out.println(j+" "+i+" "+(j+10)+" "+(i+10));
                g2D.fillOval(j, i, 10, 10);
            }
        }
        for (Animal a : animals) {
            float energy = a.getEnergy();
          /*  System.out.print(" elo pozycje");
            System.out.println(10 * a.getPosition().getX() + " " + 10 * a.getPosition().getY() +"\n");
            System.out.print(" koniec pozycji  \n");*/
            if (energy <= 1) {
                g2D.setPaint(new Color(0xFF9999));
            }
            if (energy > 1 && energy < 2) {
                g2D.setPaint(new Color(0xFF3333));
            }
            if (energy >= 2 && energy < 3) {
                g2D.setPaint(new Color(0xCC0000));
            }
            if (energy >= 4) {
                g2D.setPaint(new Color(0x660000));
            }
            Vector2d v = a.getPosition();
            g2D.fillOval(10 * v.getX(), 10 * v.getY(), 10, 10);

        }
        for (Grass t : grasses) {
            Vector2d v = t.getPosition();
            g2D.setPaint(new Color(0x00FF80));
            g2D.fillOval(10 * v.getX(), 10 * v.getY(), 10, 10);
        }
        g2D.setPaint(Color.black);
        //g2D.fillOval(710,0,100,100);
        g2D.drawString("number of animals:",710,10);
        g2D.drawString(String.valueOf(animals.size()),710,20);
        g2D.drawString("number of plants:",710,40);
        g2D.drawString(String.valueOf(grasses.size()),710,50);
        g2D.drawString("dominating genes:",710,70);
        String dominatingGenes = ""+dominatingGenes();
       /* for(int i=0;i<dominatingGenes.size();i++){
            g2D.drawString(String.valueOf(dominatingGenes.get(i)),710+10*i,80);
        }*/
        String d=dominatingGenes;
        g2D.drawString(d,710,80);
        g2D.drawString("average energy level for living animals:",710,100);
        g2D.drawString(averageEnergyLevel(),710,110);
        g2D.drawString("average number of children for living animals:",710,130);
        g2D.drawString(averageNumberOfChildren(),710,140);
        g2D.drawString("life expectancy for dead animals:",710,160);
        g2D.drawString(lifeExpectancy(),710,170);
        g2D.drawString("to run press 'a' ",710,190);
        g2D.drawString("to stop press 'w' ",710,210);
        g2D.drawString("informacje o zwierzaku:",710,230);

        g2D.drawString("zatrzymaj animcje i kliknij na nie ",710,250);
        if(a!=null){
            g2D.setPaint(Color.red);
            g2D.drawString( "geny zwierzecia: ",710,270);

            int[] geny=a.getGenes();
            String genys="";
            for(int i=0;i<32;i++) {
                genys = genys+Integer.toString(geny[i]);

            }
            g2D.setStroke(new BasicStroke(2));
            g2D.drawString( genys,710,290);
            g2D.setStroke(new BasicStroke(5));
            g2D.drawString( "ilosc dzieci aktualna: ",710,310);
            int c=this.startNumber;
            g2D.drawString(String.valueOf(a.numberOfChildren),
                    710,
                    330);
            g2D.drawString( "ilosc dzieci od klikniecia: ",710,350);
            g2D.drawString(String.valueOf(a.numberOfChildren-c),
                    710,
                    370);
            g2D.drawString( "aktualna energia",710,390);
            if(a.getEnergy()<=0) g2D.drawString( "zmarly ",710,410);
            else g2D.drawString( String.valueOf(a.getEnergy()),710,410);


        }
        g2D.setPaint(Color.black);
        g2D.drawString( "to show vectors of animals with",710,430);
        g2D.drawString( "dominating genotypes press k",710,450);
        g2D.drawString( "write to file this era :",710,470);
        g2D.drawString( "press space",710,490);
        if(drawDominatingGenes){
            int i=0;
            int j=0;
            for(Animal c: animals){

                if (d != null && c != null) {

                    if (c.genesToString().equalsIgnoreCase(d) && i<12){

                        if(j==0){
                            g2D.drawString(c.getPosition().toString(), 710, 510+10*i);
                            j=1;
                            i=i-1;
                        }else{
                            g2D.drawString(c.getPosition().toString(), 760, 510+10*i);
                            j=0;
                        }

                        //c.getPosition().toString()
                        i+=1;
                    }


                }

            }
            i=-1;
        }
        if(animals.size()<5 && magic){
            g2D.drawString("Added five animals to map", 760, 700);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(g.startstop==1){
            MoveDirection[] directions = map.skretIPrzemieszczenie();
            map.run(directions,map,magic,side);
            this.grasses = ((GrassField) map).getGrasses();

            this.animals = ((GrassField) map).animals;
            repaint();
        }
        if(g.startstop==0){
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'a':
                g.setStartstop(1);
                break;
            case 'w':
                g.setStartstop(0);
                break;
            case 'k':
                drawDominatingGenes=true;
                break;
            case ' ':
                try {
                    zapiszDoPliku();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
