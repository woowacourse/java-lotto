package lotto.domain.lotto;

import static lotto.domain.lotto.utils.LottoAttributes.LOTTO_LINE_PRICE;
import static lotto.view.messages.ErrorMessages.LOTTO_PURCHASE_PRICE_ERROR;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.rank.Rank;

public class LottoTicket {

    private static final LottoLineGenerator randomLottoGenerator = new LottoLineGenerator();
    private final List<LottoLine> lottoLines;

    public LottoTicket(int money) {
        int lottoLineCount = money / LOTTO_LINE_PRICE;
        if (money < 0 || lottoLineCount <= 0) {
            throw new IllegalArgumentException(LOTTO_PURCHASE_PRICE_ERROR.getMessage());
        }
        this.lottoLines = makeLottoLines(lottoLineCount);
    }

    private List<LottoLine> makeLottoLines(int count) {
        List<LottoLine> lottoLines = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottoLines.add(randomLottoGenerator.createLottoLine());
        }
        return lottoLines;
    }

    public List<Rank> checkLottoLines(LottoLine answerLottoLine, LottoNumber bonusNumber) {
        return lottoLines.stream().map(it -> it.checkLottoLine(answerLottoLine, bonusNumber))
            .collect(Collectors.toList());
    }

    public List<LottoLine> getLottoLines() {
        return Collections.unmodifiableList(lottoLines);
    }

    public int getLottoLineSize() {
        return lottoLines.size();
    }

}
