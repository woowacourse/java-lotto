package domain.lottostore;

import domain.Money;
import domain.lottonumbers.LottoNumbers;
import domain.lottonumbers.LottoTicket;
import domain.lottonumbers.LottoTicketDto;
import domain.lottonumbers.lottonumber.LottoNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class RandomLottoStore implements LottoStore<Money> {

    private static final Random random = new Random();

    @Override
    public List<LottoTicket> generateTickets(Money money) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        int numberOfTickets = money.getNumberOfTickets();

        for (int i = 0; i < numberOfTickets; i++) {
            LottoTicketDto randomDto = new LottoTicketDto(generateRandomLottoNumbers());
            lottoTickets.add(new LottoTicket(randomDto));
        }

        return lottoTickets;
    }

    private Set<Integer> generateRandomLottoNumbers() {
        return random.ints(LottoNumber.MIN_BOUND, LottoNumber.MAX_BOUND + 1)
                .distinct()
                .limit(LottoNumbers.SIZE)
                .boxed()
                .collect(toSet());
    }
}
