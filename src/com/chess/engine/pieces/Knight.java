package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.*;

import com.chess.engine.board.Move.*;

import com.chess.engine.board.Move.MajorMove;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Knight extends Piece {

    private final static IndexTile [] CANDIDATE_MOVE_COORDINATES ={new IndexTile (1,2) ,new IndexTile (-1,2) , new IndexTile(2,1) ,new IndexTile (-2,1) , new IndexTile(2,-1) , new IndexTile(-2,-1) ,new IndexTile (1,-2) ,new IndexTile (-1,-2)};

    public Knight(final IndexTile piecePosition,final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves( final Board board) {

        final List<Move> legalMoves =new ArrayList<>();

        for(final IndexTile currentCandidateOffset : CANDIDATE_MOVE_COORDINATES){


            final IndexTile candidateDestinationCoordinates =new IndexTile();
            candidateDestinationCoordinates.addIndexTile(currentCandidateOffset);
            //candidateDestinationCoordinates = new IndexTile ((this.piecePosition.getTileCoordinate_X()+currentCandidate.getTileCoordinate_X()),(this.piecePosition.getTileCoordinate_Y()+currentCandidate.getTileCoordinate_Y()));

            if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinates)) {
// row from me && column  (conflict here M...Q...)

//                if( isFirstColumnExclusion(this.piecePosition,currentCandidateOffset)    ||
//                    isSecondColumnExclusion(this.piecePosition,currentCandidateOffset)   ||
//                    isSeventhColumnExclusion(this.piecePosition,currentCandidateOffset)  ||
//                    isEighthColumnExclusion(this.piecePosition,currentCandidateOffset)   ||
//                    isFirstRowExclusion(this.piecePosition,currentCandidateOffset)       ||
//                    isSecondRowExclusion(this.piecePosition,currentCandidateOffset)      ||
//                    isSeventhٍRowExclusion(this.piecePosition,currentCandidateOffset)     ||
//                    isEighthٍRowExclusion(this.piecePosition,currentCandidateOffset)){
//                    continue;
//                }


                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinates);
                if(!candidateDestinationTile.isTileOccupied()){
                    legalMoves.add(new Move.MajorMove(board,this,candidateDestinationCoordinates));
                }else{
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                    if(this.pieceAlliance != pieceAlliance){
                        legalMoves.add(new AttackMove(board , this , candidateDestinationCoordinates , pieceAtDestination));
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }


    @Override
    public String toString() {
        return PieceType.KNIGHT.toString();
    }

//
//    private static boolean isFirstColumnExclusion(final IndexTile currentPosition , final IndexTile candidateOffset ){
//        return BoardUtils.FIRST_COLUMN[currentPosition.getTileCoordinate_X()][currentPosition.getTileCoordinate_Y()] &&
//                ( candidateOffset.equal(new IndexTile(-1,2)) ||
//                  candidateOffset.equal(new IndexTile(-2,1)) ||
//                  candidateOffset.equal(new IndexTile(-2,-1) )||
//                  candidateOffset.equal(new IndexTile(-1,-2)));
//    }
//
//    private static boolean isSecondColumnExclusion(final IndexTile currentPosition , final IndexTile candidateOffset ){
//        return BoardUtils.SECOND_COLUMN[currentPosition.getTileCoordinate_X()][currentPosition.getTileCoordinate_Y()] &&
//                ( candidateOffset.equal(new IndexTile(-2,1))||  candidateOffset.equal(new IndexTile(-2,-1)) ) ;
//    }
//
//
//    private static boolean isEighthColumnExclusion(final IndexTile currentPosition , final IndexTile candidateOffset ){
//        return BoardUtils.EIGHTH_COLUMN[currentPosition.getTileCoordinate_X()][currentPosition.getTileCoordinate_Y()] &&
//                ( candidateOffset.equal(new IndexTile(1,2)) || candidateOffset.equal(new IndexTile(1,-2))
//                        ||candidateOffset.equal(new IndexTile(2,1) )|| candidateOffset.equal(new IndexTile(2,-1)));
//    }
//
//
//    private static boolean isSeventhColumnExclusion(final IndexTile currentPosition , final IndexTile candidateOffset ){
//        return BoardUtils.SEVENTH_COLUMN[currentPosition.getTileCoordinate_X()][currentPosition.getTileCoordinate_Y()] &&
//                (candidateOffset.equal(new IndexTile(2,1) )|| candidateOffset.equal(new IndexTile(2,-1)));
//    }
//
//
//
//
//
//    private static boolean isFirstRowExclusion(final IndexTile currentPosition , final IndexTile candidateOffset ){
//        return BoardUtils.FIRST_Row[currentPosition.getTileCoordinate_X()][currentPosition.getTileCoordinate_Y()] &&
//                ( candidateOffset.equal(new IndexTile(2,-1)) || candidateOffset.equal(new IndexTile(-2,-1))
//                        ||candidateOffset.equal(new IndexTile(1,-2) )|| candidateOffset.equal(new IndexTile(-1,-2)));
//    }
//
//
//    private static boolean isSecondRowExclusion(final IndexTile currentPosition , final IndexTile candidateOffset ){
//        return BoardUtils.SECOND_Row[currentPosition.getTileCoordinate_X()][currentPosition.getTileCoordinate_Y()] &&
//                ( candidateOffset.equal(new IndexTile(1,-2) )|| candidateOffset.equal(new IndexTile(-1,-2)));
//    }
//
//    private static boolean isEighthٍRowExclusion(final IndexTile currentPosition , final IndexTile candidateOffset ){
//        return BoardUtils.EIGHTH_Row[currentPosition.getTileCoordinate_X()][currentPosition.getTileCoordinate_Y()] &&
//                ( candidateOffset.equal(new IndexTile(1,2)) || candidateOffset.equal(new IndexTile(-1,2))
//                        ||candidateOffset.equal(new IndexTile(2,1) )|| candidateOffset.equal(new IndexTile(-2,1)));
//    }
//
//    private static boolean isSeventhٍRowExclusion(final IndexTile currentPosition , final IndexTile candidateOffset ){
//        return BoardUtils.SEVENTH_Row[currentPosition.getTileCoordinate_X()][currentPosition.getTileCoordinate_Y()] &&
//                ( candidateOffset.equal(new IndexTile(1,2)) || candidateOffset.equal(new IndexTile(-1,2)));
//    }




}

