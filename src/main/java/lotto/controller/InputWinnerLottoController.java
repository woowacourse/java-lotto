package lotto.controller;

import static lotto.controller.ControllerTemplate.supplierTemplate;
import static lotto.view.InputView.inputBonusText;
import static lotto.view.InputView.inputWinnerLottoText;

import lotto.model.Lotto;
import lotto.model.LottoNumber;
import lotto.model.WinnerLotto;
import lotto.view.LottoConvertor;

public class InputWinnerLottoController {

    public WinnerLotto run() {
        return supplierTemplate(this::createWinnerLotto, ExceptionHandler::handle);
    }

    private WinnerLotto createWinnerLotto() {
        return new WinnerLotto(createLotto(), createBonusNumber());
    }

    private Lotto createLotto() {
        LottoConvertor lottoConvertor = new LottoConvertor();
        String lottoText = inputWinnerLottoText(ExceptionHandler::handle);
        return lottoConvertor.convert(lottoText);
    }

    private LottoNumber createBonusNumber() {
        String bonusText = inputBonusText(ExceptionHandler::handle);
        int bonus = Integer.parseInt(bonusText.trim());
        return new LottoNumber(bonus);
    }
}
