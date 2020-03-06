package domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TicketsFactory {
    private TicketsFactory() {
    }

    public static LottoTickets getTickets(List<String> manualLottoNumbers, int autoLottoTicketsCount) {
        List<LottoTicket> manualLottoTickets = LottoTicketsGenerator.generateManualLottoTickets(manualLottoNumbers);
        List<LottoTicket> autoLottoTickets = LottoTicketsGenerator.generateAutoLottoTickets(autoLottoTicketsCount);
        List<LottoTicket> lottoTickets = concatManualTicketsWithAutoTickets(manualLottoTickets, autoLottoTickets);
        return new LottoTickets(lottoTickets);
    }

    private static List<LottoTicket> concatManualTicketsWithAutoTickets(List<LottoTicket> manualLottoTickets,
                                                                        List<LottoTicket> autoLottoTickets) {
        List<LottoTicket> lottoTickets = Stream.concat(manualLottoTickets.stream(), autoLottoTickets.stream())
                .collect(Collectors.toList());
        return lottoTickets;
    }
}
