
//هذا الكلاس يمثل  قطعة الشطرنج

package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import java.util.List;
import java.util.Collection;
public abstract class Piece {

    protected final int piecePosition;
     protected final Alliance pieceAlliance;
      //piecePosition متغير يخزن موقع القطعه على الشطرنج
       //pieceAlliance متغير يحدد تحالف القطعه ابيض اسود
     
     Piece(final int piecePosition,final Alliance pieceAlliance){
         this.pieceAlliance = pieceAlliance;
         this.piecePosition = piecePosition;
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
             
             
             
     public abstract Collection<Move> calculatelegalMoves (final Board board);
     //دالة مجردة  يجب على أي كلاس يرث من كلاس بيس يحدد الحركات المسموحه
     //ترجع مجموعة من الحركات المسموحه الممكنة للقطعة على الشطرنج
     
   public enum PieceType {
       
    PAWN("P"),
    KNIGHT("N"),
    BISHOP("B"),
    ROOK("R"),
    QUEEN("Q"),
    KING("K");

    private String pieceName;
    
     //يخزن اسم القطعة أو رمزها.
    
    PieceType(final String pieceName) {
        this.pieceName = pieceName;
    }

    @Override
    public String toString() {
        return this.pieceName;
    }
}

}
