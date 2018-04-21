package hr.ferit.vedran.tasky;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vedra on 17.4.2018..
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    class TaskViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTaskTitle, tvTaskCategory, tvTaskDeadLine;
        private final RelativeLayout itemBack;

        private TaskViewHolder(View itemView, final TaskClickCallback tcc) {
            super(itemView);
            tvTaskTitle = itemView.findViewById(R.id.tvTaskTitle);
            tvTaskCategory = itemView.findViewById(R.id.tvTaskCategory);
            tvTaskDeadLine = itemView.findViewById(R.id.tvTaskDeadLine);
            itemBack = itemView.findViewById(R.id.itemBack);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    return tcc.onLongClick(mTasks.get(getAdapterPosition()));
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tcc.onClick(mTasks.get(getAdapterPosition()));
                }
            });

        }
    }
    private final LayoutInflater mInflater;
    private List<Task> mTasks; // Cached copy of tasks
    private TaskClickCallback taskClickCallback;

    TaskAdapter(Context context, TaskClickCallback tcc) {
        this.mInflater = LayoutInflater.from(context);
        this.taskClickCallback = tcc;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(itemView,taskClickCallback);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        if (mTasks != null) {
            Task current = mTasks.get(position);
            holder.tvTaskTitle.setText(current.getTitle());
            holder.tvTaskCategory.setText(current.getCategory());
            holder.tvTaskDeadLine.setText(String.valueOf(current.getDeadLine()));
            holder.itemBack.setBackgroundColor(current.getPriority());
            holder.tvTaskTitle.setTextColor(current.getPriority());
        } else {
            holder.tvTaskTitle.setText(R.string.dataNotReadyText);
        }
    }

    void setTasks(List<Task> tasks){
        mTasks = tasks;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mTasks != null)
            return mTasks.size();
        else return 0;
    }

    public Task getTask(int pos){
        return mTasks.get(pos);
    }
}
