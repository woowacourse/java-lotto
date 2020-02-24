package lotto.domain;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
    private Set<LottoNumber> lottoNumbers;
    private static final int LOTTO_NUMBER_SIZE = 6;

    public Lotto(Set<LottoNumber> lottoNumbers) {
        validateLottoNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public Set<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    public static Lotto createWinningLotto(List<Integer> winningNumbers) {
        return new Lotto(winningNumbers.stream()
            .map(integer -> new LottoNumber(integer))
            .collect(Collectors.toSet()));
    }

    private void validateLottoNumbers(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException(String.format("로또 번호의 개수는 %d개여야 합니다", LOTTO_NUMBER_SIZE));
        }
    }

    int matchWinningNumbers(Lotto winningLotto) {
        return (int)lottoNumbers.stream()
            .filter(lottoNumber -> winningLotto.lottoNumbers.contains(lottoNumber))
            .count();
    }

    public boolean matchBonusNumber(LottoNumber bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }
}
