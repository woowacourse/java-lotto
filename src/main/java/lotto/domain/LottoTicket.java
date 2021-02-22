package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.exception.LottoCustomException;

public class LottoTicket {

    private static final int LOTTO_SIZE = 6;
    private static final String NOT_DUPLICATE_NUMBERS_ERROR_MESSAGE = "당첨번호는 중복되지 않은 숫자들로 총 " + LOTTO_SIZE + "개이어야 합니다.";

    private final Set<LottoNumber> lottoNumbers;

    public LottoTicket(final List<Integer> lottoNumbers) {
        this.lottoNumbers = makeTicket(lottoNumbers);
    }

    private Set<LottoNumber> makeTicket(List<Integer> numbers){
        validateSize(numbers);
        return numbers.stream()
            .map(LottoNumber::new)
            .collect(Collectors.toSet());
    }

    private void validateSize(List<Integer> numbers) {
        if (isNotProperSize(numbers)) {
            throw new LottoCustomException(NOT_DUPLICATE_NUMBERS_ERROR_MESSAGE);
        }
    }

    private boolean isNotProperSize(List<Integer> lottoNumbers) {
        return lottoNumbers.size() != LOTTO_SIZE;
    }

    public int countHits(LottoTicket winningTicket) {
        Set<LottoNumber> hitLottoNumbers = new HashSet<>(lottoNumbers);
        hitLottoNumbers.retainAll(winningTicket.lottoNumbers);
        return hitLottoNumbers.size();
    }

    public boolean hasNumber(LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    public Set<Integer> getLottoNumbers() {
        Set<Integer> numbers = new HashSet<>();
        lottoNumbers.forEach(lottoNumber -> numbers.add(lottoNumber.getNumber()));
        return numbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoTicket lottoTicket = (LottoTicket) o;
        return lottoNumbers.equals(lottoTicket.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
