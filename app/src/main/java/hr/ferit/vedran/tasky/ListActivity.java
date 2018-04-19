package hr.ferit.vedran.tasky;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListActivity extends AppCompatActivity {

    RecyclerView rvTaskList;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.ItemDecoration mItemDecoration;
    @BindView(R.id.fabAddTask) FloatingActionButton fabAddTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        this.setUpUI();
    }

    private void setUpUI() {
        Context context = getApplicationContext();
        this.rvTaskList = (RecyclerView) this.findViewById(R.id.rvTaskList);
        ArrayList<Task> tasks = this.loadTasks();
        TaskAdapter taskAdapter = new TaskAdapter(tasks);
        this.mLayoutManager = new LinearLayoutManager(context);
        this.mItemDecoration = new DividerItemDecoration(context, 0);
        this.rvTaskList.addItemDecoration(this.mItemDecoration);
        this.rvTaskList.setLayoutManager(this.mLayoutManager);
        this.rvTaskList.setAdapter(taskAdapter);
    }

    @OnClick(R.id.fabAddTask)
    public void addTask(){
        Task task = new Task("RMA homework","Finish third RMA homework - Tasky",R.color.priorityHigh,"Faculty","19/4/2018");
        TaskDBHelper.getInstance(getApplicationContext()).insertTask(task);
        TaskAdapter adapter = (TaskAdapter) rvTaskList.getAdapter();
        adapter.insertTask(task);
    }

    private ArrayList<Task> loadTasks() {
       /* ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("RMA homework","Finish third RMA homework - Tasky",R.color.priorityHigh,"Faculty","19/4/2018"));
        tasks.add(new Task("SRS paper","Write the paper for SRS, topic: SHA1 hash cypher",R.color.priorityLow,"Faculty","4/5/2018"));
        tasks.add(new Task("Buy Groceries","Buy peanuts, milk and eggs",R.color.priorityHigh,"Private","18/4/2018"));

        return tasks;*/
       return TaskDBHelper.getInstance(this).getAllTasks();
    }
}
