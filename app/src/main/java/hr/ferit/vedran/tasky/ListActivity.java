package hr.ferit.vedran.tasky;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemLongClick;
import butterknife.OnItemSelected;
import butterknife.OnLongClick;

public class ListActivity extends AppCompatActivity {

    @BindView(R.id.rvTaskList) RecyclerView rvTasks;
    @BindView(R.id.fabAddTask) FloatingActionButton fabAddTask;
    @BindView(R.id.listBack) CoordinatorLayout listBack;

    private TaskViewModel mTaskViewModel;
    private TaskClickCallback tcc = new TaskClickCallback() {
        @Override
        public boolean onLongClick(Task task) {
            mTaskViewModel.delete(task);
            return false;
        }
        @Override
        public void onClick(Task task) {
            Toast.makeText(
                    getApplicationContext(),
                    "Notes: " + task.getText(),
                    Toast.LENGTH_LONG)
                    .show();
        }

    };
    TaskAdapter adapter;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        adapter = new TaskAdapter(this, tcc);
        rvTasks.setAdapter(adapter);
        rvTasks.setLayoutManager(new LinearLayoutManager(this));

        mTaskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
        mTaskViewModel.getAllTasks()
                      .observe(this, new Observer<List<Task>>() {
                            @Override
                             public void onChanged(@Nullable final List<Task> tasks) {
                                adapter.setTasks(tasks);
                                if (adapter.getItemCount() == 0) {
                                    listBack.setBackground(getResources().getDrawable(R.drawable.empty_state));
                                }
                                else{
                                    listBack.setBackgroundColor(getResources().getColor(R.color.listDefault));
                                }
                             }
                      });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent entry) {
        super.onActivityResult(requestCode, resultCode, entry);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Task task = new Task(
                    entry.getStringExtra("title"),
                    entry.getStringExtra("text"),
                    entry.getIntExtra("priority",R.color.priorityLow),
                    entry.getStringExtra("category"),
                    entry.getStringExtra("deadline")
                    );
            mTaskViewModel.insert(task);
        }
    }

    @OnClick(R.id.fabAddTask)
    public void addTask(){
        Intent intent = new Intent(ListActivity.this, NewTaskActivity.class);
        startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
    }


}
