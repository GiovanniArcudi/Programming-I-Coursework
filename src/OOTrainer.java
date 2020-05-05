/**
 * This class defines an {@code OOTrainer} object.
 * It provides all the properties and methods necessary to manage an OOTrainer in the bigger context of the {@code School}.
 * Each OOTrainer, inherits its properties and methods from {@code Person} and {@code Teacher}.
 * Each OOTrainer wll be able to teach subjects that require level of {@code specialism} 3.
 *
 * @author Giovanni Arcudi
 * @version 1.0
 */
public class OOTrainer extends Teacher {

    /**
     * Creates a new {@code GUITrainer} object and initializes the {@code name}, {@code gender} and {@code age} variables.
     *
     * @param name                      the name of the OOTrainer
     * @param gender                    OOTrainer's gender: male (‘M’) or female (‘F’)
     * @param age                       how old the OOTrainer is, in years
     * @throws InvalidInputException    If gender is not M or F, or if age is not positive
     */
    public OOTrainer(String name, char gender, int age) {
        super(name, gender, age);
    }

    /**
     * Tells if the {@code OOTrainer} can teach the {@code Subject} passed as parameter or not.
     *
     * @param subject       the subject we want to check if can be taught by the OOTrainer
     * @return true if the OOTrainer can teach the input subject (Subject's specialism ID = 3), false otherwise
     */
    @Override
    public boolean canTeach(Subject subject) {
        return super.canTeach(subject) || subject.getSpecialism() == 3;
    }
}
