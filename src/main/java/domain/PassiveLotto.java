package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PassiveLotto implements Lotto{

    private static final String DELIMITER = ", ";
    private static final String PREFIX = "[";
    private static final String SUFFIX = "]";

    private final List<LottoNumber> lottoNumbers;

    public PassiveLotto(List<LottoNumber> lottoNumbers) {
        validateDuplicatedNumber(lottoNumbers);
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
    }

    @Override
    public String toString() {
        List<String> numbersToStrings = lottoNumbers.stream()
                .map(String::valueOf)
                .collect(Collectors.toList());
        return PREFIX + String.join(DELIMITER, numbersToStrings) + SUFFIX;
    }

    @Override
    public int countSameNumbers(List<LottoNumber> winningNumbers) {
        return (int) lottoNumbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    @Override
    public boolean checkBonus(LottoNumber bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }

    private void validateDuplicatedNumber(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != Set.copyOf(lottoNumbers).size()) {
            throw new IllegalArgumentException();
        }
    }
}
