package domain.lotto;

import domain.rank.Rank;
import domain.rank.Ranks;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTickets {

    private final List<LottoTicket> tickets;
    private final int manualCount;
    private final int autoCount;

    public LottoTickets(final List<List<Integer>> manualNumbers, final int autoCount) {
        tickets = new ArrayList<>();
        this.manualCount = manualNumbers.size();
        this.autoCount = autoCount;
        generateManual(manualNumbers);
        generateAuto(autoCount);
    }

    private void generateManual(final List<List<Integer>> manualNumbers) {
        manualNumbers.stream()
                .map(LottoTicket::generateManual)
                .forEach(tickets::add);
    }

    private void generateAuto(final int autoCount) {
        for (int i = 0; i < autoCount; ++i) {
            tickets.add(LottoTicket.generateRandom());
        }
    }

    public Ranks checkMatch(final LottoTicket winningTicket, final LottoNumber bonusNumber) {
        List<Rank> ranks = tickets.stream()
                .map(ticket -> ticket.checkRanking(winningTicket, bonusNumber))
                .collect(Collectors.toList());
        return new Ranks(ranks);
    }

    public List<LottoTicket> toList() {
        return Collections.unmodifiableList(tickets);
    }

    public int getManualCount() {
        return manualCount;
    }

    public int getAutoCount() {
        return autoCount;
    }
}
