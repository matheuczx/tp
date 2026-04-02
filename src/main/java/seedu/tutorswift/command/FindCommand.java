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
        ArrayList<Student> results = new ArrayList<>();

        // Searches the active student list
        searchList(students.getAllActive(), results);
        // Searches the archived student list
        searchList(students.getAllArchived(), results);

        ui.showFindResults(results);
    }

    /**
     * Helper method to perform partial case-insensitive match on a list.
     */
    private void searchList(ArrayList<Student> source, ArrayList<Student> results) {
        for (Student s : source) {
            boolean isMatch = true;
            if (name != null) {
                isMatch &= s.getName().toLowerCase().contains(name.toLowerCase());
            }
            if (subject != null) {
                isMatch &= s.getSubject().toLowerCase().contains(subject.toLowerCase());
            }
            if (level != null) {
                isMatch &= s.getAcademicLevel().toLowerCase().contains(level.toLowerCase());
            }
            if (isMatch) {
                results.add(s);
            }
        }
    }
}
