package lotto.domain;

import java.util.*;

public class LottoTicket {
    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != AutoLotto.MAX_LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호의 개수가 6개가 아닙니다.");
        }
        if (isOverlap(lottoNumbers)) {
            throw new IllegalArgumentException("중복되는 로또 번호가 있습니다.");
        }
        this.lottoNumbers = lottoNumbers;
    }

    private boolean isOverlap(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> checkLottoNumbers = new HashSet<>(lottoNumbers);
        return checkLottoNumbers.size() != AutoLotto.MAX_LOTTO_SIZE;
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoTicket that = (LottoTicket) o;
        return Objects.equals(lottoNumbers, that.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        for (LottoNumber number : lottoNumbers) {
            stringJoiner.add(number.toString());
        }
        return stringJoiner.toString();
    }
}
