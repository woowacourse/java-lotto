package lotto.service;

import lotto.domain.*;
import lotto.dto.response.LottosResponse;
import lotto.dto.request.PaymentRequest;
import lotto.dto.response.ResultResponse;
import lotto.dto.request.WinningBallsRequest;

import java.util.Map;

public class LottoService {
    private final Bank bank;
    private Lottos lottos;
    private WinningNumbers winningNumbers;

    public LottoService() {
        bank = new Bank();
    }

    public LottosResponse buyLottos(PaymentRequest request) {
        bank.use(request.payment());
        lottos = new Lottos(request.payment());
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
