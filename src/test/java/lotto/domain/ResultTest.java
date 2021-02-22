package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.EnumMap;
import lotto.utils.FixedLottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {

    @Test
    @DisplayName("1등 2개")
    void getResultMap1() {
        LottoTickets lottoTickets = new LottoTickets(2, new FixedLottoGenerator());
        Result result = new Result("1,2,3,4,5,6", "7", lottoTickets);

        EnumMap<Rank, Object> mapToCompare = new EnumMap<>(Rank.class);
        mapToCompare.put(Rank.FIRST_PLACE, 2);

        assertThat(result.getResultMap()).isEqualTo(mapToCompare);
    }

    @Test
    @DisplayName("2등 2개")
    void getResultMap2() {
        LottoTickets lottoTickets = new LottoTickets(2, new FixedLottoGenerator());
        Result result = new Result("1,2,3,4,5,7", "6", lottoTickets);

        EnumMap<Rank, Object> mapToCompare = new EnumMap<>(Rank.class);
        mapToCompare.put(Rank.SEC0ND_PLACE, 2);

        assertThat(result.getResultMap()).isEqualTo(mapToCompare);
    }

    @Test
    @DisplayName("2장 구매시, 1등 2개 수익률")
    void getEarningRate1() {
        final int TICKET_COUNT = 2;
        BigInteger outputMoney = Rank.FIRST_PLACE.getPrize().multiply(new BigDecimal(TICKET_COUNT))
            .toBigInteger();
        BigInteger inputMoney = new BigInteger(String.valueOf(LottoTicket.PRICE * TICKET_COUNT));
        BigInteger earningRate = outputMoney.divide(inputMoney)
            .multiply(new BigInteger("100"));

        LottoTickets lottoTickets = new LottoTickets(TICKET_COUNT, new FixedLottoGenerator());
        Result result = new Result("1,2,3,4,5,6", "7", lottoTickets);

        assertThat(result.getEarningRate()).isEqualTo(earningRate);
    }

    @Test
    @DisplayName("2장 구매시, 2등 2개 수익률")
    void getEarningRate2() {
        final int TICKET_COUNT = 2;
        BigInteger outputMoney = Rank.SEC0ND_PLACE.getPrize().multiply(new BigDecimal(TICKET_COUNT))
            .toBigInteger();
        BigInteger inputMoney = new BigInteger(String.valueOf(LottoTicket.PRICE * TICKET_COUNT));
        BigInteger earningRate = outputMoney.divide(inputMoney)
            .multiply(new BigInteger("100"));

        LottoTickets lottoTickets = new LottoTickets(TICKET_COUNT, new FixedLottoGenerator());
        Result result = new Result("1,2,3,4,5,7", "6", lottoTickets);

        assertThat(result.getEarningRate()).isEqualTo(earningRate);
    }

    @Test
    @DisplayName("2장 구매시, 미당첨 수익률")
    void getEarningRate3() {
        final int TICKET_COUNT = 2;
        BigInteger outputMoney = Rank.UNRANKED.getPrize().multiply(new BigDecimal(TICKET_COUNT))
            .toBigInteger();
        BigInteger inputMoney = new BigInteger(String.valueOf(LottoTicket.PRICE * TICKET_COUNT));
        BigInteger earningRate = outputMoney.divide(inputMoney)
            .multiply(new BigInteger("100"));

        LottoTickets lottoTickets = new LottoTickets(TICKET_COUNT, new FixedLottoGenerator());
        Result result = new Result("11,12,13,14,15,16", "6", lottoTickets);

        assertThat(result.getEarningRate()).isEqualTo(earningRate);
    }

}