# User Guide

- Introduction
- Quick Start
- Features
- FAQ
- Command Summary

## Introduction

TutorSwift is a command-line application for private tutors to manage student records efficiently.
It allows users to add, edit, and track students, including their lessons, grades, fees, and payment status.

If you can type fast, TutorSwift helps you manage your students faster than traditional GUI apps.

## Quick Start

1. Ensure you have Java 17 or above installed.
2. Down the latest version of `TutorSwift` from [here](http://link.to/duke).
3. Copy the `.jar` file into an empty folder where you want to use as the home folder and store your TutorSwift data.
4. Open a command terminal, `cd` into the folder containing the TutorSwift jar file.
5. Run the command:
   `java -jar TutorSwift.jar`

    A CLI similar to the one below should appear in a few seconds.

   ![QuickStart image](images/QuickStart.png)
    
6. Type commands in the command line and press Enter.

Some example commands:
- `add n/John Doe l/Secondary 2 sub/Math` : Adds a Secondary 2 student named John Doe taking Math to the student list.
- `list`: List all students.
- `delete 1` : Deletes the 1st student shown in the current list.
- `bye` : Exits the app.

Refer to the Features below for details of each command.

---

## Features 

### Notes about command format

- Words in UPPER_CASE are parameters to be supplied by the user.

  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

- Items in square brackets are optional.

  e.g. `n/NAME [l/ACADEMIC_LEVEL]` can be used as `n/John Doe l/Primary 6` or as `n/John Doe`.

- Parameters can be in any order.

  e.g. if the command specifies `n/NAME l/ACADEMIC_LEVEL`, `l/ACADEMIC_LEVEL n/NAME` is also acceptable.

- `INDEX` refers to the student number shown in the list.

- All `INDEX` values must be positive integers (1, 2, 3, ...).

- Extraneous parameters for commands that do not take in parameters (such as `list`, `upcoming`,  `bye`) will be ignored.

  e.g. if the command specifies `bye 123`, it will be interpreted as `bye`.

### Editing a student: `edit`

Edits an existing student in the student list.

Format: `edit INDEX [n/NAME] [l/ACADEMIC_LEVEL] [sub/SUBJECT]`

- Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed student list. The index must be a positive integer 1, 2, 3, …

- At least one of the optional fields must be provided.
  
- Existing values will be updated to the input values.

Examples of usage:

- `edit 1 n/Jane Doe l/Secondary 2 sub/Science` Edits the name, academic level and subject of the 1st student to be `Jane Doe`, `Secondary 2` and `Science` respectively.
- `edit 2 n/Ben Tan` Edits the name of the 2nd student to be `Ben Tan` and leaves the existing `ACADEMIC_LEVEL` and `SUBJECT` untouched.

### Adding a grade: `grade`

Adds a grade record to a student.

Format: `grade INDEX m/ASSESSMENT g/SCORE`

* The `INDEX` must be valid.
* The `SCORE` must be a number.

Example of usage:
`grade 1 m/Midterm g/85`

Expected behaviour:
- Adds a grade to the specified student
- Grade is stored as [ASSESSMENT:SCORE]
- Student details will display updated grades

### Adding a remark: `remark`

Adds a remark to a student.

Format: `remark INDEX r/REMARK`

* The `INDEX` must be a valid student number.
* The `r/` prefix is required.
* The `REMARK` cannot be empty.

Example of usage:
`remark 1 r/Very hardworking student`

Expected behaviour:
- Adds or updates the remark for the student
- Displays updated student details

### Scheduling a lesson: `schedule`

Schedules a new recurring weekly lesson for an existing active student in the student list.

Format: `schedule NAME day/DAY_OF_WEEK start/START_TIME end/END_TIME`

- Schedules a lesson for the student with the specified `NAME`. The name must exactly match an existing student in your active list (case-insensitive).

- The `DAY_OF_WEEK` must be a valid day (e.g., Monday, Tuesday, etc.).

- The `START_TIME` and `END_TIME` must be formatted in 24-hour time (HH:mm).

- `START_TIME` must be strictly before the `END_TIME`.

- Note on time conflicts: You cannot schedule a lesson that overlaps with another existing lesson for the same student. However, the system allows overlapping lessons across different students (useful for scheduling group tuition classes).

Examples of usage:

- `schedule Alice day/Monday start/10:00 end/12:00` Schedules a 2-hour lesson for Alice every Monday from 10:00 AM to 12:00 PM.
- `schedule John Doe day/Wednesday start/15:30 end/17:30` Schedules a lesson for John Doe every Wednesday from 3:30 PM to 5:30 PM.

### Viewing upcoming lessons: `upcoming`

Displays a sorted list of all scheduled lessons across all active students for the next 7 days, ordered by how soon they are happening relative to the current day and time.

Format: `upcoming`

- The list is sorted chronologically, starting with the lesson that is happening the earliest relative to the current day and time.

- Lessons that have already passed for the current day will be automatically pushed to next week's schedule.

- Lessons occurring today or tomorrow will be marked with helpful `(Today)` or `(Tomorrow)` tags next to the day.

- Archived students' lessons will not appear in this list.

Examples of usage:

- `upcoming` Displays the tutor's sorted lesson schedule for the upcoming week.

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

| Action     | Format                                                        | Examples                                          |
|------------|---------------------------------------------------------------|---------------------------------------------------|
| Edit       | `edit INDEX [n/NAME] [l/ACADEMIC_LEVEL] [sub/SUBJECT]`        | `edit 1 n/Jane Doe l/Secondary 2 sub/Science`     |
| Add Grade  | `grade INDEX m/ASSESSMENT g/SCORE`                            |                                                   |
| Add Remark | `remark INDEX r/REMARK`                                       |                                                   |
| Schedule   | `schedule NAME day/DAY_OF_WEEK start/START_TIME end/END_TIME` | `schedule Alice day/Monday start/10:00 end/12:00` |
| Upcoming   | `upcoming`                                                    | -                                                 |

