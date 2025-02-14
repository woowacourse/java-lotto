package controller;

import java.util.stream.IntStream;
import model.Lotto;
import model.LottoEvaluator;
import model.LottoGenerater;
import model.LottoNumberPicker;
import model.Lottos;
import model.LottoPayment;
import model.LottoNumber;
import model.WinningLotto;
import util.Parser;
import view.InputView;
import view.OutputView;
import view.dto.LottosDTO;
import view.dto.ResultDTO;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Lottos lottos = buyLottos();
        LottoEvaluator lottoEvaluator = new LottoEvaluator(inputWinningLotto());
        ResultDTO resultDTO = ResultDTO.from(lottoEvaluator.getResult(lottos), lottoEvaluator.computeProfit(lottos));
        outputView.printResult(resultDTO);
        inputView.closeScanner();
    }

    private Lottos buyLottos() {
        LottoPayment lottoPayment = Parser.parseMoney(inputView.inputMoney());
        Lottos lottos = generateLottos(lottoPayment);
        outputView.printLottos(LottosDTO.from(lottos));
        return lottos;
    }

    private Lottos generateLottos(LottoPayment lottoPayment) {
        LottoNumberPicker lottoNumberPicker = new LottoNumberPicker();
        LottoGenerater lottoGenerater = new LottoGenerater(lottoNumberPicker);
        return new Lottos(
                IntStream.range(0, lottoPayment.computeTicketCount())
                        .mapToObj(i -> lottoGenerater.generateLotto())
                        .toList()
        );
    }

    private WinningLotto inputWinningLotto() {
        String rawWinningLotto = inputView.inputWinningLotto();
        Lotto lotto = Parser.parseLotto(rawWinningLotto);
        String rawBonusNumber = inputView.inputBonus();
        LottoNumber bonusNumber = Parser.parseNumber(rawBonusNumber);
        return new WinningLotto(lotto, bonusNumber);
    }
}
