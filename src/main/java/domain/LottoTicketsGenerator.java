package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoTicketsGenerator {
    private static final int START_LOTTO_RANGE = 1;
    private static final int END_LOTTO_RANGE = 45;
    private static final int START_LOTTO_INDEX = 0;
    private static final int LOTTO_SIZE = 6;

    private static final List<LottoNumber> shuffleLottoNumbers;

    static {
        shuffleLottoNumbers = createLottoNumbers();
    }

    private static List<LottoNumber> createLottoNumbers() {
        return IntStream.rangeClosed(START_LOTTO_RANGE, END_LOTTO_RANGE)
                .mapToObj(LottoNumber::getLottoNumber)
                .collect(Collectors.toList());
    }

    public static List<LottoTicket> generateLottoTickets(int buyCount) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = START_LOTTO_INDEX; i < buyCount; i++) {
            Collections.shuffle(shuffleLottoNumbers);
            lottoTickets.add(new LottoTicket(createLottoTicketBySize()));
        }
        return lottoTickets;
    }

    private static List<LottoNumber> createLottoTicketBySize() {
        return shuffleLottoNumbers.stream()
                .limit(LOTTO_SIZE)
                .collect(Collectors.toList());
    }
}
