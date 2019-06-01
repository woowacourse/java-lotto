package lotto.domain.lottoTicket;

import lotto.domain.LottoNumber;

import java.util.*;

public abstract class Lotto {
    public static final int MAX_LOTTO_SIZE = 6;
    private static final int SUM_OF_USER_WINNING = 12;
    private static final String NEW_LINE = "\n";

    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != MAX_LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호의 개수는 6개 입니다.");
        }
        if (isOverlap(lottoNumbers)) {
            throw new IllegalArgumentException("중복되는 로또 번호가 있습니다.");
        }
        this.lottoNumbers = lottoNumbers;
    }

    private static boolean isOverlap(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> checkLottoNumbers = new HashSet<>(lottoNumbers);
        return checkLottoNumbers.size() != MAX_LOTTO_SIZE;
    }

    public int matchLottoNumbers(Lotto lotto) {
        Set<LottoNumber> checkLottoNumbers = new HashSet<>(lottoNumbers);
        checkLottoNumbers.addAll(lotto.lottoNumbers);
        return SUM_OF_USER_WINNING - checkLottoNumbers.size();
    }

    public boolean isContainNumber(LottoNumber bonus) {
        return lottoNumbers.contains(bonus);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto that = (Lotto) o;
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
        return stringJoiner.toString() + NEW_LINE;
    }
}
