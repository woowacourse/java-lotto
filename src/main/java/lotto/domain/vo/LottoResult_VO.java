package lotto.domain.vo;

import lotto.domain.Price;
import lotto.domain.Rank;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class LottoResult_VO {
    private static final String RESULT_MESSAGE = "%d개 일치 (%d원) - %d개\n";
    private static final String RESULT_SECOND_MESSAGE = "%d개 일치, 보너스볼 일치 (%d원) - %d개\n";

    private final Map<Rank, Integer> result;
    private final Price price;
    private static final int PERSENT = 100;

    public LottoResult_VO(Map<Rank, Integer> result, Price price) {
        this.result = result;
        this.price = price;
    }

    public Map<Rank, Integer> getResult() {
        return result;
    }

    public List<String> getRank() {
        return result.keySet()
                .stream()
                .map(rank -> selectMessage(rank))
                .collect(toList());
    }

    private String selectMessage(Rank rank) {
        if (rank == Rank.MISS) {
            return "";
        }
        return String.format(rank != Rank.SECOND ? RESULT_MESSAGE : RESULT_SECOND_MESSAGE
                , rank.getCountOfMatch(), rank.getWinningMoney()
                , result.get(rank));
    }

    public int getPrize() {
        return result.keySet().stream()
                .map(rank -> rank.getWinningMoney() * result.get(rank))
                .reduce(0, Integer::sum);
    }

    public int dividendRate() {
        return (int) (getPrize() / price.getMoney() * PERSENT);
    }
}
