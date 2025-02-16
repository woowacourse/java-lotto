package lotto.service;

import java.util.Map;
import lotto.domain.Bank;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.domain.util.impl.RandomLottoGenerator;
import lotto.dto.request.PaymentRequest;
import lotto.dto.request.WinningBallsRequest;
import lotto.dto.response.LottosResponse;
import lotto.dto.response.ResultResponse;

public class LottoService {
    private final Bank bank;
    private Lottos lottos;
    private WinningLotto winningLotto;

    public LottoService() {
        this.bank = new Bank();
    }

    public LottosResponse buyLottos(PaymentRequest request) {
        bank.pay(request.payment());
        this.lottos = new Lottos(new RandomLottoGenerator(), request.payment());
        return LottosResponse.from(lottos);
    }

    public void setWinningLotto(WinningBallsRequest request) {
        winningLotto = new WinningLotto(new Lotto(request.winningNumbers()), request.bonusNumber());
    }

    public ResultResponse getResult() {
        Map<Rank, Long> rankCount = lottos.getRankCount(winningLotto);
        LottoResult result = new LottoResult(rankCount);
        return ResultResponse.of(rankCount, result.calculateRateOfReturn(bank.getUsedMoney()));
    }
}
