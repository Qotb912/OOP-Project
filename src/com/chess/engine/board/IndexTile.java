package com.chess.engine.board;


/**
 * to use it in map
 */


public  class IndexTile{
    //hhhh

    public int getTileCoordinate_X() {
        return tileCoordinate_X;
    }

    public int getTileCoordinate_Y() {
        return tileCoordinate_Y;
    }
    //int xxxx;


    // postion on x-axis (in the real word (a->h)
    private     int tileCoordinate_X;
    //position on the y-axis
    private     int tileCoordinate_Y;

    public IndexTile (){};

    //constructor put (x,y)
    public IndexTile(int tileCoordinate_X, int tileCoordinate_Y) {
        this.tileCoordinate_X = tileCoordinate_X;
        this.tileCoordinate_Y = tileCoordinate_Y;
    }

    //constructor take Index tile
    public IndexTile (IndexTile indexTile){
        tileCoordinate_X  = indexTile.getTileCoordinate_X();
        tileCoordinate_Y  = indexTile.getTileCoordinate_Y();
    }

    //to move it to new position
    public void moveTOPosition (IndexTile indexTile){
        tileCoordinate_Y =indexTile.getTileCoordinate_Y();
        tileCoordinate_X = indexTile.getTileCoordinate_X();
    }

    // to add (x,Y) to it's position
    public void addIndexTile(IndexTile indexTile){
        tileCoordinate_X  += indexTile.getTileCoordinate_X();
        tileCoordinate_Y += indexTile.getTileCoordinate_Y();
    }

    public void multiply_Y(int number){
        tileCoordinate_Y *= number ;
    }


//    public void multiply_IndexTile(int number){
//        tileCoordinate_X *=number ;
//        tileCoordinate_Y *=number ;
//    }

//    public void multiply_X(int number){
//        tileCoordinate_X *=number ;
//    }


    //to check object equal to this one
    public boolean equal(IndexTile indexTile){
        boolean equal=true;
        if(indexTile.getTileCoordinate_X() != tileCoordinate_X)
            equal=false;
        if(indexTile.getTileCoordinate_Y() != tileCoordinate_Y)
            equal=false;

        return equal;
    }

}
