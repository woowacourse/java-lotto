package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoFactory {
    private static final List<Integer> numbers = new ArrayList<>();

    static {
        for (int i = 1; i <= 45; i++) {
            numbers.add(i);
        }
    }

    public static List<LottoTicket> createLottos(int purchaseMoney) {
        int lottoCount = purchaseMoney / 1000;
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottoTickets.add(createLotto());
        }
        return lottoTickets;
    }

    private static LottoTicket createLotto() {
        Collections.shuffle(numbers);
        ArrayList<Integer> subNumbers = new ArrayList<Integer>(numbers.subList(0, 6));
        return new LottoTicket(subNumbers);
    }
}
