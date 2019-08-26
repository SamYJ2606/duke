import java.util.*;

public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        Scanner sc = new Scanner(System.in);
        String bye = "bye";
        String list = "list";
        String done = "done";
        ArrayList<MyTask> mainList = new ArrayList<MyTask>();

        System.out.println("Hello! Im Duke");
        System.out.println("What can I do for you?\n");

        while(true){
            String input = sc.nextLine();
            if(input.equals(bye)){
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            } else if(input.equals(list)){
                //System.out.println(mainList.toString());
                System.out.println("Here are the tasks in your list:");
                for(int i=0;i<mainList.size();i++){
                    int number = i+1;
                    System.out.println( number + ".[" + mainList.get(i).getStatusIcon() + "] " + mainList.get(i).toString());
                }
            } else if (input.contains(done)){
                int index = input.charAt(input.length()-1) - '1';
                mainList.get(index).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + mainList.get(index).getStatusIcon() + "] " + mainList.get(index).toString());
            } else {
                //mainList.add(input);
                MyTask newInput = new MyTask(input);
                mainList.add(newInput);
                System.out.println("added: " + input);
            }
        }
    }
}
class MyTask{
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

    public String toString(){
        return description;
    }
}
