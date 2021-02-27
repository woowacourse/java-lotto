package domain;

import java.util.*;
import java.util.stream.Collectors;

public class Lotto {
    protected static final int PRICE = 1_000;
    protected static final int LENGTH = 6;

    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        validateLottoNumbers(lottoNumbers);
        Collections.sort(lottoNumbers);
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
    }

    protected Lotto(int[] lottoNumbers) {
        this(Arrays.stream(lottoNumbers)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList()));
    }

    private void validateLottoNumbers(List<LottoNumber> lottoNumbers) {
        validateDuplicatedLottoNumbers(lottoNumbers);
        validateLottoNumbersLength(lottoNumbers);
    }

    private void validateDuplicatedLottoNumbers(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> distinctLottoNumbers = new HashSet<>(lottoNumbers);
        if (distinctLottoNumbers.size() != lottoNumbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    private void validateLottoNumbersLength(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LENGTH) {
            throw new IllegalArgumentException("로또 번호의 개수는 6자리입니다.");
        }
    }

    public static int calculateLottoNumber(Money money) {
        return money.divide(PRICE);
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.stream()
                .anyMatch(lottoNumber::equals);
    }

    public int findMatchCount(Lotto targetLotto) {
        return (int) this.lottoNumbers.stream()
                .filter(targetLotto::contains)
                .count();
    }

    public List<LottoNumber> toList() {
        return Collections.unmodifiableList(lottoNumbers);
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
}
