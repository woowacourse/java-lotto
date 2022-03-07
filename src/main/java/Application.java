import controller.LottoController;
import controller.dto.LottosDto;
import controller.dto.StatisticDto;
import domain.Lottos;
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
        Lottos lottos = lottoController.purchase(inputMoney, manualLottoCount, manualLottoNumbers);
        OutputView.printCountOfLotto(lottos.size(), manualLottoCount);

        OutputView.printLottos(LottosDto.from(lottos));
        String[] inputWinningNumber = InputView.askInputWinningNumber();
        int inputBonusBall = InputView.askInputBonusBall();

        StatisticDto statisticDto = lottoController.winningResult(inputWinningNumber, inputBonusBall, lottos);
        OutputView.printStatistics(statisticDto);
        OutputView.printProfitRate(statisticDto.getStatistic().getProfitRate(new Money(inputMoney)));

    }
}
