package controller;

import domain.Lotto;
import domain.Lottos;
import domain.PrizeResult;
import domain.WinningLotto;
import java.util.ArrayList;
import java.util.List;
import service.Judgement;
import service.LottoMaker;
import service.parser.BonusNumberParser;
import service.parser.MoneyParser;
import service.parser.WinningNumberParser;
import view.InputView;
import view.OutputView;

public class LottoController {

    private static LottoController instance;

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoMaker lottoMaker;

    private LottoController(InputView inputView, OutputView outputView, LottoMaker lottoMaker) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoMaker = lottoMaker;
    }

    public static LottoController getInstance() {
        if (instance == null) {
            instance = new LottoController(InputView.getInstance(), OutputView.getInstance(), LottoMaker.getInstance());
        }
        return instance;
    }

    public void start() {
        int lottoCount = inputLottoMoney();

        Lottos lottos = buyLotto(lottoCount);
        outputView.displayLottoNumbers(lottos);

        List<Integer> winningNumbers = inputWinningNumber();
        int bonusNumber = inputBonusNumber(winningNumbers);

        PrizeResult prizeResult = Judgement.judge(lottos, new WinningLotto(winningNumbers, bonusNumber));
        outputView.displayPrizeSummary(prizeResult);
    }

    private Lottos buyLotto(int count) {
        ArrayList<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(lottoMaker.createLotto());
        }

        return new Lottos(lottos);
    }

    private int inputLottoMoney() {
        return MoneyParser.parseLottoCount(inputView.askForNormal());
    }

    private List<Integer> inputWinningNumber() {
        return WinningNumberParser.parseWinningNumbers(inputView.askWinningNumber());
    }

    private int inputBonusNumber(List<Integer> winningNumbers) {
        return BonusNumberParser.parseBonusNumber(winningNumbers, inputView.askBonusNumber());
    }
}
