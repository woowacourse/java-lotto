package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;

import lotto.domain.Rank;
import lotto.domain.lotto.LottoLine;
import lotto.domain.lotto.LottoNumber;

public class LottoTicket {

    private final List<LottoLine> lottoLines;

    public LottoTicket(List<LottoLine> lottoLines) {
        this.lottoLines = lottoLines;
    }

    public int getLength() {
        return lottoLines.size();
    }

    public List<Rank> matchLottoLines(WinningNumbers winningNumbers) {
        List<Rank> ranks = new ArrayList<>();
        for (LottoLine lottoLine : lottoLines) {
            ranks.add(lottoLine.matchLottoNumbers(lottoLine.getValues(), winningNumbers));
        }
        return ranks;
    }

    public List<LottoLine> getLottoLines() {
        return lottoLines;
    }
}
