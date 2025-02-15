package controller;

import exception.LottoException;
import service.LottoService;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final LottoService lottoService;

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    public void startLotto() {
        buyLotto();
        settingWinningNumbers();
        settingBonusNumbers();
        printWinningResult();
    }

    private void buyLotto() {
        boolean retry = true;
        while(retry) {
            retry = buyLottoInput();
        }
        OutputView.printBuyLotto(formattingBuyLottoResult());
    }

    private boolean buyLottoInput() {
        try{
            String inputBuyLottoMoney = InputView.inputBuyLottoMoney();
            inputBuyLottoMoney(inputBuyLottoMoney);
        }catch (LottoException lottoException){
            OutputView.printError(lottoException);
            return true;
        }
        return false;
    }

    private void settingWinningNumbers() {
        boolean retry = true;
        while(retry) {
            retry = inputWinningNumber();
        }
    }

    private boolean inputWinningNumber() {
        try{
            String inputWinningNumber = InputView.inputWinningNumber();
            inputWinningNumber(inputWinningNumber);
        }catch (LottoException lottoException){
            OutputView.printError(lottoException);
            return true;
        }
        return false;
    }

    private void settingBonusNumbers() {
        boolean retry = true;
        while(retry) {
            retry = inputBonusNumber();
        }
    }

    private boolean inputBonusNumber() {
        try{
            String inputBonusNumber = InputView.inputBonusNumber();
            inputBonusNumber(inputBonusNumber);
        }catch (LottoException lottoException){
            OutputView.printError(lottoException);
            return true;
        }
        return false;
    }

    private void printWinningResult() {
        System.out.println(formattingWinningResult());
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
