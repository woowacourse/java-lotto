package lotto.service;

import lotto.domain.*;

import java.util.List;
import java.util.Map;

public class LottoService {
    private Bank bank = new Bank();
    private Lottos lottos;
    private WinningNumbers winningNumbers;

    public List<Lotto> buyLottos(int payment) {
        bank.use(payment);
        this.lottos = new Lottos(payment);
        return lottos.getLottos();
    }

    public Map<Rank, Integer> getResult(List<Integer> numbers, int bonusNumber) {
        winningNumbers = new WinningNumbers(new Lotto(numbers), bonusNumber);
        return lottos.getRankCount(winningNumbers);
    }

    public double getRateOfReturn(Map<Rank, Integer> rankCount) {
        return bank.calculateRateOfReturn(rankCount);
    }
}
