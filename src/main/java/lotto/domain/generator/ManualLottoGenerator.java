package lotto.domain.generator;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.vo.LottoNumber;

public class ManualLottoGenerator implements LottoGenerator {

    private final List<List<Integer>> numbers;

    public ManualLottoGenerator(List<List<Integer>> numbers) {
        this.numbers = numbers;
    }

    @Override
    public List<Lotto> generateLottos() {
        return numbers.stream()
            .map(this::numbersToLotto)
            .collect(Collectors.toList());
    }

    private Lotto numbersToLotto(List<Integer> numbers) {
        return Lotto.of(numbers.stream()
            .map(LottoNumber::of)
            .collect(Collectors.toList()));
    }

}
