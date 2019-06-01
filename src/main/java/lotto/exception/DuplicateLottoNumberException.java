package lotto.exception;

public class DuplicateLottoNumberException extends RuntimeException {
    public DuplicateLottoNumberException() {
        super("중복된 번호로 로또를 생성할 수 없습니다.");
    }
}
