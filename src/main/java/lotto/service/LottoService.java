package lotto.service;

import lotto.domain.*;
import lotto.dto.WinningBallsDto;

import java.util.List;
import java.util.Map;

public class LottoService {
    private final Bank bank;
    private Lottos lottos;
    private WinningNumbers winningNumbers;

    public LottoService() {
        this.bank = new Bank();
    }

    public List<Lotto> buyLottos(int payment) {
        bank.use(payment);
        this.lottos = new Lottos(payment);
        return lottos.getLottos();
    }

    public void setWinningBalls(WinningBallsDto winningBallsDto) {
        winningNumbers = new WinningNumbers(new Lotto(winningBallsDto.winningNumbers()), winningBallsDto.bonusNumber());
    }

    public Map<Rank, Integer> getResult() {
        return lottos.getRankCount(winningNumbers);
    }

    public double getRateOfReturn(Map<Rank, Integer> rankCount) {
        return bank.calculateRateOfReturn(rankCount);
    }
}
