package lotto.model.lottostore;

import lotto.model.customer.CustomLottoNumbers;
import lotto.model.customer.PurchaseAmount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoStoreTest {
    PurchaseAmount purchaseAmount = new PurchaseAmount(5000);
    List<List<Integer>> customLottoNumbers = new ArrayList<>();
    List<Integer> lottoNumber = Arrays.asList(1, 2, 3, 4, 5, 6);

    @BeforeEach
    void setup() {
        customLottoNumbers = new ArrayList<>();
    }

    @Test
    void _5000원_수동_0개일_때_로또개수_확인() {
        CustomLottoNumbers customLottoNumber = new CustomLottoNumbers(customLottoNumbers);

        assertThat(LottoStore.makeLottoTickets(purchaseAmount, customLottoNumber).size()).isEqualTo(5);
    }

    @Test
    void _5000원_수동_3개일_때_로또개수_확인() {
        for (int i = 0; i < 3; i++) {
            customLottoNumbers.add(lottoNumber);
        }
        CustomLottoNumbers customLottoNumber = new CustomLottoNumbers(customLottoNumbers);

        assertThat(LottoStore.makeLottoTickets(purchaseAmount, customLottoNumber).size()).isEqualTo(5);
    }

    @Test
    void _5000원_수동_5개일_때_로또개수_확인() {
        for (int i = 0; i < 5; i++) {
            customLottoNumbers.add(lottoNumber);
        }
        CustomLottoNumbers customLottoNumber = new CustomLottoNumbers(customLottoNumbers);

        assertThat(LottoStore.makeLottoTickets(purchaseAmount, customLottoNumber).size()).isEqualTo(5);
    }

    @AfterEach
    void tearDown() {
        customLottoNumbers = null;
    }
}
