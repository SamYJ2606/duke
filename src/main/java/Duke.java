import MyTask.Deadline;
import MyTask.Events;
import MyTask.MyTask;
import MyTask.ToDo;

import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    public Duke(String filePath){
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFile());
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */

    public String getResponse(String input) throws IOException {
        String bye = "bye";
        String list = "list";
        String done = "done";
        String todo = "todo";
        String deadline = "deadline";
        String event = "event";
        String delete = "delete";
        String find = "find";
        String help = "help";

        if (input.equals(bye)) {
            return this.ui.printExit();
        } else if (input.equals(list)) {
            return this.ui.printList(this.tasks);
        } else if (input.contains(done)) {
            try {
                String[] inputArray = input.split(" ", 2);
                int index = Integer.parseInt(inputArray[1]) - 1;
                this.tasks.getList().get(index).markAsDone();
                this.storage.saveFile(this.tasks.getList());
                return this.ui.printDone(this.tasks.getList().get(index));
            } catch (ArrayIndexOutOfBoundsException e) {
                return this.ui.printLine() + "\u2639 OOPS!!! The number of a done cannot be empty.\n" + this.ui.printLine();
            }
        } else if (input.contains(delete)) {
            try {
                String[] inputArray = input.split(" ", 2);
                int index = Integer.parseInt(inputArray[1]) - 1;
                MyTask temp = this.tasks.getList().get(index);
                this.tasks.removeFromList(index);
                this.storage.saveFile(this.tasks.getList());
                return this.ui.printDelete(this.tasks, temp);
            } catch (ArrayIndexOutOfBoundsException e) {
                return this.ui.printLine() + "\u2639 OOPS!!! The number of a delete cannot be empty.\n" + this.ui.printLine();
            }  catch(IndexOutOfBoundsException e){
                return this.ui.printLine() + "\u2639 OOPS!!! The number not found.\n" + this.ui.printLine();
            }
        } else if(input.contains(find)){
            try{
                String[] inputArray = input.split(" ", 2);
                TaskList outputList = this.tasks.findFromList(inputArray[1]);
                this.storage.saveFile(this.tasks.getList());
                if(outputList.getList().size()>0){
                    return this.ui.printList(outputList);
                }
            } catch (ArrayIndexOutOfBoundsException e){
                return this.ui.printLine() + "\u2639 OOPS!!! The number of a delete cannot be empty.\n" + this.ui.printLine();
            }
        }else if(input.equals(help)){
            return this.ui.printHelp();
        } else {
            MyTask newInput;
            if(input.contains(todo)){
                try {
                    String[] inputArray = input.split(" ", 2);
                    newInput = new ToDo(inputArray[1]);
                    this.tasks.addToList(newInput);
                } catch (ArrayIndexOutOfBoundsException e){
                    return this.ui.printLine() + "\u2639 OOPS!!! The description of a todo cannot be empty.\n" + this.ui.printLine();
                }
            } else if (input.contains(deadline)){
                try {
                    String[] inputArray = input.split(" ", 2);
                    inputArray = inputArray[1].split("/by", 2);
                    newInput = new Deadline(inputArray[0], inputArray[1].strip());
                    this.tasks.addToList(newInput);
                } catch (ArrayIndexOutOfBoundsException e){
                    return this.ui.printLine() + "\u2639 OOPS!!! The description/by of a deadline cannot be empty.\n" + this.ui.printLine();
                }
            } else if (input.contains(event)){
                try {
                    String[] inputArray = input.split(" ", 2);
                    inputArray = inputArray[1].split("/at", 2);
                    newInput = new Events(inputArray[0], inputArray[1].strip());
                    this.tasks.addToList(newInput);
                } catch (ArrayIndexOutOfBoundsException e){
                    return this.ui.printLine() + "\u2639 OOPS!!! The description/at of an event cannot be empty.\n" + this.ui.printLine();
                }
            } else {
                return this.ui.printLine() + "\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(\n" + this.ui.printLine();
            }
            this.storage.saveFile(this.tasks.getList());
            return this.ui.printAdd(this.tasks, newInput);
        }
        //this.storage.saveFile(this.tasks.getList());
        return this.ui.printIntro();
    }

    public String printIntro(){
        return this.ui.printIntro();
    }

}