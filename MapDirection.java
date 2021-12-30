package oop;



public enum MapDirection {
    POLNOC,POLNOCNYWSCHOD,WSCHOD,POLUDNIOWYWSCHOD,POLUDNIE,POLUDNIOWYZACHOD,ZACHOD,POLNOCNYZACHOD;
    public Vector2d toUnitVector()
    {
        Vector2d v = null;
        switch (this){
            case POLNOC:
                v=new Vector2d(0,1);
            case POLUDNIE:
                v=new Vector2d(0,-1);
            case WSCHOD:
                v=new Vector2d(1,0);
            case ZACHOD:
                v=new Vector2d(-1,0);
            case POLNOCNYWSCHOD:
                v=new Vector2d(1,1);
            case POLUDNIOWYWSCHOD:
                v=new Vector2d( 1,-1);
            case POLUDNIOWYZACHOD:
                v=new Vector2d(-1,-1);
            case POLNOCNYZACHOD:
                v=new Vector2d(-1,1);
            default:
                break;
        }
        return v;
    }
}
