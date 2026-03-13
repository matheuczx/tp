package seedu.tutorswift;

public class TutorSwift {
    /**
     * Main entry-point for the TutorSwift application.
     */
    public static void main(String[] args) {
        Ui ui = new Ui();

        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            String input = ui.readCommand();

            if (input.trim().equalsIgnoreCase("bye")) {
                ui.showExit();
                isExit = true;
            }
        }

        ui.close();
    }
}
