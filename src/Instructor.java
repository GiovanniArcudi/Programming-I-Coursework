import java.util.ArrayList;

/**
 * This class defines an {@code Instructor} object.
 * It provides all the properties and methods necessary to manage an instructor in the bigger context of the {@code School}.
 * Each instructor, apart from the properties inherited from {@code Person}, has also an {@code assignedCourses} ArrayList
 * containing all the courses the instructor has been assigned and methods to: assign or unassign a Course, get the
 * assigned courses see if he or she can teach a specific {@code Subject} and and pretty-print all instructors's details.
 * Each instructor type subclass will have its own level of specialism, this will influence what subjects can teach.
 *
 * @author Giovanni Arcudi
 * @version 1.0
 */
public abstract class Instructor extends Person {
    private ArrayList<Course> assignedCourses;      // The course that is assigned to the instructor.

    /**
     * Creates a new {@code Instructor} object and initializes the {@code name}, {@code gender}, {@code age} and
     * {@code assignedCourses} variables.
     *
     * @param name                      the name of the instructor
     * @param gender                    instructor's gender: male (‘M’) or female (‘F’)
     * @param age                       how old the instructor is, in years
     * @throws InvalidInputException    If gender is not M or F, or if age is not positive
     */
    public Instructor(String name, char gender, int age) throws InvalidInputException {
        super(name, gender, age);
        this.assignedCourses = new ArrayList<>();
    }

    /**
     * Adds the input {@code Course} to the instructor's {@code assignedCourses} ArrayList.
     *
     * @param course       the course to add to the instructor's assignedCourses ArrayList
     */
    public void assignCourse(Course course) {
        assignedCourses.add(course);
    }

    /**
     * Removes the input {@code Course} from the instructor's {@code assignedCourses} ArrayList.
     *
     * @param course       the course to remove from the instructor's assignedCourses ArrayList
     */
    public void unassignCourse(Course course) {
        assignedCourses.remove(course);
    }

    /**
     * Instructor's {@code assignedCourses} ArrayList accessor method.
     *
     * @return the ArrayList containing all the courses assigned to the instructor
     */
    public ArrayList<Course> getAssignedCourses() {
        return assignedCourses;
    }

    /**
     * Tells if an instructor can teach a {@code Subject} or not.
     * This abstract method will be overridden by the Instructor's subclasses.
     *
     * @param subject       the subject we want to check if can be taught by the instructor
     * @return true if the instructor can teach the input subject, false otherwise
     */
    public abstract boolean canTeach(Subject subject);

    /**
     * Returns a pretty-print String of the Instructor.
     *
     * @return a table-formatted string containing the Instructor's name, gender, age, type and assigned courses.
     */
    @Override
    public String toString() {

        // Stores the details of the instructor in a table-formatted string, procedurally adding all of them.
        StringBuilder instructorDetails = new StringBuilder();
        instructorDetails.append(String.format("%-20s %5s %16s %27s", getName(), getGender(), getAge(),
                this.getClass().getSimpleName()));

        // Depending on whether or not the instructor is teaching a course, returns the correct information.
        if (getAssignedCourses().isEmpty()) {
            instructorDetails.append(String.format("%26s%n%n", "Free"));
        } else {
            instructorDetails.append(String.format("%21s", ""));

            for (Course course : getAssignedCourses()) {
                instructorDetails.append(course.getSubject().getDescription());
                instructorDetails.append("\n");
                instructorDetails.append(String.format("%92s", ""));
            }
            instructorDetails.append("\n");
        }
        return instructorDetails.toString();
    }
}
