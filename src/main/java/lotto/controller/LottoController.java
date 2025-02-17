package lotto.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lotto.Rank;
import lotto.model.Cashier;
import lotto.model.DashBoard;
import lotto.model.Lotto;
import lotto.model.LottoNumber;
import lotto.view.Console;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        int purchaseAmount = inputView.requestPurchaseAmount();
        List<Lotto> lottos = purchaseLotto(purchaseAmount);
        Lotto winningLotto = getWinningLotto();
        LottoNumber bonusNumber = getBonusNumber();
        validateBonusNumber(winningLotto, bonusNumber);
        DashBoard dashBoard = judgeLottoResult(lottos, winningLotto, bonusNumber);
        printResult(dashBoard, purchaseAmount);
        end();
    }

    private LottoNumber getBonusNumber() {
        int bonusNumber = inputView.requestBonusNumber();
        return new LottoNumber(bonusNumber);
    }

    private List<Lotto> purchaseLotto(int purchaseAmount) {
        List<Lotto> lottos = Cashier.payForLotto(purchaseAmount);
        outputView.printLottos(convertLottoDtos(lottos));
        return lottos;
    }

    private List<LottoDto> convertLottoDtos(List<Lotto> lottos) {
        return lottos.stream()
                .map(LottoDto::from)
                .toList();
    }

    private Lotto getWinningLotto() {
        Set<LottoNumber> lottoNumbers = new HashSet<>();
        List<Integer> numbers = List.copyOf(inputView.requestWinningNumbers());
        numbers.forEach(number -> lottoNumbers.add(new LottoNumber(number)));
        return new Lotto(lottoNumbers);
    }

    private void validateBonusNumber(Lotto winningLotto, LottoNumber bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨번호와 중복될 수 없습니다.");
        }
    }

    private float calculateRatio(Map<Rank, Integer> ranks, int purchaseAmount) {
        return (float) calculateTotalWinningAmount(ranks) / purchaseAmount;
    }

    private int calculateTotalWinningAmount(Map<Rank, Integer> ranks) {
        int totalAmount = 0;
        for (Rank rank : ranks.keySet()) {
            totalAmount += rank.getWinningAmount() * ranks.get(rank);
        }
        return totalAmount;
    }

    private DashBoard judgeLottoResult(List<Lotto> lottos, Lotto winningLotto, LottoNumber bonusNumber) {
        DashBoard dashBoard = new DashBoard();
        for (Lotto lotto : lottos) {
            dashBoard.recordResult(lotto, winningLotto, bonusNumber);
        }
        return dashBoard;
    }

    private void printResult(DashBoard dashBoard, int purchaseAmount) {
        outputView.printResult(dashBoard.getRanks());
        outputView.printWinningRatio(calculateRatio(dashBoard.getRanks(), purchaseAmount));
    }

    private void end() {
        Console.close();
    }
}
