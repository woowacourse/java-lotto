package controller;

import domain.BonusNumber;
import domain.LottoDispenser;
import domain.WinningNumber;
import service.LottoService;

public class LottoController {

    private final LottoService lottoService;

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    public LottoDispenser inputBuyLottoMoney(String inputBuyLottoMoney) {
        return lottoService.inputBuyLottoMoney(inputBuyLottoMoney);
    }

    public String formattingBuyLottoResult(LottoDispenser lottoDispenser) {
        return lottoDispenser.buyLottoResult();
    }

    public String formattingWinningResult(LottoDispenser lottoDispenser, WinningNumber winningNumber,
                                          BonusNumber bonusNumber) {
        return lottoService.winningCalculate(lottoDispenser, winningNumber, bonusNumber);
    }

    public WinningNumber inputWinningNumber(String inputWinningNumber) {
        return lottoService.inputWinningNumber(inputWinningNumber);
    }

    public BonusNumber inputBonusNumber(String inputBonusNumber) {
        return lottoService.inputBonusNumber(inputBonusNumber);
    }
}
