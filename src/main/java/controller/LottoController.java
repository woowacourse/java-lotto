package controller;

import view.Output;

public class LottoController {

    private final Output output;

    public LottoController(Output output) {
        this.output = output;
    }

    public void run() {
        output.printStartMessage();
    }
}
