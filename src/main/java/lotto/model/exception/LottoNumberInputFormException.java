package lotto.model.exception;

public class LottoNumberInputFormException extends RuntimeException {
        public LottoNumberInputFormException() {
                super();
        }

        public LottoNumberInputFormException(String message) {
                super(message);
        }
}
