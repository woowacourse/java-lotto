package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Rank;

public class LottoTicket {

    private static final RandomLottoGenerator randomLottoGenerator = new RandomLottoGenerator();
    private final List<LottoLine> lottoLines;

    public LottoTicket(int money) {
        int lottoLineCount = money / 1000;
        if (money < 0 || lottoLineCount <= 0) {
            throw new IllegalArgumentException("[Error] 로또 구입 금액은 1,000원 이상 입니다.(로또 1개 당 1,000원)");
        }
        List<LottoLine> lottoLines = new ArrayList<>();
        for (int i = 0; i < lottoLineCount; i++) {
            lottoLines.add(new LottoLine(randomLottoGenerator.createLottoLine()));
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
}
