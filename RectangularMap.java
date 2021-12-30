package oop;


import java.util.LinkedList;

public class RectangularMap extends AbstractWorldMap{
    private int width;
    private int height;
    public RectangularMap(int width, int height){
        super();
        this.width=width;
        this.height=height;
        super.max= new Vector2d(width,height);
        super.min= new Vector2d(0,0);
    }
    @Override
    public boolean place(Animal animal) throws IllegalArgumentException{
        Vector2d max=this.max.upperRight(animal.getPosition());
        if (max.x > this.max.x || max.y > this.max.y){
            throw new IllegalArgumentException(" sorki jestes poza mapa");
        }
        Vector2d min=this.min.lowerLeft(animal.getPosition());
        if (min.x < this.min.x || min.y < this.min.y){
            throw new IllegalArgumentException(" TEZ POZA MAPA");
        }
        return super.place(animal);
    }

    @Override
    public LinkedList<Grass> getGrasses() {
        return this.getGrasses();
    }

    @Override
    public void removeFromGrasses(Grass grass) {
        System.out.println("RectangularMap");
    }

    @Override
    public void addGrasses(int numberOfGrassesEaten) {
        System.out.println("RectangularMap");
    }

    @Override
    public boolean canMoveTo(Vector2d position,Vector2d old){
        if(!(position.x >= 0 && position.x <= width && position.y >= 0 && position.y <= height))
            return false;
        return super.canMoveTo(position,old);
    }
}
