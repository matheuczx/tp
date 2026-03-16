package seedu.tutorswift;

/**
 * Represents a student with a name, subject, and academic level.
 */
public class Student {

    private String name;
    private String subject;
    private String academicLevel;

    /**
     * Creates a student with the given name, subject, and academic level.
     */
    public Student(String name, String academicLevel, String subject) {
        this.name = name;
        this.academicLevel = academicLevel;
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public String getAcademicLevel() {
        return academicLevel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setAcademicLevel(String academicLevel) {
        this.academicLevel = academicLevel;
    }

    public void editStudent(String name, String academicLevel, String subject) {
        if (name != null) {
            this.name = name;
        }

        if (academicLevel != null) {
            this.academicLevel = academicLevel;
        }

        if (subject != null) {
            this.subject = subject;
        }
    }

    /**
     * Returns a string representation of the student in the format:
     * "name | academic level | subject".
     *
     * This is used for displaying the student in the UI.
     *
     * @return a formatted string representing the student
     */
    @Override
    public String toString() {
        return name + " | " + academicLevel + " | " + subject;
    }

}
