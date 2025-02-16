package domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AnswerLottoTest {
    static final Lotto basicLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

    @DisplayName("AnswerLotto 객체 생성이 정상적으로 동작하는지 확인한다.")
    @Test
    void answerLotto_객체_생성_테스트() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> new AnswerLotto(basicLotto, 6));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> new AnswerLotto(basicLotto, 46));

        Assertions.assertThatNoException().isThrownBy(() -> new AnswerLotto(basicLotto, 7));
    }
}