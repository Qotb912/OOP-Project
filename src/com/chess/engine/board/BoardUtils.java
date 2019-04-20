package com.chess.engine.board;

public class BoardUtils {

//    public static final boolean[][] FIRST_COLUMN   = initColumn(new IndexTile(0,0)) ;
//    public static final boolean[][] SECOND_COLUMN  = initColumn(new IndexTile(1,0)) ;
//    public static final boolean[][] SEVENTH_COLUMN = initColumn(new IndexTile(6,0)) ;
//    public static final boolean[][] EIGHTH_COLUMN = initColumn(new IndexTile(7,0)) ;
    public static final boolean[][] FIRST_COLUMN   = initColumn(0) ;
    public static final boolean[][] SECOND_COLUMN  = initColumn(1) ;
    public static final boolean[][] SEVENTH_COLUMN = initColumn(6) ;
    public static final boolean[][] EIGHTH_COLUMN = initColumn(7) ;



    public static boolean[][] FIRST_Row   = null ;
    public static boolean[][] SECOND_Row  = null ;
    public static boolean[][] SEVENTH_Row = null ;
    public static boolean[][] EIGHTH_Row = null ;


    public static final int NUM_TILE_PER_ROW = 8 ;
    public static final int NUM_TILE_PER_COLUMN = 8 ;

    private BoardUtils(){
        throw new RuntimeException("You can not instantiate me!");
    }

    private static boolean [][] initColumn(IndexTile object){
        int y =object.getTileCoordinate_Y();
        int x=object.getTileCoordinate_X();
        final boolean [] [] column =new boolean[NUM_TILE_PER_ROW][NUM_TILE_PER_COLUMN];
        do {
            column[x][y]=true;
            y++;
        }while (y<NUM_TILE_PER_COLUMN);
        return column;
    }

    private static boolean [][] initColumn(int columnNumber){

        int x=columnNumber;
        final boolean [] [] column =new boolean[NUM_TILE_PER_ROW][NUM_TILE_PER_COLUMN];
        do {
            column[x][columnNumber]=true;
            columnNumber++;
        }while (columnNumber<NUM_TILE_PER_COLUMN);
        return column;
    }

    public static boolean isValidTileCoordinate(final IndexTile coordinate) {
        return coordinate.getTileCoordinate_Y()>=0 && coordinate.getTileCoordinate_Y()< NUM_TILE_PER_COLUMN &&
                coordinate.getTileCoordinate_X() >= 0 && coordinate.getTileCoordinate_X() < NUM_TILE_PER_ROW;
    }
}
