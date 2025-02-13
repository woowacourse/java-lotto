package model;

import static org.junit.jupiter.api.Assertions.*;
import static util.LottoUtil.*;

import com.sun.source.tree.UsesTree;
import java.util.Set;
import jdk.jshell.execution.Util;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import util.LottoUtil;

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

        Assertions.assertThat(lotto).isEqualTo(generateTestLotto(1,2,3,4,5,6));
    }
}
