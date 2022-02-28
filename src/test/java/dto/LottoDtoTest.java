package dto;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Lotto;
import domain.LottoNumber;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoDtoTest {
    private Lotto lotto;

    @BeforeEach
    void setUp() {
        Set<LottoNumber> lottoNumbers = Set.of(
                new LottoNumber(7),
                new LottoNumber(10),
                new LottoNumber(3),
                new LottoNumber(42),
                new LottoNumber(5),
                new LottoNumber(25)
        );

        lotto = new Lotto(lottoNumbers);
    }

    @Test
    @DisplayName("LottosDto 생성 테스트")
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

    @Test
    @DisplayName("getLottos 는 오름차순 정렬된 List<Integer> 를 반환한다.")
    void elementsOfGetLottosShouldSortedInAscendingOrder() {
        // given
        LottoDto lottoDto = new LottoDto(lotto);

        // when
        List<Integer> actual = lottoDto.getLottoNumbers();
        List<Integer> expected = List.of(3, 5, 7, 10, 25, 42);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
