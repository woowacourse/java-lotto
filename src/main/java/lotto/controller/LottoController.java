package lotto.controller;

import lotto.domain.LottoMachine;
import lotto.domain.lottonumber.Lotto;
import lotto.domain.lottonumber.WinningNumbers;
import lotto.domain.matchkind.LottoMatchKind;
import lotto.domain.winningresult.WinningResult;
import lotto.dto.LottoMatchKindDto;
import lotto.dto.LottoNumbersDto;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoController {
    private final LottoMachine lottoMachine;
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(final InputView inputView, final OutputView outputView, final int lottoPrice) {
        this.inputView = inputView;
        this.outputView = outputView;
        lottoMachine = new LottoMachineInitializer(inputView).initializeLottoService(lottoPrice);
    }

    public void run() {
        outputView.printPurchaseCount(
                lottoMachine.getCountOfManualLottoNumbers(), lottoMachine.getCountOfAutoLottoNumbers());
        printLottoNumbersGroup();
        final WinningNumbers winningNumbers = new WinningLottoGenerator().generateWinningNumbers(inputView);
        printResult(winningNumbers);
    }

    private void printLottoNumbersGroup() {
        final List<LottoNumbersDto> numbersGroup =
                convertLottoNumbersGroupToDtos(lottoMachine.getLottos());
        outputView.printLottoNumbersGroup(numbersGroup);
    }

    private List<LottoNumbersDto> convertLottoNumbersGroupToDtos(final List<Lotto> numbersGroup) {
        return numbersGroup.stream()
                .map(Lotto::getValues)
                .map(LottoNumbersDto::new)
                .collect(Collectors.toUnmodifiableList());
    }

    private void printResult(WinningNumbers winningNumbers) {
        final WinningResult winningResult = lottoMachine.getMatchResult(winningNumbers);
        final List<LottoMatchKindDto> winningLogs = convertWinningResultsToDtos(winningResult.getWinningNumberByKind());
        outputView.printCountOfWinningByMatchKind(winningLogs);
        outputView.printProfitRate(winningResult.getProfitRate());
    }

    private List<LottoMatchKindDto> convertWinningResultsToDtos(final Map<LottoMatchKind, Integer> results) {
        return results.keySet().stream()
                .map(lottoMatchKind -> new LottoMatchKindDto(lottoMatchKind, results.get(lottoMatchKind)))
                .collect(Collectors.toUnmodifiableList());
    }
}
