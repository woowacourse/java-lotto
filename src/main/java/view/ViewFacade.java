package view;

import dto.LottosDto;
import dto.StatisticsDto;

public class ViewFacade {
    private final InputView inputView;
    private final OutputView outputView;

    public ViewFacade(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public String getPurchaseInput() {
        return inputView.getPurchaseInput();
    }

    public String getWinningNumbers() {
        return inputView.getWinningNumbers();
    }

    public String getBonusNumber() {
        return inputView.getBonusNumber();
    }

    public void printLottos(LottosDto lottosDto) {
        outputView.printLottos(lottosDto);
    }

    public void printStatistics(StatisticsDto statisticsDto) {
        outputView.printStatistics(statisticsDto);
    }
}
