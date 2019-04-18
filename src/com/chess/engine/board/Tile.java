package com.chess.engine.board;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;


public abstract class Tile {

//    protected  final  int tileCoordinate_X;
//    protected  final  int tileCoordinate_Y;

    //try use x,Y
    protected  final IndexTile tileCoordination ;

    /**
     *
     * @param X index x-axis on the board
     * @param Y index y-axis on the board
     */

    private Tile(int X, int Y){
        tileCoordination =new IndexTile(X,Y);
        //    tileCoordinate_X=X;
        //    tileCoordinate_Y =Y;
    }

//map include empty tile
    private static final Map<IndexTile ,EmptyTile> EMPTY_TILES =   createAllPossibleEmptyTiles();

    private static Map<IndexTile, EmptyTile> createAllPossibleEmptyTiles() {

        //final IndexTile indexTile ;

        final Map<IndexTile,EmptyTile> emptyTileMap= new HashMap<>();

        for(int x=0 ; x<8 ; x++ ){
            for(int y=0 ; y<8 ; y++ ) {
                emptyTileMap.put(new IndexTile(x,y),new EmptyTile(x,y));
            }

        }
        return ImmutableMap.copyOf(emptyTileMap);
    }



    public static Tile createTile(final int tileCoordinate_X,final int tileCoordinate_Y,final Piece piece){
        return piece!= null ?new OccupiedTile(tileCoordinate_X,tileCoordinate_Y,piece) :EMPTY_TILES.get(new IndexTile(tileCoordinate_X,tileCoordinate_Y));
    }




    public  abstract boolean isTileOccupied();


    public abstract Piece getPiece();


    /**
     *  Static Nested Classes
     */
    public static final class  EmptyTile extends Tile {
        EmptyTile( final int X, final int Y ){
            super(X,Y);
        }

        @Override
        public boolean isTileOccupied() {
            return false;
        }

        @Override
        public Piece getPiece() {
            return null;
        }
    }


    /**
     *  Static Nested Classes
     */

    public static final class OccupiedTile extends  Tile{

        private final Piece pieceOnTile ;

        OccupiedTile(int X, int Y ,  Piece pieceOnTile ){
            super(X,Y);
            this.pieceOnTile = pieceOnTile ;
        }


        @Override
        public boolean isTileOccupied() {
            return true;
        }

        @Override
        public Piece getPiece() {
            return this.pieceOnTile;
        }


    }


}

/**
 * to use it in map
 */


class IndexTile{
    //hhhh
    int xxxx;

    protected  final  int tileCoordinate_X;
    protected  final  int tileCoordinate_Y;

    public IndexTile(int tileCoordinate_X, int tileCoordinate_Y) {
        this.tileCoordinate_X = tileCoordinate_X;
        this.tileCoordinate_Y = tileCoordinate_Y;
    }
}