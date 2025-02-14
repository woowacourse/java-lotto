package lotto.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import lotto.model.Lotto;
import lotto.model.LottoNumber;
import lotto.model.Lottos;
import lotto.model.RandomNumberGenerator;
import lotto.model.ReturnRatioGenerator;
import lotto.model.WinningLotto;
import lotto.model.WinningResultResponses;
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
            String money = inputView.readLine();
            issueLottoTickets(money);
            printIssuedLottoTickets();

            WinningLotto winningLotto = createWinningLotto();
            WinningResultResponses winningResultResponses = winningLotto.calculateWinning(lottos);
            outputView.printWinningResult(winningResultResponses);
            double returnRatio = ReturnRatioGenerator.calculateReturnRatio(Integer.parseInt(money),
                    winningResultResponses);
            outputView.printWinningRatio(returnRatio);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }

    private WinningLotto createWinningLotto() {
        String winningLottoNumber = inputView.readWinningLotto();
        String bonusNumber = inputView.readBonusNumber();
        return new WinningLotto(new Lotto(toLottoNumbers(winningLottoNumber)), new LottoNumber(Integer.parseInt(bonusNumber)));
    }

    private void issueLottoTickets(final String money) {
        int buyingAmount = parseInt(money);
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
//        while (hasDuplication(randomNumbers)) {
//            randomNumbers = RandomNumberGenerator.generate();
//        }
        lottos.add(new Lotto(randomNumbers));
    }

    private boolean hasDuplication(final List<LottoNumber> randomNumbers) {
        HashSet<LottoNumber> uniqueNumbers = new HashSet<>(randomNumbers);
        return uniqueNumbers.size() != randomNumbers.size();
    }

    private int parseInt(final String money) {
        try {
            return Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 금액 형식입니다.");
        }
    }

}
