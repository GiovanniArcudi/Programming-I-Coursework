import java.util.Random;

/**
 * This class defines a {@code PersonGenerator}.
 * Using the {@link #createPerson(String)} method it creates a new {@link Person} object of the specified subclass, taking
 * a random name and gender from the {@code RANDOM_NAMES_AND_GENDERS} 20x2 matrix and random generated age. It also keeps
 * in consideration that the age limit for a {@link Student} is lower than the one of an {@link Instructor}.
 *
 * @author Giovanni Arcudi
 * @version 1.0
 */
public class PersonGenerator {

    /** A 2x20 matrix containing the 20 most common english male and female names and the relative genders */
    private static final String[][] RANDOM_NAMES_AND_GENDERS = {{"James", "M"}, {"Mary", "F"}, {"John", "M"}, {"Patricia", "F"},
            {"Robert", "M"}, {"Jennifer", "F"}, {"Michael", "M"}, {"Linda", "F"}, {"William", "M"}, {"Elizabeth", "F"},
            {"David", "M"}, {"Barbara", "F"}, {"Richard", "M"}, {"Susan", "F"}, {"Joseph", "M"}, {"Jessica", "F"},
            {"Thomas", "M"}, {"Sarah", "F"}, {"Charles", "M"}, {"Karen", "F"}};

    /**
     * Creates a {@link Person} object of the specified type, with random {@code name}, {@code gender}, and {@code age}.
     *
     * @param personType        The string containing the type of Person object to create (Student, Teacher, Demonstrator,
     *                          OOTrainer, GUITrainer)
     * @return a new Person object of the specified type with random name, gender and age
     */
    public static Person createPerson(String personType) {

        // Generates a random number to select one of the 20 name-gender couple from the nameAndGendersMatrix.
        int randomNameAndGender = new Random().ints(0, 20).findFirst().getAsInt();
        int randomAge;

        // If the Person type is a student, the random age value is in the 18-26 range. For Instructors it is 30-70.
        if (personType.equalsIgnoreCase("Student")) {
            randomAge = new Random().ints(18, 26).findFirst().getAsInt();
        } else {
            randomAge = new Random().ints(30, 70).findFirst().getAsInt();
        }

        // Fetches the corresponding random name (and relative gender) from the namesAndGenders matrix.
        String randomName = RANDOM_NAMES_AND_GENDERS[randomNameAndGender][0];
        char randomGender = RANDOM_NAMES_AND_GENDERS[randomNameAndGender][1].charAt(0);

        // Creates the correct type of person object, as specified in the method parameter.
        if (personType.equalsIgnoreCase("Student")) {
            return new Student(randomName, randomGender, randomAge);
        } else if (personType.equalsIgnoreCase("Teacher")) {
            return new Teacher(randomName, randomGender, randomAge);
        } else if (personType.equalsIgnoreCase("Demonstrator")) {
            return new Demonstrator(randomName, randomGender, randomAge);
        } else if (personType.equalsIgnoreCase("OOTrainer")) {
            return new OOTrainer(randomName, randomGender, randomAge);
        } else if (personType.equalsIgnoreCase("GUITrainer")) {
            return new GUITrainer(randomName, randomGender, randomAge);
        } else {
            System.err.println("Please select the type of person to create: Student, Teacher, Demonstrator, OOTrainer, GUITrainer");
            return null;
        }
    }
}
