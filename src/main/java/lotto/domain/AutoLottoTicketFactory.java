package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.util.RandomUtil;

public class AutoLottoTicketFactory implements LottoTicketFactory {
    @Override
    public LottoTicket createLottoTicket() {
        return new LottoTicket(generateRandomNumbers());
    }

    private List<Integer> generateRandomNumbers() {
        Set<Integer> randoms = new HashSet<>();

        while (randoms.size() < 6) {
            randoms.add(RandomUtil.generateRandomNumber(1, 45));
        }

        return new ArrayList<>(randoms);
    }
}
