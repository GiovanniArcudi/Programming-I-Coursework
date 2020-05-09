import java.util.HashSet;
import java.util.Random;

/**
 * This class defines the {@link School}'s {@link Administrator}, which, in our simulation, is responsible for running
 * the School.
 * The class provides all the properties and methods necessary to run the School and simulates the probabilities
 * that a certain number of new Students and new Instructors will join or leave.
 * The probability is simulated in {@link #probabilityDeterminer(double)}, while the new {@link Person} objects that may
 * join the School are generated using {@link PersonGenerator}.
 *
 * @author Giovanni Arcudi
 * @version 1.0
 */
public class Administrator {
    private School school;                          // The school the administrator runs.
    private PersonGenerator personGenerator;        // The tool used to create new random people.

    /**
     * Creates a new {@link Administrator} object and initializes the {@code school} and {@code personGenerator} variables.
     *
     * @param school            the school the administrator runs
     */
    public Administrator(School school) {
        this.school = school;
        personGenerator = new PersonGenerator();
    }

    /**
     * Administrator's {@code school} accessor method.
     *
     * @return the school the administrator runs
     */
    public School getSchool() {
        return school;
    }

    /**
     * Runs the school for one day: <ol>
     *      <li> Admits a random number of {@code Students} to the {@code school}.</li>
     *      <li> Enrols a new {@code Instructor}.</li>
     *      <li> Runs the {@code school}.</li>
     *      <li> Removes {@code Instructors} who might leave the {@code school}.</li>
     *      <li> Removes {@code Students} who might leave the {@code school}.</li> </ol>
     */
    public void run() {
        enrolNewStudents();                // Admits a random number of students to the school.
        enrolNewInstructor();              // Enrols a new Instructor.
        school.aDayAtSchool();             // Runs the school.
        instructorLeaving();               // Removes Instructors who might leave the school.
        studentsLeaving();                 // Removes Students who might leave the school.
    }

    /**
     * Runs the {@link School} for the {@code daysToRun} passed as parameter, printing out the status at the end of each
     * one. This method overloads {@link #run()}.
     *
     * @param daysToRun         the number of days to run the school simulation for
     */
    public void run(int daysToRun) {
        for (int i = 1; i <= daysToRun; i++) {
            run();
            System.out.println("Day: " + i);
            System.out.println(school.toString());
        }
    }

    /**
     * Simulates the possibility that up to 2 new students join the school.
     */
    public void enrolNewStudents() {

        // Generates a random number between 0 and 3 to decide how many students will be admitted, then adds them.
        int admittedStudents = new Random().nextInt(3);

        for (int i = admittedStudents; i > 0; i--) {
            school.add((Student) PersonGenerator.createPerson("Student"));
        }
    }

    /**
     * Simulates the possibility that a new instructor joins the school.
     */
    public void enrolNewInstructor() {

        // Simulates a 20% chance that a new Teacher will join the school.
        if (probabilityDeterminer(20)) {
            school.add((Teacher) PersonGenerator.createPerson("Teacher"));
        }

        // Simulates a 10% chance that a new Demonstrator will join the school.
        if (probabilityDeterminer(10)) {
            school.add((Demonstrator) PersonGenerator.createPerson("Demonstrator"));
        }

        // Simulates a 5% chance that a new OOTrainer will join the school.
        if (probabilityDeterminer(5)) {
            school.add((OOTrainer) PersonGenerator.createPerson("OOTrainer"));
        }

        // Simulates a 5% chance that a new GUITrainer will join the school.
        if (probabilityDeterminer(5)) {
            school.add((GUITrainer) PersonGenerator.createPerson("GUITrainer"));
        }
    }

    /**
     * Iterates over each school's {@code Instructor} and, if free, simulates the 20% possibility that they have to leave.
     * If that is the case, removes the instructor from the school.
     */
    public void instructorLeaving() {
        Instructor[] instructors = school.getInstructors().toArray(new Instructor[0]);
        for (Instructor instructor : instructors) {
            if (instructor.getAssignedCourses() == null) {
                if (probabilityDeterminer(20)) {
                    school.remove(instructor);
                }
            }
        }
    }

    /**
     * Iterating over each {@code School}'s {@code Student}, removes graduated students and simulates the 5% possibility
     * that a student who is not enrolled to any course leaves the school.
     */
    public void studentsLeaving() {

        // Creates an Hash Set containing all school Subjects IDs (useful for next operation).
        HashSet<Integer> allSubjectsCertificates = new HashSet<>();
        for (Subject subject : school.getSubjects()) {
            allSubjectsCertificates.add(subject.getID());
        }

        /* For each student in the school, if has obtained the certificates for all subjects, will leave the school.
        Otherwise, if the student does not enrol in any course, he or she has 5% chance of leaving the school.
        It uses an array to avoid ConcurrentModificationException.*/
        Student[] students = school.getStudents().toArray(new Student[0]);

        for (Student student : students) {
            if (student.getCertificates().containsAll(allSubjectsCertificates)) {
                school.remove(student);
            }

            if (school.getActiveStudents() != null) {
                if (!school.getActiveStudents().contains(student)) {
                    if (probabilityDeterminer(5)) {
                        school.remove(student);
                    }
                }
            }
        }
    }

    /**
     * Simulates an event, given a certain {@code probabilityPercentage} that it happens.
     *
     * @param probabilityPercentage         the probability that the event happens, in percentage (0 - 100)
     * @return true if the event happens, false otherwise
     */
    public static boolean probabilityDeterminer(double probabilityPercentage) {
        if (probabilityPercentage > 100 || probabilityPercentage < 0) {
            System.err.println("Please set a percentage value between 0 and 100.");
            return false;
        } else {
            return (Math.random() <= (probabilityPercentage / 100));
        }
    }

    /**
     * Administrator's class main method.
     * Takes the name of the configuration file and runs the simulation for the specified number of days.
     */
    public static void main(String[] args) {
        SimulationUtility simulationUtility = new SimulationUtility();
        simulationUtility.setup(args[0]);
        simulationUtility.getAdministrator().run(Integer.parseInt(args[1]));
    }
}
