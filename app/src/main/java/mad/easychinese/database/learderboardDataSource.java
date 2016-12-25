package mad.easychinese.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class learderboardDataSource {

    private SQLiteDatabase database;
    private lessonSQLHelper dbHelper;


    private String[] allColumn = {
            learderboardContract.User.COLUMN_NAME,
            learderboardContract.User.COLUMN_SCORE
    };
    public learderboardDataSource(Context context){
        dbHelper = new lessonSQLHelper(context);
    }
    public void open() throws SQLException {

        database = dbHelper.getWritableDatabase();

    }
    public void close(){
        dbHelper.close();
    }
    public void insertLeaderBoard(leaderboardRecord leaderboardRecord){

        ContentValues values = new ContentValues();
        values.put(learderboardContract.User.COLUMN_NAME, leaderboardRecord.getname());
        values.put(learderboardContract.User.COLUMN_SCORE, leaderboardRecord.getscore());
        database = dbHelper.getWritableDatabase();
        database.insert(learderboardContract.User.TABLE_NAME, null, values);
        database.close();
    }
    public List<leaderboardRecord> getBoard(){

        List<leaderboardRecord> records = new ArrayList<leaderboardRecord>();
        String query = "Select * FROM " + learderboardContract.User.TABLE_NAME +" ORDER BY CAST(score AS INTEGER) DESC LIMIT 5";

        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            leaderboardRecord leaderboardRecord = new leaderboardRecord();
            leaderboardRecord.setname(cursor.getString(0));
            leaderboardRecord.setscore(cursor.getInt(1));
            records.add(leaderboardRecord);
            cursor.moveToNext();
        }
        cursor.close();
        return records;
    }
    public void dropTable(){
        dbHelper.dropTable(database);
    }
    public void delete(){
        dbHelper.delete(database);
    }
}
