
import java.util.Scanner;

public class Main {

    public static Student getTopStudent(Student[] arr) {
        if (arr == null || arr.length == 0) return null;
        Student top = null;
        for (Student s : arr) {
            if (s == null) continue;
            if (top == null || s.getGpa() > top.getGpa()) top = s;
        }
        return top;
    }

    public static int countHonors(Student[] arr) {
        if (arr == null) return 0;
        int count = 0;
        for (Student s : arr) {
            if (s != null && s.isHonors()) count++;
        }
        return count;
    }

    public static int totalCredits(Student[] arr) {
        if (arr == null) return 0;
        int sum = 0;
        for (Student s : arr) {
            if (s != null) sum += s.getCredits();
        }
        return sum;
    }

    // helper: find index of a student by id in an array
    private static int findStudentIndexById(Student[] arr, String id) {
        if (arr == null || id == null) return -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null && id.equals(arr[i].getId())) return i;
        }
        return -1;
    }

    // helper: get next free index in array or -1 if full
    private static int nextFreeIndex(Student[] arr) {
        for (int i = 0; i < arr.length; i++) if (arr[i] == null) return i;
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int CAPACITY = 20;
        Student[] students = new Student[CAPACITY];

        // Pre-populate at least five students (fulfills driver requirement)
        Student s1 = new Student("Алия Жұмабаева", "S1001", "Computer Science");
        s1.updateGPA(3.8); s1.addCredits(30);
        students[0] = s1;

        Student s2 = new Student("Иманғали Байдібекұлы", "S1002", "Software Engineering");
        s2.updateGPA(3.2); s2.addCredits(45);
        students[1] = s2;

        Student s3 = new Student("Дариға Қанатқызы", "S1003", "Electronics Engineering");
        s3.updateGPA(3.9); s3.addCredits(60);
        students[2] = s3;

        Student s4 = new Student("Темірғали Нариманұлы", "S1004", "Media Technology");
        s4.updateGPA(2.9); s4.addCredits(20);
        students[3] = s4;

        Student s5 = new Student("Айқын Серікболатов", "S1005", "Cybersecurety");
        s5.updateGPA(3.6); s5.addCredits(75);
        students[4] = s5;

        System.out.println("Pre-populated 5 students (indices 0..4).");

        Course course = null; // created on demand

        boolean running = true;
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1 - Add student");
            System.out.println("2 - Update student GPA");
            System.out.println("3 - Add credits to student");
            System.out.println("4 - List students");
            System.out.println("5 - Create course");
            System.out.println("6 - Add student to course (by student ID)");
            System.out.println("7 - Show course summary");
            System.out.println("8 - Run array processing (top, honors count, total credits)");
            System.out.println("0 - Exit");
            System.out.print("Choose option: ");

            String opt = scanner.nextLine().trim();
            switch (opt) {
                case "1": {
                    System.out.print("Enter full name: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Enter student ID: ");
                    String id = scanner.nextLine().trim();
                    System.out.print("Enter major: ");
                    String major = scanner.nextLine().trim();

                    try {
                        Student s = new Student(name, id, major);
                        int idx = nextFreeIndex(students);
                        if (idx == -1) {
                            System.out.println("Student array is full. Cannot add more students.");
                        } else {
                            students[idx] = s;
                            System.out.println("Student added at index " + idx + ": " + s);
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error creating student: " + e.getMessage());
                    }
                    break;
                }
                case "2": {
                    System.out.print("Enter student ID to update GPA: ");
                    String id = scanner.nextLine().trim();
                    int idx = findStudentIndexById(students, id);
                    if (idx == -1) {
                        System.out.println("Student not found.");
                        break;
                    }
                    System.out.print("Enter new GPA (0.0 - 4.0): ");
                    String gpaStr = scanner.nextLine().trim();
                    try {
                        double gpa = Double.parseDouble(gpaStr);
                        students[idx].updateGPA(gpa);
                        System.out.println("Updated: " + students[idx]);
                    } catch (NumberFormatException nfe) {
                        System.out.println("Invalid number format.");
                    } catch (IllegalArgumentException iae) {
                        System.out.println("Invalid GPA: " + iae.getMessage());
                    }
                    break;
                }
                case "3": {
                    System.out.print("Enter student ID to add credits: ");
                    String id = scanner.nextLine().trim();
                    int idx = findStudentIndexById(students, id);
                    if (idx == -1) {
                        System.out.println("Student not found.");
                        break;
                    }
                    System.out.print("Enter credits to add (positive integer): ");
                    String cStr = scanner.nextLine().trim();
                    try {
                        int c = Integer.parseInt(cStr);
                        students[idx].addCredits(c);
                        System.out.println("Updated: " + students[idx]);
                    } catch (NumberFormatException nfe) {
                        System.out.println("Invalid number format.");
                    } catch (IllegalArgumentException iae) {
                        System.out.println("Invalid credits: " + iae.getMessage());
                    }
                    break;
                }
                case "4": {
                    System.out.println("Students list:");
                    for (int i = 0; i < students.length; i++) {
                        System.out.print("[" + i + "] ");
                        if (students[i] != null) System.out.println(students[i]);
                        else System.out.println("<empty>");
                    }
                    break;
                }
                case "5": {
                    System.out.print("Enter course name: ");
                    String cname = scanner.nextLine().trim();
                    System.out.print("Enter instructor name: ");
                    String instr = scanner.nextLine().trim();
                    System.out.print("Enter course capacity (positive integer): ");
                    String capStr = scanner.nextLine().trim();
                    try {
                        int cap = Integer.parseInt(capStr);
                        course = new Course(cname, instr, cap);
                        System.out.println("Course created: " + cname + " by " + instr + " (capacity " + cap + ")");
                    } catch (NumberFormatException nfe) {
                        System.out.println("Invalid capacity.");
                    } catch (IllegalArgumentException iae) {
                        System.out.println("Error creating course: " + iae.getMessage());
                    }
                    break;
                }
                case "6": {
                    if (course == null) {
                        System.out.println("No course exists. Create a course first (option 5).");
                        break;
                    }
                    System.out.print("Enter student ID to add to course: ");
                    String id = scanner.nextLine().trim();
                    int sidx = findStudentIndexById(students, id);
                    if (sidx == -1) {
                        System.out.println("Student not found in array.");
                        break;
                    }
                    System.out.print("Enter index in course (0 .. " + (course.getStudents().length - 1) + "): ");
                    String idxStr = scanner.nextLine().trim();
                    try {
                        int cIndex = Integer.parseInt(idxStr);
                        course.addStudent(students[sidx], cIndex);
                        System.out.println("Added student to course at index " + cIndex);
                    } catch (NumberFormatException nfe) {
                        System.out.println("Invalid index format.");
                    } catch (IndexOutOfBoundsException | IllegalStateException | IllegalArgumentException ex) {
                        System.out.println("Error adding to course: " + ex.getMessage());
                    }
                    break;
                }
                case "7": {
                    if (course == null) {
                        System.out.println("No course exists.");
                    } else {
                        System.out.println(course);
                        Student topCredit = course.highestCreditStudent();
                        System.out.println("Highest-credit student in course: " + (topCredit != null ? topCredit : "<none>"));
                        System.out.printf("Course average GPA: %.2f%n", course.courseAverageGPA());
                    }
                    break;
                }
                case "8": {
                    Student top = getTopStudent(students);
                    System.out.println("Top student by GPA: " + (top != null ? top : "<none>"));
                    System.out.println("Number of honors students (GPA >= 3.5): " + countHonors(students));
                    System.out.println("Total credits across students: " + totalCredits(students));
                    break;
                }
                case "0": {
                    running = false;
                    System.out.println("Exiting.");
                    break;
                }
                default:
                    System.out.println("Unknown option. Try again.");
            }
        }

        scanner.close();
    }
}