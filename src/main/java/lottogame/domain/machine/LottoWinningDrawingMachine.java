package lottogame.domain.machine;

import lottogame.domain.number.LottoNumber;
import lottogame.domain.number.LottoNumbers;

import java.util.ArrayList;
import java.util.List;

public class LottoWinningDrawingMachine {

    public static final String DELIMITER = ",";
    private LottoNumbers lottoNumbers;

    public LottoNumbers drawing(String drawingNumbers) {
        drawingNumbers = drawingNumbers.replaceAll(" ", "");
        final List<LottoNumber> lottoNumberGroup = new ArrayList<>();

        for (String number : drawingNumbers.split(DELIMITER)) {
            lottoNumberGroup.add(new LottoNumber(number));
        }
        return lottoNumbers = new LottoNumbers(lottoNumberGroup);
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