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


    private Tile(final IndexTile tileCoordination){
        this.tileCoordination = tileCoordination;
        //    tileCoordinate_X=X;
        //    tileCoordinate_Y =Y;

        //EMPTY_TILES_CACHE =   createAllPossibleEmptyTiles();
    }



//map include empty tile

        private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();


    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {


        final Map<Integer,EmptyTile> emptyTileMap= new HashMap<>();

        for(int x=0 ; x<BoardUtils.NUM_TILE_PER_ROW ; x++ ){
            for(int y=0 ; y<BoardUtils.NUM_TILE_PER_COLUMN ; y++ ) {
                IndexTile indexTile= new IndexTile(x,y);
                emptyTileMap.put( 8*x+y,new EmptyTile(indexTile));
            }
        }
        return ImmutableMap.copyOf(emptyTileMap);
    }




    public static Tile  createTile(final IndexTile tileCoordination ,final Piece piece){
        return piece!= null ?new OccupiedTile(tileCoordination,piece) : EMPTY_TILES_CACHE.get(((tileCoordination.getTileCoordinate_X() * 8)+
                                                                                    ( tileCoordination.getTileCoordinate_Y() )  ));
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
        public String toString(){
            return "-";
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
        public String toString(){
            return getPiece().getPieceAlliance().isBlack() ? getPiece().toString().toLowerCase():
                    getPiece().toString();
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

