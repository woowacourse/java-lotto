package lotto.exception;

public class NotInScopeException extends IllegalArgumentException {
    public NotInScopeException(String notInScopeNumbersErrorMsg) {
        super(notInScopeNumbersErrorMsg);
    }
}
