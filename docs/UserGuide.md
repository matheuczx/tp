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
3. Open a terminal and navigate to the folder containing the jar file.
4. Run the command:
   java -jar TutorSwift.jar
5. Type commands in the input and press Enter.

Example commands:
- add n/John Doe l/Secondary 2 sub/Math
- list
- delete 1
- bye

## Features 

### Notes about command format

- Words in UPPER_CASE are parameters to be supplied by the user.
  e.g. add n/NAME means NAME is a parameter.

- Parameters can be in any order.

- INDEX refers to the student number shown in the list.

- All INDEX values must be positive integers (1, 2, 3, ...).

{Give detailed description of each feature}

### Adding a todo: `todo`
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

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

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
* Add Grade `grade INDEX m/ASSESSMENT g/SCORE`
* Add Remark `remark INDEX r/REMARK`
