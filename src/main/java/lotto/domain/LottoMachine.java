package lotto.domain;

import java.util.List;
import lotto.domain.generator.AutoLottoNumberGenerator;

public class LottoMachine {

    public final static int LOTTO_PRICE = 1000;

    public LottoTickets issueManual(int lottoCount, List<List<Integer>> manualNumbers) {
        return LottoTickets.createManualLottoTickets(lottoCount, manualNumbers);
    }

    public LottoTickets issueAuto(int lottoCount) {
        return LottoTickets.createAutoLottoTickets(lottoCount, new AutoLottoNumberGenerator());
    }
}
