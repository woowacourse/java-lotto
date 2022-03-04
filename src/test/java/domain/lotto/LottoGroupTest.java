package domain.lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import exception.NullException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class LottoGroupTest {
    private final Lotto lotto1 = Lotto.from(toNumberList(List.of(1, 2, 3, 4, 5, 6)));
    private final Lotto lotto2 = Lotto.from(toNumberList(List.of(3, 4, 5, 6, 7, 8)));

    private List<LottoNumber> toNumberList(List<Integer> raw) {
        return raw.stream().map(LottoNumber::getInstance).collect(Collectors.toList());
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void 생성자_검사() {
        LottoGroup lottoGroup = LottoGroup.of(List.of(lotto1, lotto2));
        assertThat(lottoGroup.get())
                .isEqualTo(List.of(lotto1, lotto2));
    }

    @Test
    void 생성자_빈로또_에러() {
        assertThatThrownBy(() -> LottoGroup.of(new ArrayList<>()))
                .isInstanceOf(NullException.class)
                .hasMessage("1개 이상의 값이 포함되어야 한다.");
    }
}