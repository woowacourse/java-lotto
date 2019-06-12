package lotto.application.lottoticket;

import lotto.domain.lottoresult.WinningLotto;
import lotto.domain.lottoticket.LottoTicket;
import lotto.domain.lottoticket.LottoTickets;
import lotto.domain.lottoticket.dto.LottoTicketDto;
import lotto.domain.lottoticket.dto.LottoTicketsDto;
import lotto.domain.lottoticket.dto.WinningLottoDto;
import lotto.domain.lottoticket.ticketingmachine.LottoTicketingMachine;
import lotto.domain.lottoticket.ticketingmachine.RandomLottoNumberGenerator;

import java.util.ArrayList;
import java.util.List;

class LottoTicketAssembler {
    private LottoTicketAssembler() {
    }

    static LottoTicketDto getLottoTicketDto(LottoTicket lottoTicket) {
        LottoTicketDto lottoTicketDto = new LottoTicketDto();
        lottoTicketDto.setFirstNum(lottoTicket.getLottoNumber(0).getNumber());
        lottoTicketDto.setSecondNum(lottoTicket.getLottoNumber(1).getNumber());
        lottoTicketDto.setThirdNum(lottoTicket.getLottoNumber(2).getNumber());
        lottoTicketDto.setFourthNum(lottoTicket.getLottoNumber(3).getNumber());
        lottoTicketDto.setFifthNum(lottoTicket.getLottoNumber(4).getNumber());
        lottoTicketDto.setSixthNum(lottoTicket.getLottoNumber(5).getNumber());
        return lottoTicketDto;
    }

    static LottoTicketsDto getLottoTicketsDto(long numOfAutomaticLotto) {
        LottoTickets automaticTickets = LottoTicketingMachine.generateLottoTickets(numOfAutomaticLotto
                , new RandomLottoNumberGenerator());

        List<LottoTicketDto> lottoTicketDtoes = new ArrayList<>();
        for (int index = 0; index < numOfAutomaticLotto; index++) {
            LottoTicketDto lottoTicketDto = getLottoTicketDto(automaticTickets.getTicket(index));
            lottoTicketDtoes.add(lottoTicketDto);
        }

        LottoTicketsDto lottoTicketsDto = new LottoTicketsDto();
        lottoTicketsDto.setLottoTicketDtos(lottoTicketDtoes);
        return lottoTicketsDto;
    }

    static WinningLottoDto getWinningLottoDto(WinningLotto winningLotto) {
        WinningLottoDto winningLottoDto = new WinningLottoDto();

        LottoTicketDto lottoTicketDto = getLottoTicketDto(winningLotto.getWinningTicket());
        winningLottoDto.setLottoTicketDto(lottoTicketDto);
        winningLottoDto.setBonusBall(winningLotto.getBonusBall());

        return winningLottoDto;
    }
}
