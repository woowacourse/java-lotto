package controller;

import domain.BonusNumber;
import domain.LottoDispenser;
import domain.WinningCase;
import domain.WinningNumber;
import domain.dto.WinningCalculateDto;
import java.util.Map;
import service.LottoService;

public class LottoController {

    private final LottoService lottoService;

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    public LottoDispenser inputBuyLottoMoney(String inputBuyLottoMoney) {
        return lottoService.inputBuyLottoMoney(inputBuyLottoMoney);
    }

    public WinningCalculateDto winningResult(LottoDispenser lottoDispenser, WinningNumber winningNumber,
                                             BonusNumber bonusNumber) {
        Map<WinningCase, Integer> winningCaseIntegerMap = lottoService.winningCalculate(lottoDispenser, winningNumber,
                bonusNumber);
        double earnMoneyRatio = lottoService.calculateEarnMoneyRatio(lottoDispenser, winningCaseIntegerMap);
        return new WinningCalculateDto(winningCaseIntegerMap, earnMoneyRatio);
    }

    public WinningNumber inputWinningNumber(String inputWinningNumber) {
        return lottoService.inputWinningNumber(inputWinningNumber);
    }

    public BonusNumber inputBonusNumber(String inputBonusNumber) {
        return lottoService.inputBonusNumber(inputBonusNumber);
    }
}
