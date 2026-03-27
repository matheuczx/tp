package seedu.tutorswift.command;

import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.TutorSwiftException;
import seedu.tutorswift.Ui;

import java.util.ArrayList;

/**
 * Represents a command that finds students based on name, subject, and/or academic level.
 * <p>
 * The search is case-insensitive and supports partial matching.
 * Users can filter by one or more fields using prefixes:
 * <ul>
 *     <li>n/ for name</li>
 *     <li>s/ for subject</li>
 *     <li>l/ for academic level</li>
 * </ul>
 */
public class FindCommand extends Command {

    private final String name;
    private final String subject;
    private final String level;

    public FindCommand(String name, String subject, String level) throws TutorSwiftException {
        // Remove leading/trailing spaces
        String trimmedName = (name == null) ? null : name.trim();
        String trimmedSubject = (subject == null) ? null : subject.trim();
        String trimmedLevel = (level == null) ? null : level.trim();

        // If all fields are null or empty, throw exception
        if ((trimmedName == null || trimmedName.isEmpty()) &&
                (trimmedSubject == null || trimmedSubject.isEmpty()) &&
                (trimmedLevel == null || trimmedLevel.isEmpty())) {
            throw new TutorSwiftException("At least one search field must be provided.");
        }

        this.name = name;
        this.subject = subject;
        this.level = level;
    }

    @Override
    public void execute(StudentList students, Ui ui) throws TutorSwiftException {
        assert students != null : "StudentList cannot be null";
        assert ui != null : "UI cannot be null";

        ArrayList<Student> results = new ArrayList<>();

        for (int i = 0; i < students.getSize(); i++) {
            Student s = students.getStudent(i);

            boolean matches = true;

            if (name != null) {
                matches &= s.getName().toLowerCase().contains(name.toLowerCase());
            }
            if (subject != null) {
                matches &= s.getSubject().toLowerCase().contains(subject.toLowerCase());
            }
            if (level != null) {
                matches &= s.getAcademicLevel().toLowerCase().contains(level.toLowerCase());
            }

            if (matches) {
                results.add(s);
            }
        }

        ui.showFindResults(results);
    }
}
