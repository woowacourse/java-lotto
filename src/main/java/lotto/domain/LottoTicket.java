package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTicket {

    public static final int LOTTO_NUMBERS_SIZE = 6;

    private static final String ERROR_DUPLICATION_WINNING_NUMBERS = "입력 번호가 서로 중복되었습니다.";
    private static final String ERROR_NOT_MATCH_NUMBER_COUNT = "번호는 6개를 입력해주세요.";

    private final List<LottoNumber> numbers;

    public LottoTicket(List<LottoNumber> lottoTicket) throws RuntimeException {
        validateLottoNumbersCount(lottoTicket);
        validateLottoNumbersDuplication(lottoTicket);

        this.numbers = lottoTicket;
    }

    private void validateLottoNumbersCount(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new RuntimeException(ERROR_NOT_MATCH_NUMBER_COUNT);
        }
    }

    private void validateLottoNumbersDuplication(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.stream().distinct().count() != lottoNumbers.size()) {
            throw new RuntimeException(ERROR_DUPLICATION_WINNING_NUMBERS);
        }
    }

    public List<LottoNumber> getNumbers() {
        return new ArrayList<>(numbers);
    }

    public Rank compareNumbers(LottoTicket winningNumbers, LottoNumber bonusNumber) {
        int totalMatchNumber = (int) numbers.stream()
                .filter(winningNumbers.getNumbers()::contains)
                .count();
        return Rank.getMatchResult(totalMatchNumber, numbers.contains(bonusNumber));
    }
}
