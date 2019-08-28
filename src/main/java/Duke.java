import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Duke {
    public static void main(String[] args) throws IOException {
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
        String delete = "delete";
        //ArrayList<MyTask> mainList = new ArrayList<MyTask>();
        ArrayList<MyTask> mainList = readFile("duke.txt");
        ArrayList<String> stringList = new ArrayList<String>();

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
                System.out.println("\n");
            } else if (input.contains(done)){
                try{
                    //int index = input.charAt(input.length()-1) - '1';
                    String[] inputArray = input.split(" ", 2);
                    int index = Integer.parseInt(inputArray[1]) - 1;
                    mainList.get(index).markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n");
                    //System.out.println("[" + mainList.get(index).getStatusIcon() + "] " + mainList.get(index).toString());
                    System.out.println(mainList.get(index).toString());
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("\u2639 OOPS!!! The number of a done cannot be empty.\n");
                }
            } else if (input.contains(delete)) {
                try {
                    String[] inputArray = input.split(" ", 2);
                    int index = Integer.parseInt(inputArray[1]) - 1;
                    MyTask temp = mainList.get(index);
                    mainList.remove(index);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(temp.toString());
                    System.out.println("Now you have " + mainList.size() + " tasks in the list.\n");
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("\u2639 OOPS!!! The number of a delete cannot be empty.\n");
                }
            } else {
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
                        System.out.println("\u2639 OOPS!!! The description of a todo cannot be empty.\n");
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
                        System.out.println("\u2639 OOPS!!! The description/by of a deadline cannot be empty.\n");
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
                        System.out.println("\u2639 OOPS!!! The description/at of an event cannot be empty.\n");
                        continue;
                    }
                } else {
                    System.out.println("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                    continue;
                }
                System.out.println("Got it. I've added this task:");
                System.out.println(newInput.toString());
                System.out.println("Now you have " + mainList.size() + " tasks in the list.\n");
            }
            //FileWriter fileWriter = new FileWriter("duke.txt");
            //PrintWriter printWriter = new PrintWriter("duke.txt", "");
            /*for(int i=0;i<mainList.size();i++){
                String temp = mainList.get(i).toString();
                stringList.add(temp);
            }
            Path file = Paths.get("duke.txt");
            Files.write(file, stringList, StandardCharsets.UTF_8);*/
            saveFile(mainList);
        }
    }
    public static void saveFile(ArrayList<MyTask> list) throws IOException {
        ArrayList<String> stringList = new ArrayList<String>();
        for(int i=0;i<list.size();i++){
            String temp = list.get(i).toString();
            temp = temp.replace("[", "");
            //System.out.println(temp);
            String[] tempArray = temp.split("]", 3);
            //System.out.println
            if(tempArray[1].equals("\u2713")){
                tempArray[1] = "1";
            } else {
                tempArray[1] = "0";
            }
            temp = tempArray[0] + "/" + tempArray[1] + "/";
            if(tempArray[0].equals("T")){
                temp = temp + tempArray[2].strip() + "\n";
            } else {
                tempArray = tempArray[2].split("by:");
                tempArray[0] = tempArray[0].replace("(", " ");
                tempArray[1] = tempArray[1].replace(")", " ");
                temp = temp + tempArray[0].strip() + "/" + tempArray[1].strip() + "\n";
            }
            stringList.add(temp);
        }
        Path file = Paths.get("duke.txt");
        Files.write(file,stringList, StandardCharsets.UTF_8);
    }
    public static ArrayList<MyTask> readFile(String fileName) {
        List<String> stringList = Collections.emptyList();
        try{
            stringList = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e){
            System.out.println("Unable to read file.");
        }
        ArrayList<MyTask> mainList = new ArrayList<MyTask>();
        MyTask newTask;
        for(int i=0;i<stringList.size();i++){
            String temp = stringList.get(i);
            //System.out.println(temp);
            String[] tempArray = temp.split("/");
            //System.out.println(tempArray);
            if (tempArray[0].equals("T")){
                newTask = new ToDo(tempArray[2]);
                if (tempArray[1].equals("1")){
                    newTask.markAsDone();
                }
                mainList.add(newTask);
            } else if (tempArray[0].equals("D")){
                newTask = new Deadline(tempArray[2], tempArray[3]);
                if (tempArray[1].equals("1")){
                    newTask.markAsDone();
                }
                mainList.add(newTask);
            } else if (tempArray[0].equals("E")){
                newTask = new Events(tempArray[2], tempArray[3]);
                if (tempArray[1].equals("1")){
                    newTask.markAsDone();
                }
                mainList.add(newTask);
            }
        }
        return mainList;
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

    public String getDescription(){
        return description;
    }

    public String toString(){
        //return description;
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
class Deadline extends MyTask {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy(){
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getBy() + ")";
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

    public String getBy(){
        return by;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (by: " + this.getBy() + ")";
    }
}
