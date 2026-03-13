package seedu.tutorswift;

public class StudentList {

    private Student[] students;
    private int size;

    public StudentList() {
        students = new Student[100]; // simple fixed-size array for now
        size = 0;
    }

    public void addStudent(Student s) {
        students[size] = s;
        size++;
    }

    public Student[] getStudents() {
        Student[] current = new Student[size];
        for (int i = 0; i < size; i++) {
            current[i] = students[i];
        }
        return current;
    }

    public int getSize() {
        return size;
    }
}
