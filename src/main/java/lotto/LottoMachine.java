package lotto;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class LottoMachine {

    private static final int LOTTO_NUMBER_SIZE = 6;
    private static final int LOTTO_NUMBER_LOWER_BOUND = 1;
    private static final int LOTTO_NUMBER_UPPER_BOUND = 46;

    public Set<LottoNumber> makeLottoTicket() {
        Set<LottoNumber> lottoTicket = new HashSet<>();

        while (lottoTicket.size() < LOTTO_NUMBER_SIZE) {
            final LottoNumber lottoNumber = new LottoNumber(
                    ThreadLocalRandom.current().nextInt(LOTTO_NUMBER_LOWER_BOUND, LOTTO_NUMBER_UPPER_BOUND));
            lottoTicket.add(lottoNumber);
        }

        return lottoTicket;
    }
}
