package lotto.domain;

import lotto.constant.ErrorMessage;
import lotto.util.NumberGenerator;
import lotto.util.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Lottos {
    private final List<Lotto> lottos = new ArrayList<>();
    private final NumberGenerator generator = new RandomNumberGenerator();

    public Lottos(int payment) {
        if (payment % Lotto.PRICE != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PRICE.getMessage());
        }

        for (int i = 0; i < payment / Lotto.PRICE; i++) {
            lottos.add(new Lotto(generator));
        }
    }

    public Lottos(NumberGenerator generator, int payment) {
        if (payment % Lotto.PRICE != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PRICE.getMessage());
        }

        for (int i = 0; i < payment / Lotto.PRICE; i++) {
            lottos.add(new Lotto(generator));
        }
    }

    public int getTicketCount() {
        return lottos.size();
    }

    public Map<Rank, Integer> getRankCount(WinningNumbers winningNumbers) {
        Map<Rank, Integer> rankCount = new EnumMap<>(Rank.class);
        lottos.forEach(lotto ->
            rankCount.put(
                winningNumbers.getRank(lotto),
                rankCount.getOrDefault(winningNumbers.getRank(lotto), 0) + 1));
        return rankCount;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
