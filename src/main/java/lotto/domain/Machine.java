package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import lotto.utils.LottoGenerator;
import lotto.utils.StringValidator;

// 금액입력받아서
// 로또 머신에게 발행 -> 로또티켓 리스트를 돌려줌 :List로 보관
// Enum 이랑 비교해서 결과를 파악함
// 로또 티켓을 사고 -> 우승얼마나했는지
// 결과 알려줌???
public class Machine {

    private final int money;

    public Machine(String moneyValue) {
        StringValidator.validateIsDigit(moneyValue);
        money = Integer.parseInt(moneyValue);
    }

    public List<LottoTicket> buyTickets(LottoGenerator lottoGenerator) {
        int ticketCount;
        List<LottoTicket> lottoTickets = new ArrayList<>();

        ticketCount = getTicketCount();

        for (int i = 0; i < ticketCount; i++) {
            lottoTickets.add(lottoGenerator.generateLottoTicket());
        }
        return lottoTickets;
    }

    private int getTicketCount() {
        return money / LottoTicket.PRICE;
    }

    public Result getResult(String winningNumbersValue, String bonusBallValue,
            List<LottoTicket> lottoTickets) {
        WinningNumbers winningNumbers = new WinningNumbers(winningNumbersValue, bonusBallValue);

        return new Result(winningNumbers, lottoTickets, money);
    }
}
