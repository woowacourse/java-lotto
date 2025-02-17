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

    public void initWinningLotto(WinningBallsRequest request) {
        this.winningLotto = new WinningLotto(new Lotto(request.winningNumbers()), request.bonusNumber());
    }

    public ResultResponse calculateResult() {
        Map<Rank, Long> rankCount = this.lottos.calculateWinnings(winningLotto);
        LottoResult result = new LottoResult(rankCount);
        return ResultResponse.of(rankCount, result.calculateRateOfReturn(this.bank.getUsedMoney()));
    }
}
