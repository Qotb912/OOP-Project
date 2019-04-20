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
    protected  final  int tileCoordinate_X;
    //position on the y-axis
    protected  final  int tileCoordinate_Y;



    //constructor put (x,y)
    public IndexTile(int tileCoordinate_X, int tileCoordinate_Y) {
        this.tileCoordinate_X = tileCoordinate_X;
        this.tileCoordinate_Y = tileCoordinate_Y;
    }

    //constructor take Indextile
    public IndexTile (IndexTile indexTile){
        tileCoordinate_X  =this.getTileCoordinate_X()+ indexTile.getTileCoordinate_X();
        tileCoordinate_Y = this.getTileCoordinate_Y()+indexTile.getTileCoordinate_Y();
    }


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
