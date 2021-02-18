package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Rank;

public class LottoTicket {

    private static final RandomLottoGenerator randomLottoGenerator = new RandomLottoGenerator();
    private final List<LottoLine> lottoLines;

    public LottoTicket(int money) {
        int lottoLineCount = money / 1000;
        if (lottoLineCount <= 0) {
            throw new IllegalArgumentException("[Error] 로또는 개당 1,000원 입니다.");
        }
        List<LottoLine> lottoLines = new ArrayList<>();
        for (int i = 0; i < lottoLineCount; i++) {
            lottoLines.add(new LottoLine(randomLottoGenerator.createLottoLine()));
        }
        this.lottoLines = lottoLines;
    }

    public int getLength() {
        return lottoLines.size();
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
