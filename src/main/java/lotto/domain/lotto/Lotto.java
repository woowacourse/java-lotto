package lotto.domain.lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Lotto {
    private static final String DUPLICATED_NUMBER_ERROR = "중복 되는 번호가 존재합니다.";
    private static final String LOTTO_NUMBER_SIZE_ERROR = "로또 번호가 6개가 아닙니다.";
    private static final String DELIMITER = ",";
    private static final String PREFIX = "[";
    private static final String SUFFIX = "]";

    private List<Integer> lottoNumbers;

    private Lotto(List<Integer> lottoNumbers) {
        checkLottoNumberCount(lottoNumbers);
        checkDuplicateNumber(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void checkDuplicateNumber(List<Integer> lottoNumbers) {
        if (lottoNumbers.size() != new HashSet<>(lottoNumbers).size()) {
            throw new InvalidLottoException(DUPLICATED_NUMBER_ERROR);
        }
    }

    private void checkLottoNumberCount(List<Integer> lottoNumbers) {
        if (lottoNumbers.size() != 6) {
            throw new InvalidLottoException(LOTTO_NUMBER_SIZE_ERROR);
        }
    }

    public static Lotto of(List<Integer> lottoNumbers) {
        return new Lotto(lottoNumbers);
    }

    public boolean containNumber(int value) {
        return lottoNumbers.contains(value);
    }

    public int sameNumberCount(Lotto lotto) {
        int count = 0;
        for (Integer lottoNumber : lottoNumbers) {
            count += containNumber(lotto, lottoNumber);
        }
        return count;
    }

    private int containNumber(Lotto lotto, int lottoNumber) {
        if (lotto.containNumber(lottoNumber)) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(lottoNumbers, lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(DELIMITER, PREFIX, SUFFIX);
        for (Integer lottoNumber : lottoNumbers) {
            stringJoiner.add(String.valueOf(lottoNumber));
        }
        return stringJoiner.toString();
    }
}
