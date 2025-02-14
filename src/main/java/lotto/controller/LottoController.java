package lotto.controller;

import java.util.Arrays;
import java.util.List;

import lotto.model.Lotto;
import lotto.model.LottoNumber;
import lotto.model.Lottos;
import lotto.model.Money;
import lotto.model.RandomNumberGenerator;
import lotto.model.WinningLotto;
import lotto.model.WinningResults;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final Lottos lottos;
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(final InputView inputView, final OutputView outputView) {
        this.lottos = new Lottos();
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        try {
            Money money = new Money(Integer.parseInt(inputView.readPurchaseMoney()));
            issueLottoTickets(money);
            printIssuedLottoTickets();
            WinningLotto winningLotto = createWinningLotto();
            WinningResults winningResults = winningLotto.calculateWinning(lottos);
            outputView.printWinningResult(winningResults);
            double returnRatio = money.calculateReturnRatio(winningResults.calculateEarnedMoney());
            outputView.printWinningRatio(returnRatio);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }

    private WinningLotto createWinningLotto() {
        String winningLottoNumberInput = inputView.readWinningLotto();
        Lotto winningLotto = new Lotto(toLottoNumbers(winningLottoNumberInput));

        String bonusNumberInput = inputView.readBonusNumber();
        LottoNumber bonusNumber = new LottoNumber(Integer.parseInt(bonusNumberInput));
        return new WinningLotto(winningLotto, bonusNumber);
    }

    private void issueLottoTickets(final Money money) {
        int count = (int) money.getBuyingMoney() / Money.UNIT_PRICE;
        for (int i = 0; i < count; i++) {
            addLotto();
        }
    }

    private void printIssuedLottoTickets() {
        List<List<Integer>> issuedLottoNumbers = lottos.getLottos()
                .stream()
                .map(Lotto::getNumbers)
                .map(lottoNumbers -> lottoNumbers.stream()
                        .map(LottoNumber::getNumber)
                        .toList())
                .toList();

        outputView.printIssuedLottos(issuedLottoNumbers);
    }

    private List<LottoNumber> toLottoNumbers(final String winningLottoNumber) {
        return Arrays.stream(winningLottoNumber.split(","))
                .map(String::strip)
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .toList();
    }

    private void addLotto() {
        List<LottoNumber> randomNumbers = RandomNumberGenerator.generate();
        lottos.add(new Lotto(randomNumbers));
    }

    private int parseInt(final String money) {
        try {
            return Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 금액 형식입니다.");
        }
    }

}
