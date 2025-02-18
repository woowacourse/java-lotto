package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class LottoGroup {
    private final List<Lotto> lottoGroup = new ArrayList<>();

    public void processLottoTicketGeneration(Money money, LottoGenerator lottoGenerator) {

        IntStream.range(0, money.getLottoTicketCount())
                .forEach(index -> lottoGroup.add(generateLotto(lottoGenerator)));
    }

    public Lotto generateLotto(LottoGenerator lottoGenerator) {
        final List<Integer> lottoNumbers = lottoGenerator.generateRandomNumbers();
        return new Lotto(generateLottoNumbers(lottoNumbers));
    }

    public LottoNumbers generateLottoNumbers(List<Integer> lottoNumbers) {
        return new LottoNumbers(lottoNumbers.stream().map(LottoNumber::new).toList());
    }

    public List<Lotto> getLottoGroup() {
        return lottoGroup;
    }
}
