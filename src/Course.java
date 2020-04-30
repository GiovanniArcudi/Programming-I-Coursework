import java.util.ArrayList;

/**
 * This class defines a {@code Course} object which is a school course about a specific {@link Subject}.
 * It provides all the properties and methods necessary to manage a Course in the bigger context of the {@code School}.
 * A {@code Course} object contains all the properties getters and setters, and methods to enroll a {@link Student},
 * assign an {@link Instructor}, get the size of the course, let the course advance for a day, and pretty-print all
 * course's details.
 *
 * @author Giovanni Arcudi
 * @version 1.0
 */
public class Course {
    private Subject subject;                            // The subject associated with the course.
    private int daysUntilStarts;                        // The number of days until the course starts.
    private int daysToRun;                              // The number of days that the course still has to run.
    private int status;                                 // The status of the course.
    private boolean courseCancelled;                    // T if the course has been cancelled, F otherwise.
    private Instructor courseInstructor;                // The Instructor of the course.
    private ArrayList<Student> enrolledStudents;        // The collection of students enrolled to this course.

    /**
     * Creates a {@code Course} object and initializes the {@code subject}, {@code daysUntilStarts}, {@code daysToRun}
     * and {@code enrolledStudents} properties using the given parameters.
     *
     * @param subject                   the subject associated with the course
     * @param daysUntilStarts           the number of days until the course starts
     * @throws InvalidInputException    If the number of days until the course starts is not a positive integer
     */
    public Course(Subject subject, int daysUntilStarts) {
        if (daysUntilStarts < 0) {
            throw new InvalidInputException("The number of days until the course starts must be greater or equal to 0");
        } else {
            this.subject = subject;
            this.daysUntilStarts = daysUntilStarts;
            daysToRun = subject.getDuration();
            enrolledStudents = new ArrayList<>();
        }
    }

    /**
     * Course's {@code Subject} accessor method.
     *
     * @return the subject associated with the course
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * Course's {@code status} accessor method.
     * If the course has not started, then returns the negative of the number of days until the course starts.
     * If the course is currently running, then returns the number of days left until the course finishes.
     * If the course has finished, then returns 0.
     *
     * @return the status of the course
     */
    public int getStatus() {
        if (daysUntilStarts > 0) {
            status = -daysUntilStarts;
            return status;
        } else if (daysUntilStarts == 0) {
            status = daysToRun;
            return status;
        } else {
            return 0;
        }
    }

    /**
     * Returns whether or not the course has been cancelled.
     *
     * @return true if the course has been cancelled, false otherwise
     */
    public boolean isCancelled() {
        return courseCancelled;
    }

    /**
     * Tries to assign the {@code Instructor} passed as parameter to this course.
     *
     * @param instructor        the instructor we want to set for the course
     * @return true if the instructor can teach the course, false otherwise
     */
    public boolean setInstructor(Instructor instructor) {
        if (instructor.canTeach(getSubject())) {
            courseInstructor = instructor;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Course's {@code Instructor} accessor method.
     *
     * @return the instructor of the course
     */
    public Instructor getInstructor() {
        return courseInstructor;
    }

    /**
     * Returns whether or not the course has an instructor
     *
     * @return true if the course has an instructor, false otherwise
     */
    public boolean hasInstructor() {
        return courseInstructor != null;
    }

    /**
     * Adds the {@code Student} object passed as parameter to the collection of {@code enrolledStudents}.
     *
     * @param student       the student to enrol
     * @return false <ul><li> if the course is full (each course has a maximum of 3 students) </li>
     *               <li> or if it has already started </li>
     *               <li> or if the student has already graduated from that course </li>
     *               <li> or if the student has not the prerequisites for the subject taught in the course </li> </ul>
     *         true  <ul><li> otherwise </li></ul>
     */
    public boolean enrolStudent(Student student) {
        if (getSize() >= 3 || getStatus() > 0 || student.hasCertificate(getSubject()) ||
                !student.getCertificates().containsAll(getSubject().getPrerequisites())) {
            return false;
        } else {
            enrolledStudents.add(student);
            student.addAttendedCourse(this);
            return true;
        }
    }

    /**
     * Returns an array containing all the students enrolled in the course.
     *
     * @return the Student[] array of students enrolled in the course
     */
    public Student[] getStudents() {
        return enrolledStudents.toArray(new Student[0]);
    }

    /**
     * Returns the array list of all the students enrolled in the course.
     *
     * @return the Student array list of students enrolled in the course
     */
    public ArrayList<Student> getStudentsArrayList() {
        return enrolledStudents;
    }

    /**
     * Returns the course size.
     *
     * @return the number of students enrolled in the course
     */
    public int getSize() {
        return enrolledStudents.size();
    }


    /**
     * Advances one day for the course.
     * It reduces by one the {@code daysUntilStarts} if the course has not started, or the {@code daysToRun} if it has
     * started. It also:
     * <ul><li> Cancels the course if it is starting without any instructor or students, releasing the instructor (if any)
     *          and the students (if any) from the course.</li>
     *     <li> If the course finishes, then issues the certificate for the course’s subject to all the students in
     *          the course and unassign the instructor from the course.</li></ul>
     */
    public void aDayPasses() {
        if (daysUntilStarts > 0) {
            daysUntilStarts--;

            /* Cancels the course if it is starting without any instructor or students, releasing the instructor (if any)
            and the students (if any) from the course. */
            if ((daysUntilStarts == 0) && (getInstructor() == null || getSize() == 0)) {
                courseCancelled = true;

                if (courseInstructor != null) {
                   courseInstructor.unassignCourse(this);
                }

                for (Student student : enrolledStudents) {
                    student.removeAttendedCourse(this);
                }
                enrolledStudents.clear();
            }
        } else if (daysUntilStarts == 0 && daysToRun > 0) {
            daysToRun--;

            /* If the course finishes, then issues the certificate for the course’s subject to all the students in
            the course and unassign the instructor from the course. */
            if (daysToRun == 0) {
                for (int i = 0; i < enrolledStudents.size(); i++) {
                    Student student = enrolledStudents.toArray(new Student[0])[i];
                    student.graduate(getSubject());
                    student.removeAttendedCourse(this);
                }
                enrolledStudents.clear();

                /* To avoid NullPointerException unassign the course from the instructor and the instructor from
                the course only if the course has one. */
                if (courseInstructor != null) {
                    courseInstructor.unassignCourse(this);
                    courseInstructor = null;
                }
            }
        }
    }

    /**
     * Returns a pretty-print string of the {@code Course}, containing all its details.
     *
     * @return a table-formatted string containing the course's subject, status, instructor and enrolled students
     */
    @Override
    public String toString() {
        String statusToPrint;                           // Stores the status of the course
        String courseDetails = "";                      // Stores the details of the course in a table-formatted string.

        if (isCancelled()) {
            statusToPrint = "Course cancelled!";
            courseDetails += String.format("%-30s %25s%n", getSubject().getDescription(), statusToPrint);
        } else if (getStatus() == 0) {
            statusToPrint = "Course finished!";
            courseDetails += String.format("%-30s %25s%n", getSubject().getDescription(), statusToPrint);
        } else {

            /* Depending whether the the course has started or not, sets the variable statusToPrint to contain the status
            of the course. Then adds the description and the status to the courseDetails table-formatted string. */
            if (getStatus() < 0) {
                statusToPrint = (-getStatus()) + " day(s) to start";
            } else {
                statusToPrint = getStatus() + " day(s) to the end";
            }
            courseDetails += String.format("%-30s %25s", getSubject().getDescription(), statusToPrint);

            // If the course has an Instructor, add it to the table-formatted string.
            if (!hasInstructor()) {
                courseDetails += String.format("%15s", "N/A");
            } else {
                courseDetails += String.format("%15s", getInstructor().getName());
            }

            // If present, adds in column enrolled students' names to the enrolled students column of courseDetails.
            if (enrolledStudents.size() == 0) {
                courseDetails += "\n\n";
            } else {
                StringBuilder courseDetailsBuilder = new StringBuilder(courseDetails);
                for (int i = 0; i < getSize(); i++) {
                    String studentName = getStudents()[i].getName();
                    if (i == 0 && getSize() == 1) {
                        courseDetailsBuilder.append(String.format("%26s%n%n", studentName));
                    } else if (i == 0) {
                        courseDetailsBuilder.append(String.format("%26s%n", studentName));
                    } else if (i == getSize() - 1) {
                        courseDetailsBuilder.append(String.format("%97s%n%n", studentName));
                    } else {
                        courseDetailsBuilder.append(String.format("%97s%n", studentName));
                    }
                }
                courseDetails = courseDetailsBuilder.toString();
            }
        }
        return courseDetails;
    }
}
