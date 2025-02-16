package service;

import domain.BonusNumber;
import domain.LottoBuyResultFormatter;
import domain.LottoDispenser;
import domain.WinningCalculateFormatter;
import domain.WinningCase;
import domain.WinningNumber;
import java.util.Map;

public class LottoService {

    private final WinningCalculateFormatter winningCalculateFormatter;
    private final LottoBuyResultFormatter lottoBuyResultFormatter;

    public LottoService(WinningCalculateFormatter winningCalculateFormatter,
                        LottoBuyResultFormatter lottoBuyResultFormatter) {
        this.winningCalculateFormatter = winningCalculateFormatter;
        this.lottoBuyResultFormatter = lottoBuyResultFormatter;
    }

    public LottoDispenser inputBuyLottoMoney(String inputBuyLottoMoney) {
        return new LottoDispenser(inputBuyLottoMoney, lottoBuyResultFormatter);
    }

    public WinningNumber inputWinningNumber(String inputWinningNumber) {
        return new WinningNumber(inputWinningNumber);
    }

    public BonusNumber inputBonusNumber(String inputBonusNumber) {
        return new BonusNumber(inputBonusNumber);
    }

    public String winningCalculate(LottoDispenser lottoDispenser, WinningNumber winningNumber,
                                   BonusNumber bonusNumber) {
        Map<WinningCase, Integer> winningCalculateResult = lottoDispenser.winningCalculate(winningNumber, bonusNumber);
        long earnMoney = lottoDispenser.calculateEarnMoney(winningCalculateResult);
        double earnMoneyRatio = lottoDispenser.calculateEarnMoneyRatio(earnMoney);
        return winningCalculateFormatter.winningResultFormatting(winningCalculateResult, earnMoneyRatio);
    }
}
