package lotto.domain.generator;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.vo.LottoNumber;

public class ManualLottoGenerator implements LottoGenerator {

    private final List<List<LottoNumber>> numbers;

    public ManualLottoGenerator(List<List<LottoNumber>> numbers) {
        this.numbers = numbers;
    }

    @Override
    public List<Lotto> generateLottos() {
        return numbers.stream()
            .map(Lotto::of)
            .collect(Collectors.toList());
    }
}
