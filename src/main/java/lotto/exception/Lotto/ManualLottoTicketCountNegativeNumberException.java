package lotto.exception.Lotto;

public class ManualLottoTicketCountNegativeNumberException extends LottoException{
    public static final String LOTTO_COUNT_NOT_AVAILABLE_ERROR_MESSAGE =
            "[ERROR] 구매할 로또의 수는 0매 이상이어야 합니다. 입력된 값 : %d";

    public ManualLottoTicketCountNegativeNumberException(int manualLottoCount) {
        super(String.format(LOTTO_COUNT_NOT_AVAILABLE_ERROR_MESSAGE, manualLottoCount));
    }
}
