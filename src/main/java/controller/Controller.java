package controller;

import java.util.ArrayList;
import java.util.List;
import model.Lotto;
import model.LottoGenerater;
import model.LottoNumberPicker;
import model.Lottos;
import view.InputView;
import view.OutputView;
import view.dto.LottosDTO;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        int price = Integer.parseInt(inputView.inputPrice());
        int ticketCount = price / 1000;

        LottoNumberPicker lottoNumberPicker = new LottoNumberPicker();
        LottoGenerater lottoGenerater = new LottoGenerater(lottoNumberPicker);
        List<Lotto> generatedLottos = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            Lotto lotto = lottoGenerater.generateLotto();
            generatedLottos.add(lotto);
        }
        Lottos lottos = new Lottos(generatedLottos);
        outputView.printLottos(LottosDTO.from(lottos));
    }
}
