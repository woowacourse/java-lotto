package domain.result;

import domain.Money;
import domain.lottonumbers.LottoTicket;
import domain.lottonumbers.WinningNumbers;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class LottoResult {

    private static final int NOTHING_EXIST = 0;

    private final Map<LottoRank, Integer> lottoResult;

    private LottoResult(Map<LottoRank, Integer> lottoResult) {
        this.lottoResult = lottoResult;
    }

    public static LottoResult confirmResult(List<LottoTicket> lottoTickets, WinningNumbers winningNumbers) {
        Map<LottoRank, Integer> lottoResult = lottoTickets.stream()
                .collect(groupingBy(ticket -> winningNumbers.findLottoRank(ticket), summingInt(x -> 1)));

        return new LottoResult(lottoResult);
    }

    public int findNumberOf(LottoRank lottoRank) {
        if (this.lottoResult.get(lottoRank) != null) {
            return this.lottoResult.get(lottoRank);
        }

        return NOTHING_EXIST;
    }

    public double calculateProfit(Money spentMoney) {
        long totalPrize = this.lottoResult.keySet().stream()
                .mapToLong(rank -> rank.getPrize() * lottoResult.get(rank))
                .sum();

        return totalPrize * 100 / spentMoney.getMoney();
    }
}
