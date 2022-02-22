package lotto.domain;

import lotto.domain.vo.LottoNumber;

import java.util.*;
import java.util.stream.Collectors;

public class LottoGenerator {
    private final List<LottoNumber> basicNumbers = Arrays.asList(LottoNumber.values());

    public List<LottoNumbers> generate(final int numberOfGenerating) {
        final Set<LottoNumbers> generatedLottoNumbersGroup = new HashSet<>();
        while (generatedLottoNumbersGroup.size() < numberOfGenerating) {
            generatedLottoNumbersGroup.add(generateLottoNumbers());
        }
        return generatedLottoNumbersGroup.stream()
                .collect(Collectors.toUnmodifiableList());
    }

    private LottoNumbers generateLottoNumbers() {
        Collections.shuffle(basicNumbers);
        final Set<LottoNumber> generatedLottoNumbers = basicNumbers.stream()
                .limit(6)
                .collect(Collectors.toUnmodifiableSet());
        return new LottoNumbers(generatedLottoNumbers);
    }
}
