package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.lotto.util.LottoMoney;
import lotto.domain.rank.Ranks;

public class LottoTicket {

    public static final String LOTTO_TICKET_CREATE_ERROR = "[Error] 로또 티켓을 생성하는데 필요한 금액과 로또 라인이 부족합니다.";
    private static final LottoLineGenerator RANDOM_LOTTO_GENERATOR = new LottoLineGenerator();
    private final List<LottoLine> lottoLines;

    public LottoTicket(LottoMoney money) {
        this(money, new ArrayList<>());
    }

    public LottoTicket(List<LottoLine> manualLottoLines) {
        this(new LottoMoney(0), manualLottoLines);
    }

    public LottoTicket(LottoMoney money, List<LottoLine> manualLottoLines) {
        this.lottoLines = makeAutoLottoLines(money.getCanBuyLottoLineCount());
        this.lottoLines.addAll(manualLottoLines);
        validateCreateLottoLines();
    }

    public Ranks matchLottoLines(WinningLotto winningLotto) {
        return new Ranks(lottoLines.stream()
            .map(it -> it.checkLottoLine(winningLotto))
            .collect(Collectors.toList()));
    }

    public List<LottoLine> getLottoLines() {
        return Collections.unmodifiableList(lottoLines);
    }

    public int getManualLottoLineCount() {
        long count = lottoLines.stream().filter(LottoLine::isManualLotto).count();
        return (int) count;
    }

    public int getAutoLottoLineCount() {
        long count = lottoLines.stream().filter(it -> !it.isManualLotto()).count();
        return (int) count;
    }

    public void validateCreateLottoLines() {
        if (lottoLines.size() == 0) {
            throw new IllegalArgumentException(LOTTO_TICKET_CREATE_ERROR);
        }
    }

    private List<LottoLine> makeAutoLottoLines(int count) {
        List<LottoLine> lottoLines = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottoLines.add(RANDOM_LOTTO_GENERATOR.createLottoLine());
        }
        return lottoLines;
    }

}