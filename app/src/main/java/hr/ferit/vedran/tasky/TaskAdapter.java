package hr.ferit.vedran.tasky;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by vedra on 17.4.2018..
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    ArrayList<Task> mTasks;

    public TaskAdapter(ArrayList<Task> tasks){
        mTasks = tasks;
    }

    @Override
    public int getItemCount() {
        return this.mTasks.size();
    }

    public void insertTask(Task task) {
        this.mTasks.add(task);
        this.notifyDataSetChanged();
    }

    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View taskView = inflater.inflate(R.layout.item_task, parent, false);
        ViewHolder taskViewHolder = new ViewHolder(taskView);
        return taskViewHolder;
    }

    @Override
    public void onBindViewHolder(TaskAdapter.ViewHolder holder, int position) {
        Task task = this.mTasks.get(position);
        holder.tvTaskTitle.setText(task.getTitle());
        holder.tvTaskCategory.setText(task.getCategory());
        holder.tvTaskDeadLine.setText(String.valueOf(task.getDeadLine()));
        holder.ivPriority.setBackgroundColor(task.getPriorityImage());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTaskTitle, tvTaskCategory, tvTaskDeadLine;
        public ImageView ivPriority;
        public ViewHolder(View itemView) {
            super(itemView);
            this.tvTaskTitle = (TextView) itemView.findViewById(R.id.tvTaskTitle);
            this.tvTaskCategory = (TextView) itemView.findViewById(R.id.tvTaskCategory);
            this.tvTaskDeadLine = (TextView) itemView.findViewById(R.id.tvTaskDeadLine);
            this.ivPriority = (ImageView) itemView.findViewById(R.id.ivPriority);
        }
    }
}
