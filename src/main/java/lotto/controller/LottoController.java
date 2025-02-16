package lotto.controller;

import java.util.List;

import lotto.controller.generator.LottoNumbersGenerator;
import lotto.model.Money;
import lotto.model.lotto.Lotto;
import lotto.model.lotto.LottoMachine;
import lotto.model.lotto.LottoNumber;
import lotto.model.lotto.Lottos;
import lotto.model.winning.WinningLotto;
import lotto.model.winning.WinningResultResponses;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        try {
            Money buyingAmount = new Money(inputView.readBuyingAmount());
            Lottos lottoTickets = issueRandomLottoTickets(buyingAmount);
            printIssuedLottoTickets(lottoTickets);

            WinningLotto winningLotto = createWinningLotto();
            WinningResultResponses winningResultResponses = winningLotto.calculateWinning(lottoTickets);
            printWinningResult(winningResultResponses, buyingAmount);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }

    private WinningLotto createWinningLotto() {
        Lotto winningLotto = new Lotto(inputView.readWinningLotto());
        LottoNumber bonusNumber = LottoNumber.draw(inputView.readBonusNumber());
        return new WinningLotto(winningLotto, bonusNumber);
    }

    private Lottos issueRandomLottoTickets(final Money buyingAmount) {
        LottoMachine lottoMachine = new LottoMachine();
        LottoNumbersGenerator numbersGenerator = new LottoNumbersGenerator(
                LottoNumber.MIN_LOTTO_NUMBER, LottoNumber.MAX_LOTTO_NUMBER, Lotto.LOTTO_SIZE
        );
        outputView.printChangeAmount(lottoMachine.calculateChange(buyingAmount));
        return lottoMachine.issueAutomatic(buyingAmount, numbersGenerator);
    }

    private void printIssuedLottoTickets(final Lottos lottoTickets) {
        List<List<Integer>> issuedLottoNumbers = lottoTickets.getLottos()
                .stream()
                .map(Lotto::getNumbers)
                .toList();
        outputView.printIssuedLottos(issuedLottoNumbers);
    }

    private void printWinningResult(final WinningResultResponses winningResultResponses, final Money buyingAmount) {
        outputView.printWinningResult(winningResultResponses);
        outputView.printWinningRatio(
                buyingAmount.calculateReturnRatio(winningResultResponses.calculateTotalReturn()));
    }

}
