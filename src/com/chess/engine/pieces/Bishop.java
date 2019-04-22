package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.*;
import com.chess.engine.board.Move.*;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Bishop extends Piece {


    private final static IndexTile [] CANDIDATE_MOVE_VECTOR_COORDINATES ={new IndexTile (-1,-1) ,new IndexTile (-1,1) , new IndexTile(1,-1) ,new IndexTile (1,1) };



    public Bishop(final IndexTile piecePosition, final Alliance pieceAlliance) {
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
                    legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinates));
                } else {
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                    if (this.pieceAlliance != pieceAlliance) {
                        legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinates, pieceAtDestination));
                    }
                }
                break;
            }
            }
            }



        return ImmutableList.copyOf(legalMoves);
    }

//
//// Exclusion : eqsa
//    private static boolean isFirstColumnExclusion(final IndexTile currentPosition , final IndexTile candidateOffset ){
//        return BoardUtils.FIRST_COLUMN[currentPosition.getTileCoordinate_X()][currentPosition.getTileCoordinate_Y()] &&
//                ( candidateOffset.equal(new IndexTile(-1,-1)) ||
//                        candidateOffset.equal(new IndexTile(-1,1)));
//    }
//
//    private static boolean isEighthColumnExclusion(final IndexTile currentPosition , final IndexTile candidateOffset ){
//        return BoardUtils.EIGHTH_COLUMN[currentPosition.getTileCoordinate_X()][currentPosition.getTileCoordinate_Y()] &&
//                ( candidateOffset.equal(new IndexTile(1,-1)) ||
//                        candidateOffset.equal(new IndexTile(1,1)));
//    }
//
//    private static boolean isFirstRowExclusion(final IndexTile currentPosition , final IndexTile candidateOffset ){
//        return BoardUtils.FIRST_Row[currentPosition.getTileCoordinate_X()][currentPosition.getTileCoordinate_Y()] &&
//                ( candidateOffset.equal(new IndexTile(-1,-1))
//                        ||candidateOffset.equal(new IndexTile(1,-1) ));
//    }
//
//    private static boolean isEighthٍRowExclusion(final IndexTile currentPosition , final IndexTile candidateOffset ){
//        return BoardUtils.EIGHTH_Row[currentPosition.getTileCoordinate_X()][currentPosition.getTileCoordinate_Y()] &&
//                ( candidateOffset.equal(new IndexTile(-1,1))
//                        || candidateOffset.equal(new IndexTile(1,1)));
//    }





}
