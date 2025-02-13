package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import lotto.utils.RandomNumberUtils;

public class LottoGroup {
    private final List<Lotto> lottoGroup = new ArrayList<>();

    public void generate(Money money) {
        // TODO 통합 테스트 불가능
        IntStream.range(0, money.getLottoTicketCount())
                .forEach(index -> {
                    lottoGroup.add(Lotto.create(RandomNumberUtils.generateRandomNumbers()
                            .stream()
                            .map(LottoNumber::new)
                            .toList()));
                });
    }

    public List<Lotto> getLottoGroup() {
        return lottoGroup;
    }
}
