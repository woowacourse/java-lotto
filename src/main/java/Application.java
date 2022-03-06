import controller.LottoController;
import controller.dto.LottoGeneratorDto;
import controller.dto.LottosDto;
import controller.dto.StatisticDto;
import domain.Lottos;
import domain.Money;
import domain.Statistic;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController();
        int inputMoney = InputView.askInputMoney();
        int manualLottoCount = InputView.askManualLottoCount();
        List<String[]> manualLottoNumbers = InputView.askManualLottoNumbers(manualLottoCount);
        LottoGeneratorDto lottoGeneratorDto = lottoController.purchase(inputMoney, manualLottoCount, manualLottoNumbers);
        OutputView.printCountOfLotto(lottoGeneratorDto.getAutoLottoCount(), manualLottoCount);

        Lottos lottos = Lottos.generateLottos(lottoGeneratorDto.getLottoGenerators());
        OutputView.printLottos(LottosDto.from(lottos));
        String[] inputWinningNumber = InputView.askInputWinningNumber();
        int inputBonusBall = InputView.askInputBonusBall();

        Statistic statistic = lottoController.winningResult(inputWinningNumber, inputBonusBall, lottos);
        OutputView.printStatistics(StatisticDto.from(statistic));
        OutputView.printProfitRate(statistic.getProfitRate(new Money(inputMoney)));

    }
}
