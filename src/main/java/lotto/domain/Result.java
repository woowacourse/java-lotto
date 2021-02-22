package lotto.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class Result {
    private final Map<Rank, Integer> resultMap;
    private final BigInteger earningRate;

    public Result(String winnings, String bonus, LottoTickets lottoTickets) {
        this.resultMap = new EnumMap<>(Rank.class);

        setResultMap(new WinningNumbers(winnings, bonus), lottoTickets);
        BigDecimal totalPrize = getTotalPrize();

        this.earningRate = getEarningRate(lottoTickets.size() * LottoTicket.PRICE, totalPrize);
    }

    private void setResultMap(WinningNumbers winningNumbers, LottoTickets lottoTickets) {
        for (LottoTicket lottoTicket : lottoTickets.getLottoTickets()) {
            Rank rank = winningNumbers.getRank(lottoTicket);
            resultMap.put(rank, resultMap.getOrDefault(rank, 0) + 1);
        }
    }

    private BigDecimal getTotalPrize() {
        BigDecimal localPrize = BigDecimal.ZERO;

        for (Map.Entry<Rank, Integer> result : resultMap.entrySet()) {
            localPrize = localPrize.add(
                result.getKey().getPrize()
                    .multiply(BigDecimal.valueOf(result.getValue()))
            );
        }
        return localPrize;
    }

    private BigInteger getEarningRate(int buyPrice, BigDecimal prizePerRank) {
        return (prizePerRank
            .divide(BigDecimal.valueOf(buyPrice), 2, BigDecimal.ROUND_CEILING))
            .multiply(BigDecimal.valueOf(100)).toBigInteger();
    }

    public Map<Rank, Integer> getResultMap() {
        return Collections.unmodifiableMap(resultMap);
    }

    public BigInteger getEarningRate() {
        return earningRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Result result = (Result) o;
        return Objects.equals(resultMap, result.resultMap) && Objects
            .equals(earningRate, result.earningRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultMap, earningRate);
    }

}
