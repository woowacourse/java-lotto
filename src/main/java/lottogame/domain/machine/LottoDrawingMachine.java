package lottogame.domain.machine;

import java.util.HashSet;
import lottogame.domain.number.LottoNumber;
import lottogame.domain.number.LottoNumbers;

public class LottoDrawingMachine {

    public static final int DRAWING_COUNT = 6;
    public static final String DELIMITER = ", ";

    public LottoNumbers drawing(final String drawingNumbers) {
        LottoNumbers lottoNumbers = new LottoNumbers();

        for (String number : drawingNumbers.split(DELIMITER)) {
            lottoNumbers.add(new LottoNumber(number));
        }
        validDrawingNumbers(lottoNumbers);
        return lottoNumbers;
    }

    private void validDrawingNumbers(final LottoNumbers lottoNumbers) {
        if (new HashSet<>(lottoNumbers.toList()).size() != DRAWING_COUNT) {
            throw new IllegalArgumentException("잘못된 당첨번호 입력입니다.");
        }
    }

    public LottoNumber bonusDrawing(final String drawingNumber) {
        return new LottoNumber(drawingNumber);
    }
}
