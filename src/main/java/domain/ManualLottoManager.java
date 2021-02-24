package domain;

import java.util.List;

public class ManualLottoManager {

    public static List<LottoTicket> generate(final Money money, final List<String> manualTickets) {
        validateSizeOfNum(money, manualTickets);
        return LottoMachine.generateManualLottoTickets(manualTickets);
    }

    private static void validateSizeOfNum(final Money money, final List<String> manualTickets) {
        if (!money.canBuy(manualTickets.size())) {
            throw new IllegalArgumentException("입력 숫자가 구매 가능 숫자를 초과했습니다");
        }
    }
}
