package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

public abstract class Move {
    
    
    final Board board;
    //يشير إلى رقعة الشطرنج التي تتم عليها الحركة
    
    final Piece movedPiece;
    //القطعة التي ستتحرك

    final int destinationCoordinate;
    //الموقع الجديد الذي ستنتقل إليه القطعة

    
    // كلاس مجرد لانه برايفت
   private Move(final Board board,
    final Piece movedPiece,
    final int destinationCoordinate){

this.board=board;
this.movedPiece=movedPiece;
this.destinationCoordinate=destinationCoordinate;

}

   
   //يمثل حركة عادية غير هجومية للقطعة
    public static final class MajorMove extends Move {
        
        public MajorMove(final Board board,
                         final Piece movedPiece,
                         final int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }
        
    }
    
    //يمثل حركة هجومية حيث يتم أخذ قطعة معادية
    public static final class AttackMove extends Move{

        final Piece attackedPiece; //القطعة التي يتم الهجوم عليها
        
        public AttackMove(final Board board, 
                          final Piece movedPiece, 
                          final  int destinationCoordinate, 
                          final Piece attackedPiece ) {
            super(board, movedPiece, destinationCoordinate);
            this.attackedPiece= attackedPiece;
        }
    }
    
}


