package model;

import java.util.Optional;
import java.util.Set;

public class Lotto {
    private final Set<Number> lottoNumbers;

    public Lotto(Set<Number> lottoNumbers) {
        if (lottoNumbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
        this.lottoNumbers = lottoNumbers;
    }

    public Set<Number> getLottoNumbers() {
        return lottoNumbers;
    }

    public Optional<Prize> calculatePrize(WinningLotto winningLotto) {
        boolean bonus = false;
        if (lottoNumbers.contains(winningLotto.getBonus())) {
            bonus = true;
        }
        Set<Number> winningNumbers = winningLotto.getLotto().getLottoNumbers();
        lottoNumbers.retainAll(winningNumbers);
        int matchCount = lottoNumbers.size();
        return Prize.findPrize(matchCount, bonus);
    }
}
