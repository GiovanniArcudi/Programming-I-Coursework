/**
 * This class defines a {@code GUITrainer} object.
 * It provides all the properties and methods necessary to manage a GUITrainer in the bigger context of the {@code School}.
 * Each GUITrainer, inherits its properties and methods from {@code Person} and {@code Teacher}.
 * Each GUITrainer wll be able to teach subjects that require level of {@code specialism} 4.
 *
 * @author Giovanni Arcudi
 * @version 1.0
 */
public class GUITrainer extends Teacher {

    /**
     * Creates a new {@code GUITrainer} object and initializes the {@code name}, {@code gender} and {@code age} variables.
     *
     * @param name                      the name of the GUITrainer
     * @param gender                    GUITrainer's gender: male (‘M’) or female (‘F’)
     * @param age                       how old the GUITrainer is, in years
     * @throws InvalidInputException    If gender is not M or F, or if age is not positive
     */
    public GUITrainer(String name, char gender, int age) {
        super(name, gender, age);
    }

    /**
     * Tells if the {@code GUITrainer} can teach the {@code Subject} passed as parameter or not.
     *
     * @param subject       the subject we want to check if can be taught by the GUITrainer
     * @return true if the GUITrainer can teach the input subject (Subject's specialism ID = 4), false otherwise
     */
    @Override
    public boolean canTeach(Subject subject) {
        return super.canTeach(subject) || subject.getSpecialism() == 4;
    }
}
