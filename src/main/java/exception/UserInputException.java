package exception;

public class UserInputException extends IllegalArgumentException {
    private final ErrorMessage errorMessage;

    private UserInputException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }

    public static UserInputException from(ErrorMessage errorMessage) {
        return new UserInputException(errorMessage);
    }
}
