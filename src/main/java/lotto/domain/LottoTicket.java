package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoTicket {
    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(List<Integer> numbers) {
        validateLottoNumberCount(numbers);
        validateDuplicatedLottoNumbers(numbers);

        this.lottoNumbers = numbers.stream().map(LottoNumber::new)
            .collect(Collectors.toList());
    }

    private void validateLottoNumberCount(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
    }

    private void validateDuplicatedLottoNumbers(List<Integer> numbers) {
        Set<Integer> duplicateCheck = new HashSet<>(numbers);
        if (numbers.size() != duplicateCheck.size()) {
            throw new IllegalArgumentException();
        }
    }

    public List<LottoNumber> list() {
        return lottoNumbers;
    }
}
