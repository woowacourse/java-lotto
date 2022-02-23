package lotto.domain.generator;

import lotto.domain.LottoNumbers;
import lotto.domain.vo.LottoNumber;

import java.util.*;
import java.util.stream.Collectors;

public class LottoGenerator implements Generator {
    private static final int BONUS_NUMBER_INDEX = 6;

    private final List<LottoNumber> basicNumbers = Arrays.asList(LottoNumber.values());

    public List<LottoNumbers> generateLottoNumbersGroup(final int numberOfGenerating) {
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
                .limit(BONUS_NUMBER_INDEX)
                .collect(Collectors.toUnmodifiableSet());
        return new LottoNumbers(generatedLottoNumbers);
    }
}
