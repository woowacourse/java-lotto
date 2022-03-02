package dto;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Lotto;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("LottoDto 테스트")
public class LottoDtoTest {
    private Lotto lotto;

    @BeforeEach
    void setUp() {
        Set<Integer> lottoNumberValues = Set.of(7, 10, 3, 42, 5, 25);
        lotto = new Lotto(lottoNumberValues);
    }

    @Test
    @DisplayName("생성자에 Lotto 를 전달받으면 객체가 생성된다.")
    void createLottosDto() {
        // given & when
        LottoDto lottoDto = new LottoDto(lotto);

        // then
        assertThat(lottoDto).isNotNull();
    }

    @Test
    @DisplayName("getLottos 는 크기가 6인 List<Integer> 를 반환한다.")
    void getLotto() {
        // given
        LottoDto lottoDto = new LottoDto(lotto);

        // when
        List<Integer> actual = lottoDto.getLottoNumbers();

        // then
        assertThat(actual.size()).isEqualTo(6);
    }
}
