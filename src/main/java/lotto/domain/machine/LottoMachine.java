package lotto.domain.machine;

import lotto.domain.Money;
import lotto.domain.number.LottoNumbers;
import lotto.domain.ticket.LottoTickets;

import java.util.Collections;
import java.util.List;

public abstract class LottoMachine {
    private static final String INDIVISIBLE_MONEY_ERROR_MSG_FORMAT = "로또는 %d원 단위로 구매해야 있습니다. 입력금액 : %d";
    private static final String MONEY_MUST_BE_POSITIVE_ERROR_MSG_FORMAT = "금액은 양수여야 합니다. 입력금액 : %d";

    private final Money lottoPrice;

    public LottoMachine(Money lottoPrice) {
        this.lottoPrice = lottoPrice;
    }

    public int calculateNumberOfTickets(int purchaseMoney) {
        validateNegativeDigit(purchaseMoney);
        validateNoExtraMoney(purchaseMoney);

        return purchaseMoney / lottoPrice.getMoney();
    }

    public LottoTickets createTicketsByMoney(int numberOfTickets) {
        return createTickets(numberOfTickets, Collections.emptyList());
    }

    public LottoTickets createTicketsByMoney(int numberOfTickets, List<LottoNumbers> lottoNumbersBundle) {
        return createTickets(numberOfTickets, lottoNumbersBundle);
    }

    public abstract LottoTickets createTickets(int numberOfTickets, List<LottoNumbers> lottoNumbersBundle);

    private void validateNoExtraMoney(int purchaseMoney) {
        if (purchaseMoney % lottoPrice.getMoney() != 0) {
            throw new IllegalArgumentException(
                    String.format(INDIVISIBLE_MONEY_ERROR_MSG_FORMAT, lottoPrice.getMoney(), purchaseMoney));
        }
    }

    private void validateNegativeDigit(int purchaseMoney) {
        if (purchaseMoney < 0) {
            throw new IllegalArgumentException(
                    String.format(MONEY_MUST_BE_POSITIVE_ERROR_MSG_FORMAT, purchaseMoney));
        }
    }

    public Money getLottoPrice() {
        return lottoPrice;
    }
}
