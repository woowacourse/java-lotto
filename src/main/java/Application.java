import controller.LottoController;
import controller.dto.LottosDto;
import controller.dto.StatisticDto;
import domain.Money;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController();
        int inputMoney = InputView.askInputMoney();
        int manualLottoCount = InputView.askManualLottoCount();
        List<String[]> manualLottoNumbers = InputView.askManualLottoNumbers(manualLottoCount);
        LottosDto lottosDto = lottoController.purchase(inputMoney, manualLottoCount, manualLottoNumbers);
        OutputView.printCountOfLotto(lottosDto.size(), manualLottoCount);

        OutputView.printLottos(lottosDto);
        String[] inputWinningNumber = InputView.askInputWinningNumber();
        int inputBonusBall = InputView.askInputBonusBall();

        StatisticDto statisticDto = lottoController.winningResult(inputWinningNumber, inputBonusBall, lottosDto, new Money(inputMoney));
        OutputView.printStatistics(statisticDto);
        OutputView.printProfitRate(statisticDto.getProfitRate());

    }
}
