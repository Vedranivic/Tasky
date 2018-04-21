package hr.ferit.vedran.tasky;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by vedra on 17.4.2018..
 */
@Entity(tableName = "task_table")
public class Task {

    @PrimaryKey
    @NonNull
    private String title;

    private String text;

    private int priority;

    private String category;

    private String deadLine;

    public Task(String title, String text, int priority, String category, String deadLine){
        this.title = title;
        this.text = text;
        this.priority = priority;
        this.category = category;
        this.deadLine = deadLine;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        priority = priority;
    }

    @NonNull
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }
}
