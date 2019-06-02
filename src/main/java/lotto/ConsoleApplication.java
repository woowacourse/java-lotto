package lotto;

import lotto.Utils.NumbersSplitter;
import lotto.domain.*;
import lotto.domain.LottoNumber;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class ConsoleApplication {
    public static void main(String[] args) {
        PurchaseInformation purchaseInformation = setUpPurchaseInformation();

        Lottos lottos = purchaseLottos(purchaseInformation);

        LottoGame lottoGame = setUpLottoGame();

        LottoResult lottoResult = lottoGame.play(lottos);
        OutputView.outputResult(lottoResult);
    }

    private static PurchaseInformation setUpPurchaseInformation() {
        try {
            Money money = new Money(InputView.inputMoney());
            LottoCount lottoCount =
                    new LottoCount(InputView.inputNumberOfManualLottos(), money);
            PurchaseInformation purchaseInformation = new PurchaseInformation(lottoCount);
            if (purchaseInformation.hasManualLottos()) {
                registerManualLottosNumbers(purchaseInformation);
            }
            return purchaseInformation;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return setUpPurchaseInformation();
        }
    }

    private static void registerManualLottosNumbers(PurchaseInformation purchaseInformation) {
        OutputView.requestManualLottosMessage();
        for (int i = 0; i < purchaseInformation.getManualLottoCount(); i++) {
            purchaseInformation.addManualLottoNumbers(makeLottoNumbers());
        }
    }

    private static Lottos purchaseLottos(PurchaseInformation purchaseInformation) {
        Lottos lottos = LottoMachine.buyLottos(purchaseInformation);

        OutputView.outputLottosPurchaseMessage(purchaseInformation);
        OutputView.outputLottos(lottos);
        return lottos;
    }

    private static LottoGame setUpLottoGame() {
        OutputView.requestWinningNumbersMessage();

        try {
            LottoNumbers winningNumbers = makeLottoNumbers();
            LottoNumber bonusNumber = LottoNumber.valueOf(InputView.inputBonusBall());
            WinningInformation winningInformation = new WinningInformation(winningNumbers, bonusNumber);
            return new LottoGame(winningInformation);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return setUpLottoGame();
        }
    }

    private static LottoNumbers makeLottoNumbers() {
        try {
            List<Integer> inputNumbers = NumbersSplitter.splitNumbers(InputView.inputLottoNumbers());
            return LottoNumbersGenerator.getLottoNumbers(inputNumbers);
        } catch (NumberFormatException e) {
            System.err.println("올바른 수를 입력해 주세요.");
            return makeLottoNumbers();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return makeLottoNumbers();
        }
    }
}
