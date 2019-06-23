package lotto;

import lotto.dto.LottosDTO;
import lotto.dto.WinningResultDTO;
import lotto.service.LottoService;
import lotto.service.RoundService;
import lotto.service.WinningLottoService;
import lotto.util.StringUtil;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoApplication {
    private static final int PRICE_PER_LOTTO = 1000;
    private static final String COMMA_DELIMITER = ",";

    private static final RoundService roundService = RoundService.getInstance();
    private static final LottoService lottoService = LottoService.getInstance();
    private static final WinningLottoService winningLottoService = WinningLottoService.getInstance();

    public static void main(String[] args) {
        int purchaseAmount = setPurchaseAmount();
        roundService.addRound(purchaseAmount);
        OutputView.outputCountOfPurchase(purchaseAmount / PRICE_PER_LOTTO);
        LottosDTO.Create lottos = lottoService.createLottos(
                setManualLottoNumbers(setManualPurchaseCount(purchaseAmount / PRICE_PER_LOTTO)));
        OutputView.outputLottos(lottos.getLottos());
        List<String> winningLottoNumbers = setWinningLotto();
        winningLottoService.createWinningLotto(
                winningLottoNumbers, setBonusNumber(winningLottoNumbers));
        WinningResultDTO.Create result = winningLottoService.calculateResult(roundService.findMaxRound());
        OutputView.outputWinningResult(result.getResult());
        OutputView.outputRevenueRate(result.getRevenueRate());
    }

    private static int setPurchaseAmount() {
        try {
            int purchaseAmount = InputView.inputPurchaseAmount();
            if (purchaseAmount < PRICE_PER_LOTTO) {
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

    private static List<String> setWinningLotto() {
        try {
            return StringUtil.convertToList(InputView.inputWinningLotto(), COMMA_DELIMITER);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return setWinningLotto();
        }
    }

    private static int setBonusNumber(List<String> winningLotto) {
        try {
            int bonusNumber = InputView.inputBonusNumber();
            if (winningLotto.contains(String.valueOf(bonusNumber))) {
                throw new IllegalArgumentException("당첨 번호에 포함된 숫자 입니다. 다시 입력해주세요");
            }
            return bonusNumber;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return setBonusNumber(winningLotto);
        }
    }
}
