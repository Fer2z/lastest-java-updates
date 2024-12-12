package com.chess.engine.player;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.pieces.Piece;
import java.util.Collection;

/**
 *
 * @author elffe
 */
public abstract class Player {
    
    protected final Board board ;
    protected final King playerKing;
    protected final Collection<Move> legalMoves;
    Player (final Board board,
            final Collection<Move> legalMoves,
            final Collection<Move> oppnentMoves){
    
    this.board= board;
    this.playerKing = establishKing();
    this.legalMoves = legalMoves;
    }
    
 private King establishKing(){
    for(final Piece piece : getActivePieces()){
     
        if(piece.getPiecetype().isKing()){
         return (king) piece;
       
        }
    }
 }
 
 public abstract Collection<Piece> getActivePieces();
 
    
}
