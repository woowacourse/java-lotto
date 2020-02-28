package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoFactory {
    public static final int LOTTO_SIZE = 6;

    public static List<Lotto> createLottoTickets(LottoCount lottoCount, Generator randomGenerator, ManualLottoTickets manualLottoTickets) {
        List<Lotto> lottoTickets = new ArrayList<>();
        createManualLottoTickets(manualLottoTickets, lottoTickets);
        createAutoLottoTickets(lottoCount, randomGenerator, lottoTickets);
        return lottoTickets;
    }

    public static Lotto createManualLotto(List<String> manualLottoNumbers) {
        Set<LottoNumber> manualLottoSet = new HashSet<>();
        for (String manualLottoNumber : manualLottoNumbers) {
            manualLottoSet.add(LottoNumber.valueOf(manualLottoNumber));
        }
        return new Lotto(manualLottoSet);
    }

    private static void createManualLottoTickets(ManualLottoTickets manualLottoTickets, List<Lotto> lottoTickets) {
        manualLottoTickets.addManualLottoTickets(lottoTickets);
    }

    private static void createAutoLottoTickets(LottoCount lottoCount, Generator randomGenerator, List<Lotto> lottoTickets) {
        while (lottoCount.canCreateAutoLotto()) {
            lottoCount.reduceAutoLottoCount();
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
}
