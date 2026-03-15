package seedu.tutorswift;

import org.junit.jupiter.api.Test;
import seedu.tutorswift.command.Command;
import seedu.tutorswift.command.DeleteCommand;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void parseUserInput_validDeleteIndex_returnsDeleteCommand() throws TutorSwiftException {
        Command result = Parser.parseUserInput("delete 1");
        assertInstanceOf(DeleteCommand.class, result);
    }

    @Test
    public void parseUserInput_missingDeleteIndex_throwsTutorSwiftException() {
        assertThrows(TutorSwiftException.class, () -> Parser.parseUserInput("delete"));
    }

    @Test
    public void parseUserInput_nonIntegerDeleteIndex_throwsTutorSwiftException() {
        assertThrows(TutorSwiftException.class, () -> Parser.parseUserInput("delete abc"));
    }

    @Test
    public void parseUserInput_list_returnsListCommand() throws TutorSwiftException {
        Command result = Parser.parseUserInput("list");
        assertInstanceOf(seedu.tutorswift.command.ListCommand.class, result);
    }

    @Test
    public void parseUserInput_listWithSpaces_returnsListCommand() throws TutorSwiftException {
        Command result = Parser.parseUserInput("   list   ");
        assertInstanceOf(seedu.tutorswift.command.ListCommand.class, result);
    }
}
