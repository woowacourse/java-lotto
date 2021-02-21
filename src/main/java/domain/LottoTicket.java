package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTicket {
    private static final int TOTAL_LOTTO_NUMBER_COUNT = 6;
    private static final String ERROR_INVALID_DUPLICATION_NUMBER = "[ERROR] 로또 번호는 중복되어선 안됩니다.";
    private static final String ERROR_INVALID_NUMBER_COUNT = "[ERROR] 숫자는 6개를 입력해주세요.";

    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public List<LottoNumber> numbers() {
        return Collections.unmodifiableList(this.lottoNumbers);
    }

    public static LottoTicket valueOf(List<String> lottoNumbers) {
        isValidNumberCountOrThrow(lottoNumbers);
        List<LottoNumber> lottoNumbersList = new ArrayList<>();
        lottoNumbers.stream()
                .map(LottoNumber::new)
                .filter(lottoNumber -> isNoneDuplicationNumberOrThrow(lottoNumbersList, lottoNumber))
                .forEach(lottoNumbersList::add);
        return new LottoTicket(lottoNumbersList);
    }

    private static void isValidNumberCountOrThrow(List<String> lottoNumbers) {
        if (lottoNumbers.size() != TOTAL_LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ERROR_INVALID_NUMBER_COUNT);
        }
    }

    private static boolean isNoneDuplicationNumberOrThrow(List<LottoNumber> lottoNumbers, LottoNumber lottoNumber) {
        if (lottoNumbers.contains(lottoNumber)) {
            throw new IllegalArgumentException(ERROR_INVALID_DUPLICATION_NUMBER);
        }
        return true;
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }
}
