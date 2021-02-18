package lotto.domain.lotto;

import static lotto.view.messages.ErrorMessages.LOTTO_PURCHASE_PRICE_ERROR;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.lotto.utils.Rank;

public class LottoTicket {

    private static final LottoLineGenerator randomLottoGenerator = new LottoLineGenerator();
    private final List<LottoLine> lottoLines;

    public LottoTicket(int money) {
        int lottoLineCount = money / 1000;
        if (money < 0 || lottoLineCount <= 0) {
            throw new IllegalArgumentException(LOTTO_PURCHASE_PRICE_ERROR.getMessage());
        }
        List<LottoLine> lottoLines = new ArrayList<>();
        for (int i = 0; i < lottoLineCount; i++) {
            lottoLines.add(randomLottoGenerator.createLottoLine());
        }
        this.lottoLines = lottoLines;
    }

    public List<Rank> matchLottoLines(List<LottoNumber> answerLottoNumbers,
        LottoNumber bonusNumber) {
        List<Rank> ranks = new ArrayList<>();
        for (LottoLine lottoLine : lottoLines) {
            ranks.add(lottoLine
                .matchLottoNumbers(lottoLine.getValues(), bonusNumber, answerLottoNumbers));
        }
        return ranks;
    }

    public List<LottoLine> getLottoLines() {
        return lottoLines;
    }

    public int getLottoLineSize() {
        return lottoLines.size();
    }

}
