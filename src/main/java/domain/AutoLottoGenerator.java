package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoLottoGenerator implements LottoTicketsGenerator {
    private static final int START_RANGE = 1;
    private static final int END_RANGE = 45;
    private static final int TICKET_SIZE = 6;

    private static final List<LottoNumber> shuffleLottoNumbers;
    private AutoCount autoCount;

    static {
        shuffleLottoNumbers = createLottoNumbers();
    }

    public AutoLottoGenerator(AutoCount autoCount) {
        this.autoCount = autoCount;
    }

    private static List<LottoNumber> createLottoNumbers() {
        return IntStream.rangeClosed(START_RANGE, END_RANGE)
                .mapToObj(LottoNumber::from)
                .collect(Collectors.toList());
    }

    private static LottoTicket createLottoTicketBySize() {

        Collections.shuffle(shuffleLottoNumbers);


        return new LottoTicket(shuffleLottoNumbers.stream()
                .limit(TICKET_SIZE)
                .collect(Collectors.toList()));
    }

    @Override
    public List<LottoTicket> generate() {
        return IntStream.range(0, autoCount.getAutoCount())
                .mapToObj(number -> this.createLottoTicketBySize())
                .collect(Collectors.toList());
    }
}
