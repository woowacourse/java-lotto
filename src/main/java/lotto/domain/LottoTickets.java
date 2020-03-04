package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class LottoTickets {

    private final List<LottoTicket> lottoTickets;

    private LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public static LottoTickets from(TicketCounts ticketCounts, List<String> manualLottoNumbers) {
        int autoTicketCount = ticketCounts.getAutoTicketCount();

        List<LottoTicket> lottoTickets = new ArrayList<>();
        lottoTickets.addAll(createAutoTickets(autoTicketCount));
        lottoTickets.addAll(createManualTickets(manualLottoNumbers));

        return new LottoTickets(lottoTickets);
    }

    private static List<LottoTicket> createAutoTickets(int autoTicketCount) {
        return Stream.generate(LottoTicket::createAutoTicket)
                .limit(autoTicketCount)
                .collect(toList());
    }

    private static List<LottoTicket> createManualTickets(List<String> manualLottoNumbers) {
        return manualLottoNumbers.stream()
                .map(LottoTicket::createManualTicket)
                .collect(Collectors.toList());
    }

    public int size() {
        return lottoTickets.size();
    }

    public Ranks checkOutLottos(LottoTicket winningLottoTicket, LottoNumber bonusNumber) {
        List<Rank> ranks = lottoTickets.stream()
                .map(lottoTicket -> lottoTicket.checkOut(winningLottoTicket, bonusNumber))
                .filter(Rank::isValidRank)
                .collect(Collectors.toList());

        return Ranks.of(ranks);
    }

    public List<LottoTicket> getLottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }
}