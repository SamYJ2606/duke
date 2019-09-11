import MyTask.MyTask;
import MyTask.Deadline;
import MyTask.ToDo;
import MyTask.Events;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Storage {

    private String StringPath;

    public Storage(String stringPath){
        this.StringPath = stringPath;
    }

    public void saveFile(ArrayList<MyTask> list) throws IOException {
        ArrayList<String> stringList = new ArrayList<String>();
        for(int i=0;i<list.size();i++){
            String temp = list.get(i).toString();
            temp = temp.replace("[", "");
            String[] tempArray = temp.split("]", 3);
            if(tempArray[1].equals("\u2713")){
                tempArray[1] = "1";
            } else {
                tempArray[1] = "0";
            }
            temp = tempArray[0] + "/" + tempArray[1] + "/";
            if(tempArray[0].equals("T")){
                temp = temp + tempArray[2].strip() + "\n";
            } else if(tempArray[0].equals("D")){
                tempArray = tempArray[2].split("by:");
                tempArray[0] = tempArray[0].replace("(", " ");
                tempArray[1] = tempArray[1].replace(")", " ");
                temp = temp + tempArray[0].strip() + "/";
                tempArray = tempArray[1].split(",");
                String[] dateArray = tempArray[0].strip().split(" ");
                LocalDate date = LocalDate.of(Integer.parseInt(dateArray[3]), Month.valueOf(dateArray[2]).getValue(), Integer.parseInt(dateArray[0]));
                tempArray[1] = tempArray[1].strip();
                String front = tempArray[1].substring(0, tempArray[1].length()-2);
                String back = tempArray[1].substring(tempArray[1].length()-2);
                front = front.strip();
                front = front.replace(".", ":");
                LocalTime time = LocalTime.of(0,0);
                if (back.equals("pm")){
                    time = LocalTime.parse(front);
                    time = time.plusHours(12);
                } else {
                    time = LocalTime.parse(front);
                }
                LocalDateTime DateTime = LocalDateTime.of(date, time);
                temp = temp + DateTime.toString();
            } else if (tempArray[0].equals("E")){
                tempArray = tempArray[2].split("by:");
                tempArray[0] = tempArray[0].replace("(", " ");
                tempArray[1] = tempArray[1].replace(")", " ");
                temp = temp + tempArray[0].strip() + "/";
                tempArray = tempArray[1].split(",");
                String[] dateArray = tempArray[0].strip().split(" ");
                LocalDate date = LocalDate.of(Integer.parseInt(dateArray[3]), Month.valueOf(dateArray[2]).getValue(), Integer.parseInt(dateArray[0]));
                tempArray = tempArray[1].split("-");
                String front = tempArray[0].substring(0, tempArray[0].length()-2);
                String back = tempArray[0].substring(tempArray[0].length()-2);
                if(front.length()==4)
                    front = "0" + front;
                front = front.strip();
                front = front.replace(".", ":");
                LocalTime start = LocalTime.of(0,0);
                if (back.equals("pm")){
                    start = LocalTime.parse(front);
                    start = start.plusHours(12);
                } else {
                    start = LocalTime.parse(front);
                }
                tempArray[1] = tempArray[1].strip();
                front = tempArray[1].substring(0, tempArray[1].length()-2);
                if(front.length()==4)
                    front = "0" + front;
                back = tempArray[1].substring(tempArray[1].length()-2);
                front = front.strip();
                front = front.replace(".", ":");
                LocalTime end = LocalTime.of(0,0);
                if (back.equals("pm")){
                    end = LocalTime.parse(front);
                    end = end.plusHours(12);
                } else {
                    end = LocalTime.parse(front);
                }
                temp = temp + date.toString() + " " + start.toString() + "-" + end.toString();
            }
            stringList.add(temp);
        }
        Path file = Paths.get(this.StringPath);
        Files.write(file,stringList, StandardCharsets.UTF_8);
    }
    public ArrayList<MyTask> readFile() {
        List<String> stringList = Collections.emptyList();
        try{
            stringList = Files.readAllLines(Paths.get(this.StringPath), StandardCharsets.UTF_8);
        } catch (IOException e){
            System.out.println("____________________________________________________________");
            System.out.println("Unable to read file.");
            System.out.println("____________________________________________________________");
        }
        ArrayList<MyTask> mainList = new ArrayList<MyTask>();
        MyTask newTask;
        for(int i=0;i<stringList.size();i++){
            String temp = stringList.get(i);
            String[] tempArray = temp.split("/");
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
