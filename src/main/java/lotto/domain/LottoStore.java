package lotto.domain;

import lotto.Exception.LottoTicketEmptyException;
import lotto.Exception.NumberOutOfRangeException;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class LottoStore {
    private static final int MAX_LOTTO_BALL_COUNT = 6;

    private LottoTicketNumber lottoTicketNumber;
    private LottoTickets lottoTickets = new LottoTickets();

    public LottoStore(LottoTicketNumber lottoTicketNumber) {
        this.lottoTicketNumber = lottoTicketNumber;
    }

    private LottoTicket generateManualLottoTicket(String manualLottoBallsInput) {
        try {
            return new LottoTicket(LottoBalls.generateLottoBalls(manualLottoBallsInput));
        } catch (LottoTicketEmptyException | NumberOutOfRangeException | NumberFormatException e) {
            OutputView.printErrorMessage(e.getMessage());
            return generateManualLottoTicket(manualLottoBallsInput);
        }
    }

    private LottoTicket generateAutomaticLottoTicket() {
        Set<LottoBall> lottoTicket = new TreeSet<>();
        for (int i = 0; i < MAX_LOTTO_BALL_COUNT; i++) {
            lottoTicket.add(LottoBallFactory.getInstance().get(i));
        }
        return new LottoTicket(lottoTicket);
    }

    private void generateManualLottoTickets(List<String> manualLottoBallsInputs) {
        OutputView.printManualLottoBallsGuide();
        manualLottoBallsInputs.stream().map(this::generateManualLottoTicket).forEach(lottoTickets::addLottoTicket);
    }

    private void generateAutomaticLottoTickets() {
        for (int i = 0; i < lottoTicketNumber.getAutomaticLottoTicketNumber(); i++) {
            Collections.shuffle(LottoBallFactory.getInstance());
            lottoTickets.addLottoTicket(generateAutomaticLottoTicket());
        }
        OutputView.printAnswerLottoTicketNumber(lottoTicketNumber);
        OutputView.printLottoTicket(lottoTickets);
    }

    public LottoTickets generateLottoTickets(List<String> manualLottoBallsInputs) {
        generateManualLottoTickets(manualLottoBallsInputs);
        generateAutomaticLottoTickets();
        return lottoTickets;
    }
}