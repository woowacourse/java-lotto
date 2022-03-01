package lotto.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import lotto.domain.lottonumber.LottoTicket;
import lotto.domain.lottonumber.WinningNumbers;

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
            int matchCount = winningNumbers.getMatchCount(lottoTicket);
            boolean containsBonusBall = winningNumbers.doesMatchBonusBall(lottoTicket);
            LottoRank lottoRank = LottoRank.getRank(matchCount, containsBonusBall);
            plusOneCorrectAnswerNumbers(lottoRank);
        }
    }

    public int getCorrectAnswerNumbers(LottoRank lottoRank) {
        return lottoRankMap.get(lottoRank);
    }

    private void plusOneCorrectAnswerNumbers(LottoRank lottoRank) {
        lottoRankMap.put(lottoRank, lottoRankMap.get(lottoRank) + 1);
    }

    public long getTotalPrize() {
        return lottoRankMap.keySet().stream()
                .mapToLong((LottoRank lottoRank) ->
                        lottoRank.prizeMoney() * lottoRankMap.get(lottoRank))
                .sum();
    }

    public BigDecimal getEarningsRate(PurchaseAmount money) {
        BigDecimal totalPrize = new BigDecimal(getTotalPrize());
        BigDecimal amount = new BigDecimal(money.amount());
        return totalPrize.divide(amount, EARNING_RATE_PRECISIONS, RoundingMode.HALF_EVEN);
    }
}
