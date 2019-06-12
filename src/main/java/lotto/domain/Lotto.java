package lotto.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Lotto {
    private static final int SIZE_OF_LOTTO_NUMBERS = 6;
    private final List<LottoNumber> lottoNumbers;

    private Lotto(List<LottoNumber> lottoNumbers) {
        validateLottoNumbers(lottoNumbers);
        Collections.sort(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public static Lotto create(LottoNumbersGenerator lottoNumbersGenerator) {
        return new Lotto(lottoNumbersGenerator.generateNumbers());
    }

    private void validateLottoNumbers(List<LottoNumber> lottoNumbers) {
        if (hasDuplication(lottoNumbers)) {
            throw new DuplicatedLottoNumbersException("로또 번호가 중복되면 안 됩니다.");
        }
        if (lottoNumbers.size() != SIZE_OF_LOTTO_NUMBERS) {
            throw new InvalidSizeOfLottoNumbersException(String.format("로또 번호는 %d 개여야 합니다.", SIZE_OF_LOTTO_NUMBERS));
        }
    }

    private boolean hasDuplication(List<LottoNumber> lottoNumbers) {
        if ((new HashSet<>(lottoNumbers)).size() != lottoNumbers.size()) {
            return true;
        }
        return false;
    }

    public int countMatches(Lotto another) {
        int count = 0;
        for (LottoNumber lottoNumber : this.lottoNumbers) {
            count += another.lottoNumbers.contains(lottoNumber) ? 1 : 0;
        }
        return count;
    }

    public static int getSizeOfLottoNumbers() {
        return SIZE_OF_LOTTO_NUMBERS;
    }

    public boolean contains(LottoNumber another) {
        return lottoNumbers.contains(another);
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
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
        return String.valueOf(lottoNumbers);
    }
}
