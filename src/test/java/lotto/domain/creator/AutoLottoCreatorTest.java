package lotto.domain.creator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class AutoLottoCreatorTest {
    @Test
    void 입력한_갯수만큼_로또가_생성되는지_확인() {
        AutoLottoCreator creator = new AutoLottoCreator();
        assertThat(creator.createLottos(3).size()).isEqualTo(3);
    }
}
