package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lotto.domain.Rank;
import lotto.domain.lotto.LottoLine;
import lotto.domain.lotto.LottoNumber;

public class LottoTicket {

    private final List<LottoLine> autoLottoLines;
    private final List<LottoLine> manualLottoLines;

    public LottoTicket(List<LottoLine> autoLottoLines, List<LottoLine> manualLottoLines) {
        this.autoLottoLines = autoLottoLines;
        this.manualLottoLines = manualLottoLines;
    }

    public int getAutoLottoSize() {
        return autoLottoLines.size();
    }

    public int getManualLottoSize() {
        return manualLottoLines.size();
    }

    public List<Rank> matchLottoLines(WinningNumbers winningNumbers) {
        List<Rank> ranks = new ArrayList<>();
        for (LottoLine lottoLine : autoLottoLines) {
            ranks.add(lottoLine.matchLottoNumbers(winningNumbers));
        }

        for (LottoLine lottoLine : manualLottoLines) {
            ranks.add(lottoLine.matchLottoNumbers(winningNumbers));
        }

        return ranks;
    }

    public List<LottoLine> getTotalLottoLine() {
        ArrayList<LottoLine> totalLottoLine = new ArrayList();
        totalLottoLine.addAll(autoLottoLines);
        totalLottoLine.addAll(manualLottoLines);
        return Collections.unmodifiableList(totalLottoLine);
    }

}
