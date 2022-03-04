package domain.result;

import domain.lotto.Lotto;
import domain.lotto.WinningLotto;
import exception.NullException;
import java.util.LinkedHashMap;
import java.util.List;

public class Result {
    private final LinkedHashMap<Rank, Integer> value = new LinkedHashMap<>();

    private Result(final List<Lotto> lottos, final WinningLotto winningLotto) {
        validate(lottos);
        for (Lotto lotto : lottos) {
            Rank rank = Rank.of(lotto.countSameNum(winningLotto), lotto.contains(winningLotto.getBonusNumber()));
            add(rank);
        }
    }

    public static Result of(final List<Lotto> lottos, final WinningLotto winningLotto) {
        return new Result(lottos, winningLotto);
    }

    private void validate(final List<Lotto> lottos) {
        if (lottos.size() == 0) {
            throw new NullException();
        }
    }

    private void add(final Rank rank) {
        value.put(rank, value.getOrDefault(rank, 0) + 1);
    }

    public long getPrize() {
        long prize = 0;
        for (Rank rank : value.keySet()) {
            prize += (long) rank.getPrize() * value.get(rank);
        }
        return prize;
    }

    public int getRankCount(final Rank rank) {
        return value.getOrDefault(rank, 0);
    }
}