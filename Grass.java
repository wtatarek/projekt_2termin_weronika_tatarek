package oop;



public class Grass {
    private Vector2d pozycja;
    public Grass(Vector2d pozycja){
        this.pozycja=pozycja;
    }
    public Vector2d getPosition() {
        return pozycja;
    }
    public String toString(){
        return "*";
    }
}
