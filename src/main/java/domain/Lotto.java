package domain;

import static constant.LottoConstants.LOTTO_RANGE_MAX;
import static constant.LottoConstants.LOTTO_RANGE_MIN;
import static constant.LottoConstants.LOTTO_SIZE;
import static exception.ExceptionMessage.LOTTO_NUMBER_DUPLICATED_ERROR;
import static exception.ExceptionMessage.LOTTO_RANGE_ERROR;
import static exception.ExceptionMessage.LOTTO_SIZE_ERROR;

import exception.LottoException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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

    public List<Integer> getSortedNumbers() {
        List<Integer> numbers = this.numbers.stream().map(LottoNumber::getNumber).toList();
        return numbers;
    }
}
