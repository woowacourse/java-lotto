package lottogame.domain.machine;

import lottogame.domain.number.LottoNumber;
import lottogame.domain.number.LottoNumbers;

import java.util.HashSet;

public class LottoWinningDrawingMachine {

    public static final int DRAWING_COUNT = 6;
    public static final String DELIMITER = ", ";

    private final LottoNumbers lottoNumbers = new LottoNumbers();

    public LottoNumbers drawing(final String drawingNumbers) {
        for (String number : drawingNumbers.split(DELIMITER)) {
            this.lottoNumbers.add(new LottoNumber(number));
        }
        validDrawingNumbers();
        return new LottoNumbers(this.lottoNumbers.toList());
    }

    private void validDrawingNumbers() {
        if (new HashSet<>(this.lottoNumbers.toList()).size() != DRAWING_COUNT) {
            throw new IllegalArgumentException("잘못된 당첨번호 입력입니다.");
        }
    }

    public LottoNumber bonusDrawing(final String drawingNumber) {
        validBonusNumber(drawingNumber);
        return new LottoNumber(drawingNumber);
    }

    private void validBonusNumber(final String bonusNumber) {
        if (lottoNumbers.contains(new LottoNumber(bonusNumber))) {
            throw new IllegalArgumentException("보너스 번호가 당첨번호와 중복됩니다.");
        }
    }
}
