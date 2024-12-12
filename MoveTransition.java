package com.chess.engine.player;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

/**
 *
 * @author Ferz
 */
class MoveTransition {
    
    private final Board transtionBoard ;
    private final Move move;
    private final MoveStatus moveStatus;
    
    public MoveTransition(final Board transtionBoard,
            final Move move,
            final MoveStatus moveStatus){
    this.transtionBoard = transtionBoard;
    this.move = move;
    this.moveStatus = moveStatus;
   
    }
    
}
