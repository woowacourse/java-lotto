package lotto.controller;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.LottoTickets;
import lotto.domain.Payment;
import lotto.domain.Reward;
import lotto.domain.Rewards;
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
        final LottoTickets lottoTickets = new LottoTickets(payment.count());
        showLottoTickets(payment, lottoTickets);
        final WinningLotto winningLotto = createWinningLotto();
        callResultMessage(payment, lottoTickets, winningLotto);
    }

    private WinningLotto createWinningLotto() {
        String values = InputView.getLottoNumbers();
        List<Integer> numbers = Arrays.stream(values.split(REGEX))
            .mapToInt(Integer::parseInt)
            .boxed()
            .collect(Collectors.toList());
        int bonusNumber = ParseUtils.parseInt(InputView.getBonusBallNumber());
        return new WinningLotto(numbers, bonusNumber);
    }

    private void showLottoTickets(Payment payment, LottoTickets lottoTickets) {
        OutputView.printBuyLottoCountMessage(payment.count());
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
            .filter(reward -> !reward.equals(reward.NONE))
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
