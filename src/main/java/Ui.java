import MyTask.*;

import java.util.ArrayList;

public class Ui {

    public void printLine(){
        System.out.println("______________________________________________________");
    }

    public void printIntro(){
        this.printLine();
        System.out.println("Hello! Im Duke");
        System.out.println("What can I do for you?\n");
        this.printLine();
    }

    public void printExit(){
        this.printLine();
        System.out.println("Bye. Hope to see you again soon!\n");
        this.printLine();
    }

    public void printList(TaskList mainList){
        this.printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < mainList.getList().size(); i++) {
            int number = i + 1;
            System.out.println(number + "." + mainList.getList().get(i).toString());
        }
        this.printLine();
    }

    public void printDone(MyTask task){
        this.printLine();
        System.out.println("Nice! I've marked this task as done:\n");
        System.out.println(task.toString());
        this.printLine();
    }

    public void printDelete(TaskList mainList, MyTask task){
        this.printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + mainList.getList().size() + " tasks in the list.\n");
        this.printLine();
    }

    public void printAdd(TaskList mainList, MyTask task){
        this.printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + mainList.getList().size() + " tasks in the list.\n");
        this.printLine();
    }
}
