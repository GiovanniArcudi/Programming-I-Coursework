import java.util.ArrayList;

/**
 * This class defines the {@code School}, which is where all our Students are taught and our Instructors work.
 * The class provides all the properties and methods necessary to manage the {@code School}'s' complex system of
 * {@code Subjects}, {@code Courses}, {@code Instructors} and {@code Students}.
 * A {@code School} object must have a {@code name}, and can also be set to have a maximum number of courses that a
 * student can enrol in ({@code maxEnrollableCourses}) and a maximum number of courses that an Instructor can teach
 * ({@code maxAssignableCourses}). This two parameters, if not specified in the Configuration File will be 1 by default.
 *
 * @author Giovanni Arcudi
 * @version 1.0
 */
public class School {
    private String schoolName;                             // The name of the School.
    private ArrayList<Subject> schoolSubjects;             // The Subjects taught at the School.
    private ArrayList<Course> schoolCourses;               // The Courses of the School.
    private ArrayList<Student> schoolStudents;             // The Students enrolled to the School.
    private ArrayList<Instructor> schoolInstructors;       // The Instructors of the School.
    private int maxEnrollableCourses;                      // The maximum number of courses that a student can enrol in.
    private int maxAssignableCourses;                      // The maximum number of courses that an Instructor can teach.

    /**
     * Creates a {@code School} object and initializes the {@code schoolName}, {@code schoolSubjects},
     * {@code schoolCourses}, {@code schoolStudents}, {@code schoolInstructors}, {@code maxEnrollableCourses} and
     * {@code maxAssignableCourses} properties using the given parameters.
     *
     * @param schoolName        the name of the school
     */
    public School(String schoolName) {
        this.schoolName = schoolName;
        schoolSubjects = new ArrayList<>();
        schoolCourses = new ArrayList<>();
        schoolStudents = new ArrayList<>();
        schoolInstructors = new ArrayList<>();
        maxEnrollableCourses = 1;
        maxAssignableCourses = 1;
    }

    /**
     * School's {@code maxEnrollableCourses} setter method.
     *
     * @param maxEnrollableCourses     the maximum number of courses that a student can enrol in
     */
    public void setMaxEnrollableCourses(int maxEnrollableCourses) {
        this.maxEnrollableCourses = maxEnrollableCourses;
    }

    /**
     * School's {@code maxAssignableCourses} setter method.
     *
     * @param maxAssignableCourses     the maximum number of courses that an Instructor can teach
     */
    public void setMaxAssignableCourses(int maxAssignableCourses) {
        this.maxAssignableCourses = maxAssignableCourses;
    }


    // Student manager methods.

    /**
     * Adds the {@code Student} object passed as parameter to the {@code schoolStudents} ArrayList.
     *
     * @param student       the student to be added to the enrolledStudents ArrayList
     */
    public void add(Student student) {
        schoolStudents.add(student);
    }

    /**
     * Removes the {@code Student} object passed as parameter from the {@code schoolStudents} ArrayList.
     *
     * @param student       the student to be removed from the enrolledStudents ArrayList
     */
    public void remove(Student student) {
        schoolStudents.remove(student);
    }

    /**
     * School's {@code schoolStudents} ArrayList accessor method.
     *
     * @return the ArrayList containing all the students enrolled in the School
     */
    public ArrayList<Student> getStudents() {
        return schoolStudents;
    }

    /**
     * Returns an ArrayList of the students enrolled in at least one course.
     *
     * @return an ArrayList containing all the school students enrolled in at least one course
     */
    public ArrayList<Student> getActiveStudents() {
        ArrayList<Student> activeStudents = new ArrayList<>();

        // Iterates over each active course of the school and adds the corresponding students to the set.
        for (Course course : schoolCourses) {
            activeStudents.addAll(course.getStudentsArrayList());
        }
        return activeStudents;
    }


    // Subject manager methods.

    /**
     * Adds the {@code Subject} object passed as parameter to the {@code schoolSubjects} ArrayList.
     *
     * @param subject       the subject to be added to the schoolSubjects ArrayList
     */
    public void add(Subject subject) {
        schoolSubjects.add(subject);
    }

    /**
     * Removes the {@code Subject} object passed as parameter from the {@code schoolSubjects} ArrayList.
     *
     * @param subject       the subject to be removed from the schoolSubjects ArrayList
     */
    public void remove(Subject subject) {
        schoolSubjects.remove(subject);
    }

    /**
     * School's {@code schoolSubjects} ArrayList accessor method.
     *
     * @return the ArrayList containing all the subjects taught in the School
     */
    public ArrayList<Subject> getSubjects() {
        return schoolSubjects;
    }


    // Course manager methods

    /**
     * Adds the {@code Course} object passed as parameter to the {@code schoolCourses} ArrayList.
     *
     * @param course       the course to be added to the schoolCourses ArrayList
     */
    public void add(Course course) {
        schoolCourses.add(course);
    }

    /**
     * Removes the {@code Course} object passed as parameter from the {@code schoolCourses} ArrayList.
     *
     * @param course       the course to be removed from the schoolCourses ArrayList
     */
    public void remove(Course course) {
        schoolCourses.remove(course);
    }

    /**
     * School's {@code schoolCourses} ArrayList accessor method.
     *
     * @return the ArrayList containing all the courses taught in the School
     */
    public ArrayList<Course> getCourses() {
        return schoolCourses;
    }


    // Instructor manager methods

    /**
     * Adds the {@code Instructor} object passed as parameter to the {@code schoolInstructors} ArrayList.
     *
     * @param instructor       the instructor to be added to the schoolInstructors ArrayList
     */
    public void add(Instructor instructor) {
        schoolInstructors.add(instructor);
    }

    /**
     * Removes the {@code Instructor} object passed as parameter from the {@code schoolInstructors} ArrayList.
     *
     * @param instructor       the instructor to be removed from the schoolInstructors ArrayList
     */
    public void remove(Instructor instructor) {
        schoolInstructors.remove(instructor);
    }

    /**
     * School's {@code schoolInstructors} ArrayList accessor method.
     *
     * @return the ArrayList containing all the instructors of the School
     */
    public ArrayList<Instructor> getInstructors() {
        return schoolInstructors;
    }

    /**
     * Returns a pretty-print string of the {@code School}, containing all the details about each {@link Subject},
     * {@link Course}, {@link Instructor} and {@link Student} of the {@link School}.
     *
     * @return a table-formatted string containing information about school's subjects, courses, instructors, students and the
     *         relationships between them
     */
    @Override
    public String toString() {
        String welcome = "                                  Welcome to " + this.schoolName + "\n" +
                "Below you can find a detailed list of all the Subjects, Courses, Instructors and Students of our school.\n\n\n";
        String separator = "\n**********************************************************************************************************\n\n";

        return welcome + extractSubjectDetails() + separator
                       + extractCoursesDetails() + separator
                       + extractInstructorsDetails() + separator
                       + extractStudentsDetails() + separator;
    }

    /**
     * Iterates over the {@code schoolSubjects} ArrayList and returns each {@link Subject}'s {@code name}, {@code ID}, {@code specialism},
     * {@code duration} and {@code prerequisites}.
     *
     * @return a table-formatted string containing all subjects' names, IDs, specialisms and durations (in days)
     */
    public String extractSubjectDetails() {

        // Puts the subjects properties' titles in the first row of the table-formatted string.
        StringBuilder subjectsDetails = new StringBuilder(String.format("%-15s %9s %22s %20s %25s%n", "Name:",
                "Subject ID:", "Specialism:", "Duration:", "Prerequisites:"));

        // Iterates over the Subjects array list and prints out each subject's name, id, specialism and duration.
        for (Subject subject : schoolSubjects) {
            subjectsDetails.append(subject.toString());
        }
        return "                                               SUBJECTS\n\n" + subjectsDetails;
    }

    /**
     * Iterates over the {@code schoolCourses} ArrayList and returns each {@link Course}'s {@code subject}, {@code status},
     * {@code courseInstructor}, and {@code enrolledStudents}.
     *
     * @return a table-formatted string containing all courses' subjects, statuses, instructors and enrolled students
     */
    public String extractCoursesDetails() {

        // Puts the courses properties' titles in the first row of the table-formatted string.
        StringBuilder coursesDetails = new StringBuilder(String.format("%-30s %25s %15s %25s%n", "Subject:", "Status:",
                "Instructor:", "Enrolled Students:"));

        /* Iterates over the Courses array list and prints out each course's subject, status, instructor and enrolled
        students. */
        for (Course course : schoolCourses) {
            coursesDetails.append(course.toString());
        }
        return "                                                COURSES\n\n" + coursesDetails;
    }

    /**
     * Iterates over the {@code schoolInstructors} ArrayList and returns each {@link Instructor}'s {@code name},
     * {@code gender}, {@code age}, {@code type} and {@code assignedCourse}.
     *
     * @return a table-formatted string containing all instructors' names, gender, ages, types and assigned courses
     */
    public String extractInstructorsDetails() {

        // Puts the instructors properties' titles in the first row of the table-formatted string.
        StringBuilder instructorsDetails = new StringBuilder(String.format("%-20s %4s %15s %26s %26s%n", "Name:",
                "Gender:", "Age:", "Instructor Type:", "Assigned Course:"));

        /* Iterates over the Instructors array list and prints out each instructor's name, gender, age, type and assigned
        course. */
        for (Instructor instructor : schoolInstructors) {
            instructorsDetails.append(instructor.toString());
        }
        return "                                                INSTRUCTORS\n\n" + instructorsDetails;
    }

    /**
     * Iterates over the {@code schoolStudents} ArrayList and returns each {@link Student}'s {@code name},
     * {@code gender}, {@code age}, {@code certificates} and {@code enrolledCourses}.
     *
     * @return a table-formatted string containing all students' names, gender, ages, certificates and enrolled courses
     */
    public String extractStudentsDetails() {

        // Puts the students properties' titles in the first row of the table-formatted string.
        StringBuilder studentsDetails = new StringBuilder(String.format("%-20s %4s %15s %26s %26s%n", "Name:", "Gender:",
                "Age:", "Certificates ID:", "Enrolled Course:"));

        /* Iterates over the Students array list and prints out each students's name, gender, age, enrolled course and
        certificates. */
        for (Student student : schoolStudents) {
            studentsDetails.append(student.toString());
        }
        return "                                                STUDENTS\n\n" + studentsDetails;
    }

    /**
     * Simulates the events of a day at school, which are the following.
     * <ol><li> For any topic that does not have an open-for-registration course, creates a new course for that subject
     *          to start in 2 days. </li>
     *
     *     <li> Assigns instructors and students to courses:
     *          <ul><li>Looks at each course that requires an instructor, and goes through the instructors until
     *                  you finds one that is free (i.e., not already teaching other courses), and can teach the course.
     *                  Assigns the {@link Instructor} to the {@link Course}. </li>
     *             <li> Look at each student, if they are free, (i.e., not enrolled in any course) goes through
     *                  the courses until it finds one that the student can join (e.g., not already full and the student
     *                  has not got the certificate). </li></ul>
     *     <li> Let the student learn (calls aDayPasses() on each course).</li>
     *     <li> At the end of the day, removes any course that is cancelled or already finished.</li>/<ol>
     */
    public void aDayAtSchool() {
        coursesRefresh();                           // Creates new courses.
        assignInstructorToEachCourse();             // Assigns instructors to courses that need one.
        enrolFreeStudents();                        // Assigns students to empty courses.

        /* Lets the students learn (calls aDayPasses on each course) and removes any course that is cancelled or finished.
        It uses an Array in order to avoid ConcurrentModificationException. */
        Course[] courses = this.getCourses().toArray(new Course[0]);

        for (int i = 0; i < schoolCourses.size(); i++) {
            courses[i].aDayPasses();
            if (courses[i].isCancelled() || courses[i].getStatus() == 0) {
                schoolCourses.remove(courses[i]);
            }
        }
    }

    /**
     * For any {@link Subject} in {@code schoolSubjects} that does not have an open-for-registration course, creates a
     * new course that starts in 2 days.
     */
    public void coursesRefresh() {

        /* Iterates over each school's subject, and checks if all the courses for that subjects are full, storing the
        answer in the allCoursesFull variable. */
        for (Subject subject : getSubjects()) {
            boolean allCoursesFull = true;

            for (Course course : getCourses()) {
                if (subject == course.getSubject()) {
                    if (course.getSize() < 3) {
                        allCoursesFull = false;
                        break;
                    }
                }
            }

            /* If all the courses for that specific subject are full, it checks if there are any instructors free that
            can teach the subject. If so, it creates a new course with that instructor.*/
            if (allCoursesFull) {
                Course newCourse = new Course(subject, 2);
                add(newCourse);
            }
        }
    }

    /**
     * Iterates over each {@link Course} in {@code schoolCourses} and assigns an {@link Instructor} to the courses that
     * don't have one.
     */
    public void assignInstructorToEachCourse() {

        /* Looks at each course. If there is one that has no instructor, goes through the instructors until it finds one
        that is free and can teach the course. If so, it assigns this instructor to the course. */
        for (Course course : getCourses()) {
            if (!course.hasInstructor()) {
                for (Instructor instructor : getInstructors()) {

                    if (instructor.getAssignedCourses() != null &&
                            instructor.getAssignedCourses().size() < maxAssignableCourses &&
                            instructor.canTeach(course.getSubject())) {
                        course.setInstructor(instructor);
                        instructor.assignCourse(course);
                        break;
                    }
                }
            }
        }
    }

    /**
     * Iterates over each {@link Student} in {@code schoolStudents} and, if possible, enrolls them to a new {@link Course}
     */
    public void enrolFreeStudents() {

        /* Looks at each students. If no course contains their name, goes through all the courses until it finds one that
        the student can join. If so, and if the student has not reached the maximum number of courses that can be enrolled,
        in, enrols the student */
        for (Student student : getStudents()) {
            if(getNumberOfEnrolledCourses(student) < maxEnrollableCourses) {
                for (Course course : getCourses()) {
                    if (!course.getStudentsArrayList().contains(student) &&
                            !student.getCertificates().contains(course.getSubject().getID()) &&
                            getNumberOfEnrolledCourses(student) < maxEnrollableCourses &&
                            !student.getAttendedSubject().contains(course.getSubject().getID()) ) {
                        course.enrolStudent(student);
                    }
                }
            }
        }
    }

    /**
     * Returns the number of courses the {@link Student} passed as parameter is enrolled in.
     *
     * @param student       the students we want to know the courses that is enrolled in
     * @return the number of courses a students is enrolled in
     */
    public int getNumberOfEnrolledCourses(Student student) {
        int enrolledCoursesCounter = 0;
        for (Student stud : getActiveStudents()) {
            if (stud == student){
                enrolledCoursesCounter++;
            }
        }
        return enrolledCoursesCounter;
    }
}
