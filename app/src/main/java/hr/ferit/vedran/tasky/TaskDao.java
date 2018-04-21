package hr.ferit.vedran.tasky;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by vedra on 19.4.2018..
 */

@Dao
public interface TaskDao {
    @Insert
    void insert(Task task);

    @Delete
    void delete(Task task);

    @Query("DELETE from task_table")
    void deleteAll();

    @Query("SELECT * from task_table ORDER BY priority ASC")
    LiveData<List<Task>> getAllTasks();

}
