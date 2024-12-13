
package com.chess.engine.board;

/**
 *
 * @author elffe
 */
public class BoardUtils {
//مصفوفه تشير إلى الأعمدة من الأول إلى الثامن على لوحة الشطرنج
    public static final boolean[] FIRST_COLUMN=initColumn(0);
    public static final boolean[] SECOND_COLUMN=initColumn(1);
    public static final boolean[] SEVENTH_COLUMN=initColumn(6);
    public static final boolean[] EIGHTH_COLUMN=initColumn(7);

    //مربعات بداية تحرك الجنود
     public static final boolean[] SECOND_ROW=initRow(8);
    public static final boolean[] SEVENTH_ROW=initRow(48);
    
    //إجمالي عدد المربعات على لوحة الشطرنج
    public static final int NUM_TILES=64;
    public static final int NUM_TILES_PER_ROW=8;
    
    private BoardUtils(){
     throw new RuntimeException("You cannot instantiate");
    
    }
    
    
    // إنشاء مصفوفة منطقية تمثل عمودا معينا كيف تعمل تبدأ من أول مربع في العمود المحدد بالمدخل  تضيف ثمانيه  عدد المربعات لكل صف للوصول إلى المربع التالي في نفس العمود تستمر حتى تتجاوز اربع وستين نهاية اللوحه
        private static boolean[] initColumn(int columnNumber) {
           
        final boolean [] column = new boolean[64];
        do{ 
        column[columnNumber]=true;
        columnNumber+=NUM_TILES_PER_ROW;
        }
        while (columnNumber <64);

        return column;

    }
        //مصفوفة تحتوي على القيم المنطقية لتحديد مربعات الصف
        public static boolean[] initRow(int rowNumber ){
        final boolean[] row = new boolean [NUM_TILES];
        do{
        row[rowNumber]=true;
        rowNumber++;
        }while(rowNumber % NUM_TILES_PER_ROW !=0);
        return row;
        }
        
//التحقق مما إذا كانت إحداثيات المربع ضمن حدود رقعة الشطرنج
    public static boolean isVaildTileCoordinate(final int coordinate) {

     return coordinate >=0 && coordinate <NUM_TILES;   
    }
}