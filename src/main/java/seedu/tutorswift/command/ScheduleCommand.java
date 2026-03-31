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

    public ScheduleCommand(
        String studentName,
        DayOfWeek day,
        LocalTime startTime,
        LocalTime endTime
    ) throws TutorSwiftException {
        assert studentName != null : "Student name parameter should not be null";
        assert day != null : "Day parameter should not be null";
        assert startTime != null : "Start time should not be null";
        assert endTime != null : "End time should not be null";

        if (studentName.trim().isEmpty()) {
            throw new TutorSwiftException("Student name cannot be empty.");
        }

        if (!startTime.isBefore(endTime)) {
            throw new TutorSwiftException("Start time must be before end time.");
        }

        this.studentName = studentName;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public void execute(StudentList students, Ui ui) throws TutorSwiftException {
        assert students != null : "StudentList should not be null";
        assert ui != null : "Ui should not be null";

        // Find the student
        Student targetStudent = null;
        for (int i = 0; i < students.getActiveSize(); i++) {
            if (students.getActiveStudent(i).getName().equalsIgnoreCase(studentName)) {
                targetStudent = students.getActiveStudent(i);
                break;
            }
        }

        if (targetStudent == null) {
            throw new TutorSwiftException("Student '" + studentName + "' not found.");
        }

        Lesson newLesson = new Lesson(day, startTime, endTime);
        targetStudent.addLesson(newLesson);

        ui.showLessonScheduled(targetStudent.getName(), newLesson);
    }
}
