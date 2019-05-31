package lotto.domain;

import java.util.*;

public class LottoNumbers {
    private static final int NUMBER_OF_LOTTO_NUMBERS = 6;

    private final List<LottoNumber> lottoNumbers;

    LottoNumbers(final List<LottoNumber> lottoNumbers) {
        checkDuplicate(lottoNumbers);
        checkNumberOfLottoNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void checkDuplicate(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> set = new HashSet<>(lottoNumbers);
        if (set.size() != lottoNumbers.size()) {
            throw new IllegalArgumentException("중복된 수로 로또를 생성할 수 없습니다.");
        }
    }

    private void checkNumberOfLottoNumbers(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != NUMBER_OF_LOTTO_NUMBERS) {
            throw new IllegalArgumentException("6개의 숫자로만 로또를 생성할 수 있습니다.");
        }
    }

    List<Integer> getLottoNumbers() {
        List<Integer> lottoNumbers = new ArrayList<>();
        for (LottoNumber lottoNumber : this.lottoNumbers) {
            lottoNumbers.add(lottoNumber.getNumber());
        }
        return lottoNumbers;
    }

    boolean hasLottoNumber(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    int hasLottoNumber(LottoNumbers lottoNumbers) {
        int result = 0;
        for (LottoNumber lottoNumber : lottoNumbers.lottoNumbers) {
            result += contains(lottoNumber);
        }
        return result;
    }

    /**
     * lottoNumber가 포함되어 있는지 판단하는 메서드
     * @param lottoNumber 포함되어있는지 확인하려는 로또 번호
     * @return 포함되어있으면 1, 없으면 0을 반환
     */
    private int contains(LottoNumber lottoNumber) {
        if (this.lottoNumbers.contains(lottoNumber)) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumbers that = (LottoNumbers) o;
        return Objects.equals(lottoNumbers, that.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
