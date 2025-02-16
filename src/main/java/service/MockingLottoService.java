package service;

import domain.BonusNumber;
import domain.Lotto;
import domain.LottoBuyResultFormatter;
import domain.LottoDispenser;
import domain.WinningCalculateFormatter;
import domain.WinningCase;
import domain.WinningNumber;
import java.util.List;
import java.util.Map;

public class MockingLottoService extends LottoService {

    private final WinningCalculateFormatter winningCalculateFormatter;

    public MockingLottoService(WinningCalculateFormatter winningCalculateFormatter) {
        super(winningCalculateFormatter, new LottoBuyResultFormatter());
        this.winningCalculateFormatter = winningCalculateFormatter;
    }

    public String winningCalculate(List<Lotto> lottos, String winningNumberInput, String bonusMoneyInput) {
        LottoDispenser lottoDispenser = new LottoDispenser(lottos);
        WinningNumber winningNumber = new WinningNumber(winningNumberInput);
        BonusNumber bonusNumber = new BonusNumber(bonusMoneyInput);
        Map<WinningCase, Integer> winningCalculateResult = lottoDispenser.winningCalculate(winningNumber, bonusNumber);
        long earnMoney = lottoDispenser.calculateEarnMoney(winningCalculateResult);
        double earnMoneyRatio = lottoDispenser.calculateEarnMoneyRatio(earnMoney);
        return winningCalculateFormatter.winningResultFormatting(winningCalculateResult, earnMoneyRatio);
    }
}
