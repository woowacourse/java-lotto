import controller.LottoController;
import controller.dto.LottosDto;
import domain.Money;
import domain.Statistic;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        LottoController lottoMachine = new LottoController();
        int inputMoney = InputView.askInputMoney();
        int manualLottoCount = InputView.askManualLottoCount();
        List<String> manualLottoNumbers = InputView.askManualLottoNumbers(manualLottoCount);
        LottosDto lottosDto = lottoMachine.purchase(inputMoney, manualLottoCount, manualLottoNumbers);
        OutputView.printCountOfLotto(lottosDto.getAutoLottoCount(), manualLottoCount);

        LottosDto lottos = lottoMachine.createLottos(lottosDto);
        OutputView.printLottos(lottos.getLottos());
        String inputWinningNumber = InputView.askInputWinningNumber();
        int inputBonusBall = InputView.askInputBonusBall();
        Statistic statistic = lottoMachine.winningResult(inputWinningNumber, inputBonusBall, lottos);
        OutputView.printStatistics(statistic);
        OutputView.printProfitRate(statistic, new Money(inputMoney));

    }
}
