package lotto.controller;

import java.util.List;

import lotto.domain.LottoMachine;
import lotto.domain.LottoTicket;
import lotto.domain.Money;
import lotto.domain.generator.StringInputNumberGenerator;
import lotto.utils.IntegerUtils;
import lotto.view.ErrorView;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ErrorView errorView;

    private LottoController(InputView inputView, OutputView outputView, ErrorView errorView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.errorView = errorView;
    }

    private static class LottoControllerHelper {
        private static final LottoController INSTANCE = new LottoController(
            InputView.getInstance(), OutputView.getInstance(), ErrorView.getInstance()
        );
    }

    public static LottoController getInstance() {
        return LottoControllerHelper.INSTANCE;
    }

}
