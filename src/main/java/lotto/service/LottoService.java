package lotto.service;

import static lotto.common.Constants.LOTTO_NUM_SIZE;
import static lotto.common.Constants.MAX_LOTTO_NUMBER;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoGroup;
import lotto.domain.LottoNumber;
import lotto.domain.Money;
import lotto.domain.Profit;
import lotto.domain.Rank;
import lotto.domain.WinnerLotto;

public class LottoService {

    private final RandomLottoService randomLottoService;

    public LottoService(RandomLottoService randomLottoService) {
        this.randomLottoService = randomLottoService;
    }

    public Money getMoney(int money) {
        return new Money(money);
    }

    public LottoGroup generateLottoGroupByMoney(Money money) {
        int lottoTicketCount = money.getLottoTicketCount();

        List<Lotto> lottoList = randomLottoService
                .generateLottoList(lottoTicketCount, LOTTO_NUM_SIZE, MAX_LOTTO_NUMBER)
                .stream()
                .toList();

        return LottoGroup.from(lottoList);
    }

    public List<List<Integer>> getLottoGroupMessage(LottoGroup lottoGroup) {
        return lottoGroup.toIntegerLottosList();
    }

    public Lotto getWinnerNumber(List<Integer> winnerNumberInput) {
        List<LottoNumber> winnerNumber = winnerNumberInput.stream()
                .map(LottoNumber::new)
                .toList();

        return new Lotto(winnerNumber);
    }

    public WinnerLotto getWinnerLotto(Lotto winnerNumber, int bonusNumberInput) {
        LottoNumber bonusNumber = LottoNumber.from(bonusNumberInput);
        WinnerLotto.validateBonusNumbers(winnerNumber, bonusNumber);

        return new WinnerLotto(winnerNumber, bonusNumber);
    }

    public Profit calculateProfit(WinnerLotto winnerLotto, LottoGroup lottoGroup) {
        Profit profit = new Profit();

        for (Lotto lotto : lottoGroup.getLottos()) {
            long matchCount = winnerLotto.getMatchCount(lotto);
            boolean hasBonus = winnerLotto.hasBonusNumber(lotto);
            Rank rank = Rank.find((int) matchCount, hasBonus);
            profit.incrementCount(rank);
        }

        return profit;
    }

    public List<Integer> getStatusValues(Profit profit) {
        return profit.getRankCountList();
    }

    public String getProfitRate(Profit profit, Money money) {
        return profit.calculateAverageProfitRate(money);
    }
}
