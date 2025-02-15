package lotto.domain;

import static lotto.constant.ErrorMessage.INVALID_PRICE;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lotto.util.NumberGenerator;

public class Lottos {
    private final List<Lotto> lottos = new ArrayList<>();

    public Lottos(NumberGenerator generator, int payment) {
        validate(payment);
        int count = payment / Lotto.PRICE;
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(generator));
        }
    }

    private void validate(int payment) {
        if (payment % Lotto.PRICE != 0) {
            throw new IllegalArgumentException(String.format(INVALID_PRICE.getMessage(), Lotto.PRICE));
        }
    }

    public Map<Rank, Long> getRankCount(WinningNumbers winningNumbers) {
        return lottos.stream()
                .map(winningNumbers::getRank)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public int getTicketCount() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
