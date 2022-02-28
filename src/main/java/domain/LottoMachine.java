package domain;

import util.LottoNumberGenerator;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

public class LottoMachine {

    private final Money money;
    private final LottoResult lottoResult;
    private final LottoTicket lottoTicket;

    public LottoMachine(int money, List<Lotto> passiveLottos, LottoNumberGenerator numberGenerator) {
        this.money = new Money(money);
        this.money.buyLotto(passiveLottos.size());
        this.lottoResult = new LottoResult();
        this.lottoTicket = new LottoTicket(this.money.getPurchasableLottoCount(), passiveLottos, numberGenerator);
    }

    public double calculateProfit() {
        DecimalFormat decimalFormat = decimalFormatSetting();
        double profitRate = money.calculateProfitRate(lottoResult.sumTotalPrice());
        return Double.parseDouble(decimalFormat.format(profitRate));
    }

    private DecimalFormat decimalFormatSetting() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);
        return decimalFormat;
    }

    public LottoResult getResults(WinningLotto winningLotto) {
        return lottoTicket.getLottoResult(winningLotto, lottoResult);
    }

    public LottoTicket getLottoTicket() {
        return lottoTicket;
    }
}
