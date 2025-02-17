package controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import model.Lotto;
import model.LottoEvaluator;
import model.LottoGenerater;
import model.LottoNumber;
import model.LottoNumberPicker;
import model.Lottos;
import model.Money;
import model.WinningLotto;
import util.InputConverter;
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
    }

    private Lottos buyLottos() {
        Integer inputMoney = InputConverter.parseInt(inputView.inputMoney());
        Money money = new Money(inputMoney);
        Lottos lottos = generateLottos(money);
        outputView.printLottos(LottosDTO.from(lottos));
        return lottos;
    }

    private Lottos generateLottos(Money money) {
        LottoNumberPicker lottoNumberPicker = new LottoNumberPicker();
        LottoGenerater lottoGenerater = new LottoGenerater(lottoNumberPicker);
        return new Lottos(
                IntStream.range(0, money.computeTicketCount())
                        .mapToObj(i -> lottoGenerater.generateLotto())
                        .toList()
        );
    }

    private WinningLotto inputWinningLotto() {
        String rawWinningLotto = inputView.inputWinningLotto();
        List<String> splittedWinningLotto = InputConverter.splitByDelimiter(rawWinningLotto);
        Lotto lotto = new Lotto(
                splittedWinningLotto.stream()
                        .map(singleNumber -> new LottoNumber(InputConverter.parseInt(singleNumber)))
                        .collect(Collectors.toSet()));

        String rawBonusNumber = inputView.inputBonus();
        LottoNumber bonusLottoNumber = new LottoNumber(InputConverter.parseInt(rawBonusNumber));
        return new WinningLotto(lotto, bonusLottoNumber);
    }
}
