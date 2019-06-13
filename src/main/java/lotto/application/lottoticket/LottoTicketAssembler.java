package lotto.application.lottoticket;

import lotto.domain.lottoticket.LottoTicket;
import lotto.domain.lottoticket.LottoTickets;
import lotto.domain.lottoticket.dto.LottoTicketDTO;
import lotto.domain.lottoticket.dto.LottoTicketsDTO;
import lotto.domain.lottoticket.ticketingmachine.LottoTicketingMachine;
import lotto.domain.lottoticket.ticketingmachine.RandomLottoNumberGenerator;

import java.util.ArrayList;
import java.util.List;

class LottoTicketAssembler {
    private LottoTicketAssembler() {
    }

    static LottoTicketDTO getLottoTicketDto(LottoTicket lottoTicket) {
        LottoTicketDTO lottoTicketDto = new LottoTicketDTO();
        lottoTicketDto.setFirstNum(lottoTicket.getLottoNumber(0).getNumber());
        lottoTicketDto.setSecondNum(lottoTicket.getLottoNumber(1).getNumber());
        lottoTicketDto.setThirdNum(lottoTicket.getLottoNumber(2).getNumber());
        lottoTicketDto.setFourthNum(lottoTicket.getLottoNumber(3).getNumber());
        lottoTicketDto.setFifthNum(lottoTicket.getLottoNumber(4).getNumber());
        lottoTicketDto.setSixthNum(lottoTicket.getLottoNumber(5).getNumber());
        return lottoTicketDto;
    }

    static LottoTickets getAutomaticLottoTickets(long numOfAutomaticLotto) {
        return LottoTicketingMachine.generateLottoTickets(numOfAutomaticLotto
                , new RandomLottoNumberGenerator());
    }

    static LottoTicketsDTO getLottoTicketsDto(LottoTickets automaticTickets) {
        List<LottoTicketDTO> lottoTicketDtoes = new ArrayList<>();
        for (int index = 0; index < automaticTickets.size(); index++) {
            LottoTicketDTO lottoTicketDto = getLottoTicketDto(automaticTickets.getTicket(index));
            lottoTicketDtoes.add(lottoTicketDto);
        }

        LottoTicketsDTO lottoTicketsDto = new LottoTicketsDTO();
        lottoTicketsDto.setLottoTicketDTOS(lottoTicketDtoes);
        return lottoTicketsDto;
    }
}
