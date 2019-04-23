package  com.chess.engine.pieces;


import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.IndexTile;
import com.chess.engine.board.Move;

import java.util.Collection;

public abstract class Piece {

//    protected final int piecePositionX;
//    protected final int getPiecePositionY;

    protected final IndexTile piecePosition;
    protected final Alliance pieceAlliance;
    protected final boolean isFirstMove;

    public Alliance getPieceAlliance() {
        return pieceAlliance;
    }

//constructor
    Piece(final IndexTile piecePosition, final Alliance pieceAlliance) {
//        this.piecePositionX = piecePositionX;
//        this.getPiecePositionY = getPiecePositionY;

         this.piecePosition = piecePosition;
         this.pieceAlliance = pieceAlliance;
         //TODO more here
         this.isFirstMove=false;
    }

    public IndexTile getPiecePosition(){
        return this.piecePosition;
    }

    public boolean isFirstMove() {
        return isFirstMove;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);

    public enum PieceType{
        PAWN("P"),
        ROOK("R"),
        KNIGHT("N"),
        BISHOP("B"),
        QUEEN("Q"),
        KING("K");

        private String pieceName;
        PieceType(final String pieceName){
            this.pieceName=pieceName;
        }

        @Override
        public String toString(){
            return this.pieceName;
        }
    }
}
