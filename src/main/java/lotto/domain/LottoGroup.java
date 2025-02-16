package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class LottoGroup {
    private final List<Lotto> lottoGroup = new ArrayList<>();

    public void processLottoTicketGeneration(Money money, LottoGenerator lottoGenerator) {
        // TODO 통합 테스트 불가능
        IntStream.range(0, money.getLottoTicketCount())
                .forEach(index -> lottoGroup.add(
                        new Lotto(
                                new LottoNumbers(
                                        lottoGenerator.generateRandomNumbers()
                                                .stream()
                                                .map(LottoNumber::new)
                                                .toList()))));
    }

    public List<Lotto> getLottoGroup() {
        return lottoGroup;
    }
}
