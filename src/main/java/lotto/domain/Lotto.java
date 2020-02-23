package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
    private Set<LottoNumber> lottoNumbers;
    private static final int LOTTO_NUMBER_SIZE = 6;

    Lotto(Set<LottoNumber> lottoNumbers) {
        validateLottoNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validateLottoNumbers(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException(String.format("로또 번호의 개수는 %d개여야 합니다", LOTTO_NUMBER_SIZE));
        }
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
