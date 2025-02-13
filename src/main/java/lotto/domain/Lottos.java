package lotto.domain;

import lotto.util.NumberGenerator;
import lotto.util.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static lotto.constant.ErrorMessage.INVALID_PRICE;

public class Lottos {
    public static final int DEFAULT_VALUE = 0;
    public static final int INCREASE_UNIT = 1;

    private final List<Lotto> lottos = new ArrayList<>();
    private final NumberGenerator generator = new RandomNumberGenerator();

    public Lottos(int payment) {
        validate(payment);

        for (int i = 0; i < payment / Lotto.PRICE; i++) {
            lottos.add(new Lotto(generator));
        }
    }

    public Lottos(List<Lotto> lottos) {
        this.lottos.addAll(lottos);
    }

    private void validate(int payment) {
        if (payment % Lotto.PRICE != 0) {
            throw new IllegalArgumentException(INVALID_PRICE.getMessage());
        }
    }

    public Map<Rank, Integer> getRankCount(WinningNumbers winningNumbers) {
        Map<Rank, Integer> rankCount = new EnumMap<>(Rank.class);
        lottos.forEach(lotto ->
            rankCount.put(
                winningNumbers.getRank(lotto),
                rankCount.getOrDefault(winningNumbers.getRank(lotto), DEFAULT_VALUE) + INCREASE_UNIT));
        return rankCount;
    }

    public int getTicketCount() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
