package lotto.domain.generator;

import lotto.domain.LottoNumbers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoManualGenerator implements LottoGenerator {
    private static final String DUPLICATE_MANUAL_EXCEPTION_MESSAGE = "완전히 동일한 줄이 존재합니다.";

    @Override
    public List<LottoNumbers> generateLottoNumbersGroup(
            final int numberOfGenerating, final List<List<String>> lottoNumbers) {
        final Set<LottoNumbers> manualNumbersGroup = lottoNumbers.stream()
                .map(LottoNumbers::new)
                .collect(Collectors.toUnmodifiableSet());
        validateDuplicateManualNumbersGroup(lottoNumbers, manualNumbersGroup);
        return manualNumbersGroup.stream()
                .collect(Collectors.toUnmodifiableList());
    }

    private void validateDuplicateManualNumbersGroup(
            final List<List<String>> manualLottoNumbersGroup, final Set<LottoNumbers> manualNumbersGroup) {
        if (manualNumbersGroup.size() != manualLottoNumbersGroup.size()) {
            throw new IllegalArgumentException(DUPLICATE_MANUAL_EXCEPTION_MESSAGE);
        }
    }
}
