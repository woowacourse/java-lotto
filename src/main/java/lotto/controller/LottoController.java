package lotto.controller;

import java.util.ArrayList;
import java.util.List;

import lotto.controller.generator.LottoNumbersGenerator;
import lotto.model.Money;
import lotto.model.lotto.Lotto;
import lotto.model.lotto.LottoMachine;
import lotto.model.lotto.LottoNumber;
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
            List<Lotto> lottoTickets = issueRandomLottoTickets(buyingAmount);
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

    private List<Lotto> issueRandomLottoTickets(final Money buyingAmount) {
        LottoMachine lottoMachine = new LottoMachine(buyingAmount);
        LottoNumbersGenerator numbersGenerator = new LottoNumbersGenerator(
                LottoNumber.MIN_LOTTO_NUMBER, LottoNumber.MAX_LOTTO_NUMBER, Lotto.LOTTO_SIZE
        );
        outputView.printChangeAmount(lottoMachine.calculateChange());
        return lottoMachine.issueAutomatic(numbersGenerator);
    }

    private void printIssuedLottoTickets(final List<Lotto> lottoTickets) {
        List<List<Integer>> issuedLottoNumbers = new ArrayList<>();
        for (Lotto lottoTicket : lottoTickets) {
            addSortedLottoNumbers(lottoTicket, issuedLottoNumbers);
        }
        outputView.printIssuedLottos(issuedLottoNumbers);
    }

    private void addSortedLottoNumbers(final Lotto lottoTicket, final List<List<Integer>> issuedLottoNumbers) {
        issuedLottoNumbers.add(new ArrayList<>(lottoTicket.getNumbers())
                .stream()
                .sorted()
                .toList());
    }

    private void printWinningResult(final WinningResultResponses winningResultResponses, final Money buyingAmount) {
        outputView.printWinningResult(winningResultResponses);
        outputView.printWinningRatio(
                buyingAmount.calculateReturnRatio(winningResultResponses.calculateTotalReturn()));
    }

}
