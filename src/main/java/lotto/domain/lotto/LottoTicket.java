package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.lotto.util.LottoMoney;
import lotto.domain.rank.Rank;

public class LottoTicket {

    public static final String LOTTO_PURCHASE_MONEY_LACK_ERROR = "[Error] 로또 구입 비용이 부족합니다. (로또 한 라인당 1,000원)";
    private static final LottoLineGenerator RANDOM_LOTTO_GENERATOR = new LottoLineGenerator();
    private final List<LottoLine> lottoLines;

    public LottoTicket(LottoMoney money) {
        if (money.getCanBuyLottoLineCount() == 0) {
            throw new IllegalArgumentException(LOTTO_PURCHASE_MONEY_LACK_ERROR);
        }
        int lottoLineCount = money.getCanBuyLottoLineCount();
        this.lottoLines = makeLottoLines(lottoLineCount);
    }

    public LottoTicket(LottoMoney money, List<LottoLine> lottoLines) {
        if (money.getCanBuyLottoLineCount() == 0 && lottoLines.size() == 0) {
            throw new IllegalArgumentException(LOTTO_PURCHASE_MONEY_LACK_ERROR);
        }
        int lottoLineCount = money.getCanBuyLottoLineCount();
        this.lottoLines = makeLottoLines(lottoLineCount);
        this.lottoLines.addAll(lottoLines);
    }

    private List<LottoLine> makeLottoLines(int count) {
        List<LottoLine> lottoLines = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottoLines.add(RANDOM_LOTTO_GENERATOR.createLottoLine());
        }
        return lottoLines;
    }

    public List<Rank> checkLottoLines(WinningLotto winningLotto) {
        return lottoLines.stream()
            .map(it -> it.checkLottoLine(winningLotto))
            .collect(Collectors.toList());
    }

    public List<LottoLine> getLottoLines() {
        return Collections.unmodifiableList(lottoLines);
    }

    public int getLottoLineSize() {
        return lottoLines.size();
    }

    public int getManualLottoLineCount() {
        long count = lottoLines.stream().filter(LottoLine::isManualLotto).count();
        return (int) count;
    }

    public int getAutoLottoLineCount() {
        long count = lottoLines.stream().filter(it -> !it.isManualLotto()).count();
        return (int) count;
    }

}