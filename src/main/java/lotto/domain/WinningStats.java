package lotto.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map.Entry;
import lotto.domain.lottonumber.LottoTicket;
import lotto.domain.lottonumber.WinningNumbers;
import lotto.domain.vo.PurchaseAmount;

public class WinningStats {

    public static final int EARNING_RATE_PRECISIONS = 2;
    private final EnumMap<LottoRank, Integer> lottoRankMap = new EnumMap<>(LottoRank.class);

    public WinningStats(List<LottoTicket> lottoTickets, WinningNumbers winningNumbers) {
        initLottoRankMap();
        fulfillCorrectAnswerNumbers(lottoTickets, winningNumbers);
    }

    private void initLottoRankMap() {
        Arrays.stream(LottoRank.values())
                .forEach(lottoRank -> lottoRankMap.put(lottoRank, 0));
    }

    private void fulfillCorrectAnswerNumbers(List<LottoTicket> lottoTickets, WinningNumbers winningNumbers) {
        for (LottoTicket lottoTicket : lottoTickets) {
            LottoRank lottoRank = getLottoRank(winningNumbers, lottoTicket);
            plusOneCorrectAnswerNumbers(lottoRank);
        }
    }

    private LottoRank getLottoRank(WinningNumbers winningNumbers, LottoTicket lottoTicket) {
        int matchCount = winningNumbers.getMatchCount(lottoTicket);
        boolean containsBonusBall = winningNumbers.doesMatchBonusBall(lottoTicket);
        return LottoRank.getRank(matchCount, containsBonusBall);
    }

    private void plusOneCorrectAnswerNumbers(LottoRank lottoRank) {
        lottoRankMap.put(lottoRank, lottoRankMap.get(lottoRank) + 1);
    }

    public int getCorrectAnswerNumbers(LottoRank lottoRank) {
        return lottoRankMap.get(lottoRank);
    }

    public long getTotalPrize() {
        return lottoRankMap.entrySet().stream()
                .mapToLong(this::subTotal)
                .sum();
    }

    private long subTotal(Entry<LottoRank, Integer> entry) {
        long prizeMoney = entry.getKey().prizeMoney();
        Integer hitCount = entry.getValue();
        return prizeMoney * hitCount;
    }


    public BigDecimal getEarningsRate(PurchaseAmount money) {
        BigDecimal totalPrize = new BigDecimal(getTotalPrize());
        BigDecimal amount = new BigDecimal(money.amount());
        return totalPrize.divide(amount, EARNING_RATE_PRECISIONS, RoundingMode.HALF_EVEN);
    }
}
