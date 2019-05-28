package lotto.domain;

import java.util.*;

public class Lotto {
    private List<Integer> lottoNumbers;

    private Lotto(List<Integer> lottoNumbers) {
        checkLottoNumberCount(lottoNumbers);
        checkDuplicateNumber(lottoNumbers);
        sortAscending(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void checkDuplicateNumber(List<Integer> lottoNumbers) {
        if (lottoNumbers.size() != new HashSet<>(lottoNumbers).size()) {
            throw new IllegalArgumentException("중복 되는 번호가 존재합니다.");
        }
    }

    private void checkLottoNumberCount(List<Integer> lottoNumbers) {
        if (lottoNumbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호가 6개가 아닙니다.");
        }
    }

    private void sortAscending(List<Integer> lottoNumbers) {
        Collections.sort(lottoNumbers);
    }

    public static Lotto of(List<Integer> lottoNumbers) {
        return new Lotto(lottoNumbers);
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
        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        for (Integer lottoNumber : lottoNumbers) {
            stringJoiner.add(String.valueOf(lottoNumber));
        }
        return stringJoiner.toString();
    }
}
