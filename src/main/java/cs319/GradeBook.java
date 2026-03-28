package cs319;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages student grades for a single course.
 *
 * <p>Grades are stored as numeric scores in the range [0, 100].
 * Letter grades follow a standard scale:
 * A ≥ 90, B ≥ 80, C ≥ 70, D ≥ 60, F &lt; 60.
 */
public class GradeBook {

    private final String courseName;
    private final Map<Student, List<Double>> grades = new HashMap<>();

    public GradeBook(String courseName) {
        if (courseName == null || courseName.isBlank()) {
            throw new IllegalArgumentException("Course name must not be blank");
        }
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    /** Enrolls a student so they can receive grades. */
    public void enroll(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student must not be null");
        }
        grades.putIfAbsent(student, new ArrayList<>());
    }

    /**
     * Records a grade for an enrolled student.
     *
     * @param student the student to grade
     * @param score   a value in [0, 100]
     * @throws IllegalArgumentException if the student is not enrolled or the score is out of range
     */
    public void addGrade(Student student, double score) {
        if (!grades.containsKey(student)) {
            throw new IllegalArgumentException("Student is not enrolled: " + student);
        }
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100, got: " + score);
        }
        grades.get(student).add(score);
    }

    /**
     * Returns the average score for a student, or 0.0 if no grades have been recorded.
     */
    public double getAverage(Student student) {
        List<Double> studentGrades = getGradesFor(student);
        if (studentGrades.isEmpty()) {
            return 0.0;
        }
        double sum = 0;
        for (double g : studentGrades) {
            sum += g;
        }
        return sum / studentGrades.size();
    }

    /**
     * Returns the letter grade corresponding to a student's average score.
     */
    public String getLetterGrade(Student student) {
        double avg = getAverage(student);
        if (avg >= 90) return "A";
        if (avg >= 80) return "B";
        if (avg >= 70) return "C";
        if (avg >= 60) return "D";
        return "F";
    }

    /**
     * Returns an unmodifiable view of all grades recorded for a student.
     */
    public List<Double> getGradesFor(Student student) {
        if (!grades.containsKey(student)) {
            throw new IllegalArgumentException("Student is not enrolled: " + student);
        }
        return Collections.unmodifiableList(grades.get(student));
    }

    /**
     * Returns the set of all enrolled students.
     */
    public java.util.Set<Student> getEnrolledStudents() {
        return Collections.unmodifiableSet(grades.keySet());
    }
}
