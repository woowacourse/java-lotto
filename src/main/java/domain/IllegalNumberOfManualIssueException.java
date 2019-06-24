package domain;

public class IllegalNumberOfManualIssueException extends IllegalArgumentException {
    IllegalNumberOfManualIssueException() {
        super("수동 구매 매수 오류");
    }
}
