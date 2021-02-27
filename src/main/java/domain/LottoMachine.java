package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
    public static List<LottoTicket> buyManual(List<List<Integer>> manualNumbers) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (List<Integer> manualNumber : manualNumbers) {
            lottoTickets.add(LottoTicket.of(manualNumber));
        }
        return lottoTickets;
    }

    public static List<LottoTicket> buyAuto(long autoCount) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < autoCount; i++) {
            lottoTickets.add(new LottoTicket(LottoNumberRepository.shuffleLottoNumbers()));
        }
        return lottoTickets;
    }
}
