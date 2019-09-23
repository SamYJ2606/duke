# User Guide

## 1. Quick Start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `duke-0.2.1.jar`. 
3. Copy the file to the folder you want to use as the home folder for your Duke.
4. Double-click the file to start the app. The GUI should appear in a few seconds.
![Image of Ui](https://github.com/SamYJ2606/duke/blob/master/docs/Ui.PNG)
5. Type the command in the command box and press `Enter` to execute it.
   e.g. typing `help` and pressing `Enter` will open the help window.
6. Refer to Section 2, “Features” for details of each command.

## 2. Features 

### 2.1. Viewing help: `help`
Displays help menu.
Format: `help`

### 2.2. Exiting the program: `bye`
Exits the program.
Format: `bye`

### 2.3. Listing all tasks: `list`
Shows a list of all tasks in Duke.
Format: `list`

### 2.4. Locating task by name: `find`
Finds tasks whose description contains any of the given keywords.
Format: `find KEYWORD [MORE_KEYWORDS]`
* The search is case insensitive. e.g. `hans` will match `Hans`
* Only the description is searched.
Examples:
- `find books`
 Returns `borrow books` and `return books`.

### 2.5. Deleting a task: `delete`
Deletes the specified task from Duke.
Format: `delete INDEX`
* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index must **be a positive integer** 1, 2, 3, ...
Examples:
- `list`
  `delete 2`
  Deletes the 2nd task in Duke.

### 2.6. Adding a todo task: `todo`
Adds a `todo` Task to Duke.
Format: `todo DESCRIPTION`
Examples:
- `todo Return Books`
  Adds `todo` Task with `Return Books` as its description.
- `todo Meet Friends`
  Adds `todo` Task with `Meet Friends` as its description.

### 2.7. Adding a task with a deadline: `deadline`
Add a `deadline` Task to Duke.
Format: `deadline DESCRIPTION /by DATETTIME`
Examples:
- `deadline Return Book /by 2007-12-03T10:15`
  Adds `deadline` Task with `Return Book` as its description and `2007/12/03`, `10.15am` as its date and time.
- `deadline Meet Friends /by 2019-12-31T23:00`
  Adds `deadline` Task with `Meet Friends` as its description as `2019/12/31`, `11.00pm` as its date and time.

### 2.8. Adding an event: `event`
Add an `event` Task to Duke.
Format: `event DESCRIPTION /at DATETTIME-TIME`
Examples:
- `event Return Book /at 2007-12-03T10:15-13:15`
  Adds `event` Task with `Return Book` as its description and `2007/12/03`, `10.15am` to `1.15pm` as its duration.
- `event project meeting /at 2019-12-31T12:15-23:20`
  Adds `event` Task with `project meeting` as its description and `2019/12/31`, `12.15pm` to `11.20pm` as its duration.

## 3. FAQ
**Q:** How do I transfer my data to another Computer?
**A:** Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Duke folder.

## 4. Command Summary
* **Add Todo** : `todo DESCRIPTION`
  e.g. `todo Return Books`
* **Add Deadline** : `deadline DESCRIPTION /by DATETTIME`
  e.g. `deadline Return Book /by 2007-12-03T10:15`
* **Add Event** : `event DESCRIPTION /at DATETTIME-TIME`
  e.g. `event project meeting /at 2019-12-31T12:15-23:20`
* **Delete** : `delete INDEX`
  e.g. `delete 2`
* **Find** : `find KEYWORD [MORE_KEYWORDS]`
  e.g. `find books`
* **List** : `list`
* **Help** : `help`
* **Exit** : `bye`
