package lotto.domain;

import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.result.WinningLotto;
import lotto.utils.ParseUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {
    private List<String> lottoNumbers;
    private LottoTicket winningTicket;

    @BeforeEach
    void init() {
        lottoNumbers = Arrays.asList("1", "2", "3", "4", "5", "7");
        winningTicket = LottoTicket.manual(lottoNumbers);
    }

    @Test
    @DisplayName("당첨번호와 보너스 번호가 중복되는 지 검증")
    void checkDuplicateBonusNumber() {
        LottoNumber bonusNumber = LottoNumber.valueOf(ParseUtil.parseInt("7"));
        assertThatThrownBy(() -> {
            WinningLotto winningLotto = new WinningLotto(winningTicket, bonusNumber);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨 티켓 가져오기 검증")
    void getWinningTicket() {
        LottoNumber bonusNumber = LottoNumber.valueOf(ParseUtil.parseInt("8"));
        WinningLotto winningLotto = new WinningLotto(winningTicket, bonusNumber);
        assertThat(winningLotto.getWinningTicket()).isEqualTo(winningTicket);
    }

    @Test
    @DisplayName("보너스볼 가져오기 검증")
    void getBonusNumber() {
        LottoNumber bonusNumber = LottoNumber.valueOf(ParseUtil.parseInt("8"));
        WinningLotto winningLotto = new WinningLotto(winningTicket, bonusNumber);
        assertThat(winningLotto.getBonusNumber()).isEqualTo(bonusNumber);
    }
}