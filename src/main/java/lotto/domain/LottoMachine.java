package lotto.domain;

import java.util.List;
import lotto.domain.generator.AutoLottoNumberGenerator;

public class LottoMachine {

    public final static int LOTTO_PRICE = 1000;

    public LottoTickets issueManual(List<List<Integer>> manualNumbers) {
        return LottoTickets.createManualLottoTickets(manualNumbers);
    }

    public LottoTickets issueAuto(int lottoCount) {
        return LottoTickets.createAutoLottoTickets(lottoCount, new AutoLottoNumberGenerator());
    }
}
