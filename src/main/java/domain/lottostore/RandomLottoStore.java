package domain.lottostore;

import domain.Money;
import domain.lottonumbers.LottoNumbers;
import domain.lottonumbers.LottoNumbersDto;
import domain.lottonumbers.LottoTicket;
import domain.lottonumbers.lottonumber.LottoNumber;
import util.LottoNumbersDtoGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toSet;

public class RandomLottoStore implements LottoStore<Money> {

    private static final Random random = new Random();

    @Override
    public List<LottoTicket> generateTickets(Money money) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        int numberOfTickets = money.getNumberOfTickets();

        for (int i = 0; i < numberOfTickets; i++) {
            LottoNumbersDto randomDto = new LottoNumbersDto(generateRandomLottoNumbers());
            lottoTickets.add(new LottoTicket(randomDto));
        }

        return lottoTickets;
    }

    private LottoNumbers generateRandomLottoNumbers() {
        return random.ints(LottoNumber.MIN_BOUND, LottoNumber.MAX_BOUND)
                .distinct()
                .limit(LottoNumbers.SIZE)
                .mapToObj(LottoNumber::of)
                .collect(collectingAndThen(toSet(),LottoNumbers::new));
    }
}
