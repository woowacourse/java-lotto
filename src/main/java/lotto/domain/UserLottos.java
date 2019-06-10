package lotto.domain;

import lotto.domain.Exceptions.ExceptionMessages;
import lotto.domain.Exceptions.LottoTicketException;
import lotto.domain.TicketModel.LottoCreator;
import lotto.domain.TicketModel.Ticket;
import lotto.domain.TicketModel.TicketCreator;
import lotto.domain.TicketModel.WinningTicket;
import lotto.dto.UserLottoDto;

import java.util.ArrayList;
import java.util.List;


public class UserLottos {
    private final TicketCreator ticketCreator;
    private List<Ticket> tickets;

    public UserLottos(UserLottoDto dto) {
        ticketCreator = new LottoCreator();
        generateManual(dto.getManualCount(), dto.getManualNumbers());
        generateAuto(dto.getBuyMoney());
    }

    private void generateManual(int count, List<List<Integer>> manualNumbers) {
        validate(count, manualNumbers);
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < manualNumbers.size(); i++) {
            tickets.add(ticketCreator.create(manualNumbers.get(i)));
        }
        this.tickets = tickets;
    }

    private void generateAuto(int buyMoney) {
        int iterate = buyMoney / ticketCreator.unitPrice();
        for (int i = tickets.size(); i < iterate; i++) {
            tickets.add(ticketCreator.create());
        }
    }

    private void validate(int count, List<List<Integer>> manualNumbers) {
        if (count != manualNumbers.size()) {
            throw new LottoTicketException(ExceptionMessages.TICKET.message());
        }
    }

    public LottoResult result(WinningTicket winningTicket) {
        LottoResult lottoResult = new LottoResult();
        for (Ticket ticket : tickets) {
            lottoResult.plus(Rank.rank(winningTicket.match(ticket), winningTicket.bonus(ticket)));
        }
        return lottoResult;
    }

    public List<Ticket> tickets() {
        return tickets;
    }
}
