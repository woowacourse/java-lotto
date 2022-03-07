package controller;

import dto.LottoDto;
import dto.RankResultDto;
import java.util.ArrayList;
import model.LottoMachine;
import model.lottonumber.Lotto;
import model.rank.Rank;
import model.winningresult.WinningResult;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private LottoMachine lottoMachine;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        showLottoPurchaseProcess();
        showLottoWinningResult();
    }

    private void showLottoPurchaseProcess() {
        lottoMachine = new LottoMachineInitializer(inputView).initLottoMachine();

        outputView.printTotalLottoCount(lottoMachine.getManualLottoCount(),
                lottoMachine.getAutoLottoCount());
        outputView.printTotalLottoGroupNumbers(convertLottosToDtos(lottoMachine.getLottos()));
    }

    private List<LottoDto> convertLottosToDtos(final List<Lotto> lottos) {
        List<LottoDto> lottoDtos = new ArrayList<>();
        for (Lotto lotto : lottos) {
            lottoDtos.add(LottoDto.of(lotto));
        }
        return lottoDtos;
    }

    private void showLottoWinningResult() {
        List<Integer> winningNumbers = inputView.inputWinningNumbers();
        int bonusNumber = inputView.inputBonusNumber();

        WinningResult winningResult = lottoMachine.makeLottoWinningResult(winningNumbers, bonusNumber);
        int totalLottoCount =
                lottoMachine.getManualLottoCount() + lottoMachine.getAutoLottoCount();
        printWinningResult(winningResult, totalLottoCount);
    }

    private void printWinningResult(final WinningResult winningResult, final int totalPurchaseLottoCount) {
        final List<RankResultDto> rankResultDtos = convertWinningResultToDtos(winningResult.getValue());

        outputView.printWinningResult(rankResultDtos);
        outputView.printRateOfReturn(winningResult.calculateRateOfReturn(totalPurchaseLottoCount));
    }

    private List<RankResultDto> convertWinningResultToDtos(final Map<Rank, Integer> results) {
        return results.keySet().stream()
                .map(rank -> new RankResultDto(rank, results.get(rank)))
                .collect(Collectors.toUnmodifiableList());
    }
}
