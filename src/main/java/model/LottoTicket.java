package model;

import java.util.List;

public class LottoTicket {
    public static final int LOTTO_NUMBER_COUNT = 6;

    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
        validateNotDuplicatedNumber();
        validateNumberCount();
    }

    private void validateNotDuplicatedNumber() {
        long distinctSize = lottoNumbers.stream()
                .distinct().count();
        if (distinctSize != lottoNumbers.size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 번호는 고를 수 없습니다.");
        }
    }

    private void validateNumberCount() {
        if (lottoNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 로또 숫자는 6개만 입력해야 합니다");
        }
    }
}
