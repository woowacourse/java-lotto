package lotto.domain;

import lotto.exception.LottoStoreException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class LottoStore {
    private static final int SUBLIST_FROM_INDEX = 0;
    private static final int SUBLIST_TO_INDEX = 6;

    private static LottoTicket createRandomLottoTicket() {
        List<LottoNumber> copiedNumbers = new ArrayList<>(LottoNumber.values());
        Collections.shuffle(copiedNumbers);
        List<LottoNumber> subNumbers = copiedNumbers.subList(SUBLIST_FROM_INDEX, SUBLIST_TO_INDEX);
        return new LottoTicket(subNumbers);
    }

    private static List<LottoNumber> generateSixNumbersFromInput(String inputForNumbers) {
        return Arrays.stream(inputForNumbers.split(","))
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public List<LottoTicket> buyRandomLottoTickets(int randomTicketCount) {
        return Stream.generate(LottoStore::createRandomLottoTicket).limit(randomTicketCount)
                .collect(toList());
    }

    public List<LottoTicket> buyManualLottoTickets(int manualTicketCount, String... inputForNumbers) {
        if (manualTicketCount != inputForNumbers.length) {
            throw new LottoStoreException("수동으로 구매할 로또 티켓의 개수와 로또 번호의 개수가 일치하지 않습니다.");
        }

        return Arrays.stream(inputForNumbers)
                .map(LottoStore::generateSixNumbersFromInput)
                .map(LottoTicket::new)
                .collect(Collectors.toList());
    }
}