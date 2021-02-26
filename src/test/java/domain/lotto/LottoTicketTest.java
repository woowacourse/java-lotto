package domain.lotto;

import domain.ball.LottoBall;
import domain.ball.LottoBalls;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThatCode;

public class LottoTicketTest {

    @DisplayName("LottoTicket 정상 생성 테스트.")
    @Test
    public void lottoTicketGenerateTest() {
        //given
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        //when
        List<LottoBall> lottoBalls = lottoNumbers.stream()
                .map(lottoNumber -> LottoBall.from(lottoNumber))
                .collect(Collectors.toList());

        //then
        assertThatCode(() -> new LottoTicket(new LottoBalls(lottoBalls))).doesNotThrowAnyException();
    }
}
