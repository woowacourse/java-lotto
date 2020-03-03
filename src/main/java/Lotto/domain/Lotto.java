package Lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
    private static final String LOTTO_NUMBER_DUPLICATED_MESSAGE = "로또 번호 입력은 중복이 없어야 합니다.";
    private static final String LOTTO_NUMBER_WRONG_COUNT_MESSAGE = "로또 번호 입력에는 6개의 숫자만 허용합니다.";
    private static final String COMMA = ", ";
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final String OPEN_BRACKET = "[";
    private static final String CLOSE_BRACKET = "]";

    private List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        validateDuplication(lottoNumbers);
        validateAmountOfNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validateDuplication(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> lottoNumberWithoutDuplication = new HashSet<LottoNumber>(lottoNumbers);
        if (lottoNumberWithoutDuplication.size() != lottoNumbers.size()) {
            throw new IllegalArgumentException(LOTTO_NUMBER_DUPLICATED_MESSAGE);
        }
    }

    private void validateAmountOfNumbers(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(LOTTO_NUMBER_WRONG_COUNT_MESSAGE);
        }
    }

    boolean hasBonusNumber(LottoNumber bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }

    int countMatchingAmountWith(Lotto inputLotto) {
        return (int) lottoNumbers.stream()
                .filter(inputLotto::contains)
                .count();
    }

    boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public String getLotto() {
        String result = this.lottoNumbers.stream()
                .sorted((a, b) -> a.getLottoNumber() - b.getLottoNumber())
                .map(t -> String.valueOf(t.getLottoNumber()))
                .collect(Collectors.joining(COMMA));
        return OPEN_BRACKET + result + CLOSE_BRACKET;
    }
}
