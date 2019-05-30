package lotto.domain.lotto;

import java.util.*;

public class LottoTicket {
    public static final int PRICE = 1000;
    static final int LOTTO_SIZE = 6;

    private final Set<LottoNumber> lottoNumbers;

    private LottoTicket(List<LottoNumber> numbers) {
        lottoNumbers = Collections.unmodifiableSet(new HashSet<>(numbers));
        checkDuplicate(numbers, lottoNumbers);
        checkLottoSize(lottoNumbers);
    }

    private void checkDuplicate(List<LottoNumber> numbers, Set<LottoNumber> lottoNumbers) {
        if (numbers.size() != lottoNumbers.size()) {
            throw new InvalidLottoTicketException("로또 번호는 중복이 불가능합니다.");
        }
    }

    private void checkLottoSize(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new InvalidLottoTicketException("로또는 6개의 숫자로 구성되어야합니다.");
        }
    }

    public static LottoTicket create() {
        return new LottoTicket(LottoNumbersGenerator.create());
    }

    public static LottoTicket create(List<Integer> lottoNumbers) {
        return new LottoTicket(LottoNumbersGenerator.create(lottoNumbers));
    }

    public int countOfMatch(LottoTicket lottoTicket) {
        return (int) lottoTicket.lottoNumbers.stream()
                .filter(x -> this.lottoNumbers.contains(x))
                .count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoTicket lotto = (LottoTicket) o;
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
