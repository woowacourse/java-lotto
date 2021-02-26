package lotto.controller;

import lotto.domain.EarningRate;
import lotto.domain.LottoService;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoRepository;
import lotto.domain.lottomachine.RandomLottoMachine;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.Money;
import lotto.domain.Ticket;
import lotto.domain.rating.RatingInfo;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void start() {
        final LottoRepository lottoRepository = new LottoRepository();

        final Ticket totalTicket = buyTicket();
        final Ticket manualTicket = manualBuyTicket(totalTicket);
        generateManualLottoNumbers(manualTicket.getCount(), lottoRepository);
        BuyLottoAndPrintResult(manualTicket.getCount(), totalTicket.getCount(),
            lottoRepository);

        RatingInfo ratingInfo = lottoRepository.scratchLotto(buyWinningLotto());
        printWinningStats(ratingInfo, new EarningRate(), totalTicket);
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
        int money = InputView.getMoney();
        return new Ticket(new Money(money));
    }

    private Ticket manualBuyTicket(final Ticket totalTicket) {
        try {
            return tryManualBuyTicket(totalTicket);
        } catch (IllegalArgumentException e) {
            OutputView.getMessage(e.getMessage());
            return manualBuyTicket(totalTicket);
        }
    }

    private Ticket tryManualBuyTicket(final Ticket totalTicket) {
        int count = InputView.getManualTicketCount();
        return new Ticket(count, totalTicket);
    }

    private void generateManualLottoNumbers(final int count,
        final LottoRepository lottoRepository) {
        OutputView.getMessage(InputView.INPUT_MANUAL_BUY_NUMBERS_MESSAGE);
        for (int i = 0; i < count; i++) {
            Lotto lotto = manualBuyLotto();
            lottoRepository.generateLottoByManual(lotto);
        }
    }

    private Lotto manualBuyLotto() {
        try {
            return tryManualBuyLotto();
        } catch (IllegalArgumentException e) {
            OutputView.getMessage(e.getMessage());
            return manualBuyLotto();
        }
    }

    private Lotto tryManualBuyLotto() {
        return Lotto.from(InputView.getNumbers());
    }

    private WinningLotto buyWinningLotto() {
        try {
            return tryBuyWinningLotto();
        } catch (IllegalArgumentException e) {
            OutputView.getMessage(e.getMessage());
            return buyWinningLotto();
        }
    }

    private WinningLotto tryBuyWinningLotto() {
        Lotto lotto = Lotto.from(InputView.getWinningNumbers());
        LottoNumber bonusNumber = LottoNumber.from(InputView.getBonusBall());
        return new WinningLotto(lotto, bonusNumber);
    }

    private void BuyLottoAndPrintResult(final int manualCount, final int totalCount,
        LottoRepository lottoRepository) {
        OutputView.printBuyLotto(manualCount, totalCount - manualCount);
        lottoRepository.generateLottoByTicket(new RandomLottoMachine(), totalCount);
        OutputView.printLottoResults(lottoRepository);
    }

    private void printWinningStats(final RatingInfo ratingInfo, final EarningRate earningRate,
        final Ticket ticket) {
        OutputView
            .printWinningStats(ratingInfo,
                earningRate.calculate(ratingInfo, ticket.getPrice()));
    }
}
