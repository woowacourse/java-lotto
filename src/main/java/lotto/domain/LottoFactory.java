package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoFactory {
    private static List<LottoNumber> numbers = new ArrayList<>();
    private static final Money TICKET_PRICE = Money.of(1_000);

    static {
        for (int i = 1; i <= 45; i++) {
            numbers.add(new LottoNumber(i));
        }
    }

    public static List<LottoTicket> createLottos(Money purchaseMoney) {
        int lottoCount = purchaseMoney.calculateQuotient(TICKET_PRICE);
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottoTickets.add(createLotto());
        }
        return lottoTickets;
    }

    private static LottoTicket createLotto() {
        Collections.shuffle(numbers);
        ArrayList<LottoNumber> subNumbers = new ArrayList<>(numbers.subList(0, 6));
        return new LottoTicket(subNumbers);
    }
}
