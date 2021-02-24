package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * LottoTicket.java
 * 한 장의 로또 티켓 클래스
 *
 * @author Kimun Kim, github.com/tributetothemoon
 * @author Daeun Lee, github.com/da-nyee
 */
public class LottoTicket {
    public final static Money PRICE = new Money(1000);
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

    public static LottoTicket of(List<LottoNumber> shuffleLottoNumbers) {
        return new LottoTicket(shuffleLottoNumbers);
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

    private static boolean isNoneDuplicationNumberOrThrow(List<LottoNumber> lottoNumbers, LottoNumber lottoNumber) {
        if (lottoNumbers.contains(lottoNumber)) {
            throw new IllegalArgumentException(ERROR_INVALID_DUPLICATION_NUMBER);
        }
        return true;
    }

    private static void isValidNumberCountOrThrow(List<String> lottoNumbers) {
        if (lottoNumbers.size() != TOTAL_LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ERROR_INVALID_NUMBER_COUNT);
        }
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }
}
