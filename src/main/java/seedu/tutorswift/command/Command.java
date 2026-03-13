package seedu.tutorswift.command;

import seedu.tutorswift.StudentList;
import seedu.tutorswift.Ui;

public abstract class Command {

    public abstract void execute(StudentList students, Ui ui);

    /**
     * Indicates whether the application should terminate after this command.
     * By default, commands return <code>false</code>.
     *
     * @return <code>true</code> if the application should exit; <code>false</code> otherwise.
     */
    public boolean isExit() {
        return false;
    }

}
