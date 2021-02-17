package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTicket {
    private static final int TOTAL_LOTTO_NUMBER_COUNT = 6;

    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public List<LottoNumber> numbers() {
        return Collections.unmodifiableList(this.lottoNumbers);
    }

    public static LottoTicket of(List<LottoNumber> shuffleLottoNumbers) {
        return new LottoTicket(shuffleLottoNumbers);
    }

    public static LottoTicket valueOf(List<String> lottoNumbers) {
        isValidNumberCount(lottoNumbers);
        List<LottoNumber> lottoNumbersList = new ArrayList<>();
        lottoNumbers.stream()
                .map(LottoNumber::new)
                .filter(lottoNumber -> notContainsOrThrow(lottoNumbersList, lottoNumber))
                .forEach(lottoNumbersList::add);
        return new LottoTicket(lottoNumbersList);
    }

    private static boolean notContainsOrThrow(List<LottoNumber> lottoNumbers, LottoNumber lottoNumber) {
        if (lottoNumbers.contains(lottoNumber)) {
            throw new IllegalArgumentException("로또 번호는 중복되어선 안됩니다.");
        }
        return true;
    }

    private static void isValidNumberCount(List<String> lottoNumbers) {
        if (lottoNumbers.size() != TOTAL_LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("숫자는 6개를 입력해주세요.");
        }
    }
}
