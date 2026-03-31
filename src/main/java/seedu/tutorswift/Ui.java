package seedu.tutorswift;

import java.util.List;
import java.util.Scanner;
import java.time.YearMonth;

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
     * Prints formatted details for the specified student.
     *
     * @param student The student whose details are to be printed.
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
    public void showDeleteSuccess(Student deletedStudent, int totalStudents) {
        System.out.println(LINE_DIVIDER);
        System.out.println(" Done! I've removed this student:");
        printStudentDetails(deletedStudent);
        System.out.println(" Now you have " + totalStudents + " students in the list.");
        System.out.println(LINE_DIVIDER);
    }


    // @@author Alex-Chen-666

    /**
     * Displays all students currently in the active list.
     *
     * @param students The StudentList containing active students.
     */
    public void showStudentList(StudentList students) {
        assert students != null : "StudentList cannot be null";
        System.out.println(LINE_DIVIDER);

        if (students.getActiveSize() == 0) {
            System.out.println(" Your active student list is currently empty.");
        } else {
            System.out.println(" Here are the ACTIVE students in your list:");
            for (int i = 0; i < students.getActiveSize(); i++) {
                System.out.println((i + 1) + ". " + students.getActiveStudent(i));
            }
        }
        System.out.println(LINE_DIVIDER);
    }

    /**
     * Displays all students currently in the archive.
     *
     * @param students The StudentList containing archived students.
     */
    public void showArchivedList(StudentList students) {
        assert students != null : "StudentList cannot be null";
        System.out.println(LINE_DIVIDER);

        if (students.getArchivedSize() == 0) {
            System.out.println(" Your archive is currently empty.");
        } else {
            System.out.println(" Here are the ARCHIVED students:");
            for (int i = 0; i < students.getArchivedSize(); i++) {
                System.out.println((i + 1) + ". " + students.getArchivedStudent(i));
            }
        }
        System.out.println(LINE_DIVIDER);
    }

    /**
     * Shows a success message after archiving a student.
     *
     * @param student The student who was archived.
     */
    public void showArchiveSuccess(Student student) {
        System.out.println(LINE_DIVIDER);
        System.out.println(" [Archive Success] This student has been moved to archive:");
        printStudentDetails(student);
        System.out.println(LINE_DIVIDER);
    }

    /**
     * Shows a success message after unarchiving a student.
     *
     * @param student The student who was moved back to the active list.
     */
    public void showUnarchiveSuccess(Student student) {
        System.out.println(LINE_DIVIDER);
        System.out.println(" [Unarchive Success] Student moved back to active list:");
        printStudentDetails(student);
        System.out.println(LINE_DIVIDER);
    }
    // @@author
    // @@author Alex-Chen-666
    /**
     * Shows a success message after permanently deleting a student from the archive.
     *
     * @param student The archived student who was removed.
     * @param totalArchived The remaining number of students in the archive.
     */
    public void showDeleteArchiveSuccess(Student student, int totalArchived) {
        System.out.println(LINE_DIVIDER);
        System.out.println(" [Done] Permanently removed this student from ARCHIVE:");
        printStudentDetails(student);
        System.out.println(" Now you have " + totalArchived + " students in your archive.");
        System.out.println(LINE_DIVIDER);
    }
    // @@author

    /**
     * Displays students that match the find criteria.
     *
     * @param results A list of students that match the search filters.
     */
    public void showFindResults(java.util.ArrayList<Student> results) {
        assert results != null : "Results list cannot be null";

        System.out.println(LINE_DIVIDER);

        if (results.isEmpty()) {
            System.out.println("No matching students found.");
        } else {
            System.out.println("Here are the matching students:");
            for (int i = 0; i < results.size(); i++) {
                System.out.println((i + 1) + ". " + results.get(i));
            }
        }

        System.out.println(LINE_DIVIDER);
    }

    public void showRemarkSuccess(Student student) {
        System.out.println(LINE_DIVIDER);
        System.out.println(" Done! I've added a remark for this student:");
        printStudentDetails(student);
        System.out.println(LINE_DIVIDER);
    }

    /**
     * Shows success message after setting a student's per-lesson fee.
     *
     * @param student      The student whose fee was updated.
     * @param feePerLesson The new per-lesson fee in dollars.
     */
    public void showFeeSuccess(Student student, int feePerLesson) {
        System.out.println(LINE_DIVIDER);
        System.out.println(" Done! Fee per lesson for " + student.getName()
                + " set to $" + feePerLesson + "/lesson.");
        printStudentDetails(student);
        System.out.println(LINE_DIVIDER);
    }

    /**
     * Shows success message after marking a student as paid for a given month.
     *
     * @param student The student marked as paid.
     * @param month   The month that was marked paid.
     */
    public void showPaidSuccess(Student student, YearMonth month) {
        System.out.println(LINE_DIVIDER);
        System.out.println(" Done! " + student.getName() + " is marked as PAID for "
                + month.getMonth() + " " + month.getYear() + ".");
        printStudentDetails(student);
        System.out.println(LINE_DIVIDER);
    }

    /**
     * Shows success message after marking a student as unpaid for a given month.
     *
     * @param student The student marked as unpaid.
     * @param month   The month that was marked unpaid.
     */
    public void showUnpaidSuccess(Student student, YearMonth month) {
        System.out.println(LINE_DIVIDER);
        System.out.println(" Done! " + student.getName() + " is marked as UNPAID for "
                + month.getMonth() + " " + month.getYear() + ".");
        printStudentDetails(student);
        System.out.println(LINE_DIVIDER);
    }

    /**
     * Displays a message when there are no upcoming lessons.
     */
    public void showEmptyUpcomingLessons() {
        System.out.println("You have no upcoming lessons scheduled.");
    }

    /**
     * Formats and prints the list of upcoming lessons according to current date and time.
     *
     * @param entries The sorted list of relative lessons.
     */
    public void showUpcomingLessons(List<RelativeLesson> entries) {
        StringBuilder output = new StringBuilder("Here are your upcoming lessons for the next 7 days:\n");

        for (RelativeLesson entry : entries) {
            String dayMarker = entry.getDaysFromToday() == 0 ? " (Today)" :
                    entry.getDaysFromToday() == 1 ? " (Tomorrow)" : "";

            output.append("- ").append(entry.getLesson().getDay()).append(dayMarker)
                    .append(" ").append(entry.getLesson().getStartTime())
                    .append(" to ").append(entry.getLesson().getEndTime())
                    .append(" | ").append(entry.getStudent().getName())
                    .append(" (").append(entry.getStudent().getSubject()).append(")\n");
        }

        System.out.println(output.toString().trim());
    }

    /**
     * Prints a success message when a lesson is scheduled.
     *
     * @param studentName The name of the student.
     * @param lesson The scheduled lesson timing.
     */
    public void showLessonScheduled(String studentName, Lesson lesson) {
        System.out.println("Got it! I've scheduled a lesson for " + studentName + " on " + lesson);
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

