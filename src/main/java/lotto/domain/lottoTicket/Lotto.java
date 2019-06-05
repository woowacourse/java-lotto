package lotto.domain.lottoTicket;

import lotto.domain.LottoNumber;
import lotto.domain.exception.LottoNumberSizeException;
import lotto.domain.exception.OverlapLottoNumberException;

import java.util.*;

public class Lotto {
    public static final int MAX_LOTTO_SIZE = 6;
    private static final int ORIGINAL_SIZE = 12;
    private static final String NEW_LINE = "\n";

    private final Set<LottoNumber> lottoNumbers;

    public Lotto(List<Integer> lottoNumbers) {
        this(convertLottoNumbers(lottoNumbers));
    }

    public Lotto(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != MAX_LOTTO_SIZE) {
            throw new LottoNumberSizeException("로또 번호의 개수는 6개 입니다.");
        }
        this.lottoNumbers = lottoNumbers;
    }

    public static Set<LottoNumber> convertLottoNumbers(List<Integer> userNumbers) {
        Set<LottoNumber> lottoNumbers = new TreeSet<>();
        userNumbers.stream().forEach(number -> lottoNumbers.add(new LottoNumber(number)));
        checkOverlap(userNumbers, lottoNumbers);
        return lottoNumbers;
    }

    private static void checkOverlap(List<Integer> userNumbers, Set<LottoNumber> convertNumbers) {
        if (userNumbers.size() != convertNumbers.size()) {
            throw new OverlapLottoNumberException("중복되는 로또 번호가 있습니다.");
        }
    }

    public int matchLottoNumbers(Lotto winningLotto) {
        Set<LottoNumber> checkLottoNumbers = new HashSet<>(lottoNumbers);
        checkLottoNumbers.addAll(winningLotto.lottoNumbers);
        return ORIGINAL_SIZE - checkLottoNumbers.size();
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
