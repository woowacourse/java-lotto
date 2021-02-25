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
    public final static Money PRICE = Money.from(1000);
    private static final int TOTAL_LOTTO_NUMBER_COUNT = 6;
    private static final String ERROR_INVALID_DUPLICATION_NUMBER = "[ERROR] 로또 번호는 중복되어선 안됩니다.";
    private static final String ERROR_INVALID_NUMBER_COUNT = "[ERROR] 6개의 번호를 입력해주세요. 쉼표로 구분하지 않으면 제대로 인식하지 않습니다.";

    private final List<LottoNumber> lottoNumbers;

    private LottoTicket(List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public List<LottoNumber> numbers() {
        return Collections.unmodifiableList(this.lottoNumbers);
    }

    public static LottoTicket of(List<LottoNumber> lottoNumbers) {
        return new LottoTicket(lottoNumbers);
    }

    public static LottoTicket valueOf(List<Integer> lottoNumbers) {
        isValidNumberCountOrThrow(lottoNumbers);
        List<LottoNumber> lottoNumbersList = new ArrayList<>();
        lottoNumbers.stream()
                .map(LottoNumber::from)
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

    private static boolean isValidNumberCountOrThrow(List<Integer> lottoNumbers) {
        if (lottoNumbers.size() != TOTAL_LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ERROR_INVALID_NUMBER_COUNT);
        }
        return true;
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }
}
