package com.chess.engine.board;

import com.chess.engine.Alliance;
import com.chess.engine.pieces.Knight;
import com.chess.engine.pieces.Piece;
import com.chess.engine.player.BlackPlayer;
import com.chess.engine.player.Player;
import com.chess.engine.player.WhitePlayer;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Board {
    
    private final List<Tile>gameBoard; // مربعات الشطرنج
    private final Collection<Piece> whitePieces; // مجموعة القطع البيضاء النشطة على الطاوله
     private final Collection<Piece> blackPieces; // مجموعة القطع السوداء النشطة على الطاوله
    
     
     private final WhitePlayer whitePlayer;
     private final BlackPlayer blackPlayer;
     
     
     //يقوم ببناء الطاوله وحساب القطع النشطة لكل لاعب والحركات القانونية المتاحة
    private Board(Builder builder){
        this.gameBoard = createGameBoard (builder); //لإنشاء الطاوله باستخدام إعدادات القطع
      
        //  لحساب القطع النشطة الموجودة حاليا على المربعات
        this.whitePieces= calculateActivePieces(this.gameBoard,Alliance.WHITE); 
        this.blackPieces= calculateActivePieces(this.gameBoard,Alliance.BLACK);  
        
        //لحساب الحركات الشرعية لكل لاعب
        final Collection<Move>whiteStandardLegalMoves = calculateLegalMoves(this.whitePieces);
        final Collection<Move>blackStandardLegalMoves = calculateLegalMoves(this.blackPieces);
        this.whitePlayer = new WhitePlayer(this ,whiteStandardLegalMoves, blackStandardLegalMoves);
        this.blackPlayer = new BlackPlayer(this ,whiteStandardLegalMoves, blackStandardLegalMoves);
    }
    @Override
 
    // تحويل رقعة الشطرنج إلى نص منسق بحيث يتم عرض كل مربع  بشكل مناسب في صفوف وأعمدة
    //يتم طباعة كل مربع مع فحص حدود كل صف
    public String toString(){
    final StringBuilder builder= new StringBuilder();
    for (int i=0;i<BoardUtils.NUM_TILES; i++){
     final String tileText = this.gameBoard.get(i).toString();
      builder.append(String.format("%3s", tileText));
        if ((i + 1) % BoardUtils.NUM_TILES_PER_ROW == 0) {
            builder.append("\n");    
    }
    }
        return builder.toString();
    }
    
    public Player whitePlayer(){
     return this.whitePlayer;
    }
    
     public Player blackPlayer(){
     return this.blackPlayer;
    }
    
    public Collection<Piece> getBlackPieces(){
    return this.blackPieces;}
    
    public Collection<Piece> getWhitePieces(){
    return this.whitePieces;}
    
    
    
       private static String prettyPrint(Tile tile) {
    if (tile.isTileOccupied()) {
        return tile.getPiece().getPieceAlliance().isBlack() ? tile.toString().toLowerCase() : tile.toString();
    }     
    return tile.toString();
    }
    
     //حساب جميع الحركات المسموحه لكل القطع المعطاة كمدخل
    //يتم استدعاء الدالة من كل قطعة لإرجاع الحركات الممكنة 
    private Collection <Move> calculateLegalMoves(final Collection <Piece> Pieces){
        final List<Move> legalMoves= new ArrayList<>();
         for(final Piece piece: Pieces){
           legalMoves.addAll(piece.calculateLegalMoves(this));
        }
    return ImmutableList.copyOf(legalMoves);//لجعل القائمة غير قابلة للتعديل بعد إنشائها
    }
    
    
    //حساب جميع القطع النشطة على الرقعة بناء على تحالفها أبيض أو أسود
    //تمر على كل مربع وتتحقق إذا كان مشغولا وتضيف القطعة إذا كانت تتبع التحالف المطلوب
    private static Collection<Piece>calculateActivePieces(final list<Tile>gameBoard,final Alliance alliance){
    final List<Piece> activePieces = new ArrayList<>();
    for (final Tile tile : gameBoard) {
        if (tile.isTileOccupied()) {
            final Piece piece = tile.getPiece();
            if (piece.getPieceAlliance() == alliance) {
                activePieces.add(piece);
            }
        }
    }
    return ImmutableList.copyOf(activePieces);
}

    

    public Tile getTile(final int tileCoordinate) {
     return gameBoard.get(tileCoordinate);
    }
    
    //بناء طاوله الشطرنج من خلال مربعاتها الاربع وستين باستخدام الكلاس تايل
    //تمر على كل موقع من صفر إلى  ثلاث وستين وتنشئ مربع جديد إما فارغ أو يحتوي على قطعة حسب الإعداد المقدم من الكلاس بيلدر
    private static List<Tile>createGameBoard(final Builder builder){
    final Tile[]tiles = new Tile [BoardUtils.NUM_TILES];
    for (int i = 0; i<BoardUtils.NUM_TILES;i++){
    tiles[i]= Tile.createTile(i,builder.boardConfig.get(i));
    }
   return ImmutableList.copyOf(tiles);
    }
    
    //يتم وضع القطع السوداء والبيضاء في مواقعها التقليدية ثم تحديد أن اللاعب الأبيض يبدأ الحركة
    public static Board createStandardBoard(){
    
        final Builder builder = new Builder();
        //  تخطيط الاسود
        builder.setPiece(new Rook(Alliance.BLACK, 0));
        builder.setPiece(new Knight(Alliance.BLACK, 1));
        builder.setPiece(new Bishop(Alliance.BLACK, 2));
        builder.setPiece(new Queen(Alliance.BLACK, 3));
        builder.setPiece(new King(Alliance.BLACK, 4, true, true));
        builder.setPiece(new Bishop(Alliance.BLACK, 5));
        builder.setPiece(new Knight(Alliance.BLACK, 6));
        builder.setPiece(new Rook(Alliance.BLACK, 7));
        builder.setPiece(new Pawn(Alliance.BLACK, 8));
        builder.setPiece(new Pawn(Alliance.BLACK, 9));
        builder.setPiece(new Pawn(Alliance.BLACK, 10));
        builder.setPiece(new Pawn(Alliance.BLACK, 11));
        builder.setPiece(new Pawn(Alliance.BLACK, 12));
        builder.setPiece(new Pawn(Alliance.BLACK, 13));
        builder.setPiece(new Pawn(Alliance.BLACK, 14));
        builder.setPiece(new Pawn(Alliance.BLACK, 15));
        // تخطيط الابيض
        builder.setPiece(new Pawn(Alliance.WHITE, 48));
        builder.setPiece(new Pawn(Alliance.WHITE, 49));
        builder.setPiece(new Pawn(Alliance.WHITE, 50));
        builder.setPiece(new Pawn(Alliance.WHITE, 51));
        builder.setPiece(new Pawn(Alliance.WHITE, 52));
        builder.setPiece(new Pawn(Alliance.WHITE, 53));
        builder.setPiece(new Pawn(Alliance.WHITE, 54));
        builder.setPiece(new Pawn(Alliance.WHITE, 55));
        builder.setPiece(new Rook(Alliance.WHITE, 56));
        builder.setPiece(new Knight(Alliance.WHITE, 57));
        builder.setPiece(new Bishop(Alliance.WHITE, 58));
        builder.setPiece(new Queen(Alliance.WHITE, 59));
        builder.setPiece(new King(Alliance.WHITE, 60, true, true));
        builder.setPiece(new Bishop(Alliance.WHITE, 61));
        builder.setPiece(new Knight(Alliance.WHITE, 62));
        builder.setPiece(new Rook(Alliance.WHITE, 63));
        //الابيض يمشي
        builder.setMoveMaker(Alliance.WHITE);
        //بناء اللوحه او الطاوله
        return builder.build();
    }

   
    
    public static class Builder {
        
        Map<Integer ,Piece> boardConfig;
        Alliance nextMoveMaker;
        
        public Builder(){
        this.boardConfig= new HashMap<>();
        }
        
        //يضيف قطعة إلى الموقع الخاص بها على اللوحه
        public Builder setPiece(final Piece piece){
        
        this.boardConfig.put(piece.getPiecePosition(), piece);
        return this;
        }
        
        //يبني كائن لوحه باستخدام المعلومات المخزنة
    public Board build(){
     return new Board(this);
     }
    
    }
    
    
}
