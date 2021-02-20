package lotto.domain.result;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.Money;
import lotto.domain.lotto.Rank;
import lotto.domain.lotto.WinningNumbers;

public class LottoMatcher {

    WinningNumbers winningNumbers;
    String bonusBallValue;
    List<LottoTicket> lottoTickets;
    Money paymentAmount;

    public LottoMatcher(String winningNumbersValue, String bonusBallValue,
            List<LottoTicket> lottoTickets, Money paymentAmount) {
        this.winningNumbers = new WinningNumbers(winningNumbersValue, bonusBallValue);
        this.bonusBallValue = bonusBallValue;
        this.lottoTickets = new ArrayList<>(lottoTickets);
        this.paymentAmount = paymentAmount;
    }

    public Result getResult() {
        return new Result(getResultMap(), paymentAmount.toBigInteger());
    }

    private Map<Rank, Integer> getResultMap() {
        Map<Rank, Integer> resultMap = new EnumMap<>(Rank.class);

        for (LottoTicket lottoTicket : lottoTickets) {
            Rank rank = winningNumbers.matchRank(lottoTicket);
            resultMap.put(rank, resultMap.getOrDefault(rank, 0) + 1);
        }
        return resultMap;
    }
}
