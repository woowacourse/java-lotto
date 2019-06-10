package lotto.domain;

import lotto.domain.Exceptions.LottoTicketException;
import lotto.domain.TicketModel.LottoCreator;
import lotto.domain.TicketModel.Ticket;
import lotto.domain.TicketModel.TicketCreator;
import lotto.domain.TicketModel.WinningTicket;
import lotto.dto.UserLottoDto;

import java.util.ArrayList;
import java.util.List;


public class UserLottos {
    private final TicketCreator ticketGenerator;
    private List<Ticket> tickets;

    public UserLottos(UserLottoDto dto) {
        ticketGenerator = new LottoCreator();
        generateManual(dto.getManualCount(), dto.getManualNumbers());
        generateAuto(dto.getBuyMoney());
    }

    private void generateAuto(int buyMoney) {
        for (int i = tickets.size(); i < (buyMoney / 1000); i++) {
            tickets.add(ticketGenerator.create());
        }
    }

    private void generateManual(int count, List<List<Integer>> manualNumbers) {
        validate(count, manualNumbers);
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < manualNumbers.size(); i++) {
            tickets.add(ticketGenerator.create(manualNumbers.get(i)));
        }
        this.tickets = tickets;
    }

    private void validate(int count, List<List<Integer>> manualNumbers) {
        if (count != manualNumbers.size()) {
            throw new LottoTicketException();
        }
    }

    public LottoResult result(WinningTicket winningTicket) {
        LottoResult lottoResult = new LottoResult();
        for (Ticket ticket : tickets) {
            lottoResult.plus(Rank.rank(winningTicket.match(ticket), winningTicket.bonus(ticket)));
        }
        return lottoResult;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(tickets.size()).append("장 구매했습니다.\n");
        for (Ticket ticket : tickets) {
            stringBuilder.append(ticket.toString()).append("\n");
        }
        return stringBuilder.toString();
    }
}
