package oop;


import java.util.LinkedList;
import java.util.List;

public class SimulationEngine implements IEngine{
    List<Vector2d> poczat_poz=new LinkedList();
    List<MoveDirection> ruchy=new LinkedList();
    IWorldMap map;
    public SimulationEngine(Vector2d poczat_poz,IWorldMap map,MoveDirection ruchy){
        this.map=map;
    }
    @Override
    public void run() {

    }
}
