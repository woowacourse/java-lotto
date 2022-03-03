package domain.result;

import domain.lotto.Lotto;
import domain.lotto.WinNumbers;
import exception.NullException;
import java.util.LinkedHashMap;
import java.util.List;

public class Result {
    private final LinkedHashMap<Rank, Integer> result = new LinkedHashMap<>();

    private Result(final List<Lotto> lottos, final WinNumbers winNumbers) {
        validate(lottos);
        for (Lotto lotto : lottos) {
            Rank rank = Rank.of(lotto.countSameNum(winNumbers), lotto.contains(winNumbers.getBonus()));
            add(rank);
        }
    }

    public static Result of(final List<Lotto> lottos, final WinNumbers winNumbers) {
        return new Result(lottos, winNumbers);
    }

    private void validate(final List<Lotto> lottos) {
        if (lottos.size() == 0) {
            throw new NullException();
        }
    }

    private void add(final Rank rank) {
        result.put(rank, result.getOrDefault(rank, 0) + 1);
    }

    public long getPrize() {
        long prize = 0;
        for (Rank rank : result.keySet()) {
            prize += (long) rank.getPrize() * result.get(rank);
        }
        return prize;
    }

    public int getRankCount(final Rank rank) {
        return result.getOrDefault(rank, 0);
    }
}