package seedu.tutorswift;

import java.util.Scanner;

/**
 * Handles user interface messages for the program.
 */
public class Ui {

    private static final String LOGO = ""
            + "         __...--~~~~~-._   _.-~~~~~--...__\n"
            + "      //               `V'               \\\\\n"
            + "     //                 |                 \\\\\n"
            + "    //__...--~~~~~~-._  |  _.-~~~~~~--...__\\\\\n"
            + "   //__.....----~~~~._\\ | /_.~~~~----.....__\\\\\n"
            + "  ======================\\\\|//====================\n"
            + "                       `---'\n";
    private static final String WELCOME_MESSAGE = "Welcome to TutorSwift!"
            + " We'll help you manage your students efficiently!\n"
            + "How can I help you?";
    private static final String LINE_DIVIDER = "____________________________________________________________";
    private static final String INDENT = "    ";
    private static final String EXIT_MESSAGE = "Goodbye! Thanks for using TutorSwift.";

    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }
    /**
     * Shows the welcome message when the program starts.
     */
    public void showWelcome() {
        System.out.println(LOGO);
        System.out.println(WELCOME_MESSAGE);
    }

    public String readUserInput() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        } else {
            return "";
        }
    }

    /**
     * Helper method to print formatted student details.
     *
     * @param student The student object to print.
     */
    private void printStudentDetails(Student student) {
        assert student != null : "student should not be null";
        System.out.println(INDENT + student.toString());
    }

    /**
     * Shows that edit was successful and show updated student details.
     *
     * @param updatedStudent The student that was just edited.
     */
    public void showEditSuccess(Student updatedStudent) {
        System.out.println(LINE_DIVIDER);
        System.out.println(" Done! I've updated the details for this student:");
        printStudentDetails(updatedStudent);
        System.out.println(LINE_DIVIDER);
    }

    public void showError(String error) {
        System.out.println(LINE_DIVIDER);
        System.out.println(error);
        System.out.println(LINE_DIVIDER);
    }

    /**
     * Shows a success message and the student's details after a student is successfully added.
     *
     * @param student The student object that was recently added.
     * @param totalStudents The total number of students currently in the list.
     */
    public void showAddSuccess(Student student, int totalStudents) {
        System.out.println(LINE_DIVIDER);
        System.out.println(" Got it. I've added this student:");
        printStudentDetails(student);
        System.out.println(" Now you have " + totalStudents + " students in the list.");
        System.out.println(LINE_DIVIDER);
    }
    /**
     * Shows that deletion was successful and shows the removed student details.
     *
     * @param deletedStudent The student that was just deleted.
     */
    public void showDeleteSuccess(Student deletedStudent) {
        System.out.println(LINE_DIVIDER);
        System.out.println(" Done! I've removed this student:");
        printStudentDetails(deletedStudent);
        System.out.println(LINE_DIVIDER);
    }

    /**
     * Displays all students currently in the list.
     *
     * @param students The list of students to display.
     */
    public void showStudentList(StudentList students) {
        System.out.println(LINE_DIVIDER);

        if (students.getSize() == 0) {
            System.out.println("There are currently no students.");
        } else {
            System.out.println("Here are the students in your list:");
            for (int i = 0; i < students.getSize(); i++) {
                Student student = students.getStudent(i);
                System.out.print((i + 1) + ". ");
                printStudentDetails(student);
            }
        }

        System.out.println(LINE_DIVIDER);
    }

    /**
     * Shows the exit message when the program ends.
     */
    public void showExit() {
        System.out.println(EXIT_MESSAGE);
    }

    public void close() {
        scanner.close();
    }
}

