package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoManager {
    public static LottoResults match(LottoTickets lottoTickets, WinningLottoTicket winningLottoTicket) {
        return new LottoResults(lottoTickets, winningLottoTicket);
    }

    public static LottoTickets generateLottoTickets(List<String> manualLottoTickets, AutoCount autoCount) {
        List<LottoTicket> lottoTickets = new ArrayList<>();

        lottoTickets.addAll(generateManualLottoTickets(manualLottoTickets));
        lottoTickets.addAll(generateAutoLottoTickets(autoCount));

        return new LottoTickets(lottoTickets);
    }

    private static List<LottoTicket> generateManualLottoTickets(List<String> manualLottoTickets) {
        return new ManualLottoGenerator(manualLottoTickets).generate();
    }

    private static List<LottoTicket> generateAutoLottoTickets(AutoCount autoCount) {
        return new AutoLottoGenerator(autoCount).generate();
    }
}
