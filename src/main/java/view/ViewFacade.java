package view;

import domain.Lottos;
import domain.Statistics;

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

    public void printLottos(Lottos lottos) {
        outputView.printLottos(lottos);
    }

    public void printStatistics(Statistics statistics) {
        outputView.printStatistics(statistics);
    }
}
