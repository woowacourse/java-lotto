package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
    private Set<LottoNumber> lottoNumbers;

    Lotto(Set<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }
    int matchWinningNumbers(Set<LottoNumber> winningNumbers) {
        return (int)lottoNumbers.stream()
            .filter(lottoNumber -> winningNumbers.contains(lottoNumber))
            .count();
    }

    public boolean matchBonusNumber(LottoNumber bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }

    @Override
    public String toString() {
        List<String> stringifiedLottoNumbers = lottoNumbers.stream()
            .map(lottoNumber -> lottoNumber.toString())
            .collect(Collectors.toList());

        return "[" + String.join(",", stringifiedLottoNumbers) + "]";
    }
}
