package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.domain.result.Rank;

class WinningLottoTest {

    private LottoNumber numberSix;
    private LottoNumber numberSeven;
    private LottoTicket firstLottoTicket;

    @BeforeEach
    void setUp() {
        LottoNumber numberOne = new LottoNumber(1);
        LottoNumber numberTwo = new LottoNumber(2);
        LottoNumber numberThree = new LottoNumber(3);
        LottoNumber numberFour = new LottoNumber(4);
        LottoNumber numberFive = new LottoNumber(5);
        numberSix = new LottoNumber(6);
        numberSeven = new LottoNumber(7);
        Set<LottoNumber> firstLottoNumbers = Arrays.stream(
                new LottoNumber[] {numberOne, numberTwo, numberThree, numberFour, numberFive, numberSix}).collect(Collectors.toSet());
        firstLottoTicket = new LottoTicket(firstLottoNumbers);
    }




    @Test
    @DisplayName("WinningLotto는 LottoTicket과 LottoNumber로 생성")
    void createWinningLotto() {
        LottoTicket lottoTicket = firstLottoTicket;
        LottoNumber bonusNumber = numberSeven;
        assertThat(new WinningLotto(lottoTicket, bonusNumber)).isNotNull();

    }

    @Test
    @DisplayName("WinningLotto 생성시 bonus 번호가 lottoTicket에 있으면 예외 발생")
    void lottoTicketHasBonusNumberThrowsExeption() {
        LottoTicket lottoTicket = firstLottoTicket;
        LottoNumber bonusNumber = numberSix;

        assertThatThrownBy(() -> new WinningLotto(lottoTicket, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("createRank")
    @DisplayName("WinningLotto는 lottoTicket을 받아서 rank를 반환")
    void testWinningLottoReturnRank(WinningLotto winningLotto, LottoTicket lottoTicket, Rank rank) {
        assertThat(winningLotto.getRank(lottoTicket)).isEqualTo(rank);
    }

    private static Stream<Arguments> createRank() {
        LottoNumber numberOne = new LottoNumber(1);
        LottoNumber numberTwo = new LottoNumber(2);
        LottoNumber numberThree = new LottoNumber(3);
        LottoNumber numberFour = new LottoNumber(4);
        LottoNumber numberFive = new LottoNumber(5);
        LottoNumber numberSix = new LottoNumber(6);
        LottoNumber numberSeven = new LottoNumber(7);
        LottoNumber numberEight = new LottoNumber(8);
        LottoNumber numberNine = new LottoNumber(9);
        LottoNumber numberTen = new LottoNumber(10);
        Set<LottoNumber> firstLottoNumbers = Arrays.stream(
                new LottoNumber[] {numberOne, numberTwo, numberThree, numberFour, numberFive, numberSix}).collect(Collectors.toSet());
        Set<LottoNumber> secondLottoNumbers = Arrays.stream(
                new LottoNumber[] {numberOne, numberTwo, numberThree, numberFour, numberFive, numberSeven}).collect(Collectors.toSet());
        Set<LottoNumber> thirdLottoNumbers = Arrays.stream(
                new LottoNumber[] {numberTwo, numberThree, numberFour, numberFive, numberSix, numberEight}).collect(Collectors.toSet());
        Set<LottoNumber> fourthLottoNumbers = Arrays.stream(
                new LottoNumber[] {numberThree, numberFour, numberFive, numberSix, numberSeven, numberEight}).collect(Collectors.toSet());
        Set<LottoNumber> fifthLottoNumbers = Arrays.stream(
                new LottoNumber[] {numberFour, numberFive, numberSix, numberSeven, numberEight, numberNine}).collect(Collectors.toSet());
        Set<LottoNumber> missLottoNumbers = Arrays.stream(
                new LottoNumber[] {numberFive, numberSix, numberSeven, numberEight, numberNine, numberTen}).collect(Collectors.toSet());
        LottoTicket firstLottoTicket = new LottoTicket(firstLottoNumbers);
        LottoTicket secondLottoTicket = new LottoTicket(secondLottoNumbers);
        LottoTicket thirdLottoTicket = new LottoTicket(thirdLottoNumbers);
        LottoTicket fourthLottoTicket = new LottoTicket(fourthLottoNumbers);
        LottoTicket fifthLottoTicket = new LottoTicket(fifthLottoNumbers);
        LottoTicket missLottoTicket = new LottoTicket(missLottoNumbers);
        WinningLotto winningLotto = new WinningLotto(firstLottoTicket, numberSeven);

        return Stream.of(
                Arguments.of(winningLotto, missLottoTicket, Rank.MISS),
                Arguments.of(winningLotto, fifthLottoTicket, Rank.FIFTH),
                Arguments.of(winningLotto, fourthLottoTicket, Rank.FOURTH),
                Arguments.of(winningLotto, thirdLottoTicket, Rank.THIRD),
                Arguments.of(winningLotto, secondLottoTicket, Rank.SECOND),
                Arguments.of(winningLotto, firstLottoTicket, Rank.FIRST)
        );
    }
}
