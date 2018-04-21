package hr.ferit.vedran.tasky;

/**
 * Created by vedra on 21.4.2018..
 */

public interface TaskClickCallback {
    boolean onLongClick(Task task);
    void onClick(Task task);
}
