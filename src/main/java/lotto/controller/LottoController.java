package lotto.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.LottoTickets;
import lotto.domain.Payment;
import lotto.domain.Reward;
import lotto.domain.Rewards;
import lotto.domain.SelfLottoCount;
import lotto.domain.WinningLotto;
import lotto.utils.ParseUtils;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private static final String REGEX = ", ";

    public LottoController() {
    }

    public void run() {
        final Payment payment = new Payment(ParseUtils.parseInt(InputView.getInputMoney()));
        final SelfLottoCount selfLottoCount = new SelfLottoCount(payment.count(),
            ParseUtils.parseInt(InputView.getBuySelfLottoCount()));
        final List<Lotto> selfLottoTickets = buySelfLottoTickets(selfLottoCount);
        final LottoTickets lottoTickets = new LottoTickets(payment.count(), selfLottoTickets);
        OutputView.printBuyLottoCountMessage(
            selfLottoCount.getSelfCount(), payment.count() - selfLottoCount.getSelfCount()
        );
        showLottoTickets(lottoTickets);
        final WinningLotto winningLotto = createWinningLotto();
        OutputView.printResultMessage(lottoTickets.getResult(winningLotto), payment);
    }

    private WinningLotto createWinningLotto() {
        OutputView.printWinningLottoMessage();
        String values = InputView.getLottoNumbers();
        List<Integer> numbers = ParseUtils.parseIntegerList(values, REGEX);
        int bonusNumber = ParseUtils.parseInt(InputView.getBonusBallNumber());
        return new WinningLotto(numbers, bonusNumber);
    }

    private List<Lotto> buySelfLottoTickets(SelfLottoCount selfLottoCount) {
        final List<Lotto> selfLottoTickets = new ArrayList<>();
        if (selfLottoCount.getSelfCount() == 0) {
            return selfLottoTickets;
        }
        OutputView.printBuyLottoNumberMessage();
        for (int i = 0; i < selfLottoCount.getSelfCount(); i++) {
            selfLottoTickets.add(new Lotto(getSelfLottoNumbers()));
        }
        OutputView.printNewLineMessage();
        return selfLottoTickets;
    }

    private List<Integer> getSelfLottoNumbers() {
        return ParseUtils.parseIntegerList(InputView.getLottoNumbers(), REGEX);
    }

    private void showLottoTickets(LottoTickets lottoTickets) {
        for (Lotto lotto : lottoTickets.getLottoTickets()) {
            OutputView.printLottoMessage(lotto.getLottoNumbers());
        }
        OutputView.printNewLineMessage();
    }
}