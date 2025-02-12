package model;

import static org.junit.jupiter.api.Assertions.*;

import com.sun.source.tree.UsesTree;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class LottoGeneraterTest {

    @Test
    void 로또_생성을_테스트합니다(){
        CustomLottoNumberPicker customLottoNumberPicker = new CustomLottoNumberPicker();
        LottoGenerater lottoGenerater = new LottoGenerater(customLottoNumberPicker);
        customLottoNumberPicker.addValue(1);
        customLottoNumberPicker.addValue(2);
        customLottoNumberPicker.addValue(3);
        customLottoNumberPicker.addValue(4);
        customLottoNumberPicker.addValue(5);
        customLottoNumberPicker.addValue(6);

        Lotto lotto = lottoGenerater.generateLotto();

        Assertions.assertThat(lotto.getLottoNumbers()).isEqualTo(Set.of(
                new Number(1),
                new Number(2),
                new Number(3),
                new Number(4),
                new Number(5),
                new Number(6)
        ));
    }
}
