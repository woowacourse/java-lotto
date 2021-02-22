package lotto.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.domain.number.LottoNumber;
import lotto.domain.number.LottoNumberFactory;

public class LottoTicket {
    public static final int SIZE_OF_LOTTO_NUMBERS = 6;

    private final Set<LottoNumber> lottoNumbers;

    public LottoTicket(List<Integer> numbers) {
        validateLottoNumberCount(numbers);
        validateDuplicatedLottoNumbers(numbers);

        this.lottoNumbers = numbers.stream()
            .map(LottoNumberFactory::getInstance)
            .sorted(Comparator.naturalOrder())
            .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private void validateLottoNumberCount(List<Integer> numbers) {
        if (numbers.size() != SIZE_OF_LOTTO_NUMBERS) {
            throw new IllegalArgumentException(
                String.format("로또 번호는 %d개여야 합니다. 현재 개수 : %d", SIZE_OF_LOTTO_NUMBERS, numbers.size())
            );
        }
    }

    private void validateDuplicatedLottoNumbers(List<Integer> numbers) {
        Set<Integer> duplicateCheck = new HashSet<>(numbers);
        if (numbers.size() != duplicateCheck.size()) {
            throw new IllegalArgumentException("로또 번호가 중복되었습니다.");
        }
    }

    public int compareWinningNumber(LottoTicket winningTicket) {
        return (int) lottoNumbers.stream()
            .filter(winningTicket::containsLottoNumber)
            .count();
    }

    private boolean containsLottoNumber(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public boolean containsAnyNumber(LottoNumber lottoNumber) {
        return lottoNumbers.stream()
            .anyMatch(winningNumber -> winningNumber.equals(lottoNumber));
    }

    public List<LottoNumber> getLottoNumbers() {
        return new ArrayList<>(lottoNumbers);
    }

}
