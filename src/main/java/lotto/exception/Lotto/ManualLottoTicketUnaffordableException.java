package lotto.exception.Lotto;

public class ManualLottoTicketUnaffordableException extends LottoException{
    public static final String MONEY_NOT_ENOUGH_ERROR_MESSAGE =
            "[ERROR] 금액으로 구매 가능한 로또의 매수보다 많은 양입니다. 입력된 값 : %d";

    public ManualLottoTicketUnaffordableException(int manualLottoCount) {
        super(String.format(MONEY_NOT_ENOUGH_ERROR_MESSAGE, manualLottoCount));
    }
}
