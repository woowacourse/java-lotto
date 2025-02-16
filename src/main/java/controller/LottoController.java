package controller;

import dto.LottoAmountResponse;
import global.utils.Parser;
import model.utils.Validator;
import model.LottoResult;
import model.Lottos;
import model.LottoMachine;
import model.WinningLotto;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final OutputView outputView;
    private final InputView inputView;

    public LottoController(final OutputView outputView, final InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void run() {
        LottoMachine lottoMachine = buyLotto();
        printLottoPurchaseAmount(lottoMachine);

        Lottos lottos = new Lottos(lottoMachine.getTicketAmount());
        outputView.printLottos(lottos.createResponse());

        WinningLotto winningLotto = enterWinningAndBonusNumber();
        LottoResult lottoResult = new LottoResult(lottos, winningLotto);

        printLottoResult(lottoResult, lottoMachine);
    }

    private void printLottoPurchaseAmount(final LottoMachine lottoMachine) {
        LottoAmountResponse lottoAmount = lottoMachine.createLottoAmountResponse();
        outputView.printLottoPurchaseAmount(lottoAmount);
    }

    private LottoMachine buyLotto() {
        int purchaseAmount = enterPrice();
        return new LottoMachine(purchaseAmount);
    }

    private int enterPrice() {
        try {
            String input = inputView.enterPurchasePrice();
            return Parser.parseInteger(input);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            return enterPrice();
        }
    }

    private WinningLotto enterWinningAndBonusNumber() {
        try {
            String winningNumbers = inputView.enterWinningNumbers();
            String bonusNumber = inputView.enterBonusNumber();
            return new WinningLotto(winningNumbers, bonusNumber);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            return enterWinningAndBonusNumber();
        }
    }

    private void printLottoResult(final LottoResult lottoResult, final LottoMachine lottoMachine) {
        outputView.printLottoResult(lottoResult.createResponse());
        int totalPrice = lottoResult.calculateTotalPrice();
        outputView.printReturnOfInvestmentResult(lottoMachine.createReturnOfInvestmentResponse(totalPrice));
    }
}
