package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    private static final String DELIMITER = ", ";
    private static final String PREFIX = "[";
    private static final String SUFFIX = "]";

    private final List<LottoNumber> lottoNumbers = new ArrayList<>();

    public Lotto(List<LottoNumber> lottoNumbers) {
        validateDuplicatedNumber(lottoNumbers);
        this.lottoNumbers.addAll(lottoNumbers);
    }

    private void validateDuplicatedNumber(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != Set.copyOf(lottoNumbers).size()) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        List<String> numbersToStrings = lottoNumbers.stream()
                .map(String::valueOf)
                .collect(Collectors.toList());
        return PREFIX + String.join(DELIMITER, numbersToStrings) + SUFFIX;
    }

    public int countSameNumbers(List<LottoNumber> winningNumbers) {
        return (int) lottoNumbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public boolean checkBonus(LottoNumber bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }
}
