package cs319;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GradeBookTest {

    private Student alice;
    private Student bob;
    private GradeBook gradeBook;

    @BeforeEach
    void setUp() {
        alice = new Student("A001", "Alice");
        bob = new Student("B001", "Bob");
        gradeBook = new GradeBook("CS319");
    }

    // --- Student tests ---

    @Test
    void student_gettersReturnConstructorValues() {
        assertEquals("A001", alice.getId());
        assertEquals("Alice", alice.getName());
    }

    @Test
    void student_equalityBasedOnId() {
        Student aliceDuplicate = new Student("A001", "Alice Duplicate");
        assertEquals(alice, aliceDuplicate);
        assertNotEquals(alice, bob);
    }

    @Test
    void student_throwsOnBlankId() {
        assertThrows(IllegalArgumentException.class, () -> new Student("", "Name"));
        assertThrows(IllegalArgumentException.class, () -> new Student(null, "Name"));
    }

    @Test
    void student_throwsOnBlankName() {
        assertThrows(IllegalArgumentException.class, () -> new Student("X1", ""));
        assertThrows(IllegalArgumentException.class, () -> new Student("X1", null));
    }

    // --- GradeBook enrollment tests ---

    @Test
    void gradeBook_courseNameReturnedCorrectly() {
        assertEquals("CS319", gradeBook.getCourseName());
    }

    @Test
    void gradeBook_enrolledStudentAppearsInSet() {
        gradeBook.enroll(alice);
        assertTrue(gradeBook.getEnrolledStudents().contains(alice));
    }

    @Test
    void gradeBook_enrollingSameStudentTwiceIsIdempotent() {
        gradeBook.enroll(alice);
        gradeBook.enroll(alice);
        assertEquals(1, gradeBook.getEnrolledStudents().size());
    }

    @Test
    void gradeBook_addGradeToUnenrolledStudentThrows() {
        assertThrows(IllegalArgumentException.class, () -> gradeBook.addGrade(alice, 85));
    }

    // --- Grade recording tests ---

    @Test
    void gradeBook_gradesRecordedCorrectly() {
        gradeBook.enroll(alice);
        gradeBook.addGrade(alice, 80);
        gradeBook.addGrade(alice, 90);
        List<Double> grades = gradeBook.getGradesFor(alice);
        assertEquals(List.of(80.0, 90.0), grades);
    }

    @Test
    void gradeBook_invalidScoreThrows() {
        gradeBook.enroll(alice);
        assertThrows(IllegalArgumentException.class, () -> gradeBook.addGrade(alice, -1));
        assertThrows(IllegalArgumentException.class, () -> gradeBook.addGrade(alice, 101));
    }

    // --- Average and letter grade tests ---

    @Test
    void gradeBook_averageIsCorrect() {
        gradeBook.enroll(alice);
        gradeBook.addGrade(alice, 80);
        gradeBook.addGrade(alice, 90);
        assertEquals(85.0, gradeBook.getAverage(alice), 0.001);
    }

    @Test
    void gradeBook_averageWithNoGradesIsZero() {
        gradeBook.enroll(alice);
        assertEquals(0.0, gradeBook.getAverage(alice), 0.001);
    }

    @Test
    void gradeBook_letterGradeA() {
        gradeBook.enroll(alice);
        gradeBook.addGrade(alice, 95);
        assertEquals("A", gradeBook.getLetterGrade(alice));
    }

    @Test
    void gradeBook_letterGradeB() {
        gradeBook.enroll(alice);
        gradeBook.addGrade(alice, 85);
        assertEquals("B", gradeBook.getLetterGrade(alice));
    }

    @Test
    void gradeBook_letterGradeC() {
        gradeBook.enroll(alice);
        gradeBook.addGrade(alice, 75);
        assertEquals("C", gradeBook.getLetterGrade(alice));
    }

    @Test
    void gradeBook_letterGradeD() {
        gradeBook.enroll(alice);
        gradeBook.addGrade(alice, 65);
        assertEquals("D", gradeBook.getLetterGrade(alice));
    }

    @Test
    void gradeBook_letterGradeF() {
        gradeBook.enroll(alice);
        gradeBook.addGrade(alice, 55);
        assertEquals("F", gradeBook.getLetterGrade(alice));
    }

    @Test
    void gradeBook_multipleStudents() {
        gradeBook.enroll(alice);
        gradeBook.enroll(bob);
        gradeBook.addGrade(alice, 92);
        gradeBook.addGrade(bob, 74);
        assertEquals("A", gradeBook.getLetterGrade(alice));
        assertEquals("C", gradeBook.getLetterGrade(bob));
    }
}
