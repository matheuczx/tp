package seedu.tutorswift.command;

import java.time.DayOfWeek;
import java.time.LocalTime;
import seedu.tutorswift.Lesson;
import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.TutorSwiftException;
import seedu.tutorswift.Ui;

public class ScheduleCommand extends Command {
    private final String studentName;
    private final DayOfWeek day;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public ScheduleCommand(String studentName, DayOfWeek day, LocalTime startTime, LocalTime endTime) {
        this.studentName = studentName;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public void execute(StudentList students, Ui ui) throws TutorSwiftException {
        // Find the student
        Student targetStudent = null;
        for (int i = 0; i < students.getSize(); i++) {
            if (students.getStudent(i).getName().equalsIgnoreCase(studentName)) {
                targetStudent = students.getStudent(i);
                break;
            }
        }

        if (targetStudent == null) {
            throw new TutorSwiftException("Student '" + studentName + "' not found.");
        }

        Lesson newLesson = new Lesson(day, startTime, endTime);
        targetStudent.addLesson(newLesson);

        System.out.println("Got it! I've scheduled a lesson for " + targetStudent.getName() + " on " + newLesson);
    }
}
