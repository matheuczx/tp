package seedu.tutorswift;

import seedu.tutorswift.command.Command;

/**
 * Main entry-point for the TutorSwift application.
 */
public class TutorSwift {
    private Ui ui;
    private StudentList students;

    public TutorSwift() {
        ui = new Ui();
        students = new StudentList();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readCommand();

                // Parse user input into command object to determine command name
                Command c = Parser.parseUserInput(userInput);
                c.execute(students, ui);
                isExit = c.isExit();
                ui.close();
            } catch (TutorSwiftException e) {
                ui.showError(e.getMessage());
            }
        }

    }

    public static void main(String[] args) {
        new TutorSwift().run();
    }
}
