/*
 Student.java
 Core Student class for Assignment 4: Student Management System
*/
public class Student {
    // Private fields (encapsulation)
    private String name;
    private String id;
    private String major;
    private double gpa;
    private int credits;

    // Constructor: name, id, major. GPA and credits initialized as specified.
    public Student(String name, String id, String major) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name must not be empty");
        }
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID must not be empty");
        }
        if (major == null) {
            major = "";
        }
        this.name = name;
        this.id = id;
        this.major = major;
        this.gpa = 0.0;
        this.credits = 0;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getMajor() {
        return major;
    }

    public double getGpa() {
        return gpa;
    }

    public int getCredits() {
        return credits;
    }

    // Setters with basic validation to preserve valid state
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name must not be empty");
        this.name = name;
    }

    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) throw new IllegalArgumentException("ID must not be empty");
        this.id = id;
    }

    public void setMajor(String major) {
        if (major == null) major = "";
        this.major = major;
    }

    // Sets GPA, validating typical 0.0..4.0 range
    public void setGpa(double gpa) {
        updateGPA(gpa);
    }

    // Sets credits (absolute overwrite). Must be non-negative.
    public void setCredits(int credits) {
        if (credits < 0) throw new IllegalArgumentException("Credits cannot be negative");
        this.credits = credits;
    }

    // Utility method: increases credits by c (c must be >= 0)
    public void addCredits(int c) {
        if (c < 0) throw new IllegalArgumentException("Added credits must be non-negative");
        // guard against overflow (simple check)
        long result = (long) this.credits + c;
        if (result > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Credits overflow");
        }
        this.credits += c;
    }

    // Update GPA with validation (0.0 .. 4.0). Use setter-like method name from spec.
    public void updateGPA(double newGPA) {
        if (newGPA < 0.0 || newGPA > 4.0) {
            throw new IllegalArgumentException("GPA must be between 0.0 and 4.0");
        }
        this.gpa = newGPA;
    }

    // isHonors(): true if GPA >= 3.5
    public boolean isHonors() {
        return this.gpa >= 3.5;
    }

    // toString(): textual representation
    @Override
    public String toString() {
        return String.format("Student{name='%s', id='%s', major='%s', gpa=%.2f, credits=%d}",
                name, id, major, gpa, credits);
    }
}