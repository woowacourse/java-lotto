package lotto.domain;

import java.util.*;
import java.util.stream.Collectors;

public class LottoTicket {
    private static final int VALID_LOTTO_NUMBER_COUNTS = 6;
    private static final String INVALID_LOTTO_NUMBER_COUNTS = "로또 티켓은 중복되지 않은 6자리의 숫자로 구성되어야 합니다.";
    
    private final List<LottoNumber> lottoNumbers;

    private LottoTicket(List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public static LottoTicket generateTicket(List<Integer> numbers) {
        validateNumbers(numbers);
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        return new LottoTicket(lottoNumbers);
    }

    private static void validateNumbers(List<Integer> numbers) {
        Set<Integer> distinctNumbers = new HashSet<>(numbers);
        int numberCounts = numbers.size();
        int distinctNumberCounts = distinctNumbers.size();
        if (numberCounts != VALID_LOTTO_NUMBER_COUNTS || numberCounts != distinctNumberCounts) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_COUNTS);
        }
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }
    
    /*
        public L
    
     */
}
