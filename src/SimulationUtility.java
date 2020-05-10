import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class defines a {@code SimulationUtility} object which is the tool used by the {@link Administrator}'s main
 * method to read the configuration file and run the School simulation accordingly.
 * It provides all the methods necessary to setup and read the Configuration file ({@link #setup(String)} &
 * {@link #getLine()}; and also the methods needed to create appropriate objects as specified in the Configuration file.
 *
 * @author Giovanni Arcudi
 * @version 1.0
 */
public class SimulationUtility {
    private Administrator administrator;        // The administrator that runs the school.
    private BufferedReader reader;              // The buffered reader that will iterate over the configuration file.

    /**
     * SimulationUtility's {@link Administrator} accessor method.
     *
     * @return      the administrator that runs the school
     */
    public Administrator getAdministrator() {
        return administrator;
    }

    /**
     * Initializes the {@link BufferedReader} and reads the configuration file passed as parameter one line at a time.
     * For each line it identifies the class, creates a new object of that class, sets the appropriate parameters and
     * adds it to the School.
     *
     * @param configurationFileName         the configuration file name (with .txt)
     */
    public void setup(String configurationFileName) {
        // Stores the line just read from the configuration file.
        String line = null;

        /* Tries to initialize the buffered reader and read the first line. Eventually, catches the FileNotFoundException
        thrown by FileReader(). */
        try {
            reader = new BufferedReader(new FileReader(configurationFileName));
            line = getLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Configuration file not found! Remember to include .txt.\n");
        }

        /* Iterates over each line of the configuration file and, while they are non-empty and have the
        "[Class]:[Property, property, ...]" structure, creates an object with the specified properties. */
        while (line != null) {

            // If the line in the configuration file is not [Class]:[Properties]-like, throws an InvalidInputException.
            if (line.split(":").length != 2) {
                throw new InvalidInputException("Please check your configuration file. " +
                        "Each line should be structured like this: [Class]:[property,property,...]\n");
            } else {
                String[] completeLine = line.split(":");
                String className = completeLine[0];
                String properties = completeLine[1];
                objectCreator(className, properties);
                line = getLine();
            }
        }
    }

    /**
     * Creates objects of the type, and with the {@code properties}, specified in the configuration file.
     *
     * @param className         the type of class to create
     * @param properties        the properties of the object to pass as parameters of the class constructor
     */
    public void objectCreator (String className, String properties) {
        if (className.equalsIgnoreCase("School")) {
            schoolCreator(properties);
        } else if (className.equalsIgnoreCase("Subject")) {
            subjectCreator(properties);
        } else {
            personCreator(className, properties);
        }
    }

    /**
     * Creates a {@link School} object with the specified {@code name}, {@code maxEnrollableCourses} and
     * {@code maxAssignableCourses}.
     *
     * @param properties  the name, maxEnrollableCourses and maxAssignableCourses specified in the configuration file
     */
    public void schoolCreator(String properties) {

        /* If the properties provided are not in the form "[name],[maxEnrollableCourses],[maxAssignableCourses]" or
        "[name],[one of the two MAX values]" or just "[name]" it throws an InvalidInputException.
         Otherwise, it creates the Subject object and adds it to the school. */
        if (properties.split(",").length < 1 || properties.split(",").length > 3) {
            throw new InvalidInputException("Please check your configuration file. " +
                    "Each school must have a name, a maximum number of courses a student can enroll in (optional) and" +
                    "a maximum number of courses that can be assigned to an instructor (optional)\n");
        } else {

            /* Converts the properties string into 1 (or 2 or 3 if there are number of courses conditions), one for each
            property. */
            String[] schoolProperties = properties.split(",");
            String name = schoolProperties[0];
            School newSchool = new School(name);

            // If there are maxEnrollableCourses or maxAssignableCourses assigns them in the school class variables.
            if (schoolProperties.length == 2) {
                int maxEnrollableCourses = Integer.parseInt(schoolProperties[1]);
                newSchool.setMaxEnrollableCourses(maxEnrollableCourses);
            } else if (schoolProperties.length == 3) {
                int maxEnrollableCourses = Integer.parseInt(schoolProperties[1]);
                newSchool.setMaxEnrollableCourses(maxEnrollableCourses);
                int maxAssignableCourses = Integer.parseInt(schoolProperties[2]);
                newSchool.setMaxAssignableCourses(maxAssignableCourses);
            }
            administrator = new Administrator(newSchool);
        }
    }

    /**
     * Creates a {@link Subject} object with the specified {@code description}, {@code id}, {@code specialism} and
     * {@code duration}. Then adds it to the {@link School}.
     *
     * @param properties        the description, id, specialism and duration values specified in the configuration file
     */
    public void subjectCreator(String properties) {

        /* If the properties provided are not in the form "[description],[id],[specialism],[duration]" or
        "[description],[id],[specialism],[duration],[prerequisite1 - prerequisite2 -...]" it throws an InvalidInputException.
         Otherwise, it creates the Subject object and adds it to the school. */
        if (properties.split(",").length < 4 || properties.split(",").length > 5) {
            throw new InvalidInputException("Please check your configuration file. " +
                    "Each subject requires a description, an id, a specialism and a duration to be specified; and a series" +
                    "of prerequisites that can also be omitted.\n");
        } else {

            // Converts the properties string into 4 (or 5 if there are prerequisites), one for each property.
            String[] subjectProperties = properties.split(",");
            String description = subjectProperties[0];
            int id = Integer.parseInt(subjectProperties[1]);
            int specialism = Integer.parseInt(subjectProperties[2]);
            int duration = Integer.parseInt(subjectProperties[3]);

            // Creates a new Subject object with the specified properties.
            Subject newSubject = new Subject(id, specialism, duration);
            newSubject.setDescription(description);

            // If there are prerequisite for the subjects adds them to the subject's prerequisites array list.
            if (subjectProperties.length == 5) {
                String prerequisitesList = subjectProperties[4];
                String[] prerequisitesIDs = prerequisitesList.split("-");
                for (String prerequisitesID : prerequisitesIDs) {
                    newSubject.addPrerequisites(Integer.parseInt(prerequisitesID));
                }
            }
            administrator.getSchool().add(newSubject);
        }
    }

    /**
     * Creates a {@link Person} object with the specified {@code name}, {@code gender} and {@code age}. Then adds it to
     * the {@link School}.
     *
     * @param className              the type of Person to create (Student, Teacher, Demonstrator, OOTrainer, GUITrainer)
     * @param properties             the name, gender and age values specified in the configuration file
     * @throws InvalidInputException If the part of the line containing properties is not well formatted or incomplete
     */
    public void personCreator(String className, String properties) {

        /* If the properties provided are not in the form "[name],[gender],[age]" it throws an InvalidInputException.
        Otherwise, it creates the Person object of the specified type and adds it to the school. */
        if (properties.split(",").length != 3) {
            throw new InvalidInputException("Please check your configuration file. " +
                    "Each person requires a name, a gender and an age to be specified.\n");
        } else {

            // Converts the properties string into 3 different strings, one for each property.
            String[] personProperties = properties.split(",");
            String name = personProperties[0];
            char gender = personProperties[1].charAt(0);
            int age = Integer.parseInt(personProperties[2]);

            // Creates a new Person object of the specified type with the specified properties and adds it to the school.
            if (className.equalsIgnoreCase("Student")) {
                Student newStudent = new Student(name, gender, age);
                administrator.getSchool().add(newStudent);
            } else if (className.equalsIgnoreCase("Teacher")) {
                Teacher newTeacher = new Teacher(name, gender, age);
                administrator.getSchool().add(newTeacher);
            } else if (className.equalsIgnoreCase("Demonstrator")) {
                Demonstrator newDemonstrator = new Demonstrator(name, gender, age);
                administrator.getSchool().add(newDemonstrator);
            } else if (className.equalsIgnoreCase("OOTrainer")) {
                OOTrainer newOOTrainer = new OOTrainer(name, gender, age);
                administrator.getSchool().add(newOOTrainer);
            } else if (className.equalsIgnoreCase("GUITrainer")) {
                GUITrainer newGUITrainer = new GUITrainer(name, gender, age);
                administrator.getSchool().add(newGUITrainer);
            }
        }
    }

    /**
     * Reads the next line of the contents of the Configuration File and returns it.
     *
     * @return the next line of the contents of the file
     */
    public String getLine() {
        String line = null;     // Stores the line just read.

        // Reads the next line and returns it. If an I/O error occurs it catches the IOException.
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Unable to read next line!\n");
        }
        return line;
    }
}
