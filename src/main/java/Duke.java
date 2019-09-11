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

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath){
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(this.storage.readFile());
    }

    public void run() throws IOException {
        Scanner sc = new Scanner(System.in);
        String bye = "bye";
        String list = "list";
        String done = "done";
        String todo = "todo";
        String deadline = "deadline";
        String event = "event";
        String delete = "delete";
        String find = "find";

        //Storage storage = new Storage("duke.txt");
        //Ui ui = new Ui();
        //TaskList mainList = new TaskList(storage.readFile());

        //ArrayList<MyTask> mainList = storage.readFile("duke.txt");
        ArrayList<String> stringList = new ArrayList<String>();

        ui.printIntro();

        while(true) {
            String input = sc.nextLine();
            if (input.equals(bye)) {
                this.ui.printExit();
                break;
            } else if (input.equals(list)) {
                this.ui.printList(this.tasks);
            } else if (input.contains(done)) {
                try {
                    String[] inputArray = input.split(" ", 2);
                    int index = Integer.parseInt(inputArray[1]) - 1;
                    this.tasks.getList().get(index).markAsDone();
                    this.ui.printDone(this.tasks.getList().get(index));
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("____________________________________________________________");
                    System.out.println("\u2639 OOPS!!! The number of a done cannot be empty.\n");
                    System.out.println("____________________________________________________________");
                }
            } else if (input.contains(delete)) {
                try {
                    String[] inputArray = input.split(" ", 2);
                    int index = Integer.parseInt(inputArray[1]) - 1;
                    MyTask temp = this.tasks.getList().get(index);
                    //mainList.remove(index);
                    this.tasks.removeFromList(index);
                    ui.printDelete(this.tasks, temp);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("____________________________________________________________");
                    System.out.println("\u2639 OOPS!!! The number of a delete cannot be empty.\n");
                    System.out.println("____________________________________________________________");
                }  catch(IndexOutOfBoundsException e){
                    System.out.println("____________________________________________________________");
                    System.out.println("\u2639 OOPS!!! The number not found.\n");
                    System.out.println("____________________________________________________________");
                }
            } else if(input.contains(find)){
                try{
                    String[] inputArray = input.split(" ", 2);
                    TaskList outputList = this.tasks.findFromList(inputArray[1]);
                    if(outputList.getList().size()>0){
                        ui.printList(outputList);
                    }
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("____________________________________________________________");
                    System.out.println("\u2639 OOPS!!! The number of a delete cannot be empty.\n");
                    System.out.println("____________________________________________________________");
                }
            } else {
                MyTask newInput;
                if(input.contains(todo)){
                    try {
                        String[] inputArray = input.split(" ", 2);
                        newInput = new ToDo(inputArray[1]);
                        this.tasks.addToList(newInput);
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
                        newInput = new Deadline(inputArray[0], inputArray[1]);
                        this.tasks.addToList(newInput);
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
                        newInput = new Events(inputArray[0], inputArray[1]);
                        this.tasks.addToList(newInput);
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
                ui.printAdd(this.tasks, newInput);
            }
            storage.saveFile(this.tasks.getList());
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke("duke.txt").run();
    }
}