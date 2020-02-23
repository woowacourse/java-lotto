package domain.lottostore;

import domain.Money;
import domain.lottonumber.LottoTicket;
import util.LottoNumbersDtoGenerator;

import java.util.ArrayList;
import java.util.List;

public class RandomLottoStore implements LottoStore<Money> {

    @Override
    public List<LottoTicket> generateTickets(Money money) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        int numberOfTickets = money.getNumberOfTickets();

        for (int i = 0; i < numberOfTickets; i++) {
            lottoTickets.add(new LottoTicket(LottoNumbersDtoGenerator.generateRandomTicketDto()));
        }

        return lottoTickets;
    }
}
