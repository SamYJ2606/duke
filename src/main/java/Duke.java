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
        String todo = "todo";
        String deadline = "deadline";
        String event = "event";
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
                    //System.out.println( number + ".[" + mainList.get(i).getStatusIcon() + "] " + mainList.get(i).toString());
                    System.out.println(number + "." + mainList.get(i).toString());
                }
            } else if (input.contains(done)){
                int index = input.charAt(input.length()-1) - '1';
                mainList.get(index).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                //System.out.println("[" + mainList.get(index).getStatusIcon() + "] " + mainList.get(index).toString());
                System.out.println(mainList.get(index).toString());
            } /*else if(input.contains(todo)){
                String[] inputArray = input.split(" ", 2);
                MyTask newInput = new ToDo(inputArray[1]);
                mainList.add(newInput);
                System.out.println("Got it. I've added this task:");
                System.out.println(newInput.toString());
                System.out.println("Now you have " + mainList.size() + " tasks in the list.");
            } else if(input.contains(deadline)){
                String[] inputArray = input.split(" ", 2);
                inputArray = inputArray[1].split("/by", 2);
                MyTask newInput = new Deadline(inputArray[0], inputArray[1]);
                mainList.add(newInput);
                System.out.println("Got it. I've added this task:");
                System.out.println(newInput.toString());
                System.out.println("Now you have " + mainList.size() + " tasks in the list.");
            } else if(input.contains(event)){
                String[] inputArray = input.split(" ", 2);
                inputArray = inputArray[1].split("/at", 2);
                MyTask newInput = new Events(inputArray[0], inputArray[1]);
                mainList.add(newInput);
                System.out.println("Got it. I've added this task:");
                System.out.println(newInput.toString());
                System.out.println("Now you have " + mainList.size() + " tasks in the list.");
            }*/ else {
                //mainList.add(input);
                //MyTask newInput = new MyTask(input);
                //mainList.add(newInput);
                //System.out.println("added: " + input);
                MyTask newInput;
                if(input.contains(todo)){
                    try {
                        String[] inputArray = input.split(" ", 2);
                        //MyTask newInput = new ToDo(inputArray[1]);
                        newInput = new ToDo(inputArray[1]);
                        mainList.add(newInput);
                    } catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("\u2639 OOPS!!! The description of a todo cannot be empty.");
                        continue;
                    }
                } else if (input.contains(deadline)){
                    try {
                        String[] inputArray = input.split(" ", 2);
                        inputArray = inputArray[1].split("/by", 2);
                        //MyTask newInput = new Deadline(inputArray[0], inputArray[1]);
                        newInput = new Deadline(inputArray[0], inputArray[1]);
                        mainList.add(newInput);
                    } catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("\u2639 OOPS!!! The description/by of a deadline cannot be empty.");
                        continue;
                    }
                } else if (input.contains(event)){
                    try {
                        String[] inputArray = input.split(" ", 2);
                        inputArray = inputArray[1].split("/at", 2);
                        //MyTask newInput = new Events(inputArray[0], inputArray[1]);
                        newInput = new Events(inputArray[0], inputArray[1]);
                        mainList.add(newInput);
                        //pointerInput = newInput;
                    } catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("\u2639 OOPS!!! The description/at of an event cannot be empty.");
                        continue;
                    }
                } else {
                    System.out.println("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                    continue;
                }
                System.out.println("Got it. I've added this task:");
                System.out.println(newInput.toString());
                System.out.println("Now you have " + mainList.size() + " tasks in the list.");
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
        //return description;
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
class Deadline extends MyTask {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

class ToDo extends MyTask {

    public ToDo(String description){
        super(description);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}

class Events extends MyTask{

    protected String by;

    public Events(String description, String by){
        super(description);
        this.by = by;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (by: " + by + ")";
    }
}
