package controller;

import service.LottoService;

public class LottoController {

    private final LottoService lottoService;

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    public void inputBuyLottoMoney(String inputBuyLottoMoney) {
        lottoService.inputBuyLottoMoney(inputBuyLottoMoney);
    }

    public String formattingBuyLottoResult() {
        return lottoService.formattingBuyLottoResult();
    }

    public String formattingWinningResult() {
        return lottoService.winningCalculate();
    }

    public void inputWinningNumber(String inputWinningNumber) {
        lottoService.inputWinningNumber(inputWinningNumber);
    }

    public void inputBonusNumber(String inputBonusNumber){
        lottoService.inputBonusNumber(inputBonusNumber);
    }

}
