package service;

import domain.BonusNumber;
import domain.LottoDispenser;
import domain.WinningCase;
import domain.WinningNumber;
import domain.strategy.LottoRandomGeneratorStrategy;
import java.util.Map;

public class LottoService {

    public LottoService() {
    }

    public LottoDispenser inputBuyLottoMoney(String inputBuyLottoMoney) {
        return new LottoDispenser(inputBuyLottoMoney, new LottoRandomGeneratorStrategy());
    }

    public WinningNumber inputWinningNumber(String inputWinningNumber) {
        return new WinningNumber(inputWinningNumber);
    }

    public BonusNumber inputBonusNumber(String inputBonusNumber) {
        return new BonusNumber(inputBonusNumber);
    }

    public Map<WinningCase, Integer> winningCalculate(LottoDispenser lottoDispenser, WinningNumber winningNumber,
                                                      BonusNumber bonusNumber) {
        return lottoDispenser.winningCalculate(winningNumber, bonusNumber);
//        return new WinningCalculateDto(Collections.unmodifiableMap(winningCalculateResult), earnMoneyRatio);
    }

    public double calculateEarnMoneyRatio(LottoDispenser lottoDispenser,
                                          Map<WinningCase, Integer> winningCalculateResult) {
        long earnMoney = lottoDispenser.calculateEarnMoney(winningCalculateResult);
        return lottoDispenser.calculateEarnMoneyRatio(earnMoney);
    }
}

