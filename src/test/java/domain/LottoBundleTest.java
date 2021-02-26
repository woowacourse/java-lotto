package domain;

import domain.lotto.Lotto;
import domain.lotto.LottoBall;
import domain.lotto.LottoBundle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class LottoBundleTest {
    private List<Lotto> manualLottoBundle;
    private List<Lotto> autoLottoBundle;

    @BeforeEach
    void setUp() {
        manualLottoBundle = new ArrayList<>();
        manualLottoBundle.add(Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6)));
        manualLottoBundle.add(Lotto.of(Arrays.asList(2, 3, 4, 5, 6, 7)));

        autoLottoBundle = new ArrayList<>();
        autoLottoBundle.add(Lotto.of(Arrays.asList(38, 39, 40, 41, 42, 43)));
        autoLottoBundle.add(Lotto.of(Arrays.asList(39, 40, 41, 42, 43, 44)));
        autoLottoBundle.add(Lotto.of(Arrays.asList(40, 41, 42, 43, 44, 45)));
    }

    @DisplayName("LottoBundle 생성 테스트")
    @Test
    void LottoBundleConstructorTest() {
        assertThatCode(() -> new LottoBundle(Arrays.asList(
                makeProperLotto(),
                makeProperLotto()
        ))).doesNotThrowAnyException();
    }

    @DisplayName("LottoBundle countNumberOfLotto 테스트")
    @Test
    void countNumberOfLottoTest() {
        final LottoBundle lottoBundle = new LottoBundle(Arrays.asList(
                makeProperLotto(),
                makeProperLotto(),
                makeProperLotto()
        ));
        assertThat(lottoBundle.countNumberOfLotto()).isEqualTo(3);
    }

    @DisplayName("LottoBundle manualLottoBundle과 autoLottoBundle 구분")
    @Test
    void LottoBundleManualAndAuto() {
        final LottoBundle lottoBundle = new LottoBundle(manualLottoBundle, autoLottoBundle);
        final int totalCount = manualLottoBundle.size() + autoLottoBundle.size();
        assertThat(lottoBundle.countNumberOfLotto()).isEqualTo(totalCount);
    }

    @DisplayName("LottoBundle manualLottoBundle과 autoLottoBundle 가지고 있는지 확인")
    @Test
    void LottoBundleCheckManualAndAutoSaved() {
        final LottoBundle lottoBundle = new LottoBundle(manualLottoBundle, autoLottoBundle);
        for (Lotto manualLotto : manualLottoBundle) {
            assertThat(lottoBundle.getLottoBundle()).contains(manualLotto);
        }
        for (Lotto autoLotto : autoLottoBundle) {
            assertThat(lottoBundle.getLottoBundle()).contains(autoLotto);
        }
    }

    @DisplayName("LottoBundle에 저장된 manualLottoBundle과 autoLottoBundle 갯수 검증")
    @Test
    void LottoBundleCheckManualAndAutoNumber() {
        final LottoBundle manualAndAuto = new LottoBundle(manualLottoBundle, autoLottoBundle);
        assertThat(manualAndAuto.countNumberOfLotto()).isEqualTo(5);
        assertThat(manualAndAuto.countNumberOfManualLotto()).isEqualTo(2);
        assertThat(manualAndAuto.countNumberOfAutoLotto()).isEqualTo(3);

        final LottoBundle autoOnly = new LottoBundle(autoLottoBundle);
        assertThat(autoOnly.countNumberOfLotto()).isEqualTo(3);
        assertThat(autoOnly.countNumberOfManualLotto()).isEqualTo(0);
        assertThat(autoOnly.countNumberOfAutoLotto()).isEqualTo(3);
    }

    private Lotto makeProperLotto() {
        List<LottoBall> lotto = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lotto.add(LottoBall.valueOf(i));
        }
        return new Lotto(lotto);
    }
}
