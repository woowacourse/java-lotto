package lotto.domain;

import static lotto.constant.ErrorMessage.ERROR_LOTTO_DUPLICATE_OR_WRONG_SIZE;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    private static final int LOTTO_NUMBERS_SIZE = 6;

    private final Set<LottoNumber> numbers;

    public Lotto(final LottoGenerator lottoGenerator) {
        final Set<LottoNumber> lottoNumbers = new HashSet<>(lottoGenerator.makeLottos());
        validateDuplicationAndSize(lottoNumbers);
        this.numbers = lottoNumbers;
    }

    private void validateDuplicationAndSize(final Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException(ERROR_LOTTO_DUPLICATE_OR_WRONG_SIZE.message());
        }
    }

    public List<Integer> getIntValues() {
        return numbers.stream()
                .map(LottoNumber::getValue)
                .sorted()
                .collect(Collectors.toUnmodifiableList());
    }

    public Set<LottoNumber> getMatchedNumbers(final Set<LottoNumber> targetNumbers) {
        final Set<LottoNumber> copyNumbers = new HashSet<>(numbers);
        copyNumbers.retainAll(targetNumbers);
        return copyNumbers;
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }

    public Set<LottoNumber> getLottoNumbers() {
        return new HashSet<>(numbers);
    }
}
