package domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Lotto {
    public static final int LOTTO_SIZE = 6;

    private final Set<LottoNumber> lotto;

    public Lotto(Set<LottoNumber> lottoNumbers) {
        checkLottoSize(lottoNumbers);
        this.lotto = new HashSet<>(lottoNumbers);
    }

    private void checkLottoSize(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또는 6개의 중복되지 않은 숫자로 이루어져 있어야 합니다.");
        }
    }

    public int calculateMatchCount(Lotto targetLotto) {
        return (int) lotto.stream()
                .filter(targetLotto::isContain)
                .count();
    }

    public boolean isContain(LottoNumber lottoNumber) {
        return lotto.contains(lottoNumber);
    }

    public Set<LottoNumber> getLotto() {
        return Collections.unmodifiableSet(lotto);
    }

    @Override
    public String toString() {
        return "추첨된 번호는 " + lotto + " 입니다.";
    }
}
