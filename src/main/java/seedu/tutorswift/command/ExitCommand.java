package seedu.tutorswift.command;

import seedu.tutorswift.StudentList;
import seedu.tutorswift.Ui;

/**
 * Represents the command to exit the application.
 * When executed, it triggers the display of the exit message and signals
 * the main program loop to stop.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(StudentList students, Ui ui){
        assert ui != null : "Ui must not be null when executing ExitCommand";
        assert students != null : "StudentList must not be null when executing ExitCommand";

        ui.showExit();
    }

    @Override
    public boolean isExit(){
        return true;
    }
}
