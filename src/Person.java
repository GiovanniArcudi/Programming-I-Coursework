import java.lang.String;

/**
 * This class defines a {@code Person} object, which in the context of the {@code School}, could be a {@code Student},
 * an {@code Instructor}, a {@code Teacher}, a {@code GUITrainer}, a {@code OOTrainer} or a {@code Demonstrator}.
 * A {@code Course} object contains all the properties getters and setters, that will be inherited by all its aforementioned
 * subclasses.
 *
 * @author Giovanni Arcudi
 * @version 1.0
 */
public class Person {
    private String name;        // The name of the person.
    private char gender;        // Person's gender: male (‘M’) or female (‘F’).
    private int age;            // How old the person, in years.

    /**
     * Creates a new {@code Person} object and initializes the name, gender and age variables.
     *
     * @param name                      the name of the person
     * @param gender                    person's gender: male (‘M’) or female (‘F’)
     * @param age                       how old the person is, in years
     * @throws InvalidInputException    If gender is not M or F, or if age is not positive
     */
    public Person(String name, char gender, int age) {
        if (gender != 'M' && gender != 'F') {
            throw new InvalidInputException("Gender must be M or F.");
        } else if (age < 0) {
            throw new InvalidInputException("Age must be a positive number.");
        } else {
            this.name = name;
            this.gender = gender;
            this.age = age;
        }
    }

    /**
     * Person's {@code name} accessor method.
     *
     * @return the name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Person's {@code gender} accessor method.
     *
     * @return person's gender: male (‘M’) or female (‘F’)
     */
    public char getGender() {
        return gender;
    }

    /**
     * Person's {@code age} setter method.
     *
     * @param age       how old the person, in years
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Person's {@code age} accessor method.
     *
     * @return how old the person, in years
     */
    public int getAge() {
        return age;
    }
}
