package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lotto.domain.util.LottoGenerator;

public class Lottos {
    private final List<Lotto> lottos = new ArrayList<>();

    public Lottos(final LottoGenerator generator, final int payment) {
        validate(payment);
        int count = payment / Lotto.PRICE;
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(generator));
        }
    }

    private void validate(final int payment) {
        if (payment % Lotto.PRICE != 0) {
            throw new IllegalArgumentException(String.format("[ERROR] 구입 금액은 %,d원 단위로 입력해야 합니다.", Lotto.PRICE));
        }
    }

    public Map<Rank, Long> calculateWinnings(final WinningLotto winningLotto) {
        return lottos.stream()
                .map(winningLotto::calculateWinning)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public int size() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
