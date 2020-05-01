import java.util.ArrayList;

/**
 * This class defines a {@code Student} object.
 * It provides all the properties and methods necessary to manage a student in the bigger context of the {@code School}.
 * Each student, apart from the properties inherited from {@code Person}, has also an {@code attendedCourses} ArrayList
 * containing all the courses the student is enrolled in, and a {@code certificates} ArrayList containing all the subjectIDs
 * of the subjects that he or she has already taken.
 *
 * @author Giovanni Arcudi
 * @version 1.0
 */
public class Student extends Person {
    private ArrayList<Course> attendedCourses;    // The courses the student is attending.
    private ArrayList<Integer> certificates;      // The collection of subject IDs of the subjects that they have taken.

    /**
     * Creates a new {@code Student} object and initializes the {@code name}, {@code gender}, {@code age},
     * {@code attendedCourses} and {@code certificates} properties.
     *
     * @param name                      the name of the student
     * @param gender                    student's gender: male (‘M’) or female (‘F’)
     * @param age                       how old the student is, in years
     * @throws InvalidInputException    If gender is not M or F, or if age is not positive
     */
    public Student(String name, char gender, int age) {
        super(name, gender, age);
        attendedCourses = new ArrayList<>();
        certificates = new ArrayList<>();
    }

    /**
     * Adds the {@code Course} object passed as parameter to the {@code attendedCourses} ArrayList.
     *
     * @param attendedCourse        the course to add to the attendedCourses ArrayList
     */
    public void addAttendedCourse(Course attendedCourse) {
        this.attendedCourses.add(attendedCourse);
    }

    /**
     * Removes the {@code Course} object passed as parameter from the {@code attendedCourses} ArrayList.
     *
     * @param attendedCourse        the course to remove from the attendedCourses ArrayList
     */
    public void removeAttendedCourse(Course attendedCourse) {
        this.attendedCourses.remove(attendedCourse);
    }

    /**
     * Student's {@code attendedCourses} ArrayList accessor method.
     *
     * @return the ArrayList containing all the courses the student is attending
     */
    public ArrayList<Course> getAttendedCourse() {
        return attendedCourses;
    }

    /**
     * Returns an ArrayList of all the {@code Subjects} the student is studying, deriving them from the
     * {@code attendedCourses} ArrayList.
     *
     * @return the ArrayList containing all the courses the student is attending
     */
    public ArrayList<Integer> getAttendedSubject() {
        ArrayList<Integer> subjectList = new ArrayList<>();
        for(Course course : getAttendedCourse()){
            subjectList.add(course.getSubject().getID());
        }
        return subjectList;
    }

    /**
     * Adds the {@code ID} of the subject to the {@code certificates} ArrayList.
     *
     * @param subject       the name of the subject the student graduated from
     */
    public void graduate(Subject subject) {
        certificates.add(subject.getID());
    }

    /**
     * Student's {@code certificates} ArrayList accessor method.
     *
     * @return the ArrayList of certificates obtained by the student
     */
    public ArrayList<Integer> getCertificates() {
        return certificates;
    }

    /**
     * Checks whether or not the student has already obtained the certificate for the input {@code Subject}.
     *
     * @param subject       the name of the subject
     * @return true if the student has already obtained the subject's certificate, false otherwise
     */
    public boolean hasCertificate(Subject subject) {
        return certificates.contains(subject.getID());
    }

    /**
     * Returns a pretty-print String of the subject.
     *
     * @return a table-formatted string containing the student's name, gender, age, and certificate.
     */
    @Override
    public String toString() {

        // Stores the details of the student in a table-formatted string, procedurally adding all of them.
        StringBuilder studentDetails = new StringBuilder();
        studentDetails.append(String.format("%-20s %5s %16s", getName(), getGender(), getAge()));

        /* Depending on how many certificates a student has, adds the correct row of information with the right
        formatting to the table-formatted string */
        StringBuilder certificateString = new StringBuilder();
        for (int certificate : getCertificates()) {
            certificateString.append(certificate).append(" ");
        }
        studentDetails.append(String.format("%22s", certificateString.toString()));

        /* If is attending some Courses, in columns, it adds them to the table-formatted string, otherwise it adds "Free" to the
        string. */
        if (getAttendedCourse().isEmpty()) {
            studentDetails.append(String.format("%32s%n", "Free"));
        } else {
            for (int i = 0; i < getAttendedCourse().size(); i++) {
                Course[] courseName = getAttendedCourse().toArray(new Course[0]);
                if (i == 0 && getAttendedCourse().size() == 1) {
                    studentDetails.append(String.format("%32s%n", courseName[i].getSubject().getDescription()));
                } else if (i == 0) {
                    studentDetails.append(String.format("%32s%n", courseName[i].getSubject().getDescription()));
                } else if (i == getAttendedCourse().size() - 1) {
                    studentDetails.append(String.format("%97s%n", courseName[i].getSubject().getDescription()));
                } else {
                    studentDetails.append(String.format("%97s%n", courseName[i].getSubject().getDescription()));
                }
            }
        }
        studentDetails.append("\n");
        return studentDetails.toString();
    }
}
