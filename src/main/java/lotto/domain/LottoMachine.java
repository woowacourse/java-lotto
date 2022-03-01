package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoMachine {

    private static final int LOTTO_NUMBER_START_INDEX = 0;
    private static final int LOTTO_NUMBER_END_INDEX = 6;
    private static final int LOTTO_NUMBER_LOWER_BOUND = 1;
    private static final int LOTTO_NUMBER_UPPER_BOUND = 46;

    private final List<LottoNumber> numbers;
    private final int totalTicketCount;
    private final int manualTicketCount;

    public LottoMachine(int totalTicketCount, int manualTicketCount) {
        numbers = initNumbers();

        if (totalTicketCount < manualTicketCount) {
            throw new IllegalArgumentException("[ERROR] 최대 구입 가능 개수를 넘었습니다. 최대 구입 가능 개수 = " + totalTicketCount);
        }
        this.totalTicketCount = totalTicketCount;
        this.manualTicketCount = manualTicketCount;
    }

    private List<LottoNumber> initNumbers() {
        List<LottoNumber> numbers = new ArrayList<>();
        for (int i = LOTTO_NUMBER_LOWER_BOUND; i < LOTTO_NUMBER_UPPER_BOUND; i++) {
            numbers.add(new LottoNumber(i));
        }
        return numbers;
    }

    public List<Lotto> makeLottoTickets(List<Lotto> manualLottos) {
        ArrayList<Lotto> lottoTickets = new ArrayList<>();
        if (manualLottos.size() != 0) {
            lottoTickets.addAll(manualLottos);
        }

        int autoLottoCount = totalTicketCount - manualTicketCount;
        for (int i = 0; i < autoLottoCount; i++) {
            lottoTickets.add(makeLottoTicket());
        }
        return lottoTickets;
    }

    private Lotto makeLottoTicket() {
        Collections.shuffle(numbers);
        return new Lotto(numbers.subList(LOTTO_NUMBER_START_INDEX, LOTTO_NUMBER_END_INDEX));
    }

    public int getManualTicketCount() {
        return manualTicketCount;
    }

    public int getAutoTicketCount() {
        return totalTicketCount - manualTicketCount;
    }
}
