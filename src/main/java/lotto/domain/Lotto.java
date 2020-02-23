package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
    private Set<Integer> lottoNumbers;

    Lotto(Set<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }
    int matchWinningNumbers(Set<Integer> winningNumbers) {
        return (int)lottoNumbers.stream()
            .filter(lottoNumber -> winningNumbers.contains(lottoNumber))
            .count();
    }

    public boolean matchBonusBall(int bonusBall) {
        return lottoNumbers.contains(bonusBall);
    }

    @Override
    public String toString() {
        List<String> stringifiedLottoNumbers = lottoNumbers.stream()
            .map(lottoNumber -> lottoNumber.toString())
            .collect(Collectors.toList());

        return "[" + String.join(",", stringifiedLottoNumbers) + "]";
    }
}
