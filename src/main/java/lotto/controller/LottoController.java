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
        final List<List<Integer>> selfLottoTickets = buySelfLottoTickets(selfLottoCount);
        final LottoTickets lottoTickets = new LottoTickets(payment.count(), selfLottoTickets);
        OutputView.printBuyLottoCountMessage(
            selfLottoCount.getSelfCount(), payment.count() - selfLottoCount.getSelfCount()
        );
        showLottoTickets(lottoTickets);
        final WinningLotto winningLotto = createWinningLotto();
        callResultMessage(payment, lottoTickets, winningLotto);
    }

    private WinningLotto createWinningLotto() {
        OutputView.printWinningLottoMessage();
        String values = InputView.getLottoNumbers();
        List<Integer> numbers = ParseUtils.parseIntegerList(values, REGEX);
        int bonusNumber = ParseUtils.parseInt(InputView.getBonusBallNumber());
        return new WinningLotto(numbers, bonusNumber);
    }

    private List<List<Integer>> buySelfLottoTickets(SelfLottoCount selfLottoCount) {
        final List<List<Integer>> selfLottoTickets = new ArrayList<>();
        OutputView.printBuyLottoNumberMessage();
        for (int i = 0; i < selfLottoCount.getSelfCount(); i++) {
            selfLottoTickets.add(getSelfLottoNumbers());
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

    private void callResultMessage(Payment payment, LottoTickets lottoTickets,
        WinningLotto winningLotto) {
        OutputView.printResultMessage();
        Rewards rewards = lottoTickets.getResult(winningLotto);
        Arrays.stream(Reward.values())
            .sorted(Comparator.comparing(Reward::getWinningMoney))
            .filter(reward -> !reward.equals(Reward.NONE))
            .forEach(reward -> callMatchMessage(reward, rewards.getRankCount(reward)));
        OutputView.printProfitMessage(rewards.profit(payment.getPayment()));
    }

    private void callMatchMessage(Reward reward, int count) {
        if (reward != Reward.SECOND) {
            OutputView.printMatchMessage(reward, count);
            return;
        }
        OutputView.printMatchBonusMessage(reward, count);
    }
}