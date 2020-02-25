package lotto.domain.lotto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static lotto.domain.lotto.LottoNumberTest.getFromOneToSevenWithoutSixFixture;
import static lotto.domain.lotto.LottoNumberTest.getLottoNumberFromFiveToTenFixture;
import static lotto.domain.lotto.LottoNumberTest.getLottoNumberFromFourToNineFixture;
import static lotto.domain.lotto.LottoNumberTest.getLottoNumberFromThreeToEightFixture;
import static lotto.domain.lotto.LottoNumberTest.getLottoNumberFromTwoToEightWithoutSevenFixture;
import static lotto.domain.lotto.LottoNumberTest.getNotProperLottoNumbersFixture;
import static lotto.domain.lotto.LottoNumberTest.getProperLottoNumbersFixture;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTicketTest {

    @Test
    void testLottoTicket() {
        Set<LottoNumber> lottoNumbers = getProperLottoNumbersFixture();
        LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
        assertThat(lottoTicket);
    }

    @Test
    void createLottoTicketThrowException() {
        Set<LottoNumber> lottoNumbers = getNotProperLottoNumbersFixture();
        assertThatThrownBy(() -> new LottoTicket(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void countNumberOfSameLottoNumber() {
        Set<LottoNumber> lottoNumbers = getProperLottoNumbersFixture();
        LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
        LottoTicket sameLottoTicket = new LottoTicket(lottoNumbers);
        int numberOfSameLottoNumber = 6;

        assertThat(lottoTicket.countMatches(sameLottoTicket)).isEqualTo(numberOfSameLottoNumber);
    }

    @Test
    void countMathcesWhenMachingFive() {
        Set<LottoNumber> lottoNumbers = getProperLottoNumbersFixture();
        LottoTicket lottoTicket = new LottoTicket(lottoNumbers);

        Set<LottoNumber> lottoNumberFromOneToSevenWithoutSix = getFromOneToSevenWithoutSixFixture();
        LottoTicket targetLottoTicket = new LottoTicket(lottoNumberFromOneToSevenWithoutSix);

        assertThat(lottoTicket.countMatches(targetLottoTicket)).isEqualTo(5);
    }

    public static LottoTicket getLottoTicketFromOneToSixFixture() {
        Set<LottoNumber> lottoNumbers = getProperLottoNumbersFixture();
        return new LottoTicket(lottoNumbers);
    }

    public static LottoTicket getLottoTicketFromOneToSevenWithoutSixFixture() {
        Set<LottoNumber> lottoNumbers = getFromOneToSevenWithoutSixFixture();
        return new LottoTicket(lottoNumbers);
    }

    public static LottoTicket getLottoTicketFromTwoToEightWithoutSevenFixture() {
        Set<LottoNumber> lottoNumbers = getLottoNumberFromTwoToEightWithoutSevenFixture();
        return new LottoTicket(lottoNumbers);
    }

    public static LottoTicket getLottoTicketFromThreeToEightFixture() {
        Set<LottoNumber> lottoNumbers = getLottoNumberFromThreeToEightFixture();
        return new LottoTicket(lottoNumbers);
    }

    public static LottoTicket getLottoTicketFromFourToNineFixture() {
        Set<LottoNumber> lottoNumbers = getLottoNumberFromFourToNineFixture();
        return new LottoTicket(lottoNumbers);
    }

    public static LottoTicket getLottoTicketFromFiveToTenFixture() {
        Set<LottoNumber> lottoNumbers = getLottoNumberFromFiveToTenFixture();
        return new LottoTicket(lottoNumbers);
    }

    public static List<LottoTicket> getLottoTicketListFixture() {
        LottoTicket firstLottoTicket = getLottoTicketFromOneToSixFixture();
        LottoTicket secondLottoTicket = getLottoTicketFromOneToSevenWithoutSixFixture();
        LottoTicket thirdLottoTicket = getLottoTicketFromTwoToEightWithoutSevenFixture();
        LottoTicket fourthLottoTicket = getLottoTicketFromThreeToEightFixture();
        LottoTicket fifthLottoTicket = getLottoTicketFromFourToNineFixture();
        LottoTicket missLottoTicket = getLottoTicketFromFiveToTenFixture();
        return Arrays.asList(firstLottoTicket, secondLottoTicket, thirdLottoTicket, fourthLottoTicket, fifthLottoTicket,
                missLottoTicket);
    }

}
