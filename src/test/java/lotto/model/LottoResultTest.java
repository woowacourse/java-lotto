package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {
    private LottoNumber one = LottoNumber.valueOf(1);
    private LottoNumber two = LottoNumber.valueOf(2);
    private LottoNumber three = LottoNumber.valueOf(3);
    private LottoNumber four = LottoNumber.valueOf(4);
    private LottoNumber five = LottoNumber.valueOf(5);
    private LottoNumber six = LottoNumber.valueOf(6);
    private LottoNumber seven = LottoNumber.valueOf(7);

    private LottoTicket lottoTicket = new LottoTicket(Arrays.asList(one, two, three, four, five, six));
    private WinNumber winNumber = new WinNumber(new LottoTicket(Arrays.asList(one, two, three, five, six, seven)));
    private BonusBall bonusBall = new BonusBall(winNumber, four);

    @Test
    @DisplayName("맞은 갯수에 따른 LottoRank 키의 값 상승")
    void checkCount() {
        LottoResult lottoResult = new LottoResult();
        lottoResult.checkCount(lottoTicket, winNumber, bonusBall);

        assertThat(lottoResult.rankResult(LottoRank.SECOND)).isEqualTo(1);
    }
}
