package seedu.tutorswift.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.TutorSwiftException;
import seedu.tutorswift.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * JUnit test class for FindCommand.
 * Tests finding students by name, subject, level, and multiple fields.
 * Also ensures invalid inputs throw exceptions.
 */
public class FindCommandTest {
    private StudentList students;
    private Ui ui;

    /**
     * Sets up a StudentList with sample students and a Ui before each test.
     */
    @BeforeEach
    public void setUp() {
        students = new StudentList();
        ui = new Ui();

        students.addStudent(new Student("John Doe", "Primary 1", "Math"));
        students.addStudent(new Student("Jane Smith", "Secondary 3", "Physics"));
        students.addStudent(new Student("Alice Tan", "Primary 1", "Math"));
    }

    @Test
    public void execute_findByName_returnsExpectedStudent() throws TutorSwiftException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        FindCommand cmd = new FindCommand("John Doe", null, null);
        cmd.execute(students, ui);

        String output = outContent.toString();
        assertTrue(output.contains("John Doe | Primary 1 | Math"));
        assertFalse(output.contains("Jane Smith"));
    }

    @Test
    public void execute_findBySubject_returnsExpectedStudents() throws TutorSwiftException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        FindCommand cmd = new FindCommand(null, "Math", null);
        cmd.execute(students, ui);

        String output = outContent.toString();
        assertTrue(output.contains("John Doe | Primary 1 | Math"));
        assertTrue(output.contains("Alice Tan | Primary 1 | Math"));
        assertFalse(output.contains("Jane Smith"));
    }

    @Test
    public void execute_findByLevel_returnsExpectedStudents() throws TutorSwiftException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        FindCommand cmd = new FindCommand(null, null, "Secondary 3");
        cmd.execute(students, ui);

        String output = outContent.toString();
        assertTrue(output.contains("Jane Smith | Secondary 3 | Physics"));
        assertFalse(output.contains("John Doe"));
    }

    @Test
    public void execute_findByMultipleFields_returnsExpectedStudents() throws TutorSwiftException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Should match John Doe: name and subject
        FindCommand cmd = new FindCommand("John Doe", "Math", null);
        cmd.execute(students, ui);

        String output = outContent.toString();
        assertTrue(output.contains("John Doe | Primary 1 | Math"));
        assertFalse(output.contains("Jane Smith"));
        assertFalse(output.contains("Alice Tan"));
    }

    @Test
    public void execute_findNoMatchingStudents_displaysNoResults() throws TutorSwiftException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        FindCommand cmd = new FindCommand("Nonexistent", null, null);
        cmd.execute(students, ui);

        String output = outContent.toString();
        assertTrue(output.contains("No matching students found."));
    }

    @Test
    public void execute_findNullFields_throwsException() {
        // FindCommand constructor should throw exception if all fields null
        assertThrows(TutorSwiftException.class, () -> new FindCommand(null, null, null));
    }

    @Test
    public void execute_findEmptyPrefixValues_throwsException() {
        // Empty strings for fields should be treated as invalid
        assertThrows(TutorSwiftException.class, () -> new FindCommand("", "", ""));
    }
}
