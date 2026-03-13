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
    public Student(String name, String subject, String academicLevel) {
        this.name = name;
        this.subject = subject;
        this.academicLevel = academicLevel;
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

    public void editStudent(String name, String subject, String academicLevel) {
        if (name != null) this.name = name;
        if (subject != null) this.subject = subject;
        if (academicLevel != null) this.academicLevel = academicLevel;
    }

}
