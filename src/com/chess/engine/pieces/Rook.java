package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.*;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Rook extends Piece {


    private final static IndexTile [] CANDIDATE_MOVE_VECTOR_COORDINATES ={new IndexTile (0,1) ,new IndexTile (0,-1) , new IndexTile(1,0) ,new IndexTile (-1,0) };



    public Rook(final IndexTile piecePosition,final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {

        final List<Move> legalMoves =new ArrayList<>();

        for( final IndexTile currentCandidateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES){

            IndexTile candidateDestinationCoordinates = this.piecePosition;

            while(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinates)) {
                candidateDestinationCoordinates.addIndexTile(currentCandidateOffset);
                if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinates)){

//                    if (isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) ||
//                            isEighthColumnExclusion(this.piecePosition, currentCandidateOffset) ||
//                            isFirstRowExclusion(this.piecePosition, currentCandidateOffset) ||
//                            isEighthٍRowExclusion(this.piecePosition, currentCandidateOffset)) {
//                        continue;
//                    }

                    final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinates);
                    if (!candidateDestinationTile.isTileOccupied()) {
                        legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinates));
                    } else {
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                        if (this.pieceAlliance != pieceAlliance) {
                            legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinates, pieceAtDestination));
                        }
                    }
                    break;
                }
            }
        }



        return ImmutableList.copyOf(legalMoves);
    }

}
