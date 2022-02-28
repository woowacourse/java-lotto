package domain;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import util.LottoNumberGenerator;

public class LottoMachine {

    private static final int LOTTO_PRICE = 1000;

    private final int money;
    private final LottoTicket lottoTicket;

    public LottoMachine(int money, LottoNumberGenerator numberGenerator) {
        this.money = money;
        this.lottoTicket = new LottoTicket(money / LOTTO_PRICE, numberGenerator);
    }

    public double calculateProfit(LottoResult lottoResult) {
        DecimalFormat decimalFormat = decimalFormatSetting();
        double profitRate = lottoResult.sumTotalPrice() / (double) money;
        return Double.parseDouble(decimalFormat.format(profitRate));
    }

    private DecimalFormat decimalFormatSetting() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);
        return decimalFormat;
    }

    public LottoResult getResults(WinningLotto winningLotto) {
        return new LottoResult(lottoTicket, winningLotto);
    }

    public LottoTicket getLottoTicket() {
        return lottoTicket;
    }
}
