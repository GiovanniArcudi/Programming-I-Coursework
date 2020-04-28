import java.util.ArrayList;

/**
 * This class defines a {@code Subject} object.
 * It provides all the properties and methods necessary to manage a subject in the bigger context of the {@code School}.
 *
 * @author Giovanni Arcudi
 * @version 1.0
 */
public class Subject {
    private int id;                                 // The unique ID of the subject.
    private int specialism;                         // The specialism ID of the subject.
    private int duration;                           // The number of days required for any course covering the subject.
    private String description;                     // The string description of the subject.
    private ArrayList<Integer> prerequisites;       // The subjectsIDs of the subjects needed to study this subject.

    /**
     * Creates a new {@code Subject} object and initializes its {@code name}, {@code ID}, {@code specialism},
     * {@code duration} and {@code prerequisites} variables.
     *
     * @param id                        the unique ID of the subject
     * @param specialism                the specialism ID of the subject
     * @param duration                  the duration (number of days) required for any course covering the subject
     * @throws InvalidInputException    If id, specialism and duration are not all positive integers
     */
    public Subject(int id, int specialism, int duration) {
        if (id < 0 || specialism < 0 || duration < 0) {
            throw new InvalidInputException("Id, specialism and duration must be positive numbers.");
        } else {
            this.id = id;
            this.specialism = specialism;
            this.duration = duration;
            prerequisites = new ArrayList<>();
        }
    }

    /**
     * Subject's {@code id} accessor method.
     *
     * @return the unique ID of the subject
     */
    public int getID() {
        return id;
    }

    /**
     * Subject's {@code specialism} accessor method.
     *
     * @return the specialism ID of the subject
     */
    public int getSpecialism() {
        return specialism;
    }

    /**
     * Subject's {@code duration} accessor method.
     *
     * @return the duration (number of days) required for any course covering the subject
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Subject's {@code description} accessor method.
     *
     * @return the string description of the subject
     */
    public String getDescription() {
        return description;
    }

    /**
     * Subject's {@code description} setter method.
     *
     * @param description       the string description of the subject
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Subject's {@code prerequisites} accessor method.
     *
     * @return the subjectIDs of the subjects you have to take before studying this subject
     */
    public ArrayList<Integer> getPrerequisites() {
        return prerequisites;
    }

    /**
     * Adds a prerequisite to the Subject's {@code prerequisites} ArrayList.
     *
     * @param prerequisite     the subjectIDs of the subjects you have to take before studying this one
     */
    public void addPrerequisites(int prerequisite) {
        this.prerequisites.add(prerequisite);
    }

    /**
     * Returns a pretty-print string of the {@code Subject}, containing all its details.
     *
     * @return a table-formatted string containing the subject's name, ID, specialism, duration (in days) and prerequisites
     */
    @Override
    public String toString() {

        // Stores the details of the subject in a table-formatted string, procedurally adding all of them.
        StringBuilder subjectDetails = new StringBuilder();
        subjectDetails.append(String.format("%-15s %10s %22s %20s", getDescription(), getID(), getSpecialism(),
                getDuration() + " days"));

        /* If prerequisites are needed to take this subject, it adds them to the table-formatted string, otherwise it
        adds "No Prerequisites" to the string. */
        if (prerequisites.size() == 0) {
            subjectDetails.append(String.format("%26s%n", "No Prerequisites"));
        } else {
            int i = 0;
            for (int subjectID : getPrerequisites()) {
                if (getPrerequisites().size() == 1) {
                    subjectDetails.append(String.format("%23s%n", subjectID));
                } else {
                    if (i == 0) {
                        subjectDetails.append(String.format("%23s", subjectID));
                        i++;
                    } else if (i == getPrerequisites().size() - 1) {
                        subjectDetails.append(", ").append(subjectID).append("\n");
                    } else {
                        subjectDetails.append(", ").append(subjectID);
                        i++;
                    }
                }
            }
        }
        return subjectDetails.toString();
    }
}
