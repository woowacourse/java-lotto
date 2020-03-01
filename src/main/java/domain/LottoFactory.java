package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoFactory {
    public static final int LOTTO_SIZE = 6;

    public static LottoTickets createLottoTickets(LottoCount lottoCount, Generator randomGenerator, List<List<String>> manualLottoNumbers) {
        return new LottoTickets(createManualLottoTickets(lottoCount, manualLottoNumbers), createAutoLottoTickets(lottoCount, randomGenerator));
    }

    private static List<Lotto> createManualLottoTickets(LottoCount lottoCount, List<List<String>> manualLottoNumbers) {
        List<Lotto> manualLottoTickets = new ArrayList<>();
        for (int i = 0; i < lottoCount.getManualCount(); i++) {
            manualLottoTickets.add(createManualLotto(manualLottoNumbers.get(i)));
        }
        return manualLottoTickets;
    }

    public static Lotto createManualLotto(List<String> manualLottoNumbers) {
        Set<LottoNumber> manualLotto = new HashSet<>();
        for (String manualLottoNumber : manualLottoNumbers) {
            manualLotto.add(LottoNumber.valueOf(manualLottoNumber));
        }
        return new Lotto(manualLotto);
    }

    private static List<Lotto> createAutoLottoTickets(LottoCount lottoCount, Generator randomGenerator) {
        List<Lotto> autoLottoTickets = new ArrayList<>();
        for (int i = 0; i < lottoCount.getAutoCount(); i++) {
            autoLottoTickets.add(createAutoLotto(randomGenerator));
        }
        return autoLottoTickets;
    }

    private static Lotto createAutoLotto(Generator randomGenerator) {
        Set<LottoNumber> autoLotto = new HashSet<>();
        while (autoLotto.size() < LOTTO_SIZE) {
            int number = randomGenerator.generate(RandomNumber.generateRandomNumber());
            autoLotto.add(LottoNumber.valueOf(number));
        }
        return new Lotto(autoLotto);
    }
}
