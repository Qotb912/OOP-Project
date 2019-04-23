package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.IndexTile;
import com.chess.engine.board.Move;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece {

    private final static IndexTile [] CANDIDATE_MOVE_COORDINATE = {new IndexTile(0,1) , new IndexTile(0,2) ,
                                                                    new IndexTile(1,1),new IndexTile(-1,1)};


    public Pawn(final IndexTile piecePosition,final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        final List<Move>  legalMoves =new ArrayList<>();

        for(final IndexTile currentCandidateOffset : CANDIDATE_MOVE_COORDINATE){

            IndexTile indexTile =new IndexTile(currentCandidateOffset);
            indexTile.multiply_Y(this.pieceAlliance.getDirection());
            indexTile.addIndexTile(this.piecePosition);
            final IndexTile candidateDestinationCoordinate =new IndexTile(indexTile);

            if(!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                continue;
            }
            if(currentCandidateOffset.equal(new IndexTile(0,1)) && board.getTile(candidateDestinationCoordinate).isTileOccupied()){
               //TODO more here
                legalMoves.add( new Move.MajorMove(board,this,candidateDestinationCoordinate));
            }else if(currentCandidateOffset.equal(new IndexTile(0,2))&& this.isFirstMove() &&
                    (BoardUtils.SECOND_Row[this.piecePosition.getTileCoordinate_X()][this.piecePosition.getTileCoordinate_Y()] && this.pieceAlliance.isBlack()) ||
                    (BoardUtils.SEVENTH_Row[this.piecePosition.getTileCoordinate_X()][this.piecePosition.getTileCoordinate_Y()] && this.pieceAlliance.isWhite())) {

                IndexTile indexTile2 =new IndexTile(new IndexTile(0,1)  );
                indexTile2.multiply_Y(this.pieceAlliance.getDirection());
                indexTile2.addIndexTile(this.piecePosition);
                final IndexTile behindCandidateDestinationCoordinate =new IndexTile(indexTile2);

                if(!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied() &&
                   !board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                        legalMoves.add(new Move.MajorMove(board,this,candidateDestinationCoordinate));
                }

            }else if(currentCandidateOffset.equal(new IndexTile(-1,1))){
                if(board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                    if(this.pieceAlliance != pieceOnCandidate.getPieceAlliance()){
                        //TODO more here
                        legalMoves.add(new Move.MajorMove(board,this,candidateDestinationCoordinate));
                    }
                }
            }else if(currentCandidateOffset.equal(new IndexTile(1,1))){
                if(board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                    if(this.pieceAlliance != pieceOnCandidate.getPieceAlliance()){
                        //TODO more here
                        legalMoves.add(new Move.MajorMove(board,this,candidateDestinationCoordinate));

                    }
                }
            }
        }


        return ImmutableList.copyOf(legalMoves);
    }



    @Override
    public String toString() {
        return PieceType.PAWN.toString();
    }
}
