package lotto.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.LottoTickets;

public class LottoTicketsDto {

    private final List<LottoTicketDto> manualLottoTickets;
    private final List<LottoTicketDto> autoLottoTickets;

    public LottoTicketsDto(List<LottoTicketDto> manualLottoTickets, List<LottoTicketDto> autoLottoTickets) {
        this.manualLottoTickets = manualLottoTickets;
        this.autoLottoTickets = autoLottoTickets;
    }

    public static LottoTicketsDto from(LottoTickets manualLottoTickets, LottoTickets autoLottoTickets) {
        return new LottoTicketsDto(createLottoTicketDto(manualLottoTickets), createLottoTicketDto(autoLottoTickets));
    }

    private static List<LottoTicketDto> createLottoTicketDto(LottoTickets lottoTickets) {
        return lottoTickets.getLottoTickets().stream()
                .map(LottoTicketDto::from)
                .collect(Collectors.toList());
    }

    public List<LottoTicketDto> getManualLottoTickets() {
        return new ArrayList<>(manualLottoTickets);
    }

    public List<LottoTicketDto> getAutoLottoTickets() {
        return new ArrayList<>(autoLottoTickets);
    }
}
