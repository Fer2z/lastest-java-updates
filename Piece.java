
//هذا الكلاس يمثل  قطعة الشطرنج

package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import java.util.List;
import java.util.Collection;
public abstract class Piece {

    protected final PieceType pieceType;
    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    protected final boolean isFirstMove; 
      //piecePosition متغير يخزن موقع القطعه على الشطرنج
       //pieceAlliance متغير يحدد تحالف القطعه ابيض اسود
     
     Piece(final PieceType pieceType,final int piecePosition,final Alliance pieceAlliance){
         this.pieceType = pieceType;
         this.pieceAlliance = pieceAlliance;
         this.piecePosition = piecePosition;
         this.isFirstMove = false;
         //تجهيز القطعة عند إنشائها بتحديد موقعهابيس بوزشن وتحالفها بيس الاينس 

        
     }
     public int getPiecePosition(){
     return this.piecePosition;
     
    //إرجاع موقع القطعة الحالي على الشطرنج
     
     }
     public Alliance getPieceAlliance(){
     
     return this.pieceAlliance;
     
     //إرجاع تحالف القطعة أبيض أو أسود
     }
     
     // لتمييز الحركات الأولى لبعض القطع مثل البيدق الذي يمكنه التحرك مربعين للأمام في أول حركة فقط
     public boolean isFirstMove() {
    return this.isFirstMove;
}

             
             
             
     public abstract Collection<Move> calculateLegalMoves (final Board board);
     //دالة مجردة  يجب على أي كلاس يرث من كلاس بيس يحدد الحركات المسموحه
     //ترجع مجموعة من الحركات المسموحه الممكنة للقطعة على الشطرنج
     
   public enum PieceType {
       
    PAWN("P") {
        @Override
        public boolean isKing() {
            return false;
        }
    },
    KNIGHT("N") {
        @Override
        public boolean isKing() {
         return false;

        }
    },
    BISHOP("B") {
        @Override
        public boolean isKing() {
         return false;
        }
    },
    ROOK("R") {
        @Override
        public boolean isKing() {
         return false;

        }
    },
    QUEEN("Q") {
        @Override
        public boolean isKing() {
          return false;
        }
    },
    KING("K") {
        @Override
        public boolean isKing() {
            return true;
        }
    };

    private String pieceName;
    
     //يخزن اسم القطعة أو رمزها.
    
    PieceType(final String pieceName) {
        this.pieceName = pieceName;
    }

    @Override
    public String toString() {
        return this.pieceName;
    }
    
    public abstract boolean isKing();
}

}
