package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.LottoNumber;
import lotto.domain.LottoResult;
import lotto.domain.LottoStatistics;
import lotto.domain.LottoTickets;
import lotto.domain.MoneyManager;
import lotto.domain.WinningNumbers;

public class LottoController {

    private MoneyManager moneyManager;
    private LottoTickets lottoTickets;

    public LottoTickets buyLottoTickets(int inputMoney) {
        moneyManager = new MoneyManager(inputMoney);
        return getLottoTickets();
    }

    public LottoResult matchLottoTickets(List<Integer> lottoNumbers, int lottoNumber) {
        WinningNumbers winningNumbers = new WinningNumbers(getWinningNumbers(lottoNumbers),
                getBonusNumber(lottoNumber));
        LottoStatistics lottoStatistics = new LottoStatistics(lottoTickets.getRanksWithWinningNumbers(winningNumbers));
        return new LottoResult(lottoStatistics.getStatistics(),
                moneyManager.calculateYield(lottoStatistics.getLottoTotalReward()));
    }

    private LottoTickets getLottoTickets() {
        lottoTickets = new LottoTickets(moneyManager.getLottoCount());
        return lottoTickets;
    }

    private LottoNumber getBonusNumber(int lottoNumber) {
        return new LottoNumber(lottoNumber);
    }

    private List<LottoNumber> getWinningNumbers(List<Integer> lottoNumbers) {
        return lottoNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }
}
