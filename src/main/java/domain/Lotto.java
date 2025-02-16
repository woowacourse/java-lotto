package domain;

import static constant.LottoConstants.LOTTO_SIZE;
import static exception.ExceptionMessage.LOTTO_NUMBER_DUPLICATED_ERROR;
import static exception.ExceptionMessage.LOTTO_SIZE_ERROR;

import exception.LottoException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Lotto {
    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream().map(LottoNumber::new)
                .collect(Collectors.toList());
        validateLottoDuplicate(lottoNumbers);
        validateLottoNumberSize(lottoNumbers);
        Collections.sort(lottoNumbers);
        this.numbers = lottoNumbers;
    }

    public boolean hasNumber(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    public int getMatchedCount(Lotto lotto) {
        List<LottoNumber> lottoNumbers = lotto.numbers;
        return (int) lottoNumbers.stream().filter(numbers::contains).count();
    }

    private void validateLottoDuplicate(List<LottoNumber> numbers) {
        HashSet<LottoNumber> set = new HashSet<>(numbers);
        if (set.size() != numbers.size()) {
            throw LottoException.from(LOTTO_NUMBER_DUPLICATED_ERROR);
        }
    }

    private void validateLottoNumberSize(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE.getValue()) {
            throw LottoException.from(LOTTO_SIZE_ERROR);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lotto lotto = (Lotto) o;
        return Objects.equals(numbers, lotto.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numbers);
    }

    public List<Integer> getSortedLottoNumbers() {
        return numbers.stream().map(LottoNumber::getNumber).toList();
    }
}
