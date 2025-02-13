package lotto.domain;

import static lotto.common.Constants.LOTTO_NUM_SIZE;
import static lotto.common.Constants.MAX_LOTTO_NUMBER;

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
                    lottoGroup.add(Lotto.create(RandomNumberUtils.generateRandomNumbers(LOTTO_NUM_SIZE, MAX_LOTTO_NUMBER )
                            .stream()
                            .map(LottoNumber::new)
                            .toList()));
                });
    }

    public List<Lotto> getLottoGroup() {
        return lottoGroup;
    }
}
