package oop;



import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{

    Comparator yComparator= new YComparator();
    SortedSet<Animal> ySorted=new TreeSet<>(yComparator);
    Comparator xComparator= new XComparator();
    SortedSet<Animal> xSorted=new TreeSet<>(xComparator);
    //DOPISAC METODE DODAJACA ZWIERZE DO XsOrted i ySorted, i z niej skorzystac w klasie AbstratWorldMap
    public void addAnimal(Animal a){
        Vector2d v=a.getPosition();
        //int x=v.getX();
        //int y=v.getY();
        ySorted.add(a);
        xSorted.add(a);
    }

    @Override
    public void positionChanged(Vector2d oldPosition,Vector2d newPosition){

        return;
    }
    public Vector2d getMin() {return xSorted.first().getPosition().lowerLeft(ySorted.first().getPosition());}
    public Vector2d getMax() {return xSorted.last().getPosition().upperRight(ySorted.last().getPosition()); }
    public class YComparator implements Comparator<Animal>{
        @Override
        public int compare(Animal o1,Animal o2){
            Vector2d v1=o1.getPosition();
            Vector2d v2=o2.getPosition();
            int x1=v1.getX();
            int y1=v1.getY();
            int x2=v2.getX();
            int y2=v2.getY();
            if(y1==y2){
                if(x1>x2){
                    return 1;
                }
                else{
                    return -1;
                }
            }
            if(y1>y2) return 1;
            return -1;
        }
    }
    public class XComparator implements Comparator<Animal>{

        @Override
        public int compare(Animal o1, Animal o2) {
            Vector2d v1=o1.getPosition();
            Vector2d v2=o2.getPosition();
            int x1=v1.getX();
            int y1=v1.getY();
            int x2=v2.getX();
            int y2=v2.getY();
            if(x1==x2){
                if(y1>y2){
                    return 1;
                }
                else{
                    return -1;
                }
            }
            if(x1>x2) return 1;
            return -1;
        }
    }
}
