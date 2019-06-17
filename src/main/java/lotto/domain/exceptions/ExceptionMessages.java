package lotto.domain.exceptions;

public enum ExceptionMessages {
    TICKET("로또 티켓을 발급할 수 없습니다."),
    NUMBER("유효한 로또 번호가 아닙니다.");

    private final String message;

    private ExceptionMessages(String message) {
        this.message = message;
    }

    public String message() {
        return this.message;
    }
}
