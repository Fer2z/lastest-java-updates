package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.AttackMove;
import com.chess.engine.board.Move.MajorMove;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

public class Knight extends Piece { //(constructor) الخاص بالفارس.

    
    private final static int [] CANDIDATE_MOVE_COORDINATES = { -17 ,-15,-10,-6,6,10,15,17}; //مصفوفة تحتوي على كل التحركات الممكنة للفارس
    
    
    
    Knight(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public List<Move> calculatelegalMoves(final Board board) { //حساب الحركات المسموحة       
        int candidateDestinationCoordinate;
       final List<Move> legalMoves = new ArrayList<>(); //متغير لتحديد المواقع
       
        for(final int currentCandidateOffset: CANDIDATE_MOVE_COORDINATES) { // التكرار عبر التحركات الممكنة       
           final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;     
            if(BoardUtils.isValidTileCoordinate (candidateDestinationCoordinate)) {//هذا شرط للتحقق مما إذا كان الموقع الجديد صالح .   
            if(isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) ||
            isSecondColumnExclusion(this.piecePosition, currentCandidateOffset) ||
            isSeventhColumnExclusion(this.piecePosition, currentCandidateOffset) ||
            isEighthColumnExclusion(this.piecePosition, currentCandidateOffset)) {
            continue;
}
               final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate); //إذا كانت الخانة غير مشغولة (!candidateDestinationTile.isTileOccupied())، يتم اعتبار الحركة مسموحه
            
               if (!candidateDestinationTile.isTileOccupied()){
                   legalMoves.add(new MajorMove (board,this,candidateDestinationCoordinate));
              
                 } else{
                     final Alliance pieceAtDestination = candidateDestinationTile.getPiece();
                   final Alliance PieceAlliance = pieceAtDestination.getPieceAlliance();
                    if (this.pieceAlliance != pieceAlliance){
                    legalMoves.add(new AttackMove(board,this,candidateDestinationCoordinate,pieceAtDestination));
                    
                    /*
                   (Alliance) إذا كانت الخانة مشغولة،
                    يتم التحقق من التحالف  للقطعة الموجودة.
                   إذا كانت قطعة العدو (التحالف مختلف)، 
                   تعتبر الحركة قانونية لأنها ستكون عملية "أخذ" للقطعة
                    */
                    
                    
                    }
                      }
               
            }
            
        } 
     return ImmutableList.copyOf(legalMoves);
    }

        private static boolean isValidTileCoordinate(int coordinate) {
               return coordinate >= 0 && coordinate < 64;
           }
    
 private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
    return BoardUtils.FIRST_COLUMN[currentPosition] &&
           (candidateOffset == -17 || candidateOffset == -10 || candidateOffset == 6 || candidateOffset == 15);
}
 private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffset) {
    return BoardUtils.SECOND_COLUMN[currentPosition] && (candidateOffset == -10 || candidateOffset == 6);
}
private static boolean isSeventhColumnExclusion(final int currentPosition, final int candidateOffset) {
return BoardUtils.SEVENTH_COLUMN [currentPosition] && (candidateOffset == -6 || candidateOffset == 10);
}
private static boolean isEighthColumnExclusion (final int currentPosition, final int candidateOffset) {
return BoardUtils.EIGHTH_COLUMN [currentPosition] && (candidateOffset == -15 || candidateOffset == -6 ||
candidateOffset == 10 || candidateOffset == 17);
}
    }





