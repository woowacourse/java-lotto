package service;

import domain.BonusNumber;
import domain.LottoDispenser;
import domain.WinningCase;
import domain.WinningNumber;
import domain.dto.WinningCalculateDto;
import domain.strategy.LottoRandomGeneratorStrategy;
import java.util.Collections;
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

    public WinningCalculateDto winningCalculate(LottoDispenser lottoDispenser, WinningNumber winningNumber,
                                                BonusNumber bonusNumber) {
        Map<WinningCase, Integer> winningCalculateResult = lottoDispenser.winningCalculate(winningNumber, bonusNumber);
        long earnMoney = lottoDispenser.calculateEarnMoney(winningCalculateResult);
        double earnMoneyRatio = lottoDispenser.calculateEarnMoneyRatio(earnMoney);
        return new WinningCalculateDto(Collections.unmodifiableMap(winningCalculateResult), earnMoneyRatio);
    }
}

