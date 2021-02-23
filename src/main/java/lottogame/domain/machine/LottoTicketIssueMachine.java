package lottogame.domain.machine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lottogame.domain.Count;
import lottogame.domain.Money;
import lottogame.domain.number.LottoNumber;
import lottogame.domain.ticket.LottoTicket;
import lottogame.domain.ticket.LottoTickets;

public class LottoTicketIssueMachine {

    private static final int TICKET_PRICE = 1000;
    private static final int LOTTO_NUMBER_COUNT = 6;

    private final Money money;
    private final Count manualTicketCount;

    public LottoTicketIssueMachine(final Money money, final Count manualTicketCount) {
        this.money = money;
        this.manualTicketCount = manualTicketCount;
        validateMinPurchaseAmount(this.money);
        validateCanBuyCount(this.manualTicketCount);
    }

    public static int getTicketPrice() {
        return TICKET_PRICE;
    }

    private void validateMinPurchaseAmount(final Money money) {
        if (!money.canBuyAmount(TICKET_PRICE)) {
            throw new IllegalArgumentException("구입금액은 " + TICKET_PRICE + "원 이상이어야 합니다.");
        }
    }

    private void validateCanBuyCount(final Count manualTicketCount) {
        if (!money.canBuyAmount(manualTicketCount.multiplyWith(TICKET_PRICE))) {
            throw new IllegalArgumentException("수동으로 구매할 로또의 총 금액이 구입금액을 넘습니다.");
        }
    }

    public LottoTickets issueManualTickets(final List<Set<Integer>> manualTicketNumbers) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (Set<Integer> ticketNumbers : manualTicketNumbers) {
            this.money.spent(TICKET_PRICE);
            lottoTickets.add(new LottoTicket(convertToLottoNumbers(ticketNumbers)));
        }
        return new LottoTickets(lottoTickets);
    }

    private Set<LottoNumber> convertToLottoNumbers(Set<Integer> ticketNumbers) {
        return ticketNumbers.stream()
            .map(LottoNumber::new)
            .collect(Collectors.toSet());
    }

    public LottoTickets issueAutoTickets() {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        while (this.money.canBuyAmount(TICKET_PRICE)) {
            this.money.spent(TICKET_PRICE);
            lottoTickets.add(new LottoTicket(issueNumbers()));
        }
        return new LottoTickets(lottoTickets);
    }

    private Set<LottoNumber> issueNumbers() {
        Set<LottoNumber> lottoNumbers = new HashSet<>();
        while (lottoNumbers.size() < LOTTO_NUMBER_COUNT) {
            lottoNumbers.add(new LottoNumber());
        }
        return lottoNumbers;
    }
}
