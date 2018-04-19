package hr.ferit.vedran.tasky;

import java.util.Date;

/**
 * Created by vedra on 17.4.2018..
 */

public class Task {
    private String Title;
    private String Text;
    private int Image;
    private String Category;
    private String DeadLine;

    public Task(){}
    public Task(String title, String text, int img, String category, String ddl){
        Title = title;
        Text = text;
        Image = img;
        Category = category;
        DeadLine = ddl;
    }
    public String getTitle(){
        return Title;
    }
    public String getText(){
        return Text;
    }
    public int getPriorityImage(){
        return Image;
    }
    public String getCategory(){
        return Category;
    }
    public String getDeadLine(){
        return DeadLine;
    }
}
