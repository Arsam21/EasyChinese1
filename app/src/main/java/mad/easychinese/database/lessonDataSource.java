package mad.easychinese.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class lessonDataSource {

    private SQLiteDatabase database;
    private lessonSQLHelper dbHelper;

    private String animal = "animal";
    private String fruit = "fruit";
    private String school = "school";
    private String plant = "plant";

    private String[] allColumn = {
            lessonContract.User.COLUMN_IMAGE,
            lessonContract.User.COLUMN_DES,
            lessonContract.User.COLUMN_TYPE
    };
    public lessonDataSource(Context context){
        dbHelper = new lessonSQLHelper(context);
    }
    public void open() throws SQLException {

        database = dbHelper.getWritableDatabase();

    }
    public void close(){
        dbHelper.close();
    }
    public void insertLesson(lessonRecord lessonRecord){

        ContentValues values = new ContentValues();
        values.put(lessonContract.User.COLUMN_IMAGE, lessonRecord.getImage());
        values.put(lessonContract.User.COLUMN_DES, lessonRecord.getDes());
        values.put(lessonContract.User.COLUMN_TYPE, lessonRecord.gettype());
        database = dbHelper.getWritableDatabase();
        database.insert(lessonContract.User.TABLE_NAME, null, values);
        database.close();
    }
    public List<lessonRecord> getlessonAnimal(){

        List<lessonRecord> records = new ArrayList<lessonRecord>();
       String query = "Select * FROM " + lessonContract.User.TABLE_NAME + " WHERE " + lessonContract.User.COLUMN_TYPE + " =  \"" + animal + "\"";
   //     String query = "Select * FROM " + lessonContract.User.TABLE_NAME;
        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            lessonRecord lessonRecord = new lessonRecord();
            lessonRecord.setImage(cursor.getInt(0));
            lessonRecord.setDes(cursor.getString(1));
            lessonRecord.settype(cursor.getString(2));
            records.add(lessonRecord);
            cursor.moveToNext();
        }
        cursor.close();
        return records;
    }
    public List<lessonRecord> getlessonFruit(){

        List<lessonRecord> records = new ArrayList<lessonRecord>();
        String query = "Select * FROM " + lessonContract.User.TABLE_NAME + " WHERE " + lessonContract.User.COLUMN_TYPE + " =  \"" + fruit + "\"";
        //     String query = "Select * FROM " + lessonContract.User.TABLE_NAME;
        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            lessonRecord lessonRecord = new lessonRecord();
            lessonRecord.setImage(cursor.getInt(0));
            lessonRecord.setDes(cursor.getString(1));
            lessonRecord.settype(cursor.getString(2));
            records.add(lessonRecord);
            cursor.moveToNext();
        }
        cursor.close();
        return records;
    }
    public List<lessonRecord> getlessonschool(){

        List<lessonRecord> records = new ArrayList<lessonRecord>();
        String query = "Select * FROM " + lessonContract.User.TABLE_NAME + " WHERE " + lessonContract.User.COLUMN_TYPE + " =  \"" + school + "\"";
        //     String query = "Select * FROM " + lessonContract.User.TABLE_NAME;
        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            lessonRecord lessonRecord = new lessonRecord();
            lessonRecord.setImage(cursor.getInt(0));
            lessonRecord.setDes(cursor.getString(1));
            lessonRecord.settype(cursor.getString(2));
            records.add(lessonRecord);
            cursor.moveToNext();
        }
        cursor.close();
        return records;
    }
    public List<lessonRecord> getlessonplant(){

        List<lessonRecord> records = new ArrayList<lessonRecord>();
        String query = "Select * FROM " + lessonContract.User.TABLE_NAME + " WHERE " + lessonContract.User.COLUMN_TYPE + " =  \"" + plant + "\"";
        //     String query = "Select * FROM " + lessonContract.User.TABLE_NAME;
        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            lessonRecord lessonRecord = new lessonRecord();
            lessonRecord.setImage(cursor.getInt(0));
            lessonRecord.setDes(cursor.getString(1));
            lessonRecord.settype(cursor.getString(2));
            records.add(lessonRecord);
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
        String query = "DELETE FROM lesson ";
        //     String query = "Select * FROM " + lessonContract.User.TABLE_NAME;
        database.execSQL(query);
        database.close();
    }
}
