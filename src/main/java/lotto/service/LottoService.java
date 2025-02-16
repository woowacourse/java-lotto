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
import lotto.utils.NumberUtils;
import lotto.utils.RandomNumberUtils;

public class LottoService {

    public Money getMoney(String input) {
        int money = NumberUtils.parseInt(input);
        return new Money(money);
    }

    public LottoGroup generateLottoGroupByMoney(Money money) {
        LottoGroup lottoGroup = LottoGroup.create();
        int lottoTicketCount = money.getLottoTicketCount();

        for (List<Integer> randomNumbers : RandomNumberUtils.generateRandomNumbersList(lottoTicketCount, LOTTO_NUM_SIZE,
                MAX_LOTTO_NUMBER)) {
            lottoGroup.add(randomNumbers);
        }

        return lottoGroup;
    }


    public String getLottoGroupMessage(LottoGroup lottoGroup) {
        return lottoGroup.toString();
    }

    public Lotto getWinnerNumber(String winnerNumber) {
        return Lotto.from(winnerNumber);
    }

    public WinnerLotto getWinnerLotto(Lotto winnerNumber, String bonusNumberInput) {
        int parseInput = NumberUtils.parseInt(bonusNumberInput);
        LottoNumber bonusNumber = LottoNumber.from(parseInput);
        WinnerLotto.validateBonusNumbers(winnerNumber, bonusNumber);

        return new WinnerLotto(winnerNumber, bonusNumber);
    }

    public Profit calculateProfit(WinnerLotto winnerLotto, LottoGroup lottoGroup) {
        Profit profit = new Profit();

        for (Lotto lotto : lottoGroup.getItem()) {
            long matchCount = winnerLotto.getMatchCount(lotto);
            boolean hasBonus = winnerLotto.hasBonusNumber(lotto);
            Rank rank = Rank.find((int) matchCount, hasBonus);
            profit.incrementCount(rank);
        }

        return profit;
    }

    public List<Integer> getStatusValues(Profit profit) {
        return profit.getValues();
    }

    public String getProfitRate(Profit profit, Money money) {
        return profit.calculateAverageProfitRate(money);
    }
}
