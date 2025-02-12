package lotto;

public class Main {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        new LottoController(inputView).start();
    }
}
