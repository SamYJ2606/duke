import MyTask.MyTask;

public class Ui {

    public String printLine(){
        return "____________________________________\n";
    }

    public String printIntro(){
        return this.printLine() + "Hello! Im Duke\nWhat can I do for you?\n" + this.printLine();
    }

    public String printExit(){
        return this.printLine() + "Bye. Hope to see you again soon!\n" + this.printLine();
    }

    public String printList(TaskList mainList){
        String output;
        output = this.printLine() + "Here are the tasks in your list:\n";
        for (int i = 0; i < mainList.getList().size(); i++) {
            int number = i + 1;
            output = output + number + "." + mainList.getList().get(i).toString() + "\n";
        }
        output = output + this.printLine();
        return output;
    }

    public String printDone(MyTask task){
        return this.printLine() + "Nice! I've marked this task as done:\n" + task.toString() + this.printLine();
    }

    public String printDelete(TaskList mainList, MyTask task){
        return this.printLine() + "Noted. I've removed this task:\n" + task.toString() + "\nNow you have " + mainList.getList().size() + " tasks in the list.\n" + this.printLine();
    }

    public String printAdd(TaskList mainList, MyTask task){
        return this.printLine() + "Got it. I've added this task:\n" + task.toString() + "\nNow you have " + mainList.getList().size() + " tasks in the list.\n" + this.printLine();
    }

    public String printHelp(){
        return this.printLine() + "- list\n- bye\n- todo Description\n- deadline Description /by Day\n- event Description /at Day Time-Time\n- find Keyword\n- done Index\n- delete Index\n" + this.printLine();
    }
}
