package domain.result;

import domain.lotto.Lotto;
import domain.lotto.WinNumbers;
import exception.lotto.LottosNullException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Result {
    private final LinkedHashMap<Rank, Integer> result = new LinkedHashMap<>();

    private Result(List<Lotto> lottos, WinNumbers winNumbers) {
        validate(lottos);
        for (Lotto lotto : lottos) {
            Rank rank = Rank.of(lotto.countSameNum(winNumbers), lotto.contains(winNumbers.getBonus()));
            add(rank);
        }
    }

    public static Result of(List<Lotto> lottos, WinNumbers winNumbers) {
        return new Result(lottos, winNumbers);
    }

    private void validate(List<Lotto> lottos) {
        if (lottos == null || lottos.size() == 0) {
            throw new LottosNullException();
        }
    }

    private void add(final Rank rank) {
        result.put(rank, result.getOrDefault(rank, 0) + 1);
    }

    public Map<Rank, Integer> get() {
        return result;
    }

    public long getPrize() {
        long prize = 0;
        for (Rank rank : result.keySet()) {
            prize += (long) rank.getPrize() * result.get(rank);
        }
        return prize;
    }

    public int getRankCount(Rank rank) {
        return result.getOrDefault(rank, 0);
    }
}