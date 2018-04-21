package hr.ferit.vedran.tasky;

import android.app.Application;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewTaskActivity extends AppCompatActivity {

    @BindView(R.id.etTitle) EditText etTitle;
    @BindView(R.id.etText) EditText etText;
    @BindView(R.id.etCategory) EditText etCategory;
    @BindView(R.id.etDeadline) EditText etDeadline;
    @BindView(R.id.spPriority) Spinner spPriority;
    @BindView(R.id.bSaveTask) Button bSaveTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bSaveTask)
    public void saveTask(){
        Intent replyIntent = new Intent();

        if(TextUtils.isEmpty(etTitle.getText())){
            setResult(RESULT_CANCELED, replyIntent);
            Toast.makeText(
                    getApplicationContext(),
                    R.string.errorEmpty,
                    Toast.LENGTH_SHORT).show();
        }

        else{
            String title = etTitle.getText().toString();
            String text = etText.getText().toString();
            String category = etCategory.getText().toString();
            String deadline = etDeadline.getText().toString();
            String pSelect = spPriority.getSelectedItem().toString();
            int priority = getResources().getColor(R.color.priorityHigh);
            switch (pSelect){
                case "HIGH": priority = getResources().getColor(R.color.priorityHigh); break;
                case "MEDIUM": priority = getResources().getColor(R.color.priorityMedium); break;
                case "LOW": priority = getResources().getColor(R.color.priorityLow); break;
            }
            replyIntent.putExtra("title",title);
            replyIntent.putExtra("text",text);
            replyIntent.putExtra("category",category);
            replyIntent.putExtra("deadline",deadline);
            replyIntent.putExtra("priority",priority);
            setResult(RESULT_OK,replyIntent);
            finish();
        }

    }
}
