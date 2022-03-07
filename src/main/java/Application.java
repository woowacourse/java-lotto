import controller.LottoController;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import model.Budget;
import model.IssuedLottos;
import model.LottoNumber;
import model.LottoResult;
import model.ManualLottoCount;
import model.WinningLottoNumbers;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView.runUntilValid(Application::run);
    }

    public static void run() {
        LottoController controller = new LottoController();

        Budget budget = controller.getBudget(InputView.inputBudget());
        IssuedLottos allLottos = issueLottos(controller, budget);

        WinningLottoNumbers winningLottoNumbers = controller.getWinningLottoNumbers(InputView.inputWinningLotto(),
                InputView.inputBonusNumber());
        LottoResult result = controller.getResultOf(allLottos, winningLottoNumbers);
        OutputView.printResult(result.getResultMap(), result.getProfitRate(budget));
    }

    private static IssuedLottos issueLottos(LottoController controller, Budget budget) {
        ManualLottoCount manualCount = controller.getManualCount(InputView.inputManualCount(), budget);
        InputView.printManualLottoMessage();
        IssuedLottos manualLottos = controller.getManualLottos(inputManualLottos(manualCount));
        IssuedLottos autoLottos = controller.getAutoLottos(budget, manualLottos);
        IssuedLottos allLottos = controller.getAllLottos(manualLottos, autoLottos);
        OutputView.printIssuedLottos(manualLottos.getLottosCount(), autoLottos.getLottosCount(),
                getNumbersOf(allLottos));
        return allLottos;
    }

    private static List<List<String>> inputManualLottos(ManualLottoCount manualCount) {
        List<List<String>> manualLottosTokens = new ArrayList<>();
        for (int i = 0; !manualCount.isSameCount(i); i++) {
            manualLottosTokens.add(InputView.inputLottos());
        }
        return manualLottosTokens;
    }

    private static List<Set<Integer>> getNumbersOf(IssuedLottos issuedLottos) {
        return issuedLottos.getLottos().stream()
                .map(lotto -> toInts(lotto.getLottoNumbers()))
                .collect(Collectors.toList());
    }

    private static Set<Integer> toInts(Set<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
                .map(LottoNumber::intValue)
                .collect(Collectors.toSet());
    }
}
