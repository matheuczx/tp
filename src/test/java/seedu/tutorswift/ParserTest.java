package seedu.tutorswift;

import org.junit.jupiter.api.Test;
import seedu.tutorswift.command.Command;
import seedu.tutorswift.command.DeleteCommand;
import seedu.tutorswift.command.EditCommand;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ParserTest {
    @Test
    public void showWelcome_printsCorrectMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ui ui = new Ui();
        ui.showWelcome();

        String output = outContent.toString();

        assertTrue(output.contains("Welcome to TutorSwift"));
        assertTrue(output.contains("How can I help you?"));

        ui.close();
    }

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

    @Test
    public void parseEdit_validAllFields_success() throws TutorSwiftException {
        String input = "edit 1 n/John l/Sec 4 s/Math";
        Command result = Parser.parseUserInput(input);

        assertInstanceOf(EditCommand.class, result);
    }

    @Test
    public void parseEdit_singleField_success() throws TutorSwiftException {
        String input = "edit 2 s/Biology";
        Command result = Parser.parseUserInput(input);

        assertInstanceOf(EditCommand.class, result);
    }

    @Test
    public void parseEdit_invalidIndex_throwsException() {
        // Test non-numeric index
        TutorSwiftException e1 = assertThrows(TutorSwiftException.class, () ->
                Parser.parseUserInput("edit abc n/John"));
        assertTrue(e1.getMessage().contains("is not a valid number"));

        // Test negative index
        TutorSwiftException e2 = assertThrows(TutorSwiftException.class, () ->
                Parser.parseUserInput("edit -1 n/John"));
        assertEquals("The index must be a positive integer.", e2.getMessage());
    }

    @Test
    public void parseEdit_noFieldsToEdit_throwsException() {
        // Test providing index but no prefixes
        TutorSwiftException e = assertThrows(TutorSwiftException.class, () ->
                Parser.parseUserInput("edit 1"));
        assertTrue(e.getMessage().contains("Name (n/), level (l/), or subject (s/) cannot be empty"));
    }

    @Test
    public void parseEdit_emptyPrefixValue_returnsNullForThatField() throws TutorSwiftException {
        String input = "edit 1 n/  l/Sec 2";
        Command result = Parser.parseUserInput(input);
        assertInstanceOf(EditCommand.class, result);
    }

    @Test
    public void parseUserInput_bye_returnsExitCommand() throws TutorSwiftException {
        Command result = Parser.parseUserInput("bye");
        assertInstanceOf(seedu.tutorswift.command.ExitCommand.class, result);
    }

    @Test
    public void parseUserInput_byeCommand_isExitTrue() throws TutorSwiftException {
        Command result = Parser.parseUserInput("bye");
        assertTrue(result.isExit());
    }
}
