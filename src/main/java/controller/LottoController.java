package controller;

public class LottoController {
    
    private final InputView inputView;
    private final OutputView outputView;
    
    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }
    
    public void run() {
        outputView.printInputMoney();
        int money = inputView.inputMoney();
    }
}
