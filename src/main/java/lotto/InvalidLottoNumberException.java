package lotto;

public class InvalidLottoNumberException extends RuntimeException {
        public InvalidLottoNumberException() {
                super();
        }

        public InvalidLottoNumberException(String message) {
                super(message);
        }
}
