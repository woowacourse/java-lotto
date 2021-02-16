package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoNumbers {
    private List<LottoNumber> lottoNumbers;

    public LottoNumbers(List<Integer> randomNumbers) {
        validateLottoNumberCount(randomNumbers);
        validateDuplicatedLottoNumbers(randomNumbers);

        this.lottoNumbers = randomNumbers.stream().map(LottoNumber::new)
            .collect(Collectors.toList());
    }

    private void validateLottoNumberCount(List<Integer> randomNumbers) {
        if (randomNumbers.size() != 6) {
            throw new IllegalArgumentException();
        }
    }

    private void validateDuplicatedLottoNumbers(List<Integer> randomNumbers) {
        Set<Integer> duplicateCheck = new HashSet(randomNumbers);
        if (randomNumbers.size() != duplicateCheck.size()) {
            throw new IllegalArgumentException();
        }
    }

    public List<LottoNumber> list() {
        return lottoNumbers;
    }
}
