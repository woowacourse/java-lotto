package lotto.domain.factory;

import lotto.domain.LottoMoney;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketsFactory {
    private static final int LOTTO_TICKETS_MIN_CONDITION = 0;

    public static LottoTickets create(final int manualAmount, List<String> manualLottoNumbers, final LottoMoney lottoMoney) {
        int totalAmount = lottoMoney.getAmount();
        int randomAmount = totalAmount - manualAmount;
        validateAmount(totalAmount, manualAmount);
        List<LottoTicket> lottoTickets = new ArrayList<>();
        if (manualAmount != LOTTO_TICKETS_MIN_CONDITION) {
            generateManualTickets(manualLottoNumbers, lottoTickets);
        }
        generateRandomTickets(randomAmount, lottoTickets);
        return new LottoTickets(lottoTickets);
    }

    private static void validateAmount(int totalAmount, int manualAmount) {
        if (totalAmount < manualAmount) {
            throw new IllegalArgumentException("수동으로 구입할수 있는 갯수는 총 갯수를 초과할수 없습니다.");
        }
    }

    private static void generateManualTickets(List<String> manualLottoNumbers, List<LottoTicket> lottoTickets) {
        for (String manualLottoNumber : manualLottoNumbers) {
            lottoTickets.add(new ManualTicketFactory(manualLottoNumber).create());
        }
    }

    private static void generateRandomTickets(int randomAmount, List<LottoTicket> lottoTickets) {
        for (int i = 0; i < randomAmount; i++) {
            lottoTickets.add(new RandomTicketFactory().create());
        }
    }
}
