package domain;

import java.util.*;

public class Lotto {
    private static final int LOTTO_SIZE = 6;
    private final List<LottoNumber> lotto;

    public Lotto(final List<LottoNumber> lotto) {
        checkLottoSizeSix(lotto.size());
        checkLottoNumberRange(lotto);
        checkDuplicatedLottoNumbers(lotto);
        Collections.sort(lotto);
        this.lotto = lotto;
    }

    private void checkLottoNumberRange(final List<LottoNumber> lotto) {
        for (LottoNumber number : lotto) {
            LottoNumber.checkLottoNumberNull(number);
        }
    }

    private void checkLottoSizeSix(final int size) {
        if (size != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또의 번호는 6개의 숫자로 이루어져 있어야 합니다.");
        }
    }

    private void checkDuplicatedLottoNumbers(final List<LottoNumber> lotto) {
        Set<LottoNumber> lottoSet = new HashSet<>(lotto);
        if (lottoSet.size() != lotto.size()) {
            throw new IllegalArgumentException("중복된 로또 번호가 입력되었습니다.");
        }
    }

    public int countMatchNumbers(final Lotto targetLotto) {
        return (int) this.lotto.stream()
                .filter(targetLotto::contains)
                .count();
    }

    public boolean contains(final LottoNumber number) {
        return lotto.contains(number);
    }

    public List<LottoNumber> getLotto() {
        return lotto;
    }
}
