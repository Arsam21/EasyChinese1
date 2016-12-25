package mad.easychinese.database;


import android.provider.BaseColumns;
import android.provider.MediaStore;

public class lessonContract {
    public lessonContract(){}
    public static abstract class User implements BaseColumns {

        public static final String TABLE_NAME ="lesson";
        public static final String COLUMN_IMAGE ="image";
        public static final String COLUMN_DES ="des";
        public static final String COLUMN_TYPE ="type";
    }
}
