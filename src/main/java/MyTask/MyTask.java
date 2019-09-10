package MyTask;

public class MyTask{

    protected String description;
    protected Boolean isDone;

    public MyTask(String in){
        this.description = in;
        this.isDone = false;
    }

    public String getStatusIcon(){
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone(){
        if(!isDone){
            isDone = true;
        }
    }

    public String getDescription(){
        return description;
    }

    public String toString(){
        //return description;
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}