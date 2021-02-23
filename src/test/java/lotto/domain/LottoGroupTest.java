package lotto.domain;

import lotto.util.LottoGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGroupTest {

    private LottoGroup lottoGroup;
    private List<Lotto> lottos;

    @BeforeEach
    void setUp() {
        lottos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            lottos.add(LottoGenerator.generate());
        }
    }

    @Test
    @DisplayName("LottoGroup 생성 성공 테스트")
    void createLottoGroup() {
        lottoGroup = new LottoGroup(lottos, 5);
        assertThat(lottoGroup).isNotNull();
    }

    @Test
    @DisplayName("LottoGroup 사이즈가 일치하는 지 테스트")
    void checkLottoGroupSize() {
        lottoGroup = new LottoGroup(lottos,5);
        assertThat(lottoGroup.size()).isEqualTo(5);
    }

    @Test
    @DisplayName("Lotto를 저장하는 리스트인 lottos 변수를 반환하는지 테스트")
    void checkReturnLottosVariable() {
        lottoGroup = new LottoGroup(lottos, 5);
        assertThat(lottoGroup.lottoGroup()).isEqualTo(lottos);
    }
}
