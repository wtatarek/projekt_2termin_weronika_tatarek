package oop;



import java.util.LinkedList;

public interface IWorldMap {

    boolean canMoveTo(Vector2d position,Vector2d oldPosition);
    boolean place(Animal newAnimal) throws IllegalArgumentException;
    void run(MoveDirection[] directions, IWorldMap map, boolean magic, String side);
    boolean isOccupied(Vector2d position);
    Object objectAt(Vector2d position);

    MoveDirection[] skretIPrzemieszczenie();

    int [] wylosujGenotyp(int[] genes);

    Vector2d getMin();

    Vector2d wylosujVector();

    Vector2d getMax();

    int getNumberOfDeadAnimals();

    int getAgeOfDeadAnimals();

    LinkedList<Grass> getGrasses();

    void removeFromGrasses(Grass grass);

    void addGrasses(int numberOfGrassesEaten);
}
