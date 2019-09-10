import MyTask.Deadline;
import MyTask.Events;
import MyTask.MyTask;
import MyTask.ToDo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class Duke {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        String bye = "bye";
        String list = "list";
        String done = "done";
        String todo = "todo";
        String deadline = "deadline";
        String event = "event";
        String delete = "delete";
        String find = "find";
        //ArrayList<MyTask.MyTask> mainList = new ArrayList<MyTask.MyTask>();
        Storage storage = new Storage();
        ArrayList<MyTask> mainList = storage.readFile("duke.txt");
        ArrayList<String> stringList = new ArrayList<String>();

        System.out.println("____________________________________________________________");
        System.out.println("Hello! Im Duke");
        System.out.println("What can I do for you?\n");
        System.out.println("____________________________________________________________");

        while(true) {
            String input = sc.nextLine();
            if (input.equals(bye)) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!\n");
                System.out.println("____________________________________________________________");
                break;
            } else if (input.equals(list)) {
                //System.out.println(mainList.toString());
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < mainList.size(); i++) {
                    int number = i + 1;
                    //System.out.println( number + ".[" + mainList.get(i).getStatusIcon() + "] " + mainList.get(i).toString());
                    System.out.println(number + "." + mainList.get(i).toString());
                }
                //System.out.println("\n");
                System.out.println("____________________________________________________________");
            } else if (input.contains(done)) {
                try {
                    //int index = input.charAt(input.length()-1) - '1';
                    String[] inputArray = input.split(" ", 2);
                    int index = Integer.parseInt(inputArray[1]) - 1;
                    mainList.get(index).markAsDone();
                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done:\n");
                    System.out.println("____________________________________________________________");
                    //System.out.println("[" + mainList.get(index).getStatusIcon() + "] " + mainList.get(index).toString());
                    System.out.println(mainList.get(index).toString());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("____________________________________________________________");
                    System.out.println("\u2639 OOPS!!! The number of a done cannot be empty.\n");
                    System.out.println("____________________________________________________________");
                }
            } else if (input.contains(delete)) {
                try {
                    String[] inputArray = input.split(" ", 2);
                    int index = Integer.parseInt(inputArray[1]) - 1;
                    MyTask temp = mainList.get(index);
                    mainList.remove(index);
                    System.out.println("____________________________________________________________");
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(temp.toString());
                    System.out.println("Now you have " + mainList.size() + " tasks in the list.\n");
                    System.out.println("____________________________________________________________");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("____________________________________________________________");
                    System.out.println("\u2639 OOPS!!! The number of a delete cannot be empty.\n");
                    System.out.println("____________________________________________________________");
                }
            } else if(input.contains(find)){
                try{
                    ArrayList<MyTask> outputList = new ArrayList<MyTask>();
                    String[] inputArray = input.split(" ", 2);
                    for(int i=0;i<mainList.size();i++){
                        String description = mainList.get(i).getDescription();
                        MyTask temp = mainList.get(i);
                        if(description.contains(inputArray[1])){
                            outputList.add(temp);
                        }
                    }
                    if(outputList.size()>0){
                        System.out.println("____________________________________________________________");
                        System.out.println("Here are the matching tasks in your list:");
                        for(int j=0;j<outputList.size();j++){
                            int number = j + 1;
                            System.out.println(number + "." + outputList.get(j).toString());
                        }
                        System.out.println("____________________________________________________________");
                    }
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("____________________________________________________________");
                    System.out.println("\u2639 OOPS!!! The number of a delete cannot be empty.\n");
                    System.out.println("____________________________________________________________");
                }
            } else {
                //mainList.add(input);
                //MyTask.MyTask newInput = new MyTask.MyTask(input);
                //mainList.add(newInput);
                //System.out.println("added: " + input);
                MyTask newInput;
                if(input.contains(todo)){
                    try {
                        String[] inputArray = input.split(" ", 2);
                        //MyTask.MyTask newInput = new MyTask.ToDo(inputArray[1]);
                        newInput = new ToDo(inputArray[1]);
                        mainList.add(newInput);
                    } catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("____________________________________________________________");
                        System.out.println("\u2639 OOPS!!! The description of a todo cannot be empty.\n");
                        System.out.println("____________________________________________________________");
                        continue;
                    }
                } else if (input.contains(deadline)){
                    try {
                        String[] inputArray = input.split(" ", 2);
                        inputArray = inputArray[1].split("/by", 2);
                        //MyTask.MyTask newInput = new MyTask.Deadline(inputArray[0], inputArray[1]);
                        newInput = new Deadline(inputArray[0], inputArray[1]);
                        mainList.add(newInput);
                    } catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("____________________________________________________________");
                        System.out.println("\u2639 OOPS!!! The description/by of a deadline cannot be empty.\n");
                        System.out.println("____________________________________________________________");
                        continue;
                    }
                } else if (input.contains(event)){
                    try {
                        String[] inputArray = input.split(" ", 2);
                        inputArray = inputArray[1].split("/at", 2);
                        //MyTask.MyTask newInput = new MyTask.Events(inputArray[0], inputArray[1]);
                        newInput = new Events(inputArray[0], inputArray[1]);
                        mainList.add(newInput);
                        //pointerInput = newInput;
                    } catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("____________________________________________________________");
                        System.out.println("\u2639 OOPS!!! The description/at of an event cannot be empty.\n");
                        System.out.println("____________________________________________________________");
                        continue;
                    }
                } else {
                    System.out.println("____________________________________________________________");
                    System.out.println("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                    System.out.println("____________________________________________________________");
                    continue;
                }
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(newInput.toString());
                System.out.println("Now you have " + mainList.size() + " tasks in the list.\n");
                System.out.println("____________________________________________________________");
            }
            storage.saveFile(mainList);
        }
    }
}