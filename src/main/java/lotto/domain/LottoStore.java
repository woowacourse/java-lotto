package lotto.domain;

import lotto.Exception.LottoTicketEmptyException;
import lotto.Exception.NumberOutOfRangeException;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.*;

public class LottoStore {
    private static final int MAX_LOTTO_BALL_COUNT = 6;

    private LottoTicketNumber lottoTicketNumber;

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

    private void generateManualLottoTickets(List<String> manualLottoBallsInputs,LottoTickets lottoTickets) {
        manualLottoBallsInputs.stream().map(this::generateManualLottoTicket).forEach(lottoTickets::addLottoTicket);
    }

    private void generateAutomaticLottoTickets(LottoTickets lottoTickets) {
        for (int i = 0; i < lottoTicketNumber.getAutomaticLottoTicketNumber(); i++) {
            Collections.shuffle(LottoBallFactory.getInstance());
            lottoTickets.addLottoTicket(generateAutomaticLottoTicket());
        }
        OutputView.printAnswerLottoTicketNumber(lottoTicketNumber);
    }

    public LottoTickets generateLottoTickets() {
        LottoTickets lottoTickets = new LottoTickets();
        generateManualLottoTickets(InputView.inputManualLottoTickets(lottoTicketNumber.getManualLottoTicketNumber()),
                lottoTickets);
        generateAutomaticLottoTickets(lottoTickets);
        OutputView.printLottoTicket(lottoTickets);
        return lottoTickets;
    }
}