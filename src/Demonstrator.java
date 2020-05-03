/**
 * This class defines a {@code Demonstrator} object.
 * It provides all the properties and methods necessary to manage a Demonstrator in the bigger context of the {@code School}.
 * Each Demonstrator, inherits its properties and methods from {@code Person} and {@code Instructor}.
 * Each Demonstrator wll be able to teach subjects that require level of {@code specialism} 2.
 *
 * @author Giovanni Arcudi
 * @version 1.0
 */
public class Demonstrator extends Instructor {

    /**
     * Creates a new {@code Demonstrator} object and initializes the {@code name}, {@code gender} and {@code age} variables.
     *
     * @param name                      the name of the Demonstrator
     * @param gender                    Demonstrator's gender: male (‘M’) or female (‘F’)
     * @param age                       how old the Demonstrator is, in years
     * @throws InvalidInputException    If gender is not M or F, or if age is not positive
     */
    public Demonstrator(String name, char gender, int age) {
        super(name, gender, age);
    }

    /**
     * Tells if the {@code Demonstrator} can teach the {@code Subject} passed as parameter or not.
     *
     * @param subject       the subject we want to check if can be taught by the Demonstrator
     * @return true if the Demonstrator can teach the input subject (Subject's specialism ID = 2), false otherwise
     */
    @Override
    public boolean canTeach(Subject subject) {
        return subject.getSpecialism() == 2;
    }
}
