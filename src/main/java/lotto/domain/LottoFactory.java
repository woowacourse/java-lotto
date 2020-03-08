package lotto.domain;

import lotto.view.InputView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoFactory {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private static List<LottoNumber> lottoNumbers = new ArrayList<>();
    private static final RandomLottoGenerator randomLottoGenerator = new RandomLottoGenerator();

    private LottoFactory() {
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
    }

    private static class LottoFactoryHolder {
        public static final LottoFactory instance = new LottoFactory();
    }

    public static LottoFactory getInstance() {
        return LottoFactoryHolder.instance;
    }

    public static Set<LottoTicket> createLottoNumbers(int autoLottoTicketCounts, int manualLottoTicketCounts) {
        Set<LottoTicket> lottoTickets = new HashSet<>();
        for (int i = 0; i < autoLottoTicketCounts; i++) {
            lottoTickets.add(new LottoTicket(randomLottoGenerator.generateNumbers()));
        }
        for (int i = 0; i < manualLottoTicketCounts; i++) {
            InputView.inputManualLottoNumbers();
            ManualLottoGenerator manualLottoGenerator = new ManualLottoGenerator(InputView.inputLottoNumbers());
            Set<LottoNumber> manualLottoNumbers = manualLottoGenerator.generateNumbers();
            lottoTickets.add(new LottoTicket(manualLottoNumbers));
        }
        return lottoTickets;
    }

    static List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }
}
