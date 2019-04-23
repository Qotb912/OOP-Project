package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.*;
import com.chess.engine.board.Move.*;
import com.google.common.collect.ImmutableList;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

public class King extends Piece {

    private final static IndexTile [] CANDIDATE_MOVE_COORDINATE ={ new IndexTile (0,1) ,new IndexTile (0,-1) , new IndexTile(1,0)
            ,new IndexTile (-1,0) , new IndexTile (-1,-1) ,new IndexTile (-1,1) , new IndexTile(1,-1) ,new IndexTile (1,1) };


    public King(final IndexTile piecePosition,final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        final List<Move> legalMoves =new ArrayList<>();

        for(final IndexTile currentCandidateOffset : CANDIDATE_MOVE_COORDINATE){
            IndexTile indexTile=new IndexTile(currentCandidateOffset);
            indexTile.addIndexTile(this.piecePosition);
            final IndexTile candidateDestinationCoordinate =new IndexTile(indexTile);

            if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                final Tile candidateDestinationTile =board.getTile(candidateDestinationCoordinate);
                if(!candidateDestinationTile.isTileOccupied()){
                    legalMoves.add(new MajorMove(board,this,candidateDestinationCoordinate) );
                }else {
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                    if (this.pieceAlliance != pieceAlliance) {
                        legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                    }
                }
            }
        }



        return ImmutableList.copyOf(legalMoves);
    }




    @Override
    public String toString() {
        return PieceType.KING.toString();
    }

}
