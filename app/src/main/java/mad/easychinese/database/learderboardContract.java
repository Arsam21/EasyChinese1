package mad.easychinese.database;

import android.provider.BaseColumns;

/**
 * Created by MSI on 2016-08-25.
 */
public class learderboardContract {
    public learderboardContract(){}
    public static abstract class User implements BaseColumns {

        public static final String TABLE_NAME ="learderboard";
        public static final String COLUMN_NAME ="name";
        public static final String COLUMN_SCORE ="score";
    }
}
