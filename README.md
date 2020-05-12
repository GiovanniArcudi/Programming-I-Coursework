# Programming-I-Coursework

ECS Java Training School
Author: Giovanni Arcudi (ga1g19@soton.ac.uk)

0.1  - INFORMATION FOR MARKERS:

    Completed parts of the coursework: 
    Parts 1-6

    Extensions to the coursework: 
    1) JavaDoc documentation (which can be found in the doc subfolder)
    2) Implemented the prerequisites for subjects and ensures that students can only enrol on a course if they have all the prerequisites for that course.
    3) Allowed Instructors to be able to teach more than one course in a day (up to a certain limit) and students to enrol in more than on course at a time (up to a certain limit). 
    4) Altre cose che ho fatto...

    NOTE: Extension 3 structurally interferes with the basic specification of the Coursework.
        Specifically, in order to store the courses assigned to an Instructor, being them more than one now, I am using an ArrayList of Courses rather than a single Course variable.
        This implies that the accessor method for an Instructor's assigned courses will now return an ArrayList<Course> rather than a Course object, therefore test TestPart3 cannot be passed unless modified.



0.2  - COMMAND LINE USAGE:

    java Administrator [ConfigurationFileNme.txt] [Number Of Days]

    To run the simulation, there must be provided a configuration file in the format specified in the documentation and a certain number of days to run the simulation for. 
    In the configuration file it is possible to specify the maximum number of courses that a Student can enrol in and the maximum number of courses that an Instructor can teach,
    but when it is not specified the default value of one Course is used.
    Similarly, if prerequisites for a Subject are not specified, the subject will be automaticaly sat to not have any prerequisites.

    Example way of invoking the program:
    java Administrator Configuration.txt 100



0.3 -  CONFIGURATION FILE FORMAT:

    The simulator uses the format of the configuration file provided in the coursework specification.
    However for the purposes of the extensions the format has been enhanced. 
    
    The format consists of CLASS_NAME and PROPERTIES.


    0.3.1 VALID CLASS_NAMES EXAMPLES:

        Example 1: (describes a school having name = "University of Southampton", maxEnrollableCourses = "2", maxAssignableCourses = "2"):
        school:University of Southamptonn,2,2

        Example 2: (describes a Subject having descriprtion = "Programming I", subjectID = "1", specialismID = "1", duration = "5" days, prerequisites = the course whose subjectID is "2"):
        subject:Programming I, 1, 1, 5, 2

        Example 3: (describes a Student having name = "Peter", gender = "M", age = "20"):
        student:Peter,M,60

        Example 4: (describes a Teacher having name = "Yvonne", gender = "F", age = "55"):
        Teacher:Yvonne,F,55


    0.3.2 PROPERTIES FORMAT:

        PROPERTIES consists of a certain number of fields, separated by commas (no extra spaces), which describe every object property. 
        The reader fetches all the lines and selects the data needed to create an Object of the specified class. 

        For different classes the properties format looks as following:
         - For School objects: [SchoolName, maxEnrollableCourses (optional), maxAssignableCourses (optional)].
         - For Subject objects: [SubjectName, subjectID, specialismID, duration, prerequisite-prerequisite-prerequisite... (optionals)].
         - For Person objects: [SubclassNameAsString (one of: Student, Teacher, Demonstrator, OOTrainer, GUITrainer), gender('M' or 'F'), age].

        Example PROPERTIES:
        school:UniversityOfSouthampton,2,2
        subject:Lab 1,2,2,2,1-3
        student:Annabelle,F,31
        Teacher:Yvonne,F,55



1.0 - EXTENSIONS

1.1 - SUBJECTS PREREQUISITE

    Inspired by coursework specification I have decided to implement possible prerequisites for each Subject object.
    This means that the user is able, via the configuration file, to decide if a students has to obtain the certificate for other subjects (specifing which of them by subjectID)
    to enroll in a course about a specific subject.

    1.1.1 - Modify the way the Configuration File is read by SimulationUtility
        To implement this idea I firstly have added to the SimulationUtility's subjectCreator(String properties) method the functionality of reading properties strings with one extra property.
        To do so, I had to take into account the case for which properties.split(",") is equal to 5 and store the extra property (prerequisites) in a new String.
        Of course, there is no limit to the number of possible prerequisites for a Subject so, I decided that, if more than more presequisite is to be added it should be used "-" to separate the subjectIDs.
        In this way, splitting the prerequisites String at every("-"), I easily obtained the prerequisite for that subject, singularily.

    1.1.2 - Add Prerequisites property to Subject's class
        In oder to keep track of a Subject's prerequisites, I had to introduce a new property to the Subject's class: prerequisites,
        an ArrayList of Integers, specifically containing all the subjectsIDs of the subjects that a student is required to take before studing this one.
        In this ArrayList I added, one at a time, the subject prerequisite read in SimulationUtility's subjectCreator(String properties) method.

    1.1.2 - Modify the critarion for which students are enrolled in a course
        Of course, once the prerequisites are stored they have to be checked every time a student is about to be enrolled in a new course. In my code, this is done in Course's enrolStudent(Student student) method.

    1.1.3 - Update the toString() method in School and Subject
        To finish, I updated the toString() method in School and Subject, taking into account that the Subjects now have a new property that has to be printed out.



1.2 - MULTIPLE COURSES HANDLING FOR INSTRUCTORS AND STUDENTS:

    Because I enjoyed working with the input handling from the configuration file, I decided to also implement the second of the proposed Extensions.
    Instructors are now able to teach more than one course in a day (up to a certain limit) and the students can enrol in more than on course at a time (up to a certain limit). 
    
     1.2.1 - Modify the way the Configuration File is read by SimulationUtility
        To implement this idea I firstly have added to the SimulationUtility's schoolCreator(String properties) the functionality of reading properties strings with one or two extra properties.
        To do so, I had to take into account the case for which properties.split(",") is greater than 1 and store the extra properties (maxAssignableCourses and maxEnrollableCourses) in new Strings.
        If maxAssignableCourses and maxEnrollableCourses are not specified they will by default be set to 1.

    1.2.2 - Add Prerequisites property to SChool's class
        In oder to keep track of the maxAssignableCourses and maxEnrollableCourses, I had to introduce 2 new properties to the School's class: int maxAssignableCourses and int axEnrollableCourses.

    1.2.3 - Modify the critarion for which students are enrolled in a course and instructors are assigned to a course
        Of course, once the prerequisites are stored they have to be checked every time a student is about to be enrolled in a new course and an instructor assigned to a course.
        In my code, this is done in School's assignInstructorToEachCourse() method and in School's enrolFreeStudents() method.



1.3 - PERSON GENERATOR:
    
    To have a more realistic simulation I decided to implement a PersonGenerator.
    Using the createPerson(String) method it creates a new Person object of the specified subclass, taking a random name and gender from the RANDOM_NAMES_AND_GENDERS 20x2 matrix and random generated age.
    It also keeps in consideration that the age limit for a Student is lower than the one of an Instructor.
    Being all self contained in the PersonGenerator class, I suggest to read the more detailed methods description from there.
 


1.4 - SCHEDULING UPDATES:
    
    To improve the general performance of the code I added a method to the School class: getActiveStudents().
    It simply returns an ArrayList of the students enrolled in at least one course. 
    Despite being a very basic concept, checking if a student is enrolled to ad least one course has proven to be repeatedly useful throughout the project development, to avoid iterating through all of them many times.  



@author Giovanni Arcudi



