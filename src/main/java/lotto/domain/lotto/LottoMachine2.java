package lotto.domain.lotto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.result.UsersLottoTickets;
import lotto.utils.LottoGenerator;
import lotto.utils.NumericStringValidator;
import lotto.utils.RandomLottoGenerator;

public class LottoMachine2 {

    private Money money;
    //todo 티켓수 BigInteger or int?
    private final int manuallyBuyAmount;
    private final LottoGenerator lottoGenerator;

    private LottoMachine2(Money money, int manuallyBuyAmount, LottoGenerator lottoGenerator) {
        this.money = money;
        this.manuallyBuyAmount = manuallyBuyAmount;
        this.lottoGenerator = lottoGenerator;
    }

    public static LottoMachine2 getInstance(Money money, String manualAmountInput) {
        // todo Amount 클래스 분리?
        // 정규식 검증
        if (!NumericStringValidator.isValid(manualAmountInput)) {
            throw new IllegalArgumentException("수동 구매 개수는 0 이상의 정수만 가능합니다.");
        }

        // 구입금액,총구매개수 이내 검증
        int amount = Integer.parseInt(manualAmountInput);
        int ticketCount = money.toBigInteger().divide(LottoTicket.PRICE).intValue();
        if (amount > ticketCount) {
            throw new IllegalArgumentException("수동 구매 개수는 총 구매 개수 이내만 가능합니다.");
        }

        return new LottoMachine2(money, amount, new RandomLottoGenerator());
    }

    public int getManualBuyAmount() {
        //todo 티켓수 BigInteger or int?
        return manuallyBuyAmount;
    }

    public UsersLottoTickets buyTickets(List<String> manualTicketsValue) {
        List<LottoTicket> manualTickets = convertToLottoTickets(manualTicketsValue);
        List<LottoTicket> autoTickets = getAutoTickets();

        return new UsersLottoTickets(manualTickets, autoTickets);
    }

    private List<LottoTicket> convertToLottoTickets(List<String> manualTicketsValue) {
        List<LottoTicket> manualLottoTickets = manualTicketsValue.stream()
                .map(LottoTicket::new)
                .collect(Collectors.toList());

        decreaseMoney(manualLottoTickets);
        return manualLottoTickets;
    }

    private List<LottoTicket> getAutoTickets() {
        ArrayList<LottoTicket> lottoTickets = new ArrayList<>();

        //todo 티켓수 BigInteger or int?
        for (BigInteger ticketAmount = getAutoTicketAmount();
                ticketAmount.compareTo(BigInteger.ZERO) > 0;
                ticketAmount = ticketAmount.subtract(BigInteger.ONE)) {

            lottoTickets.add(lottoGenerator.generateLottoTicket());
        }

        decreaseMoney(lottoTickets);
        return lottoTickets;
    }

    private void decreaseMoney(List<LottoTicket> manualLottoTickets) {
        money = money.subtract(getPrice(manualLottoTickets));
    }

    private BigInteger getPrice(List<LottoTicket> manualLottoTickets) {
        return LottoTicket.PRICE.multiply(BigInteger.valueOf(manualLottoTickets.size()));
    }

    private BigInteger getAutoTicketAmount() {
        return money.toBigInteger().divide(LottoTicket.PRICE);
    }
}
