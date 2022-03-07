package exception.lotto;

public class LottoNumWrongSizeException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "로또 번호는 6개로 이루어져야 합니다. : %d개";

    public LottoNumWrongSizeException(final int size) {
        super(String.format(ERROR_MESSAGE, size));
    }
}
