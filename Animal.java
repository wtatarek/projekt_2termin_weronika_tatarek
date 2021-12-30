package oop;



import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class Animal {
    private MapDirection md;
    private Vector2d v;
    private IWorldMap map;
    private float energy;
    private int[] geny = new int[32];
    public int ile_zwierzat_ma_geny_co_ja;
    public int numberOfChildren;
    public int steps;
    public int age;
    private LinkedList<IPositionChangeObserver> observers = new LinkedList<>();

    public String genesToString(){
        String genes="";
        for(int i=0;i<32;i++) {
            genes = genes+Integer.toString(this.geny[i]);
        }
        return genes;
    }
    public int getAge(){
        return this.age;
    }
    public Animal(int x, int y) {
        this.md = MapDirection.POLNOC;
        this.v = new Vector2d(x, y);
    }

    public Animal() {
        this(2, 2);
    }

    public Animal(IWorldMap map) {
        this();
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.md = MapDirection.POLNOC;
        this.v = initialPosition;
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition, float energy, int[] geny) {
        this.md = MapDirection.POLNOC;
        this.v = initialPosition;
        this.map = map;
        this.energy = energy;
        this.geny = geny;
        this.numberOfChildren =0;
        this.steps=0;
        this.age=0;
        this.ile_zwierzat_ma_geny_co_ja=0;
    }

    public void updateAge(){
        this.age+=1;
    }

    public void updateNumberOfChildren(){
        this.numberOfChildren+=1;
    }

    public Animal(MapDirection md, Vector2d v) {
        this.md = md;
        this.v = v;
        this.ile_zwierzat_ma_geny_co_ja=0;
    }

    public Vector2d getPosition() {
        return this.v;
    }

    public float getEnergy() {
        return this.energy;
    }

    public int[] getGenes() {
        return this.geny;
    }

    public void ChangeEnergy(float diff) {
        this.energy += diff;
    }

    public void changePosition(Vector2d v) {
        this.v = v;
    }

    public IWorldMap getMap() {
        return this.map;
    }

    public void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer) {
        observers.remove(observer);
    }

    public void positionChanged(Vector2d oldPosition) {
        for (IPositionChangeObserver i : observers) {
            i.positionChanged(oldPosition, this.v);
        }
    }

    @Override
    public String toString() {
        return "^";
    }

    public Vector2d move(MoveDirection mv,String side) {
        Vector2d old = new Vector2d(this.v.x, this.v.y);
        Vector2d vec=new Vector2d(2,2);
        switch (mv) {
            case POLNOC:
                vec=makeAMove(0, 1,side);
                break;
            case POLNOCNYWSCHOD:
                vec=makeAMove(1, 1,side);
                break;
            case WSCHOD:
                vec=makeAMove(1, 0,side);
                break;
            case POLUDNIOWYWSCHOD:
                vec=makeAMove(1, -1,side);
                break;
            case POLUDNIE:
                vec=makeAMove(0, -1,side);
                break;
            case POLUDNIOWYZACHOD:
                vec=makeAMove(-1, -1,side);
                break;
            case ZACHOD:
                vec=makeAMove(-1, 0,side);
                break;
            case POLNOCNYZACHOD:
                vec=makeAMove(-1, 1,side);
                break;
        }
        return vec;
    }

    private Vector2d makeAMove(int a, int b,String side) {

        Vector2d old = new Vector2d(this.v.x, this.v.y);
        Vector2d new1 = new Vector2d(this.v.x + a, this.v.y + b);
        if (this.map.canMoveTo(new1, old)) {
            this.v = this.v.add(new Vector2d(a, b));
            this.positionChanged(old);
            return new1;
        }
        if(side.equalsIgnoreCase("right")){
            if(old.x==map.getMin().getX()){
                return old;
            }else if(old.y==map.getMin().y){
                return old;
            }else if(old.x==map.getMax().x){
                return old;
            }else if(old.y==map.getMax().y){
                return old;
            }


        }
        return new1;

    }

}





