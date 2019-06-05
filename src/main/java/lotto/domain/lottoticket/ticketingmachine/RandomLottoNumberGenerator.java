package lotto.domain.lottoticket.ticketingmachine;

import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottoticket.LottoTicket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoNumberGenerator implements LottoNumberGenerator {
    @Override
    public List<Integer> create() {
        List<Integer> candidates = initCandidates();
        Collections.shuffle(candidates);
        return candidates.subList(0, LottoTicket.SIZE_OF_LOTTO);
    }

    private static List<Integer> initCandidates() {
        List<Integer> candidates = new ArrayList<>();
        for (int number = LottoNumber.FIRST_NUMBER; number <= LottoNumber.LAST_NUMBER; number++) {
            candidates.add(number);
        }
        return candidates;
    }
}
