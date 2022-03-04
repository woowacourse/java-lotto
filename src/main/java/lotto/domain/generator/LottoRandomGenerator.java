package lotto.domain.generator;

import lotto.domain.LottoNumbers;
import lotto.domain.vo.LottoNumber;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoRandomGenerator implements LottoGenerator {
    private static final int LAST_LOTTO_NUMBER = 45;
    private static final int BONUS_NUMBER_INDEX = 6;

    private final List<LottoNumber> basicNumbers;

    public LottoRandomGenerator() {
        basicNumbers = IntStream.range(0, LAST_LOTTO_NUMBER)
                .mapToObj(index -> LottoNumber.from(String.valueOf(index + 1)))
                .collect(Collectors.toList());
    }

    @Override
    public List<LottoNumbers> generateLottoNumbersGroup(
            final int numberOfGenerating, final List<List<String>> lottoNumbers) {
        final Set<LottoNumbers> generatedNumbersGroup = new HashSet<>();
        while (generatedNumbersGroup.size() < numberOfGenerating) {
            generatedNumbersGroup.add(generateNumbers());
        }
        return generatedNumbersGroup.stream()
                .collect(Collectors.toUnmodifiableList());
    }

    private LottoNumbers generateNumbers() {
        Collections.shuffle(basicNumbers);
        final Set<LottoNumber> generatedNumbers = basicNumbers.stream()
                .limit(BONUS_NUMBER_INDEX)
                .collect(Collectors.toUnmodifiableSet());
        return new LottoNumbers(generatedNumbers);
    }
}
