package lotto.exception;

public class InvalidManualLottoAmountException extends RuntimeException {
    public InvalidManualLottoAmountException() {
        super("수동 로또 구매량은 전체 로또 구매량을 넘을 수 없습니다.");
    }
}
