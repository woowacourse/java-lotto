package lotto.service;

import lotto.domain.lotto.WinningLotto;
import lotto.dto.request.WinningLottoDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class LottoServiceTest {

    private static LottoService lottoService = new LottoService();

    @Test
    @DisplayName("lottoService는 winningLottoDto를 받아서 winningLotto를 생성")
    void createWinningLottoFromDto() {
        Set<Integer> winningLottoNumbers = Arrays.stream(new int[]{1,2,3,4,5,6}).boxed().collect(Collectors.toSet());
        Integer bonusNumber = 7;
        WinningLottoDto winningLottoDto = new WinningLottoDto(winningLottoNumbers, bonusNumber);
        WinningLotto winningLotto = lottoService.createWinningLotto(winningLottoDto);
        assertThat(winningLotto).isNotNull();
    }
}