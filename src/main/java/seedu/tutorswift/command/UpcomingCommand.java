package seedu.tutorswift.command;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import seedu.tutorswift.Lesson;
import seedu.tutorswift.RelativeLesson;
import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.TutorSwiftException;
import seedu.tutorswift.Ui;

public class UpcomingCommand extends Command {

    @Override
    public void execute(StudentList students, Ui ui) throws TutorSwiftException {
        assert students != null : "StudentList should not be null";
        assert ui != null : "Ui should not be null";

        List<RelativeLesson> allLessons = new ArrayList<>();
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        LocalTime now = LocalTime.now();

        for (int i = 0; i < students.getActiveSize(); i++) {
            Student student = students.getActiveStudent(i);
            for (Lesson lesson : student.getLessons()) {
                allLessons.add(new RelativeLesson(student, lesson, today, now));
            }
        }

        if (allLessons.isEmpty()) {
            ui.showEmptyUpcomingLessons();
        } else {
            allLessons.sort(Comparator
                    .comparingInt(RelativeLesson::getDaysFromToday)
                    .thenComparing(entry -> entry.getLesson().getStartTime()));
            ui.showUpcomingLessons(allLessons);
        }
    }
}
