package seedu.tutorswift;

import java.time.YearMonth;
import java.util.ArrayList;

/**
 * Represents a student with a name, subject, and academic level.
 * Includes status tracking for active or archived states.
 */
public class Student {

    private String name;
    private String subject;
    private String academicLevel;
    private final ArrayList<Lesson> lessons;
    private boolean isArchived;
    private final ArrayList<Grade> grades;
    private String remark;
    private final FeeRecord feeRecord;

    /**
     * Creates a student with the given name, subject, and academic level.
     * Default status is active (not archived).
     */
    public Student(String name, String academicLevel, String subject) {
        this.name = name;
        this.academicLevel = academicLevel;
        this.subject = subject;
        this.isArchived = false;
        this.lessons = new ArrayList<>();
        this.grades = new ArrayList<>();
        this.remark = null;
        this.feeRecord = new FeeRecord();
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

    public FeeRecord getFeeRecord() {
        return feeRecord;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
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

    public void setFeePerLesson(int fee) {
        this.feeRecord.setFeePerLesson(fee);
    }

    public void markPaid(YearMonth month) {
        this.feeRecord.markPaid(month);
    }

    public void markUnpaid(YearMonth month) {
        this.feeRecord.markUnpaid(month);
    }

    public void editStudent(String name, String academicLevel, String subject) {
        if (name != null) {
            this.name = name;
        }

        if (academicLevel != null) {
            this.academicLevel = academicLevel;
        }

        if (subject != null) {
            if (!subject.equals(this.subject)) {
                this.subject = subject;
                this.grades.clear();
            }
        }
    }
    // @@author Alex-Chen-666
    /**
     * Checks if the student is currently archived.
     *
     * @return true if archived, false otherwise.
     */
    public boolean isArchived() {
        return isArchived;
    }
    /**
     * Sets the archived status of the student.
     *
     * @param archived The new status to set.
     */
    public void setArchived(boolean archived) {
        isArchived = archived;
    }
    // @@author

    public void addLesson(Lesson newLesson) throws TutorSwiftException {
        for (Lesson existingLesson : lessons) {
            if (existingLesson.isOverlapping(newLesson)) {
                throw new TutorSwiftException("Schedule conflict! Overlaps with existing lesson: " + existingLesson);
            }
        }
        lessons.add(newLesson);
    }

    public void addGrade(String assessment, int score) {
        grades.add(new Grade(assessment, score));
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void removeGrade(String assessment) throws TutorSwiftException {
        boolean removed = false;

        for (int i = 0; i < grades.size(); i++) {
            if (grades.get(i).getAssessment().equals(assessment)) {
                grades.remove(i);
                removed = true;
                break;
            }
        }

        if (!removed) {
            throw new TutorSwiftException("Grade not found.");
        }
    }

    public void clearRemark() throws TutorSwiftException {
        if (remark == null) {
            throw new TutorSwiftException("No remark to remove.");
        }
        remark = null;
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
        StringBuilder gradeStr = new StringBuilder();
        if (!grades.isEmpty()) {
            gradeStr.append(" | Grades: ");
            for (int i = 0; i < grades.size(); i++) {
                gradeStr.append("[").append(grades.get(i).toString()).append("]");
                if (i < grades.size() - 1) {
                    gradeStr.append(" "); // space between grades only
                }
            }
        }

        StringBuilder lessonStr = new StringBuilder();
        if (!lessons.isEmpty()) {
            lessonStr.append(" | Lessons: ");
            for (int i = 0; i < lessons.size(); i++) {
                lessonStr.append("[").append(lessons.get(i).toString()).append("]");
                if (i < lessons.size() - 1) {
                    lessonStr.append(" ");
                }
            }
        }
      
        String status = isArchived ? " [ARCHIVED]" : "";

        String remarkStr = (remark != null) ? " | Remark: " + remark : "";

        return name + " | " + academicLevel + " | " + subject
                + gradeStr.toString()
                + lessonStr.toString()
                + remarkStr
                + " | " + feeRecord.toString()
                + status;
    }

    // @@author Alex-Chen-666
    /**
     * Converts the student data into a save-friendly string format.
     *
     * @return A formatted string for file storage.
     */
    public String toSaveFormat() {
        StringBuilder gradeStr = new StringBuilder();

        for (int i = 0; i < grades.size(); i++) {
            gradeStr.append(grades.get(i).getAssessment())
                .append(":")
                .append(grades.get(i).getScore());
            if (i < grades.size() - 1) {
                gradeStr.append(",");
            }
        }
        String gradeFinal = gradeStr.length() == 0 ? "EMPTY" : gradeStr.toString();

        String remarkFinal = (remark == null) ? "NONE" : remark;

        StringBuilder lessonStr = new StringBuilder();
        for (int i = 0; i < lessons.size(); i++) {
            Lesson l = lessons.get(i);
            lessonStr.append(l.getDay().name())
                    .append(":")
                    .append(l.getStartTime().toString())
                    .append(":")
                    .append(l.getEndTime().toString());
            if (i < lessons.size() - 1) {
                lessonStr.append(",");
            }
        }
        String lessonFinal = lessonStr.isEmpty() ? "EMPTY" : lessonStr.toString();

        return name + " | " + academicLevel + " | " + subject + " | " + isArchived
                + " | " + gradeFinal + "|" + lessonFinal + "|" + remarkFinal + " | " + feeRecord.toSaveFormat();
    }
}
