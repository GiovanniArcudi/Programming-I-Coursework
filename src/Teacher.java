/**
 * This class defines a {@code Teacher} object.
 * It provides all the properties and methods necessary to manage a teacher in the bigger context of the {@code School}.
 * Each teacher, inherits its properties and methods from {@code Person} and {@code Instructor}.
 * Each teacher wll be able to teach subjects that require level of {@code specialism} 1 and 2.
 *
 * @author Giovanni Arcudi
 * @version 1.0
 */
public class Teacher extends Instructor {

    /**
     * Creates a new {@code Teacher} object and initializes the {@code name}, {@code gender} and {@code age} variables.
     *
     * @param name                      the name of the teacher
     * @param gender                    teacher's gender: male (‘M’) or female (‘F’)
     * @param age                       how old the teacher is, in years
     * @throws InvalidInputException    If gender is not M or F, or if age is not positive
     */
    public Teacher(String name, char gender, int age) {
        super(name, gender, age);
    }

    /**
     * Tells if the teacher can teach the {@code Subject} passed as parameter or not.
     *
     * @param subject       the subject we want to check if can be taught by the teacher
     * @return true if the teacher can teach the input subject (Subject's specialism ID = 1 or 2), false otherwise
     */
    @Override
    public boolean canTeach(Subject subject) {
        return subject.getSpecialism() == 1 || subject.getSpecialism() == 2;
    }
}
