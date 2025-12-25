
import java.util.Arrays;

public class Course {
    private String courseName;
    private String instructor;
    private Student[] students; // composition: Course "has" Students in an array

    // Constructor: courseName, instructor, and size of students array
    public Course(String courseName, String instructor, int arraySize) {
        if (courseName == null || courseName.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name must not be empty");
        }
        if (instructor == null) instructor = "";
        if (arraySize <= 0) {
            throw new IllegalArgumentException("Array size must be positive");
        }
        this.courseName = courseName;
        this.instructor = instructor;
        this.students = new Student[arraySize];
    }

    // Add student at specified index. Validates index and non-null student.
    // Does not allow overwriting an occupied slot.
    public void addStudent(Student s, int index) {
        if (s == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        if (index < 0 || index >= students.length) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }
        if (students[index] != null) {
            throw new IllegalStateException("Slot " + index + " is already occupied");
        }
        students[index] = s;
    }

    // Compute average GPA across non-null students. Returns 0.0 if no students present.
    public double courseAverageGPA() {
        double sum = 0.0;
        int count = 0;
        for (Student s : students) {
            if (s != null) {
                sum += s.getGpa();
                count++;
            }
        }
        return count == 0 ? 0.0 : sum / count;
    }

    // Return student with maximum credits; return null if no students.
    public Student highestCreditStudent() {
        Student top = null;
        for (Student s : students) {
            if (s != null) {
                if (top == null || s.getCredits() > top.getCredits()) {
                    top = s;
                }
            }
        }
        return top;
    }

    // Number of enrolled (non-null) students
    public int enrolledCount() {
        int cnt = 0;
        for (Student s : students) if (s != null) cnt++;
        return cnt;
    }

    // toString: summary with student list (compact)
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Course{name='").append(courseName).append("', instructor='").append(instructor).append("', ");
        sb.append("capacity=").append(students.length).append(", enrolled=").append(enrolledCount()).append(", ");
        sb.append(String.format("averageGPA=%.2f", courseAverageGPA())).append("}\n");
        sb.append("Students:\n");
        for (int i = 0; i < students.length; i++) {
            sb.append("  [").append(i).append("] ");
            if (students[i] != null) sb.append(students[i].toString());
            else sb.append("<empty>");
            sb.append("\n");
        }
        return sb.toString();
    }

    // Optional: getter to expose array snapshot (defensive copy)
    public Student[] getStudents() {
        return Arrays.copyOf(students, students.length);
    }
}