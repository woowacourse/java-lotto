package domain;

import java.util.*;

public class LottoTicket {
    private static final int TOTAL_LOTTO_NUMBER_COUNT = 6;
    private static final String ERROR_INVALID_DUPLICATION_NUMBER = "[ERROR] 로또 번호는 중복되지 않게 입력해주세요.";
    private static final String ERROR_INVALID_NUMBER_COUNT = "[ERROR] 숫자는 6개를 입력해주세요.";

    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public List<LottoNumber> numbers() {
        return Collections.unmodifiableList(this.lottoNumbers);
    }

    public static LottoTicket valueOf(List<String> lottoNumbers) {
        validateNumberCount(lottoNumbers);
        validateDuplicationNumber(lottoNumbers);

        List<LottoNumber> lottoTicket = new ArrayList<>();
        lottoNumbers.stream()
                .map(LottoNumber::new)
                .forEach(lottoTicket::add);
        return new LottoTicket(lottoTicket);
    }

    private static void validateNumberCount(List<String> lottoNumbers) {
        if (lottoNumbers.size() != TOTAL_LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ERROR_INVALID_NUMBER_COUNT);
        }
    }

    private static void validateDuplicationNumber(List<String> lottoNumbers) {
        Set<String> distinctLottoNumbers = new HashSet<>(lottoNumbers);

        if (distinctLottoNumbers.size() < TOTAL_LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ERROR_INVALID_DUPLICATION_NUMBER);
        }
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }
}
