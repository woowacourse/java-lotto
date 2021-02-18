package lotto.controller;

import java.util.Scanner;
import lotto.view.InputView;

public class LottoController {

    private final InputView inputView;

    public LottoController(Scanner scanner) {
        this.inputView = new InputView(scanner);
    }

    public void play() {
    }
}


