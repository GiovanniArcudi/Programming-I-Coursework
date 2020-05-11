/**
 * Personalised {@code Exception} thrown when invalid inputs are provided.
 */
public class InvalidInputException extends RuntimeException {

    /**
     * Creates an {@link InvalidInputException} and prints out the {@code errorMessage} passed as parameter.
     *
     * @param errorMessage      the error message to print out
     */
    public InvalidInputException(String errorMessage) {
        System.err.println(errorMessage);
    }
}
