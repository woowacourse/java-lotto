package lotto.service;

import lotto.domain.Money;
import lotto.domain.number.LottoNumbers;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTickets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoMachineServiceTest {
    private LottoMachineService lottoMachineService = new LottoMachineService(new Money(1000));

    @DisplayName("자동 로또 티켓 구매")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 10})
    void buyLottoTicket(int testSize) {
        LottoTickets lottoTickets = lottoMachineService.buyAutoLottoTicket(new Money(1000 * testSize));

        assertThat(lottoTickets.size()).isEqualTo(testSize);
    }

    @DisplayName("수동 로또 티켓 구매")
    @ParameterizedTest
    @MethodSource
    void buyManualLottoTicket(int sizeOfManualLotto, List<LottoNumbers> lottoNumbersBundle) {
        LottoTickets lottoTickets = lottoMachineService.buyManualLottoTicket(sizeOfManualLotto, lottoNumbersBundle);

        for (int i = 0; i < lottoNumbersBundle.size(); i++) {
            LottoTicket lottoTicket = lottoTickets.list().get(i);
            assertThat(lottoTicket.list().containsAll(lottoNumbersBundle.get(i).list()));
        }
        assertThat(lottoTickets.size()).isEqualTo(sizeOfManualLotto);
    }

    private static Stream<Arguments> buyManualLottoTicket() {
        return Stream.of(
                Arguments.of(1,
                        Arrays.asList(new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)))),
                Arguments.of(2,
                        Arrays.asList(
                                new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)),
                                new LottoNumbers(Arrays.asList(45, 44, 43, 42, 41, 40))
                        )
                ),
                Arguments.of(3,
                        Arrays.asList(
                                new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)),
                                new LottoNumbers(Arrays.asList(45, 44, 43, 42, 41, 40)),
                                new LottoNumbers(Arrays.asList(10, 11, 12, 13, 14, 15))
                        )
                )
        );
    }

    @DisplayName("로또 가격 받아오는 기능")
    @Test
    void getLottoPriceTest() {
        assertThat(lottoMachineService.getLottoPrice()).isEqualTo(new Money(1000));
    }
}