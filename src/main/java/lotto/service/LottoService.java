package lotto.service;

import lotto.domain.Bank;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.domain.WinningNumbers;
import lotto.dto.request.PaymentRequest;
import lotto.dto.request.WinningBallsRequest;
import lotto.dto.response.LottosResponse;
import lotto.dto.response.ResultResponse;

import java.util.Map;

public class LottoService {
    private final Bank bank;
    private Lottos lottos;
    private WinningNumbers winningNumbers;

    public LottoService() {
        this.bank = new Bank();
    }

    public LottosResponse buyLottos(PaymentRequest request) {
        bank.use(request.payment());
        this.lottos = new Lottos(request.payment());
        return LottosResponse.from(lottos);
    }

    public void setWinningBalls(WinningBallsRequest request) {
        winningNumbers = new WinningNumbers(new Lotto(request.winningNumbers()), request.bonusNumber());
    }

    public ResultResponse getResult() {
        Map<Rank, Integer> rankCount = lottos.getRankCount(winningNumbers);
        return ResultResponse.of(rankCount, bank.calculateRateOfReturn(rankCount));
    }
}
