package seedu.tutorswift;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.tutorswift.command.AddCommand;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit test class for AddCommand.
 * Verifies that the command correctly interacts with the StudentList and Ui.
 */
class AddCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private StudentList studentList;
    private Ui ui;

    /**
     * Sets up the test environment before each test method is executed.
     * Redirects System.out to a stream and initializes the student list and UI.
     */
    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        studentList = new StudentList();
        ui = new Ui();
    }

    /**
     * Tests adding a single student and verifies all attributes are stored correctly.
     */
    @Test
    void execute_validStudent_success() {
        Student student = new Student("John Doe", "Secondary 4", "Mathematics");
        AddCommand command = new AddCommand(student);
        command.execute(studentList, ui);

        assertEquals(1, studentList.getSize(), "Size should be 1");
        Student added = studentList.getStudent(0);

        assertEquals("John Doe", added.getName());
        assertEquals("Secondary 4", added.getAcademicLevel());
        assertEquals("Mathematics", added.getSubject());

        assertTrue(outContent.toString().contains("Got it. I've added this student:"));
    }

    /**
     * Tests adding multiple students to ensure the list tracks them correctly.
     */
    @Test
    void execute_multipleStudents_listSizeIncrements() {
        AddCommand cmd1 = new AddCommand(new Student("Alice", "P6", "English"));
        AddCommand cmd2 = new AddCommand(new Student("Bob", "S1", "Science"));

        cmd1.execute(studentList, ui);
        cmd2.execute(studentList, ui);

        assertEquals(2, studentList.getSize(), "Size should be 2 after two additions");
        assertEquals("Alice", studentList.getStudent(0).getName());
        assertEquals("Bob", studentList.getStudent(1).getName());
    }

    /**
     * Tests adding a student with long strings or extra spaces.
     * This verifies that the command handles the data object as provided.
     */
    @Test
    void execute_studentWithSpacesInName_storedCorrectly() {
        String nameWithSpaces = "Mary Ann Tan";
        Student student = new Student(nameWithSpaces, "JC 1", "H2 Physics");
        AddCommand command = new AddCommand(student);
        command.execute(studentList, ui);

        assertEquals(nameWithSpaces, studentList.getStudent(0).getName());
    }

}
