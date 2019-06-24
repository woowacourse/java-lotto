package lotto.controller.web.exception;

public class LottoCheckControllerException extends RuntimeException {
    public LottoCheckControllerException(String message) {
        super(message + " -> 이로 인한 LottoCheckController에서 오류 발생");
    }
}
