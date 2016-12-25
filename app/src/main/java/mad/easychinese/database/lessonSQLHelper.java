package mad.easychinese.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MSI on 2016-08-20.
 */
public class lessonSQLHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "users.db";
    private static final String SQL_CREATE_ENTRIES_LESSON =
            "CREATE TABLE " + lessonContract.User.TABLE_NAME + "(" +
                    lessonContract.User.COLUMN_IMAGE + " IMAGE," +
                    lessonContract.User.COLUMN_DES + " TEXT," +
                    lessonContract.User.COLUMN_TYPE + " TEXT)";
    private static final String SQL_CREATE_ENTRIES_STROKE =
            "CREATE TABLE " + strokeContract.User.TABLE_NAME + "(" +
                    strokeContract.User.COLUMN_IMAGE + " IMAGE, " +
                    strokeContract.User.COLUMN_TEXT + " TEXT," +
                    strokeContract.User.COLUMN_PINYIN + " TEXT)";
    private static final String SQL_CREATE_ENTRIES_LEARDER =
            "CREATE TABLE " + learderboardContract.User.TABLE_NAME + "(" +
                    learderboardContract.User.COLUMN_NAME + " TEXT," +
                    learderboardContract.User.COLUMN_SCORE + " TEXT)";
    private static final String SQL_DELETE_ENTRIES_LESSON =
            "DROP TABLE IF EXISTS " + lessonContract.User.TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_LEARDER =
            "DROP TABLE IF EXISTS " + learderboardContract.User.TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_STROKE =
            "DROP TABLE IF EXISTS " + strokeContract.User.TABLE_NAME;
    public lessonSQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_LESSON);
        db.execSQL(SQL_CREATE_ENTRIES_STROKE);
        db.execSQL(SQL_CREATE_ENTRIES_LEARDER);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// This database is only a cache for online data, so its upgrade
// policy is to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES_LESSON);
        db.execSQL(SQL_DELETE_ENTRIES_STROKE);
        db.execSQL(SQL_DELETE_ENTRIES_LEARDER);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }
    public void dropTable(SQLiteDatabase db){
        db.execSQL(SQL_DELETE_ENTRIES_LESSON);
        db.execSQL(SQL_DELETE_ENTRIES_STROKE);
        db.execSQL(SQL_DELETE_ENTRIES_LEARDER);
        onCreate(db);
    }
    public void delete(SQLiteDatabase db){
        db.execSQL("DELETE FROM "+ lessonContract.User.TABLE_NAME);
        db.execSQL("DELETE FROM "+ strokeContract.User.TABLE_NAME);
    }
}
