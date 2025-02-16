package lotto.domain;

import lotto.util.NumberGenerator;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static lotto.constant.ErrorMessage.INVALID_PRICE;

public class Lottos {
    public static final int DEFAULT_VALUE = 0;
    public static final int INCREASE_UNIT = 1;

    private final List<Lotto> lottos = new ArrayList<>();

    public Lottos(NumberGenerator generator, int payment) {
        validate(payment);

        int lottoCount = payment / Lotto.PRICE;
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(new Lotto(generator));
        }
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
