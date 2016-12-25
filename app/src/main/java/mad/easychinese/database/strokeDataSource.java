package mad.easychinese.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class strokeDataSource {

    private SQLiteDatabase database;
    private lessonSQLHelper dbHelper;
    private String[] allColumn = {
            strokeContract.User.COLUMN_IMAGE,
            strokeContract.User.COLUMN_TEXT,
            strokeContract.User.COLUMN_PINYIN
    };
    public strokeDataSource(Context context){
        dbHelper = new lessonSQLHelper(context);
    }
    public void open() throws SQLException {

        database = dbHelper.getWritableDatabase();

    }
    public void close(){
        dbHelper.close();
    }
    public void insertStroke(strokeRecord strokeRecord){

        ContentValues values = new ContentValues();
        values.put(strokeContract.User.COLUMN_IMAGE, strokeRecord.getImage());
        values.put(strokeContract.User.COLUMN_TEXT, strokeRecord.getText());
        values.put(strokeContract.User.COLUMN_PINYIN, strokeRecord.getPinyin());
        database = dbHelper.getWritableDatabase();
        database.insert(strokeContract.User.TABLE_NAME, null, values);
        database.close();
    }
    public List<strokeRecord> getAllStroke(){

        List<strokeRecord> records = new ArrayList<strokeRecord>();
        Cursor cursor = database.query(strokeContract.User.TABLE_NAME, allColumn , null,
                null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            strokeRecord strokeRecord = new strokeRecord();
            strokeRecord.setImage(cursor.getInt(0));
            strokeRecord.setText(cursor.getString(1));
            strokeRecord.setPinyin(cursor.getString(2));
            records.add(strokeRecord);
            cursor.moveToNext();
        }
        cursor.close();
        return records;
    }
    public void dropTable(){
        dbHelper.dropTable(database);
    }
    public void delete(){
        database = dbHelper.getWritableDatabase();
        String query = "DELETE FROM stroke ";
        //     String query = "Select * FROM " + lessonContract.User.TABLE_NAME;
        database.execSQL(query);
        database.close();
    }
}
