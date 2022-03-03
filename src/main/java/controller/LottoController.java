package controller;

import dto.LottoDto;
import dto.RankResultDto;
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
        purchaseLottos();
        showLottoWinningResult();
    }

    private void purchaseLottos() {
        lottoMachine = new LottoMachineInitializer(inputView).initLottoMachine();

        outputView.printTotalPurchaseLottoCount(lottoMachine.sendManualLottoCount(), lottoMachine.sendAutoLottoCount());
        outputView.printTotalLottos(convertLottosToDtos(lottoMachine.sendLottosInMachine()));
    }

    private List<LottoDto> convertLottosToDtos(final List<Lotto> lottos) {
        return lottos.stream()
                .map(lotto -> new LottoDto(lotto.getLottoNumbers()))
                .collect(Collectors.toUnmodifiableList());
    }

    private void showLottoWinningResult() {
        List<Integer> winningNumbers = inputView.inputWinningNumbers();
        int bonusNumber = inputView.inputBonusNumber();

        WinningResult winningResult = lottoMachine.makeLottoWinningResult(winningNumbers, bonusNumber);
        int totalPurchaseCount = lottoMachine.sendTotalPurchaseLottoCount();
        printWinningResult(winningResult, totalPurchaseCount);
    }

    private void printWinningResult(final WinningResult winningResult, final int totalPurchaseLottoCount) {
        final List<RankResultDto> rankResultDtos = convertWinningResultToDtos(winningResult.getWinningResult());

        outputView.printWinningResult(rankResultDtos);
        outputView.printRateOfReturn(winningResult.sendRateOfReturn(totalPurchaseLottoCount));
    }

    private List<RankResultDto> convertWinningResultToDtos(final Map<Rank, Integer> results) {
        return results.keySet().stream()
                .map(rank -> new RankResultDto(rank, results.get(rank)))
                .collect(Collectors.toUnmodifiableList());
    }
}
