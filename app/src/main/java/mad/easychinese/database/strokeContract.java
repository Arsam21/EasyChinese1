package mad.easychinese.database;


import android.provider.BaseColumns;
import android.provider.MediaStore;

public class strokeContract {
    public strokeContract(){}
    public static abstract class User implements BaseColumns {

        public static final String TABLE_NAME ="stroke";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_TEXT ="text";
        public static final String COLUMN_PINYIN ="pinyin";
    }
}
