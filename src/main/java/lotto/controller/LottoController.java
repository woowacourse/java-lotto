package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.LottoNumber;
import lotto.domain.LottoResult;
import lotto.domain.LottoStatistics;
import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.domain.WinningNumbers;

public class LottoController {

    private Money money;
    private LottoTickets lottoTickets;

    public LottoTickets buyLottoTickets(int inputMoney) {
        money = new Money(inputMoney);
        return getLottoTickets();
    }

    public LottoResult matchLottoTickets(List<Integer> lottoNumbers, int lottoNumber) {
        WinningNumbers winningNumbers = new WinningNumbers(getWinningNumbers(lottoNumbers),
                getBonusNumber(lottoNumber));
        LottoStatistics lottoStatistics = new LottoStatistics(lottoTickets.getRanksWithWinningNumbers(winningNumbers));
        return new LottoResult(lottoStatistics.getStatistics(),
                money.calculateYield(lottoStatistics.getLottoTotalReward()));
    }

    private LottoTickets getLottoTickets() {
        lottoTickets = new LottoTickets(money.getLottoCount());
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
