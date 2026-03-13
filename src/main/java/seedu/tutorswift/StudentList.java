package seedu.tutorswift;

import java.util.ArrayList;

/**
 * Provides methods for operations (e.g. add, delete, and get student) handling
 * an <code>ArrayList</code> of <code>Student</code> objects.
 */
public class StudentList {
    private final ArrayList<Student> students;

    public StudentList(){
        this.students = new ArrayList<Student>();
    }

    public void addStudent(Student studentToAdd){
        this.students.add(studentToAdd);
    }

    public Student getStudent(int studentId) {
        return this.students.get(studentId);
    }

    public int getSize() {
        return this.students.size();
    }
}
