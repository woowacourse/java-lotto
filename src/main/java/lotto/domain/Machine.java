package lotto.domain;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.List;

public class Machine {
    private static final String IS_NUMBER = "\\d+";

    public List<LottoTicket> buyTickets(String moneyValue) {
        if (!Pattern.matches(IS_NUMBER, moneyValue)) {
            throw new RuntimeException();
        }
        int money = Integer.parseInt(moneyValue);
        int ticketCount = money / LottoTicket.PRICE;

        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            lottoTickets.add(new LottoTicket("1,2,3,4,5,6"));
        }
        return lottoTickets;
    }

    // 금액입력받아서

    // 로또 머신에게 발행 -> 로또티켓 리스트를 돌려줌 :List로 보관

    // Enum 이랑 비교해서 결과를 파악함

    // 로또 티켓을 사고 -> 우승얼마나했는지

    // 결과 알려줌???
}
