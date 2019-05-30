package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoApplication {
    private static final int LOTTO_PRICE = 1000;

    public static void main(String[] args) {
        int purchaseAmount = setPurchaseAmount();
        OutputView.outputCountOfPurchase(purchaseAmount / LOTTO_PRICE);
        Lottos lottos = new Lottos(
                setManualLottoNumbers(setManualPurchaseCount(purchaseAmount / LOTTO_PRICE)),
                purchaseAmount / LOTTO_PRICE);
        OutputView.outputLottos(lottos);
        WinningResult winningResult = lottos.match(setWinningLotto());
        OutputView.outputWinningResult(winningResult);
        OutputView.outputRevenueRate(winningResult.calculateRevenueRate(purchaseAmount));
    }

    private static int setPurchaseAmount() {
        try {
            int purchaseAmount = InputView.inputPurchaseAmount();
            if (purchaseAmount < LOTTO_PRICE) {
                throw new IllegalArgumentException("1000원 이상 입력하세요.");
            }
            return purchaseAmount;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return setPurchaseAmount();
        }
    }

    private static int setManualPurchaseCount(int countOfPurchase) {
        try {
            int manualPurchaseCount = InputView.inputManualPurchaseCount();
            if (manualPurchaseCount > countOfPurchase || manualPurchaseCount < 0) {
                throw new IllegalArgumentException("구매 횟수보다 적은 0 이상의 정수를 입력해주세요.");
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
