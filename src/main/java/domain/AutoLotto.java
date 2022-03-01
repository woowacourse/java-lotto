package domain;

import util.LottoNumberGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AutoLotto implements Lotto{

    private static final String DELIMITER = ", ";
    private static final String PREFIX = "[";
    private static final String SUFFIX = "]";

    private final List<LottoNumber> lottoNumbers;

    public AutoLotto(LottoNumberGenerator generatorPolicy) {
        this.lottoNumbers = new ArrayList<>(generatorPolicy.generate());
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
}
