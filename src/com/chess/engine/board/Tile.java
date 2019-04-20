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

//
//    private Tile(int X, int Y){
//        tileCoordination =new IndexTile(X,Y);
//        //    tileCoordinate_X=X;
//        //    tileCoordinate_Y =Y;
//    }

    private Tile(final IndexTile tileCoordination){
        this.tileCoordination = tileCoordination;
        //    tileCoordinate_X=X;
        //    tileCoordinate_Y =Y;
    }



//map include empty tile
    private static final Map<IndexTile ,EmptyTile> EMPTY_TILES_CACHE =   createAllPossibleEmptyTiles();

    private static Map<IndexTile, EmptyTile> createAllPossibleEmptyTiles() {

        //final IndexTile indexTile ;

        final Map<IndexTile,EmptyTile> emptyTileMap= new HashMap<>();

        for(int x=0 ; x<BoardUtils.NUM_TILE_PER_ROW ; x++ ){
            for(int y=0 ; y<BoardUtils.NUM_TILE_PER_COLUMN ; y++ ) {
                IndexTile indexTile= new IndexTile(x,y);
                emptyTileMap.put( indexTile,new EmptyTile(indexTile));
            }
        }
        return ImmutableMap.copyOf(emptyTileMap);
    }



//    public static Tile createTile(final int tileCoordinate_X,final int tileCoordinate_Y,final Piece piece){
//        return piece!= null ?new OccupiedTile(tileCoordinate_X,tileCoordinate_Y,piece) : EMPTY_TILES_CACHE.get(new IndexTile(tileCoordinate_X,tileCoordinate_Y));
//    }

    public static Tile createTile(final IndexTile tileCoordination ,final Piece piece){
        return piece!= null ?new OccupiedTile(tileCoordination,piece) : EMPTY_TILES_CACHE.get(tileCoordination);
    }




    public  abstract boolean isTileOccupied();


    public abstract Piece getPiece();


    /**
     *  Static Nested Classes
     */
    public static final class  EmptyTile extends Tile {
        private EmptyTile( final IndexTile indexTile ){
            super(indexTile);
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

        private OccupiedTile(IndexTile indexTile,final   Piece pieceOnTile ){
            super(indexTile);
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

