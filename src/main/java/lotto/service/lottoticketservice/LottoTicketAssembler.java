package lotto.service.lottoticketservice;

import lotto.dto.LottoTicketDTO;
import lotto.model.lotto.LottoNumber;
import lotto.model.lotto.LottoNumberRepository;
import lotto.model.lotto.LottoTicket;
import lotto.model.lotto.LottoTickets;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class LottoTicketAssembler {
    public static LottoTicketDTO toDTO(LottoTicket from) {
        String lottoTicket = from.getLottoTicket()
                .stream()
                .map(LottoNumber::getLottoNumber)
                .map(String::valueOf)
                .collect(Collectors.joining(", "));

        LottoTicketDTO lottoTicketDTO = new LottoTicketDTO();
        lottoTicketDTO.setLottoTicket(lottoTicket);

        return lottoTicketDTO;
    }

    public static LottoTickets toLottoTickets(List<LottoTicketDTO> lottoTicketDTOs) {
        List<LottoTicket> lottoTickets = lottoTicketDTOs.stream().map(LottoTicketDTO::getLottoTicket)
                .map(ticket -> Arrays.stream(ticket.split(","))
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .map(LottoNumberRepository::fromNumber)
                        .collect(Collectors.toCollection(TreeSet::new)))
                .map(LottoTicket::from)
                .collect(Collectors.toList());

        return LottoTickets.from(lottoTickets);
    }

    public static LottoTicket toLottoTicket(LottoTicketDTO lottoTicketDTO) {
        Set<LottoNumber> lottoTicket = Arrays.stream(lottoTicketDTO.getLottoTicket().split(","))
                .map(Integer::parseInt)
                .map(LottoNumberRepository::fromNumber)
                .collect(Collectors.toCollection(TreeSet::new));

        return LottoTicket.from(lottoTicket);
    }
}
