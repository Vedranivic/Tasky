package hr.ferit.vedran.tasky;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import javax.xml.validation.Schema;

/**
 * Created by vedra on 18.4.2018..
 */

public class TaskDBHelper extends SQLiteOpenHelper{

    private static TaskDBHelper mTaskDBHelper = null;

    public TaskDBHelper(Context context) {
        super(context.getApplicationContext(), Schema.DATABASE_NAME,null, Schema.SCHEMA_VERSION);
    }

    public static synchronized TaskDBHelper getInstance(Context context){
        if(mTaskDBHelper == null){
            mTaskDBHelper = new TaskDBHelper(context);
        }
        return mTaskDBHelper;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MY_TASKS);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_MY_TASKS);
        this.onCreate(db);
    }

    //SQL statements
    private static final String CREATE_TABLE_MY_TASKS = "CREATE TABLE " + Schema.TABLE_MY_TASKS +
            " (" + Schema.TITLE + " TEXT," + Schema.TEXT + " TEXT," + Schema.IMAGE + " INTEGER,"
            + Schema.CATEGORY + " TEXT," + Schema.DEADLINE + " TEXT);";
    private static final String DROP_TABLE_MY_TASKS = "DROP TABLE IF EXISTS " + Schema.TABLE_MY_TASKS;
    private static final String SELECT_ALL_TASKS = "SELECT " + Schema.TITLE + "," + Schema.TEXT + ","
            + Schema.CATEGORY + " FROM " + Schema.TABLE_MY_TASKS;

    // CRUD should be performed on another thread
    public void insertTask(Task task){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Schema.TITLE, task.getTitle());
        contentValues.put(Schema.TEXT, task.getText());
        contentValues.put(Schema.IMAGE, task.getPriorityImage());
        contentValues.put(Schema.CATEGORY, task.getCategory());
        contentValues.put(Schema.DEADLINE, task.getDeadLine());
        SQLiteDatabase writeableDatabase = this.getWritableDatabase();
        writeableDatabase.insert(Schema.TABLE_MY_TASKS, Schema.TITLE,contentValues);
        writeableDatabase.close();
    }
    public ArrayList<Task> getAllTasks(){
        SQLiteDatabase writableDatabase = this.getWritableDatabase();
        Cursor taskCursor = writableDatabase.rawQuery(SELECT_ALL_TASKS,null);
        ArrayList<Task> tasks = new ArrayList<>();
        if(taskCursor.moveToFirst()){
            do{
                String title = taskCursor.getString(0);
                String text = taskCursor.getString(1);
                int image = taskCursor.getInt(2);
                String category = taskCursor.getString(3);
                String deadline = taskCursor.getString(4);
                tasks.add(new Task(title, text, image, category, deadline));
            }while(taskCursor.moveToNext());
        }
        taskCursor.close();
        writableDatabase.close();
        return tasks;
    }
    public static class Schema{
        private static final int SCHEMA_VERSION = 1;
        private static final String DATABASE_NAME = "tasks.db";
        //A table to store tasks:
        static final String TABLE_MY_TASKS = "my_tasks";
        static final String TITLE = "title";
        static final String TEXT = "text";
        static final String IMAGE = "image";
        static final String CATEGORY = "category";
        static final String DEADLINE = "deadline";
    }

}
