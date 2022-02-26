package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTickets;
import lotto.domain.MoneyManager;
import lotto.domain.Ranks;
import lotto.domain.Statistics;
import lotto.domain.WinningNumbers;

public class LottoController {

    private MoneyManager moneyManager;
    private LottoTickets lottoTickets;

    public LottoTickets buyLottoTickets(int inputMoney) {
        moneyManager = new MoneyManager(inputMoney);
        return getLottoTickets();
    }

    public Statistics matchLottoTickets(List<Integer> lottoNumbers, int lottoNumber) {
        WinningNumbers winningNumbers = new WinningNumbers(getWinningNumbers(lottoNumbers),
                getBonusNumber(lottoNumber));
        Ranks ranks = new Ranks(lottoTickets.getRanksWithWinningNumbers(winningNumbers));
        return new Statistics(ranks.getStatistics(), moneyManager.calculateYield(ranks.getLottoTotalReward()));
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
