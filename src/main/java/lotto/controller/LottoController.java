package lotto.controller;

import java.util.HashSet;
import java.util.List;

import lotto.model.RandomNumberGenerator;
import lotto.model.ReturnRatioGenerator;
import lotto.model.lotto.Lotto;
import lotto.model.lotto.LottoNumber;
import lotto.model.lotto.Lottos;
import lotto.model.winning.WinningLotto;
import lotto.model.winning.WinningResultResponses;
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
            int buyingAmount = inputView.readBuyingAmount();
            issueLottoTickets(buyingAmount);
            printIssuedLottoTickets();

            WinningLotto winningLotto = createWinningLotto();
            WinningResultResponses winningResultResponses = winningLotto.calculateWinning(lottos);
            outputView.printWinningResult(winningResultResponses);
            double returnRatio = ReturnRatioGenerator.calculateReturnRatio(buyingAmount, winningResultResponses);
            outputView.printWinningRatio(returnRatio);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }

    private WinningLotto createWinningLotto() {
        List<Integer> winningLottoNumbers = inputView.readWinningLotto();
        int bonusNumber = inputView.readBonusNumber();
        return new WinningLotto(new Lotto(winningLottoNumbers), LottoNumber.draw(bonusNumber));
    }

    private void issueLottoTickets(final int buyingAmount) {
        if (buyingAmount % Lottos.UNIT_PRICE != 0) {
            throw new IllegalArgumentException("천원 단위로 입력해 주세요.");
        }
        int count = buyingAmount / Lottos.UNIT_PRICE;
        for (int i = 0; i < count; i++) {
            addLotto();
        }
    }

    private void printIssuedLottoTickets() {
        List<List<Integer>> issuedLottoNumbers = lottos.getLottos()
                .stream()
                .map(Lotto::getNumbers)
                .toList();
        outputView.printIssuedLottos(issuedLottoNumbers);
    }

    private void addLotto() {
        List<Integer> randomNumbers = RandomNumberGenerator.generate();
        while (hasDuplication(randomNumbers)) {
            randomNumbers = RandomNumberGenerator.generate();
        }
        lottos.add(new Lotto(randomNumbers));
    }

    private boolean hasDuplication(final List<Integer> randomNumbers) {
        HashSet<Integer> uniqueNumbers = new HashSet<>(randomNumbers);
        return uniqueNumbers.size() != randomNumbers.size();
    }

}
