package domain;

import domain.numberscontainer.LottoNumber;
import domain.numberscontainer.LottoNumbersDto;
import domain.numberscontainer.LottoNumbersDtoAssembler;
import domain.numberscontainer.Ticket;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

public class RandomTicketFactory {
    private static final int FIRST_INDEX = 0;
    private static final int SIXTH_INDEX = 6;

    public static Ticket createTicket() {
        return new Ticket(getShuffledList());
    }

    private static LottoNumbersDto getShuffledList() {
        List<LottoNumber> lottoNumbers = Arrays.asList(LottoNumber.values());
        Collections.shuffle(lottoNumbers);

        return LottoNumbersDtoAssembler.assemble(new LinkedHashSet<>(lottoNumbers.subList(FIRST_INDEX, SIXTH_INDEX)));
    }
}