package lotto.controller;

import lotto.domain.lotto.Lotto;
import lotto.domain.lottomachine.LottoMachine;
import lotto.domain.primitive.LottoNumber;
import lotto.domain.primitive.Money;
import lotto.domain.primitive.Ticket;
import lotto.domain.rating.Rating;
import lotto.domain.rating.RatingCounter;
import lotto.domain.statistics.LottoStatistics;
import lotto.domain.statistics.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
    private final LottoMachine lottoMachine;
    private final RatingCounter ratingCounter = new RatingCounter();
    private final List<Lotto> lottos = new ArrayList<>();

    public LottoController(LottoMachine lottoMachine) {
        this.lottoMachine = lottoMachine;
    }

    public void start() {
        Ticket ticket = buyTicket();

        OutputView.printBuyTicket(ticket.getCount());
        generateLottos(ticket);
        OutputView.printLottoResults(lottos);

        WinningLotto winningLotto = getWinningLotto();
        RatingCounter ratingCounter = scratchLotto(winningLotto);
        OutputView.printWinningStats(new LottoStatistics(ratingCounter), ticket.getPrice());
    }

    private Ticket buyTicket() {
        try {
            return tryBuyTicket();
        } catch (IllegalArgumentException e) {
            OutputView.getMessage(e.getMessage());
            return buyTicket();
        }
    }

    private Ticket tryBuyTicket() {
        OutputView.getMessage("구입금액을 입력해 주세요.");
        int money = InputView.getInt();
        return new Ticket(new Money(money));
    }

    public void generateLottos(Ticket ticket) {
        for (int i = 0; i < ticket.getCount(); i++) {
            lottos.add(Lotto.createByInteger(lottoMachine.generate()));
        }
    }

    private WinningLotto getWinningLotto() {
        try {
            return tryGetWinningLotto();
        } catch (IllegalArgumentException e) {
            OutputView.getMessage(e.getMessage());
            return getWinningLotto();
        }
    }

    private WinningLotto tryGetWinningLotto() {
        Lotto lotto = Lotto.createByInteger(InputView.getWinningNumbers());
        OutputView.getMessage("보너스 볼을 입력해 주세요.");
        LottoNumber bonusNumber = new LottoNumber(InputView.getInt());
        return new WinningLotto(lotto, bonusNumber);
    }

    public RatingCounter scratchLotto(WinningLotto winningLotto) {
        for (Lotto lotto : lottos) {
            int match = winningLotto.compareLottoNumber(lotto);
            boolean hasBonusBall = winningLotto.compareBonusBall(lotto);
            ratingCounter.update(Rating.getRating(match, hasBonusBall));
        }
        return ratingCounter;
    }
}
