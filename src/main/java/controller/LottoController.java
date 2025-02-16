package controller;

import domain.BonusNumber;
import domain.LottoDispenser;
import domain.WinningLotto;
import domain.WinningNumber;
import dto.DrawResultDto;
import exception.LottoException;
import view.InputView;
import view.OutputView;

public class LottoController {

    public void startLotto() {
        LottoDispenser lottoDispenser = buyLotto();
        displayBuyLottos(lottoDispenser);
        WinningNumber winningNumber = settingWinningNumbers();
        WinningLotto winningLotto = settingWinningLotto(winningNumber);
        displayDrawResult(lottoDispenser.getDrawResult(winningLotto));
    }

    private LottoDispenser buyLotto() {
        LottoDispenser lottoDispenser;
        do {
            lottoDispenser = buyLottoInput();
            return lottoDispenser;
        } while (lottoDispenser != null);
    }

    private LottoDispenser buyLottoInput() {
        try{
            String inputBuyLottoMoney = InputView.inputBuyLottoMoney();
            return new LottoDispenser(inputBuyLottoMoney);
        }catch (LottoException lottoException){
            OutputView.printError(lottoException);
            return null;
        }
    }

    private void displayBuyLottos(LottoDispenser lottoDispenser) {
        OutputView.printBuyLottos(lottoDispenser.getBuyLottos());
    }

    private WinningNumber settingWinningNumbers() {
        WinningNumber winningNumber;
        do {
            winningNumber = inputWinningNumber();
            return winningNumber;
        } while (winningNumber != null);
    }

    private WinningNumber inputWinningNumber() {
        try{
            String inputWinningNumber = InputView.inputWinningNumber();
            return new WinningNumber(inputWinningNumber);
        }catch (LottoException lottoException){
            OutputView.printError(lottoException);
            return null;
        }
    }

    private WinningLotto settingWinningLotto(WinningNumber winningNumber) {
        WinningLotto winningLotto = null;
        while (winningLotto == null) {
            winningLotto = checkWinningLotto(winningNumber);
        }
        return winningLotto;
    }

    private WinningLotto checkWinningLotto(WinningNumber winningNumber) {
        try{
            String inputBonusNumber = InputView.inputBonusNumber();
            return generateWinningLotto(winningNumber, new BonusNumber(inputBonusNumber));
        }catch (LottoException lottoException){
            OutputView.printError(lottoException);
            return null;
        }
    }
    private WinningLotto generateWinningLotto(WinningNumber winningNumber, BonusNumber bonusNumber) {
        try {
            return new WinningLotto(winningNumber, bonusNumber);
        } catch (LottoException lottoException) {
            OutputView.printError(lottoException);
            return null;
        }
    }

    private void displayDrawResult(DrawResultDto drawResultDto) {
        OutputView.printDrawResult(drawResultDto);
    }

}
