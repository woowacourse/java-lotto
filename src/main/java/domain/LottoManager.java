package domain;

import static domain.LottoRules.WINNING_NUMBERS_REQUIRED;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import utils.RandomNumber;

public class LottoManager {

    private final List<Lotto> lottos;

    private LottoManager() {
        lottos = new ArrayList<>();
    }

    public static LottoManager create() {
        return new LottoManager();
    }

    public List<Lotto> purchaseLottoByTicketQuantity(Ticket ticket) {
        for (int i = 0; i < ticket.getQuantity(); i++) {
            List<Integer> numbers = RandomNumber.generateNumbers(WINNING_NUMBERS_REQUIRED);
            Lotto lotto = Lotto.from(numbers);
            lottos.add(lotto);
        }
        return Collections.unmodifiableList(lottos);
    }

}
