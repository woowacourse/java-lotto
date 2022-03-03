package model;

import java.util.Collections;
import java.util.List;

public class Lotto {
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final String INVALID_LOTTO_NUMBER_COUNT = "[ERROR] 로또 숫자는 6개만 입력해야 합니다";
    private static final String LOTTO_NUMBER_DUPLICATED = "[ERROR] 중복된 번호는 고를 수 없습니다.";

    private final List<LottoNumber> lottoNumbers;

    private Lotto(List<LottoNumber> lottoNumbers) {
        lottoNumbers = List.copyOf(lottoNumbers);
        validate(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public static Lotto from(List<LottoNumber> lottoNumbers) {
        return new Lotto(lottoNumbers);
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }

    public int countMatching(Lotto target) {
        return (int) lottoNumbers.stream()
                .filter(target::contains)
                .count();
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    private void validate(List<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers);
        validateIsNotDuplicated(lottoNumbers);
    }

    private void validateSize(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_COUNT);
        }
    }

    private void validateIsNotDuplicated(List<LottoNumber> lottoNumbers) {
        long distinctSize = lottoNumbers.stream()
                .distinct().count();
        if (distinctSize != lottoNumbers.size()) {
            throw new IllegalArgumentException(LOTTO_NUMBER_DUPLICATED);
        }
    }
}
