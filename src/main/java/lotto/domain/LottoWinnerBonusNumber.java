package lotto.domain;

public class LottoWinnerBonusNumber extends LottoNumber {

    private static final String DUPLICATE_ERROR_MESSAGE = "당첨번호에 이미 있는 보너스 숫자입니다.";

    public LottoWinnerBonusNumber(int number, LottoWinnerTicket lottoWinnerTicket) {
        super(number);
        validateIfWinnerBonusNumberInWinnerTicket(number, lottoWinnerTicket);
    }

    public void validateIfWinnerBonusNumberInWinnerTicket
            (int number, LottoWinnerTicket lottoWinnerTicket) {
        if (lottoWinnerTicket.isContain(number)) {
            throw new IllegalArgumentException(DUPLICATE_ERROR_MESSAGE);
        }
    }
}
