package lotto.domain.machine;

import lotto.domain.Money;
import lotto.domain.number.LottoNumbers;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTickets;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class LottoMachine {
    private static final String INDIVISIBLE_MONEY_ERROR_MSG_FORMAT = "로또는 %d원 단위로 구매해야 있습니다. 입력금액 : %d";
    private static final String MONEY_MUST_BE_POSITIVE_ERROR_MSG_FORMAT = "금액은 양수여야 합니다. 입력금액 : %d";
    private static final int MINIMUM_AMOUNT = 0;
    private static final int ZERO_REMAINDER = 0;

    private final Money lottoPrice;

    protected LottoMachine(Money lottoPrice) {
        this.lottoPrice = lottoPrice;
    }

    public int calculateNumberOfTickets(Money purchaseMoney) {
        validateNegativeDigit(purchaseMoney.getMoney());
        validateNoExtraMoney(purchaseMoney.getMoney());

        return purchaseMoney.getMoney() / lottoPrice.getMoney();
    }

    public LottoTickets createTickets(int numberOfTickets, List<LottoNumbers> lottoNumbersBundle) {
        return lottoNumbersBundle.stream()
                .map(LottoTicket::createLottoTicket)
                .collect(collectingAndThen(toList(), LottoTickets::new));
    }

    private void validateNoExtraMoney(int purchaseMoney) {
        if (purchaseMoney % lottoPrice.getMoney() != ZERO_REMAINDER) {
            throw new IllegalArgumentException(
                    String.format(INDIVISIBLE_MONEY_ERROR_MSG_FORMAT, lottoPrice.getMoney(), purchaseMoney));
        }
    }

    private void validateNegativeDigit(int purchaseMoney) {
        if (purchaseMoney < MINIMUM_AMOUNT) {
            throw new IllegalArgumentException(
                    String.format(MONEY_MUST_BE_POSITIVE_ERROR_MSG_FORMAT, purchaseMoney));
        }
    }

    public Money getLottoPrice() {
        return lottoPrice;
    }
}
