package controller;

import dto.LottoDto;
import dto.RankResultDto;
import model.LottoMachine;
import model.lottonumber.Lottos;
import model.money.PurchaseMoney;
import model.lottonumber.Lotto;
import model.rank.Rank;
import model.lottonumber.WinningNumbers;
import model.winningresult.WinningResult;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        LottoMachine lottoMachine = new LottoMachineInitializer(inputView).initLottoMachine();

        /*
        WinningNumbers winningNumbers = insertWinningInformation();
        showWinningResult(lottos, winningNumbers, purchaseMoney);
    */
    }

    private List<LottoDto> convertLottosToDtos(List<Lotto> lottos) {
        return lottos.stream()
                .map(lotto -> new LottoDto(lotto.getLottoNumbers()))
                .collect(Collectors.toUnmodifiableList());
    }

    private WinningNumbers insertWinningInformation() {
        List<Integer> winningNumbers = insertWinningNumbers();
        int bonusNumber = insertBonusNumber();
        try {
            return new WinningNumbers(winningNumbers, bonusNumber);
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
            return insertWinningInformation();
        }
    }

    private List<Integer> insertWinningNumbers() {
        try {
            return inputView.inputWinningNumbers();
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
            return insertWinningNumbers();
        }
    }

    private int insertBonusNumber() {
        try {
            return inputView.inputBonusNumber();
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
            return insertBonusNumber();
        }
    }

    private void showWinningResult(Lottos lottos, WinningNumbers winningNumbers, PurchaseMoney purchaseMoney) {
        WinningResult winningResult = lottos.makeWinningResult(winningNumbers);
        List<RankResultDto> rankResultDtos = convertWinningResultToDtos(winningResult.getWinningResult());

        outputView.printWinningResult(rankResultDtos);
        outputView.printRateOfReturn(winningResult.getRateOfReturn(purchaseMoney));
    }

    private List<RankResultDto> convertWinningResultToDtos(final Map<Rank, Integer> results) {
        return results.keySet().stream()
                .map(rank -> new RankResultDto(rank, results.get(rank)))
                .collect(Collectors.toUnmodifiableList());
    }
}
