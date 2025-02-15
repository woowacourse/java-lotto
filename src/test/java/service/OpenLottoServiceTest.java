package service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import constant.WinningCount;
import dto.IssuedLottoDto;
import dto.IssuedLottosDto;
import dto.WinningLottoDto;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class OpenLottoServiceTest {

    private final OpenLottoService openLottoService = new OpenLottoService();

    @DisplayName("로또 결과 반환 테스트")
    @Test
    void lottoResultTest(){
        // given
        WinningLottoDto winningLottoDto = new WinningLottoDto(List.of(1,2,3,4,5,6),7);
        IssuedLottoDto issuedLottoDto1 = new IssuedLottoDto(List.of(1,10,11,12,13,14));
        IssuedLottoDto issuedLottoDto2 = new IssuedLottoDto(List.of(1,2,11,12,13,14));
        IssuedLottoDto issuedLottoDto3 = new IssuedLottoDto(List.of(1,2,3,12,13,14));
        IssuedLottoDto issuedLottoDto4 = new IssuedLottoDto(List.of(1,2,3,4,13,14));
        IssuedLottoDto issuedLottoDto5 = new IssuedLottoDto(List.of(1,2,3,4,5,14));
        IssuedLottoDto issuedLottoDto6 = new IssuedLottoDto(List.of(1,2,3,4,5,7));
        IssuedLottoDto issuedLottoDto7 = new IssuedLottoDto(List.of(1,2,3,4,5,6));
        IssuedLottosDto issuedLottosDto = new IssuedLottosDto(
                List.of(issuedLottoDto1,issuedLottoDto2,issuedLottoDto3,issuedLottoDto4,issuedLottoDto5,issuedLottoDto6,issuedLottoDto7)
        );
        // when
        Map<WinningCount, Integer> result = openLottoService.openResult(winningLottoDto, issuedLottosDto);
        // then
        assertThat(result.get(WinningCount.NONE)).isEqualTo(2);
        assertThat(result.get(WinningCount.THREE)).isEqualTo(1);
        assertThat(result.get(WinningCount.FOUR)).isEqualTo(1);
        assertThat(result.get(WinningCount.FIVE)).isEqualTo(1);
        assertThat(result.get(WinningCount.FIVE_BONUS)).isEqualTo(1);
        assertThat(result.get(WinningCount.SIX)).isEqualTo(1);
    }

    @Nested
    @DisplayName("이익률 계산 검증")
    class EarningRateTest{
        @DisplayName("당첨되었을 때")
        @Test
        void earningRateTest(){
            // given
            Map<WinningCount,Integer> result = new EnumMap<>(WinningCount.class);
            result.put(WinningCount.THREE,2);
            result.put(WinningCount.FOUR,1);
            result.put(WinningCount.FIVE,1);
            result.put(WinningCount.FIVE_BONUS,1);
            int purchasedCost = 430000;
            // when
            Double earningRate = openLottoService.calculateEarningRate(result, purchasedCost);
            // then
            assertThat(earningRate).isEqualTo(73.4);
        }

        @DisplayName("당첨이 안되었을 때")
        @Test
        void earningRateTest2(){
            // given
            Map<WinningCount,Integer> result = new EnumMap<>(WinningCount.class);
            result.put(WinningCount.NONE,43);
            int purchasedCost = 430000;
            // when
            Double earningRate = openLottoService.calculateEarningRate(result, purchasedCost);
            // then
            assertThat(earningRate).isEqualTo(0);
        }
    }
}