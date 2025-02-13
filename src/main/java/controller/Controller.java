package controller;

import java.util.ArrayList;
import java.util.List;
import model.Lotto;
import model.LottoGenerater;
import model.LottoNumberPicker;
import model.Lottos;
import model.Money;
import model.Number;
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
        Money money = Parser.parseMoney(inputView.inputMoney());
        LottoNumberPicker lottoNumberPicker = new LottoNumberPicker();
        LottoGenerater lottoGenerater = new LottoGenerater(lottoNumberPicker);
        List<Lotto> generatedLottos = new ArrayList<>();
        for (int i = 0; i < money.computeTicketCount(); i++) {
            Lotto lotto = lottoGenerater.generateLotto();
            generatedLottos.add(lotto);
        }
        Lottos lottos = new Lottos(generatedLottos);
        outputView.printLottos(LottosDTO.from(lottos));

        String rawWinningLotto = inputView.inputWinningLotto();
        Lotto lotto = Parser.parseLotto(rawWinningLotto);

        String rawBonusNumber = inputView.inputBonus();
        Number bonusNumber = Parser.parseNumber(rawBonusNumber);

        WinningLotto winningLotto = new WinningLotto(lotto, bonusNumber);

        ResultDTO resultDTO = ResultDTO.from(lottos.getResult(winningLotto), lottos.computeProfit(winningLotto));

        outputView.printResult(resultDTO);
    }
}
