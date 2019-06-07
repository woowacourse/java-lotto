package lotto.domain;

import lotto.domain.Dto.UserLottoDto;
import lotto.domain.Exceptions.LottoTicketException;
import lotto.domain.TicketModel.LottoGenerator;
import lotto.domain.TicketModel.Ticket;
import lotto.domain.TicketModel.TicketCreator;
import lotto.domain.TicketModel.WinningTicket;

import java.util.ArrayList;
import java.util.List;


public class UserLottos {
    private List<Ticket> tickets;
    private TicketCreator ticketGenerator;

    public UserLottos(UserLottoDto dto) {
        ticketGenerator = new LottoGenerator();
        generateManual(dto.getManualCount(), dto.getManualNumbers());
        generateAuto(dto.getBuyMoney());
    }

    private void generateAuto(int buyMoney) {
        for (int i = tickets.size(); i <= (buyMoney / 1000); i++) {
            tickets.add(ticketGenerator.create());
        }
    }

    private void generateManual(int count, List<List<Integer>> manualNumbers) {
        validate(count, manualNumbers);
        List<Ticket> tickets = new ArrayList<>();
        for (List<Integer> manualNumber : manualNumbers) {
            tickets.add(ticketGenerator.create(manualNumber));
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
        return "구입한 로또는\n" +
                tickets;
    }
}
