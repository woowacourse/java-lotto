package lotto.domain;

import lotto.exception.LottoPriceException;
import lotto.util.LottoGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class LottoSellerTest {

    private List<Lotto> manualLottos;

    @BeforeEach
    void setUp() {
        manualLottos = new ArrayList<>();
    }

    @Test
    @DisplayName("로또 생성 성공")
    void createLotto_enoughMoney() {
        LottoGroup lottoGroup = LottoSeller.sellLotto(Money.of(2000), 0, manualLottos);
        assertThat(lottoGroup.size()).isEqualTo(2);
    }

    @ParameterizedTest
    @DisplayName("로또 생성 실패 - 음수 또는 부족한 돈")
    @ValueSource(ints = {500, -100})
    void createLotto_notEnoughMoney(int price) {
        Assertions.assertThatThrownBy(() -> LottoSeller.sellLotto(Money.of(price), 0, manualLottos))
                .isInstanceOf(LottoPriceException.class);
    }

    @ParameterizedTest
    @DisplayName("로또 수동 생성 성공")
    @CsvSource(value = {"3000:3:1,2,3,4,5,6/7,8,9,10,11,12/13,14,15,16,17,18"}, delimiter = ':')
    void createLotto_Manual(String money, int manualCount, String numbers) {
        for(String number : numbers.split("/")){
            List<Integer> lottoNumbers = Arrays
                    .stream(number.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            manualLottos.add(LottoGenerator.generate(lottoNumbers));
        }

        List<Lotto> lottos = LottoSeller.sellLotto(Money.of(Integer.parseInt(money)), manualCount, manualLottos).lottoGroup();
        assertThat(lottos).isEqualTo(manualLottos);
    }

    @ParameterizedTest
    @DisplayName("로또 수동 생성 실패 - 전체 로또 개수보다 많은 수동 로또 개수")
    @CsvSource(value = {"1000:3:1,2,3,4,5,6/7,8,9,10,11,12/13,14,15,16,17,18"}, delimiter = ':')
    void createLotto_OverManualCount(String money, int manualCount, String numbers) {
        List<Lotto> manualLottos = new ArrayList<>();
        for(String number : numbers.split("/")){
            List<Integer> lottoNumbers = Arrays
                    .stream(number.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            manualLottos.add(LottoGenerator.generate(lottoNumbers));
        }
        Assertions.assertThatThrownBy(() -> LottoSeller.sellLotto(Money.of(Integer.parseInt(money)), manualCount, manualLottos))
                .isInstanceOf(LottoPriceException.class);
    }
}