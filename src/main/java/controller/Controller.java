package controller;

import domain.Lotto;
import domain.Lottos;
import java.util.ArrayList;
import service.LottoMaker;
import service.parser.MoneyParser;
import view.InputView;
import view.OutputView;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoMaker lottoMaker;

    public Controller(InputView inputView, OutputView outputView, LottoMaker lottoMaker) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoMaker = lottoMaker;
    }

    public void start() {
         int money = inputLottoMoney();

        Lottos lottos = buyLotto(money / 1000);
        outputView.displayLottoNumbers(lottos);
    }

    private Lottos buyLotto(int count) {
        ArrayList<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(lottoMaker.createLotto());
        }

        return new Lottos(lottos);
    }

    private int inputLottoMoney() {
        while (true) {
            try {
                String response = inputView.askForNormal();
                return MoneyParser.parseMoney(response);
            } catch (IllegalArgumentException e) {
                outputView.displayErrorMessage(e.getMessage());
            }
        }
    }
}