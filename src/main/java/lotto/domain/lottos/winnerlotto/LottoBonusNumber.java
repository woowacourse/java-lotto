package lotto.domain.lottos.winnerlotto;

import lotto.domain.lottos.LottoNumber;
import lotto.domain.lottos.LottoTicket;

public class LottoBonusNumber extends LottoNumber {

    private static final String DUPLICATE_ERROR_MESSAGE = "당첨번호에 이미 있는 보너스 숫자입니다.";
    private static final String NUMBER_FORMAT_ERROR_MESSAGE = "입력은 숫자여야 합니다.";

    private LottoBonusNumber(int number) {
        super(number);
    }

    public static LottoBonusNumber of(String input, LottoTicket lottoWinnerTicket) {
        int number = parseInt(input);
        validateDuplicateBonusNumber(number, lottoWinnerTicket);
        return new LottoBonusNumber(Integer.parseInt(input));
    }

    private static int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(NUMBER_FORMAT_ERROR_MESSAGE);
        }
    }

    public static void validateDuplicateBonusNumber
            (int number, LottoTicket lottoWinnerTicket) {
        if (lottoWinnerTicket.isContain(number)) {
            throw new IllegalArgumentException(DUPLICATE_ERROR_MESSAGE);
        }
    }
}
