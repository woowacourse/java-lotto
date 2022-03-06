package domain;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ManualLottoNumberGenerator implements LottoNumbersGenerator {

    private final List<Integer> numbers;

    public ManualLottoNumberGenerator(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public Set<LottoNumber> generate() {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());
        return new TreeSet<>(lottoNumbers);
    }
}
