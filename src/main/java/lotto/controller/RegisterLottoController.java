package lotto.controller;

import static java.util.stream.Collectors.toList;
import static lotto.controller.ControllerTemplate.runnableTemplate;
import static lotto.view.InputView.inputManualLottoesText;

import java.util.List;
import lotto.model.Lotto;
import lotto.model.LottoMachine;
import lotto.model.Lottoes;
import lotto.view.LottoConvertor;

public class RegisterLottoController {

    private final LottoMachine lottoMachine;

    public RegisterLottoController(LottoMachine lottoMachine) {
        this.lottoMachine = lottoMachine;
    }

    public void run() {
        runnableTemplate(this::registerManualLottoes, ExceptionHandler::handle);
    }

    public void registerManualLottoes() {
        List<String> manualLottoesText = inputManualLottoesText(ExceptionHandler::handle);
        Lottoes manualLottoes = createManualLottoes(manualLottoesText);
        lottoMachine.registerManualLotto(manualLottoes);
    }

    private Lottoes createManualLottoes(List<String> manualLottoesText) {
        LottoConvertor convertor = new LottoConvertor();
        List<Lotto> manualLottoes = manualLottoesText.stream()
            .map(convertor::convert)
            .collect(toList());
        return new Lottoes(manualLottoes);
    }
}
