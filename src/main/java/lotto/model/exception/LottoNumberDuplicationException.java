package lotto.model.exception;

public class LottoNumberDuplicationException extends RuntimeException {
        public LottoNumberDuplicationException() {
                super();
        }

        public LottoNumberDuplicationException(String message) {
                super(message);
        }
}
