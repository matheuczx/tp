package seedu.tutorswift.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit tests for the ListCommand feature.
 * Tests listing students when the list is empty and when it has entries.
 */
class ListCommandTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private Ui ui;
    private StudentList studentList;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        ui = new Ui();
        studentList = new StudentList();
    }
    /**
     * JUnit tests for the ListCommand feature.
     * Tests listing students when it has entries.
     */
    @Test
    void execute_listStudents_displaysAllStudents() throws Exception {
        Student s1 = new Student("John Tan", "Math", "Secondary 3");
        Student s2 = new Student("Sarah Lim", "English", "Primary 6");
        studentList.addStudent(s1);
        studentList.addStudent(s2);

        ListCommand listCommand = new ListCommand();
        listCommand.execute(studentList, ui);

        String output = outContent.toString();

        assertTrue(output.contains("1. John Tan | Math | Secondary 3"));
        assertTrue(output.contains("2. Sarah Lim | English | Primary 6"));

    }
    /**
     * JUnit tests for the ListCommand feature.
     * Tests listing students when it has no entries.
     */
    @Test
    void execute_listEmpty_displaysNoStudentsMessage() throws Exception {
        ListCommand listCommand = new ListCommand();
        listCommand.execute(studentList, ui);

        String output = outContent.toString();

        assertTrue(output.contains("There are currently no students."));
    }
}
