package strategy;

import domain.numberscontainer.LottoNumber;
import domain.numberscontainer.SixLottoNumbersDTO;
import domain.numberscontainer.Ticket;

import java.util.*;

public class RandomTicketFactory {
    private static final int FIRST_INDEX = 0;
    private static final int SIXTH_INDEX = 6;

    public static Ticket createTicket() {
        Set<LottoNumber> sixNumbers = new HashSet<>(getShuffledList().subList(FIRST_INDEX, SIXTH_INDEX));
        return new Ticket(new SixLottoNumbersDTO(sixNumbers));
    }

    private static List<LottoNumber> getShuffledList() {
        List<LottoNumber> lottoNumbers = Arrays.asList(LottoNumber.values());
        Collections.shuffle(lottoNumbers);

        return lottoNumbers;
    }
}