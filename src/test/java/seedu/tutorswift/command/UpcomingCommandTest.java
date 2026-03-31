package seedu.tutorswift.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.tutorswift.RelativeLesson;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.TutorSwiftException;
import seedu.tutorswift.Ui;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UpcomingCommandTest {

    private StudentList students;
    private UiTest uiTest;

    static class UiTest extends Ui {
        boolean isEmptyCalled = false;
        List<RelativeLesson> capturedLessons = null;

        @Override
        public void showEmptyUpcomingLessons() {
            isEmptyCalled = true;
        }

        @Override
        public void showUpcomingLessons(List<RelativeLesson> lessons) {
            capturedLessons = lessons;
        }
    }

    @BeforeEach
    public void setUp() {
        students = new StudentList();
        uiTest = new UiTest();
    }

    @Test
    public void execute_noLessons_callsShowEmptyUpcomingLessons() throws TutorSwiftException {
        UpcomingCommand command = new UpcomingCommand();

        command.execute(students, uiTest);

        assertTrue(uiTest.isEmptyCalled);
    }
}
