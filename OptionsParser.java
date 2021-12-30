package oop;

import java.util.ArrayList;
import java.util.List;

public class    OptionsParser {
    public static MoveDirection[] parse(String [] tab) throws IllegalArgumentException {
        List<MoveDirection> ret= new ArrayList<>();
        for(String i: tab){
            switch (i){
                case "0":
                    ret.add(MoveDirection.POLNOC);
                    break;
                case "1":
                    ret.add(MoveDirection.POLNOCNYWSCHOD);
                    break;
                case "2":
                    ret.add(MoveDirection.WSCHOD);
                    break;
                case "3":
                    ret.add(MoveDirection.POLUDNIOWYWSCHOD);
                    break;
                case "4":
                    ret.add(MoveDirection.POLUDNIE);
                    break;
                case "5":
                    ret.add(MoveDirection.POLUDNIOWYZACHOD);
                    break;
                case "6":
                    ret.add(MoveDirection.ZACHOD);
                    break;
                case "7":
                    ret.add(MoveDirection.POLNOCNYZACHOD);
                    break;
                default:
                    throw new IllegalArgumentException(i + " oj nie byczq -1 ");
            }
        }
        MoveDirection[] a=new MoveDirection[ret.size()];
        a=ret.toArray(a);
        return a;
    }
}
