package lotto.controller;

import java.util.List;
import lotto.domain.LottoGame;
import lotto.domain.LottoStatistics;
import lotto.domain.LottoTickets;
import lotto.domain.WinningLotto;
import lotto.domain.lottonumbergenerator.LottoNumberAutoGenerator;
import lotto.domain.lottonumbergenerator.LottoNumberManualGenerator;
import lotto.dto.LottoResultDto;
import lotto.dto.LottoTicketsDto;

public class LottoController {

    private final LottoGame lottoGame;
    private LottoTickets lottoTickets;

    public LottoController(LottoGame lottoGame) {
        this.lottoGame = lottoGame;
    }

    public LottoTicketsDto publishLottoTickets() {
        lottoGame.billingManualLottoOrder();
        LottoTickets manualLottoTickets = getManualLottoTickets(lottoGame.getManualLottoNumbers());
        LottoTickets autoLottoTickets = getAutoLottoTickets(lottoGame.getNumberOfAutoLotto());
        lottoTickets = manualLottoTickets.combine(autoLottoTickets);
        return LottoTicketsDto.from(manualLottoTickets, autoLottoTickets);
    }

    public LottoResultDto matchLottoTickets(List<Integer> lottoNumbers, int bonusNumber) {
        WinningLotto winningLotto = new WinningLotto(lottoNumbers, bonusNumber);
        LottoStatistics lottoStatistics = new LottoStatistics(lottoTickets.getRanksBy(winningLotto));
        return LottoResultDto.from(lottoStatistics);
    }

    private LottoTickets getManualLottoTickets(List<List<Integer>> numbers) {
        return new LottoTickets(new LottoNumberManualGenerator(numbers), numbers.size());
    }

    private LottoTickets getAutoLottoTickets(int autoLottoCount) {
        return new LottoTickets(new LottoNumberAutoGenerator(), autoLottoCount);
    }
}
