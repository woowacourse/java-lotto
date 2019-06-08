package domain;

public class IllegalNumberOfManualIssueException extends IllegalArgumentException {
    IllegalNumberOfManualIssueException() {
        super("구매 금액을 초과해서 수동 구매할 수 없습니다.");
    }
}
