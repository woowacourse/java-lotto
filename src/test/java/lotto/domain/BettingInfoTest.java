package lotto.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

public class BettingInfoTest {
    static PaidPrice paidPrice;
    static LottoSize autoLottoSize;
    static LottoSize manualLottoSize;
    static List<List<String>> manualLottoNumbers;

    @BeforeAll
    static void init() {
        paidPrice = new PaidPrice(5000);
        autoLottoSize = new LottoSize(paidPrice, 3);
        manualLottoSize = new LottoSize(paidPrice, 2);
        manualLottoNumbers =new ArrayList (Arrays.asList(IntStream.rangeClosed(1, 6).toArray(),
                IntStream.rangeClosed(11, 16).toArray()));
        System.out.println("");
    }

    @Test
    void 정상적으로_생성할_때 () {
        new BettingInfo(paidPrice, manualLottoSize, manualLottoNumbers);
    }

    @ParameterizedTest
    @NullSource
    void validateNotNull_PaidPrice가_Null일_때(PaidPrice nullValue) {
        assertThatThrownBy(() -> new BettingInfo(nullValue, manualLottoSize, manualLottoNumbers))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("구입 금액이(가) 입력되지 않았습니다.");
    }

    @ParameterizedTest
    @NullSource
    void validateNotNull_ManualLottoSize가_Null일_때(LottoSize nullValue) {
        assertThatThrownBy(() -> new BettingInfo(paidPrice, nullValue, manualLottoNumbers))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("수동 로또 개수이(가) 입력되지 않았습니다.");
    }

    @ParameterizedTest
    @NullSource
    void validateNotNull_ManualLottoNumbers가_Null일_때(List<List<String>> nullValue) {
        assertThatThrownBy(() -> new BettingInfo(paidPrice, manualLottoSize, nullValue))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("수동 로또 번호이(가) 입력되지 않았습니다.");
    }

    @Test
    void getOtherTypeLottoSize() {
        BettingInfo bettingInfo = new BettingInfo(paidPrice, manualLottoSize, manualLottoNumbers);
        int expected = 3;
        assertThat(bettingInfo.getAutoLottoSize() == expected);
    }
}
