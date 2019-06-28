package lotto.domain;

import lotto.dto.LottoDTO;

import java.util.*;

public class Lotto {
    private static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        validateDuplication(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validateDuplication(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> nonDuplicatedLottoNumbers = new HashSet<>(lottoNumbers);
        if (nonDuplicatedLottoNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("숫자가 중복 입력되었거나, 로또의 숫자가 6개가 아닙니다.");
        }
    }

    boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    int compareTo(Lotto lotto) {
        int countOfMatch = 0;
        for (LottoNumber lottoNumber : lottoNumbers) {
            countOfMatch += (lotto.contains(lottoNumber))? 1 : 0;
        }
        return countOfMatch;
    }

    public int getLottoNumber(int index) {
        return lottoNumbers.get(index).getNumber();
    }

    public LottoDTO toDTO(int round) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < LOTTO_SIZE; i++) {
            numbers.add(getLottoNumber(i));
        }
        LottoDTO lottoDTO = new LottoDTO(round, numbers);

        return lottoDTO;
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
        return lottoNumbers.toString();
    }
}
