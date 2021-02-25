package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoTicketFactory {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int LOTTO_TICKET_SIZE = 6;

    private static final List<LottoNumber> lottoNumberRange;

    static {
        lottoNumberRange = new ArrayList<>();
        IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .forEach(number -> lottoNumberRange.add(LottoNumber.of(Integer.toString(number))));
    }

    private LottoTicketFactory() {
    }

    public static LottoTickets createLottoTicketsIncludingManualTickets(Money money, List<List<String>> manualTicketInputs) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        addManualLottoTickets(lottoTickets, manualTicketInputs);

        int autoCreateCount = money.getPurchasableLottoCount() - manualTicketInputs.size();
        addAutoLottoTickets(lottoTickets, autoCreateCount);
        return new LottoTickets(lottoTickets);
    }

    private static void addManualLottoTickets(List<LottoTicket> lottoTickets, List<List<String>> lottoTicketsNumbers) {
        for (List<String> numbers : lottoTicketsNumbers) {
            lottoTickets.add(createManualLottoTicket(numbers));
        }
    }

    private static void addAutoLottoTickets(List<LottoTicket> lottoTickets, int autoCreateCount) {
        for (int i = 0; i < autoCreateCount; i++) {
            lottoTickets.add(createAutoLottoTicket());
        }
    }

    private static LottoTicket createAutoLottoTicket() {
        Collections.shuffle(lottoNumberRange);
        return new LottoTicket(lottoNumberRange.stream()
                .limit(LOTTO_TICKET_SIZE)
                .sorted()
                .collect(Collectors.toList()));
    }

    public static LottoTicket createManualLottoTicket(List<String> numbers) {
        return new LottoTicket(numbers.stream()
                .map(LottoNumber::of)
                .sorted()
                .collect(Collectors.toList()));
    }
}
