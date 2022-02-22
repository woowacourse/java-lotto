package domain;

import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private static final int LOTTO_SIZE = 6;
    private static final String ERROR_MESSAGE_FOR_INVALID_SIZE_OF_LOTTO_NUMBERS = "%d개의 숫자를 골라주세요.";
    private static final String ERROR_MESSAGE_FOR_DUPLICATE_LOTTO_NUMBERS = "숫자는 중복될 수 없습니다.";

    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers);
        validateDuplicate(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validateSize(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(
                    String.format(ERROR_MESSAGE_FOR_INVALID_SIZE_OF_LOTTO_NUMBERS, LOTTO_SIZE));
        }
    }

    private void validateDuplicate(List<LottoNumber> lottoNumbers) {
        if (isDuplicate(lottoNumbers)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FOR_DUPLICATE_LOTTO_NUMBERS);
        }
    }

    private boolean isDuplicate(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.size() != lottoNumbers.stream()
                .distinct()
                .count();
    }

    public int getSameNumberCount(Lotto anotherLotto) {
        return lottoNumbers.stream()
                .filter(lottoNumber -> anotherLotto.containsLottoNumber(lottoNumber))
                .collect(Collectors.toList())
                .size();
    }

    private boolean containsLottoNumber(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }
}
