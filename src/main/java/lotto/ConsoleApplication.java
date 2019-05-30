package lotto;

import lotto.Utils.NumbersSplitter;
import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class ConsoleApplication {
    public static void main(String[] args) {
        PurchaseInformation purchaseInformation =
                new PurchaseInformation(InputView.inputMoney(), InputView.inputNumberOfManualLottos());

        Lottos lottos = purchaseLottos(purchaseInformation);

        LottoGame lottoGame = setUpLottoGame();

        LottoResult lottoResult = lottoGame.play(lottos);
        OutputView.outputResult(lottoResult);
    }

    private static Lottos purchaseLottos(PurchaseInformation purchaseInformation) {
        int numberOfManualLottos = purchaseInformation.getNumberOfManualLottos();
        int numberOfAutoLottos = purchaseInformation.getNumberOfAutoLottos();

        Lottos lottos = LottoMachine.buyManualLotto(makeManualLottosNumbers(numberOfManualLottos));
        lottos.addAll(LottoMachine.buyAutoLottos(numberOfAutoLottos));

        OutputView.outputLottosMessage(numberOfManualLottos, numberOfAutoLottos);
        OutputView.outputLottos(lottos);
        return lottos;
    }

    private static List<LottoNumbers> makeManualLottosNumbers(int numberOfManualLottos) {
        OutputView.outputManualLottosMessage();
        List<LottoNumbers> lottoNumbers = new ArrayList<>();
        for (int i = 0; i < numberOfManualLottos; i++) {
            lottoNumbers.add(makeLottoNumbers());
        }
        return lottoNumbers;
    }

    private static LottoGame setUpLottoGame() {
        OutputView.outputWinningNumbersMessage();
        LottoNumbers winningNumbers = makeLottoNumbers();
        LottoNumber bonusNumber = LottoNumbersGenerator.getLottoNumber(InputView.inputBonusBall());

        WinningInformation winningInformation = new WinningInformation(winningNumbers, bonusNumber);
        return new LottoGame(winningInformation);
    }

    private static LottoNumbers makeLottoNumbers() {
        try {
            List<Integer> inputNumbers = NumbersSplitter.splitNumbers(InputView.inputLottoNumbers());
            return LottoNumbersGenerator.getLottoNumbers(inputNumbers);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return makeLottoNumbers();
        }
    }
}
