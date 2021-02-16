package domain;

public class InvalidNumberRangeException extends RuntimeException {
    public InvalidNumberRangeException() {
        super("로또 번호는 1~45 사이로 입력해주세요.");
    }
}
