package lotto.domain.lotto;

import static lotto.view.messages.ErrorMessages.LOTTO_PURCHASE_PRICE_ERROR;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.rank.Rank;

public class LottoTicket {

    public static final int LOTTO_LINE_PRICE = 1000;
    private static final LottoLineGenerator RANDOM_LOTTO_GENERATOR = new LottoLineGenerator();
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
            lottoLines.add(RANDOM_LOTTO_GENERATOR.createLottoLine());
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
