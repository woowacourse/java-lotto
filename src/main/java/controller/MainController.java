package controller;

import domain.lotto.Lotto;
import domain.lotto.LottoFactory;
import domain.lotto.LottoMoney;
import domain.lotto.LottoNumber;
import domain.lotto.LottoTicketCount;
import domain.lotto.WinNumbers;
import domain.result.Result;
import java.util.ArrayList;
import java.util.List;
import utils.NumsGenerator;
import utils.Util;
import view.InputView;
import view.OutputView;

public class MainController {
    private MainController() {
    }

    public static MainController create() {
        return new MainController();
    }

    public void run() {
        final LottoMoney lottoMoney = makeLottoMoney();
        final LottoTicketCount count = LottoTicketCount.of(lottoMoney.toLottoCount(),
                InputView.inputManualTicketCount());
        final List<Lotto> lottoTickets = makeLottos(count);
        final WinNumbers winNumbers = makeWinNums();

        final Result result = Result.of(lottoTickets, winNumbers);
        end(result, lottoMoney);
    }

    private LottoMoney makeLottoMoney() {
        return LottoMoney.from(InputView.inputMoney());
    }

    private List<Lotto> makeLottos(final LottoTicketCount count) {
        final List<Lotto> lottos = new ArrayList<>();
        addManualLottos(lottos, count.getManualCount());
        addAutoLottos(lottos, count.getAutoCount());
        OutputView.printLottoTickets(count, lottos);
        return lottos;
    }

    private void addManualLottos(List<Lotto> lottos, final int count) {
        final List<List<Integer>> manualLottoNumsGroup = InputView.inputManualTicketGroup(count);
        for (int i = 0; i < count; i++) {
            lottos.add(LottoFactory.createLotto(NumsGenerator.generate(manualLottoNumsGroup.get(i))));
        }
    }

    private void addAutoLottos(List<Lotto> lottos, final int count) {
        for (int i = 0; i < count; i++) {
            lottos.add(LottoFactory.createLotto(NumsGenerator.generateByRandom()));
        }
    }

    private WinNumbers makeWinNums() {
        List<Integer> winLottoRawNums = InputView.inputWinLottoNums();
        LottoNumber bonus = LottoNumber.getInstance(InputView.inputBonusNumber());
        return LottoFactory.createWinNums(NumsGenerator.generate(winLottoRawNums), bonus);
    }

    private void end(final Result result, final LottoMoney lottoMoney) {
        OutputView.printLottosResult(result);
        OutputView.printProfit(Util.getProfit((float) result.getPrize(), (float) lottoMoney.get()));
    }
}