import MyTask.*;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<MyTask> taskList;

    public TaskList(ArrayList<MyTask> taskList){
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<MyTask>();
    }

    public ArrayList<MyTask> getList(){
        return taskList;
    }

    public void addToList(MyTask task){
        this.taskList.add(task);
    }

    public void removeFromList(int index) {
        if (this.taskList.size() < index) {
            throw new IndexOutOfBoundsException();
        }
        this.taskList.remove(index);
    }

    public TaskList findFromList(String keyWord){
        TaskList outputList = new TaskList();
        for(int i=0;i<this.taskList.size();i++){
            String description = this.taskList.get(i).getDescription();
            MyTask temp = this.taskList.get(i);
            if(description.contains(keyWord)){
                outputList.addToList(temp);
            }
        }
        return outputList;
    }

}
