package lottogame.domain.machine;

import lottogame.domain.number.LottoNumber;
import lottogame.domain.number.LottoNumbers;

public class LottoWinNumberIssueMachine {

    private static final String DELIMITER = ", ";

    private LottoWinNumberIssueMachine() {
    }

    public static LottoNumbers issueWinNumbers(final String numbers) {
        LottoNumbers lottoNumbers = new LottoNumbers();
        for (String number : numbers.split(DELIMITER)) {
            lottoNumbers.add(new LottoNumber(number));
        }
        return new LottoNumbers(lottoNumbers.toList());
    }

    public static LottoNumber issueBonusNumber(final String number) {
        return new LottoNumber(number);
    }
}
