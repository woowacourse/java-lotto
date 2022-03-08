import controller.LottoController;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import service.LottoService;
import service.dto.BudgetDto;
import service.dto.LottoDto;
import service.dto.LottoResultDto;
import service.dto.ManualLottoCountDto;
import service.dto.ManualLottosRequest;
import service.dto.PurchasedLottosDto;
import service.dto.WinningLottoNumbersDto;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        LottoController lottoController = new LottoController(new LottoService());
        InputView.runUntilValid(() -> requestPurchase(lottoController));
        InputView.runUntilValid(() -> requestMatch(lottoController));
    }

    private static void requestPurchase(LottoController lottoController) {
        BudgetDto budgetDto = new BudgetDto(InputView.inputBudget());
        ManualLottoCountDto manualLottoCountDto = new ManualLottoCountDto(InputView.inputManualCount());
        ManualLottosRequest manualLottosRequest = new ManualLottosRequest(getManualLottoNumbers(manualLottoCountDto));
        PurchasedLottosDto purchasedLottosDto = lottoController.issueLottos(budgetDto, manualLottoCountDto,
                manualLottosRequest);
        OutputView.printIssuedLottos(purchasedLottosDto);
    }

    private static List<List<Integer>> getManualLottoNumbers(ManualLottoCountDto manualCount) {
        InputView.printManualLottoMessage();
        return IntStream.range(0, manualCount.getCount())
                .mapToObj(i -> InputView.inputLottos())
                .collect(Collectors.toList());
    }

    private static void requestMatch(LottoController lottoController) {
        WinningLottoNumbersDto winningLottoNumbersDto = new WinningLottoNumbersDto(
                new LottoDto(InputView.inputWinningLotto()), InputView.inputBonusNumber());
        LottoResultDto lottoResultDto = lottoController.matchLottos(winningLottoNumbersDto);
        OutputView.printResult(lottoResultDto);
    }
}
