package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoFactory {
    public static final int LOTTO_SIZE = 6;

    public static List<Lotto> createLottoTickets(int lottoCount, Generator randomGenerator) {
        List<Lotto> lottoTickets = new ArrayList<>();
        createAutoLottoTickets(lottoCount, randomGenerator, lottoTickets);
        return lottoTickets;
    }

    private static void createAutoLottoTickets(int lottoCount, Generator randomGenerator, List<Lotto> lottoTickets) {
        for (int i = 0; i < lottoCount; i++) {
            lottoTickets.add(createAutoLotto(randomGenerator));
        }
    }

    private static Lotto createAutoLotto(Generator randomGenerator) {
        Set<LottoNumber> lotto = new HashSet<>();
        while (lotto.size() < LOTTO_SIZE) {
            int number = randomGenerator.generate(RandomNumber.generateRandomNumber());
            lotto.add(LottoNumber.valueOf(number));
        }
        return new Lotto(lotto);
    }

    public static Lotto createManualLotto(List<String> manualLottoNumbers) {
        Set<LottoNumber> manualLottoSet = new HashSet<>();
        for (String manualLottoNumber : manualLottoNumbers) {
            manualLottoSet.add(LottoNumber.valueOf(manualLottoNumber));
        }
        return new Lotto(manualLottoSet);
    }
}
