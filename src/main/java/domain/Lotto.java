package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lotto {
    private static final int LOTTO_SIZE = 6;
    public static final int LOTTO_PRICE = 1000;
    private static final String ERROR_MESSAGE_FOR_NULL_LOTTO_NUMBERS = "로또 번호가 비었습니다";
    private static final String ERROR_MESSAGE_FOR_DUPLICATE_LOTTO_NUMBERS = "숫자는 중복될 수 없습니다.";
    private static final String ERROR_MESSAGE_FOR_INVALID_SIZE_OF_LOTTO_NUMBERS = "%d개의 숫자를 골라주세요.";

    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        Objects.requireNonNull(lottoNumbers, ERROR_MESSAGE_FOR_NULL_LOTTO_NUMBERS);

        validateSize(lottoNumbers);
        validateDuplicate(lottoNumbers);
        this.lottoNumbers = List.copyOf(lottoNumbers);
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

    public long getSameNumberCount(Lotto anotherLotto) {
        List<LottoNumber> lottoNumbers = new ArrayList<>(this.lottoNumbers);
        lottoNumbers.retainAll(anotherLotto.lottoNumbers);

        return lottoNumbers.size();
    }

    public boolean containsLottoNumber(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }
}
