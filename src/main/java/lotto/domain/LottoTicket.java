package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTicket {

    private final List<LottoLine> lottoLines;

    public LottoTicket(List<LottoLine> lottoLines) {
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
}
