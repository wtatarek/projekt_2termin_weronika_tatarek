package oop;

public class Eras {
    Eras(){

    }
    public String getEra(int i)
    {
        switch (i){
            case 1:
                return "ElEPHANT";
            case 2:
                return "DRAGON";
            case 3:
                return "SNAKE";
            case 4:
                return "WAR";
            case 5:
                return "FIRE";
            case 6:
                return "SWORD";
            case 7:
                return "MAGIC";
            case 8:
                return "LION";
            default:
                return "PRESENT";

        }
    }
}
