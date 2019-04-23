package com.chess.engine.board;

import com.chess.engine.Alliance;
import com.chess.engine.pieces.*;
import com.google.common.collect.ImmutableList;


import java.util.*;

public class Board {

    private final List<Tile [] > gameBoard;
    private final Collection<Piece> blackPieces;
    private final Collection<Piece> whitePieces;

    public Board(Builder builder){
        this.gameBoard = createGameBoard(builder);
        this.blackPieces=calculateActivePieces(this.gameBoard,Alliance.BLACK);
        this.whitePieces=calculateActivePieces(this.gameBoard,Alliance.WHITE);

        final Collection<Move> whiteStandardLegalMoves = calculateLegalMoves(this.whitePieces);
        final Collection<Move> blackStandardLegalMoves = calculateLegalMoves(this.blackPieces);

    }

    @Override
    public String toString(){
        final StringBuilder builder =new StringBuilder();
        for(int x=0 ; x<BoardUtils.NUM_TILE_PER_COLUMN ; x++)
        for(int y=0 ; y<BoardUtils.NUM_TILE_PER_ROW ; y++){
            final String tileText =this.gameBoard.get(x)[y].toString();
            builder.append(String.format("%3s",tileText));
            if((y+1)%BoardUtils.NUM_TILE_PER_ROW == 0)
                builder.append("\n");
        }
        return builder.toString();
    }



    private  Collection<Move> calculateLegalMoves(Collection<Piece> pieces) {

        final List<Move> legalMove = new ArrayList<>();
        for(final Piece piece:pieces){
            legalMove.addAll(piece.calculateLegalMoves(this));

        }
        return ImmutableList.copyOf(legalMove);
    }

    private Collection<Piece> calculateActivePieces( final List<Tile[]> gameBoard, final Alliance alliance) {
        final List<Piece> activePieces =new ArrayList<>();
        for(int i=0 ; i<gameBoard.size() ; i++ ){
            for(final Tile tile : gameBoard.get(i)){
                    if (tile.isTileOccupied()) {
                        final Piece piece = tile.getPiece();
                        if (piece.getPieceAlliance() == alliance) {
                            activePieces.add(piece);
                        }
                    }
            }
        }
        return ImmutableList.copyOf(activePieces);
    }

    //V_board ||
    public static  List<Tile []> createGameBoard( final Builder builder) {
         final Tile[][] tiles =new Tile[BoardUtils.NUM_TILE_PER_ROW][BoardUtils.NUM_TILE_PER_COLUMN];
        List<Tile []> list =new ArrayList<Tile []>();
        for (int x=0 ; x<BoardUtils.NUM_TILE_PER_ROW ; x++  ) {
            Tile [] tile=new Tile[8];
            for(int  y=0 ; y<BoardUtils.NUM_TILE_PER_COLUMN ; y++){
                IndexTile indexTile =new IndexTile(x,y);
                tiles[x][y]=Tile.createTile(indexTile,builder.boardConfig.get(8*x+y));
            }
        }

        for (int x=0 ; x<BoardUtils.NUM_TILE_PER_ROW ; x++  ) {
            Tile [] tile=new Tile[8];
            for(int  y=0 ; y<BoardUtils.NUM_TILE_PER_COLUMN ; y++) {
                tile[y]=tiles[y][x];            }
            list.add(x,tile);
        }
        //TODO i think i have an error here
        // return (List<Tile>) ImmutableList.copyOf(tiles);
        return ImmutableList.copyOf(list);
    }




    public static Board createStandardBoard(){
        final Builder builder =new Builder();
        //Black layout
        builder.setPiece(new Rook(new IndexTile(0,0),Alliance.BLACK));
        builder.setPiece(new Knight(new IndexTile(1,0),Alliance.BLACK));
        builder.setPiece(new Bishop(new IndexTile(2,0),Alliance.BLACK));
        builder.setPiece(new Queen(new IndexTile(3,0),Alliance.BLACK));
        builder.setPiece(new King(new IndexTile(4,0),Alliance.BLACK));
        builder.setPiece(new Bishop(new IndexTile(5,0),Alliance.BLACK));
        builder.setPiece(new Knight(new IndexTile(6,0),Alliance.BLACK));
        builder.setPiece(new Rook(new IndexTile(7,0),Alliance.BLACK));
        for(int x=0 ; x<BoardUtils.NUM_TILE_PER_COLUMN ; x++)
            builder.setPiece(new Pawn(new IndexTile(x,1),Alliance.BLACK));

        //White layout
        builder.setPiece(new Rook(new IndexTile(0,7),Alliance.WHITE));
        builder.setPiece(new Knight(new IndexTile(1,7),Alliance.WHITE));
        builder.setPiece(new Bishop(new IndexTile(2,7),Alliance.WHITE));
        builder.setPiece(new Queen(new IndexTile(3,7),Alliance.WHITE));
        builder.setPiece(new King(new IndexTile(4,7),Alliance.WHITE));
        builder.setPiece(new Bishop(new IndexTile(5,7),Alliance.WHITE));
        builder.setPiece(new Knight(new IndexTile(6,7),Alliance.WHITE));
        builder.setPiece(new Rook(new IndexTile(7,7),Alliance.WHITE));
        for(int x=0 ; x<BoardUtils.NUM_TILE_PER_COLUMN ; x++)
            builder.setPiece(new Pawn(new IndexTile(x,6),Alliance.WHITE));

        IndexTile a=new IndexTile(0,0);

        return  builder.build();
    }

    public Tile getTile(final  IndexTile tileCoordinate ){
        return gameBoard.get(tileCoordinate.getTileCoordinate_X())[tileCoordinate.getTileCoordinate_Y()];
    }

    public static class Builder {

        Map<Integer, Piece> boardConfig ;
        Alliance nextMoveMaker;

        public Builder (){
            this.boardConfig=new HashMap<Integer, Piece> ();
        }

        public Builder setPiece(final Piece piece){
            boardConfig.put((piece.getPiecePosition().getTileCoordinate_X() * 8 )+(piece.getPiecePosition().getTileCoordinate_Y() ) ,piece);
            int x=piece.getPiecePosition().getTileCoordinate_X();
            int y=piece.getPiecePosition().getTileCoordinate_Y();
            return this;
        }

        public Builder setMoveMaker(final Alliance nextMoveMaker){
            this.nextMoveMaker = nextMoveMaker;
            return this;
        }

        public Board build(){
            return new Board(this);
        }
    }

}
