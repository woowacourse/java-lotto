package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoFactory {
    public static final int LOTTO_SIZE = 6;

    public static List<Lotto> createLottoTickets(int lottoCount, Generator randomGenerator) {
        List<Lotto> lottoTickets = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottoTickets.add(createLotto(randomGenerator));
        }
        return lottoTickets;
    }

    private static Lotto createLotto(Generator randomGenerator) {
        Set<LottoNumber> lotto = new HashSet<>();
        while (lotto.size() < LOTTO_SIZE) {
            int number = randomGenerator.generate(RandomNumber.generateRandomNumber());
            lotto.add(LottoNumber.valueOf(number));
        }
        return new Lotto(lotto);
    }
}
