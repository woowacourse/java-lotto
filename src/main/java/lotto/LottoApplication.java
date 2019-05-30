package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoApplication {
    public static void main(String[] args) {
        int countOfPurchase = setPurchaseAmount() / 1000;
        OutputView.outputCountOfPurchase(countOfPurchase);
        Lottos lottos = new Lottos(setManualLottoNumbers(setManualPurchaseCount(countOfPurchase)), countOfPurchase);
        OutputView.outputLottos(lottos);
        WinningResult winningResult = lottos.match(setWinningLotto());
        OutputView.outputWinningResult(winningResult);
        OutputView.outputRevenueRate(winningResult.calculateRevenueRate(countOfPurchase));
    }

    private static int setPurchaseAmount() {
        try {
            return InputView.inputPurchaseAmount();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return setPurchaseAmount();
        }
    }

    private static int setManualPurchaseCount(int countOfPurchase) {
        try {
            int manualPurchaseCount = InputView.inputManualPurchaseCount();
            if (manualPurchaseCount > countOfPurchase) {
                throw new IllegalArgumentException("구매 횟수보다 많습니다.");
            }
            return manualPurchaseCount;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return setManualPurchaseCount(countOfPurchase);
        }
    }

    private static List<String> setManualLottoNumbers(int manualPurchaseCount) {
        List<String> manualLottoNumbers = new ArrayList<>();
        while (manualLottoNumbers.size() < manualPurchaseCount) {
            manualLottoNumbers.add(InputView.inputManualLottoNumbers());
        }
        return manualLottoNumbers;
    }

    private static WinningLotto setWinningLotto() {
        try {
            Lotto winningLotto = LottoFactory.createLottoManually(InputView.inputWinningLotto());
            return new WinningLotto(winningLotto, setBonusNumber(winningLotto));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return setWinningLotto();
        }
    }

    private static LottoNumber setBonusNumber(Lotto winningLotto) {
        try {
            LottoNumber bonusNumber = LottoNumber.get(InputView.inputBonusNumber());
            if (winningLotto.contain(bonusNumber)) {
                throw new IllegalArgumentException("당첨 번호에 포함된 숫자 입니다. 다시 입력해주세요");
            }
            return bonusNumber;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return setBonusNumber(winningLotto);
        }
    }
}
