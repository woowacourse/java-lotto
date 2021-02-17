package lotto.domain;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTicketsTest {
    public LottoTickets lottoTickets;

    @BeforeEach
    public void initLottoTickets() {
        List<LottoNumber> lottoNumbers1 = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(45)
        );
        List<LottoNumber> lottoNumbers2 = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(45)
        );
        List<LottoNumber> lottoNumbers3 = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(45)
        );
        lottoTickets = new LottoTickets(Arrays.asList(
                new LottoTicket(lottoNumbers1),
                new LottoTicket(lottoNumbers2),
                new LottoTicket(lottoNumbers3)
        ));
    }

    @ParameterizedTest
    @NullSource
    public void nullParameterTest(List<LottoTicket> lottoTickets) {
        assertThatThrownBy(() -> {
            new LottoTickets(lottoTickets);
        }).isInstanceOf(NullPointerException.class).hasMessage("null 값은 허용하지 않습니다.");
    }

    @ParameterizedTest
    @EmptySource
    public void emptyParameterTest(List<LottoTicket> lottoTickets) {
        assertThatThrownBy(() -> {
            new LottoTickets(lottoTickets);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("로또는 한장 이상 구매해야 합니다.");
    }


    @Test  //이 코드를 좀 더 깔끔하게 쓰는법 혹시 없을까요?
    @DisplayName("구매한 여러장의 로또 티켓들을 가진 객체를 생성한다.")
    public void createLottoTicketsTest() {
        List<LottoNumber> expectedLottoNumbers1 = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(45)
        );
        List<LottoNumber> expectedLottoNumbers2 = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(45)
        );
        List<LottoNumber> expectedLottoNumbers3 = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(45)
        );
        LottoTickets expectedLottoTickets = new LottoTickets(Arrays.asList(
                new LottoTicket(expectedLottoNumbers1),
                new LottoTicket(expectedLottoNumbers2),
                new LottoTicket(expectedLottoNumbers3)
        ));

        assertThat(lottoTickets).isEqualTo(expectedLottoTickets);
    }

    @Test
    @DisplayName("구매한 로또 티켓의 갯수만큼 로또가 생성 되었는지 확인한다.")
    public void lottoTicketsCountTest() {
        assertThat(lottoTickets.getLottoTickets().size()).isEqualTo(3);
    }
}
