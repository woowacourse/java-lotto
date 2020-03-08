package lotto.domain;

import lotto.view.InputView;

import java.util.*;

public class LottoFactory {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int LOTTO_LENGTH = 6;

    private static List<LottoNumber> lottoNumbers = new ArrayList<>();

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

    static Set<LottoTicket> createLottoNumbers(int autoLottoTicketCounts, int manualLottoTicketCounts) {
        Set<LottoTicket> lottoTickets = new HashSet<>();
        for (int i = 0; i < autoLottoTicketCounts; i++) {
            lottoTickets.add(new LottoTicket(LottoFactory.createRandomLottoNumbers()));
        }
        for (int i = 0; i < manualLottoTicketCounts; i++) {
            Set<LottoNumber> manualLottoNumbers = LottoFactory.createManualLottoNumbers(InputView.inputManualLottoNumbers());
            lottoTickets.add(new LottoTicket(manualLottoNumbers));
        }
        return lottoTickets;
    }

    static Set<LottoNumber> createRandomLottoNumbers() {
        Set<LottoNumber> lotto = new HashSet<>();
        Collections.shuffle(lottoNumbers);
        for (int i = 0; i < LOTTO_LENGTH; i++) {
            lotto.add(lottoNumbers.get(i));
        }
        return lotto;
    }

    public static Set<LottoNumber> createManualLottoNumbers(List<Integer> manualLottoNumbers) {
        Set<Integer> duplicationManualLottoNumbers = new HashSet<>(manualLottoNumbers);
        Set<LottoNumber> lotto = new HashSet<>();

        for (Integer duplicationManualLottoNumber : duplicationManualLottoNumbers) {
            lotto.add(new LottoNumber(duplicationManualLottoNumber));
        }
        return lotto;
    }
}
