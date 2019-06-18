package lotto.domain;

import lotto.domain.autocreatelotto.AutoCreateLotto;
import lotto.domain.customcreatelotto.CustomCreateLotto;

import java.util.*;

/**
 * @author heebg
 * @version 1.0 2019-05-29
 */
public class Lotto implements Iterable<LottoNumber> {
    private static final int LOTTO_SIZE = 6;
    private static final String EX_LOTTO_SIZE_MESSAGE = "로또 숫자는 6개여야 합니다.";
    private static final String EX_LOTTO_DUPLICATE_MESSAGE = "중복된 숫자는 사용할 수 없습니다";
    private final List<LottoNumber> lotto;

    private Lotto(List<LottoNumber> lotto) {
        this.lotto = lotto;
        checkLottoNumberCondition();
    }

    public static Lotto customLotto(List<Integer> noFormedLotto, CustomCreateLotto customCreateLotto) {
        return new Lotto(customCreateLotto.custom(noFormedLotto));
    }

    public static Lotto createLotto(AutoCreateLotto autoCreateLotto) {
        return new Lotto(autoCreateLotto.autoCreate());
    }

    public static Lotto createLotto(List<LottoNumber> lottoNumbers) {
        return new Lotto(lottoNumbers);
    }

    private void checkLottoNumberCondition() {
        checkSize();
        checkDuplicate();
    }

    private void checkSize() {
        if (lotto.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(EX_LOTTO_SIZE_MESSAGE);
        }
    }

    private void checkDuplicate() {
        if (lotto.size() != new ArrayList<>(new HashSet<>(lotto)).size()) {
            throw new IllegalArgumentException(EX_LOTTO_DUPLICATE_MESSAGE);
        }
    }

    public int matchCount(Lotto lotto) {
        int match = 0;
        for (LottoNumber lottoNumber : lotto) {
            match = generateMatch(match, lottoNumber);
        }
        return match;
    }

    private int generateMatch(int match, LottoNumber lottoNumber) {
        if (contains(lottoNumber)) {
            return match + 1;
        }
        return match;
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lotto.contains(lottoNumber);
    }

    public String toStringWithFormat(String startSymbol, String endSymbol, String separator) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(startSymbol);
        StringJoiner stringJoiner = new StringJoiner(separator);
        for (LottoNumber lottoNumber : lotto) {
            stringJoiner.add(lottoNumber.toString());
        }
        stringBuilder.append(stringJoiner).append(endSymbol);
        return stringBuilder.toString();
    }

    public LottoNumber get(int index) {
        return lotto.get(index);
    }

    @Override
    public Iterator iterator() {
        return lotto.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(this.lotto, lotto.lotto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotto);
    }

    @Override
    public String toString() {
        return String.valueOf(lotto);
    }
}
