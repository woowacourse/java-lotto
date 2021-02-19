package lotto.domain.machine;

import lotto.domain.ticket.AutoLottoTicketFactory;
import lotto.domain.ticket.LottoTicketFactory;
import lotto.domain.ticket.LottoTickets;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoLottoMachine implements LottoMachine {
    public static final int LOTTO_PRICE = 1000;

    private static final String INDIVISIBLE_MONEY_ERROR_MSG_FORMAT = "로또는 %d원 단위로 구매해야 있습니다. 입력금액 : %d";
    private static final String MONEY_MUST_BE_POSITIVE_ERROR_MSG_FORMAT = "금액은 양수여야 합니다. 입력금액 : %d";

    private final LottoTicketFactory lottoTicketFactory = new AutoLottoTicketFactory();

    @Override
    public LottoTickets createTickets(int numberOfTickets) {
        return IntStream.range(0, numberOfTickets)
                .mapToObj(i -> lottoTicketFactory.createLottoTicket())
                .collect(Collectors.collectingAndThen(Collectors.toList(), LottoTickets::new));
    }

    @Override
    public LottoTickets createTicketsByMoney(int purchaseMoney) {
        validateNegativeDigit(purchaseMoney);
        validateNoExtraMoney(purchaseMoney);

        int numberOfTickets = purchaseMoney / LOTTO_PRICE;
        return createTickets(numberOfTickets);
    }

    private void validateNoExtraMoney(int purchaseMoney) {
        if (purchaseMoney % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(
                    String.format(INDIVISIBLE_MONEY_ERROR_MSG_FORMAT, LOTTO_PRICE, purchaseMoney));
        }
    }

    private void validateNegativeDigit(int purchaseMoney) {
        if (purchaseMoney < 0) {
            throw new IllegalArgumentException(
                    String.format(MONEY_MUST_BE_POSITIVE_ERROR_MSG_FORMAT, purchaseMoney));
        }
    }
}
