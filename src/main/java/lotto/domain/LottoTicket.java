package lotto.domain;

import java.util.*;

public class LottoTicket {
    private static final int LOTTO_SIZE = 6;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private final Set<Integer> lottoNumbers;

    public LottoTicket(List<Integer> numbers) {
        checkRange(numbers);
        lottoNumbers = Collections.unmodifiableSet(new HashSet<>(numbers));
        checkDuplicate(numbers, lottoNumbers);
        checkLottoSize(lottoNumbers);
    }

    private void checkRange(List<Integer> numbers) {
        if (numbers.stream().filter(x -> x < MIN_LOTTO_NUMBER || x > MAX_LOTTO_NUMBER).count() != 0) {
            throw new InvalidLottoTicketException("로또 번호의 범위에 벗어납니다.");
        }
    }

    private void checkDuplicate(List<Integer> numbers, Set<Integer> lottoNumbers) {
        if (numbers.size() != lottoNumbers.size()) {
            throw new InvalidLottoTicketException("로또 번호는 중복이 불가능합니다.");
        }
    }

    private void checkLottoSize(Set<Integer> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new InvalidLottoTicketException("로또는 6개의 숫자로 구성되어야합니다.");
        }
    }

    public static LottoTicket create(List<Integer> numbers) {
        return new LottoTicket(numbers);
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
}
