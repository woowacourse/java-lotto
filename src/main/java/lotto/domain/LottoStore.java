package lotto.domain;

import lotto.domain.LottoTicketNumber.AutomaticLottoTicketNumber;
import lotto.domain.LottoTicketNumber.ManualLottoTicketNumber;
import lotto.view.OutputView;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class LottoStore {
    private static final int MAX_LOTTO_BALL_COUNT = 6;

    private ManualLottoTicketNumber manualLottoTicketNumber;
    private AutomaticLottoTicketNumber automaticLottoTicketNumber;

    public LottoStore(ManualLottoTicketNumber manualLottoTicketNumber, AutomaticLottoTicketNumber automaticLottoTicketNumber) {
        this.manualLottoTicketNumber = manualLottoTicketNumber;
        this.automaticLottoTicketNumber = automaticLottoTicketNumber;
    }

    private LottoTicket generateManualLottoTicket(Set<LottoBall> manualLottoBallsInput) {
        return new LottoTicket(manualLottoBallsInput);
    }

    private LottoTicket generateAutomaticLottoTicket() {
        Set<LottoBall> lottoTicket = new TreeSet<>();
        for (int i = 0; i < MAX_LOTTO_BALL_COUNT; i++) {
            lottoTicket.add(LottoBallFactory.getInstance().get(i));
        }
        return new LottoTicket(lottoTicket);
    }

    private void generateManualLottoTickets(List<Set<LottoBall>> manualLottoBallsInputs, LottoTickets lottoTickets) {
        manualLottoBallsInputs.stream().map(this::generateManualLottoTicket).forEach(lottoTickets::addLottoTicket);
    }

    private void generateAutomaticLottoTickets(LottoTickets lottoTickets) {
        for (int i = 0; i < automaticLottoTicketNumber.getLottoTicketNumber(); i++) {
            Collections.shuffle(LottoBallFactory.getInstance());
            lottoTickets.addLottoTicket(generateAutomaticLottoTicket());
        }
    }

    public LottoTickets generateLottoTickets(List<Set<LottoBall>> inputManualLottoTickets) {
        LottoTickets lottoTickets = new LottoTickets();
        OutputView.printAnswerLottoTicketNumber(manualLottoTicketNumber, automaticLottoTicketNumber);
        generateManualLottoTickets(inputManualLottoTickets, lottoTickets);
        generateAutomaticLottoTickets(lottoTickets);
        OutputView.printLottoTicket(lottoTickets);
        return lottoTickets;
    }
}