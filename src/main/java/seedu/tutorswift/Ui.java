package seedu.tutorswift;

import java.util.Scanner;

/**
 * Handles user interface messages for the program.
 */
public class Ui {

    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }
    /**
     * Shows the welcome message when the program starts.
     */
    public void showWelcome() {
        String logo = ""
                + "         __...--~~~~~-._   _.-~~~~~--...__\n"
                + "      //               `V'               \\\\\n"
                + "     //                 |                 \\\\\n"
                + "    //__...--~~~~~~-._  |  _.-~~~~~~--...__\\\\\n"
                + "   //__.....----~~~~._\\ | /_.~~~~----.....__\\\\\n"
                + "  ======================\\\\|//====================\n"
                + "                       `---'\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Welcome to TutorSwift! Manage your students efficiently." +
                "How can I help you?");
    }

    public String readCommand() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        } else {
            return "";
        }
    }

    /**
     * Shows that edit was successful and show updated student details.
     *
     * @param updatedStudent The student that was just edited.
     */
    public void showEditSuccess(Student updatedStudent) {
        System.out.println("____________________________________________________________");
        System.out.println(" Done! I've updated the details for this student:");
        System.out.println("   " + updatedStudent.getName() + " | " +
                updatedStudent.getAcademicLevel() + " | " +
                updatedStudent.getSubject());
        System.out.println("____________________________________________________________");
    }

    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Shows the exit message when the program ends.
     */
    public void showExit() {
        System.out.println("Goodbye! Thanks for using TutorSwift.");
    }

    public void close() {
        scanner.close();
    }
}

