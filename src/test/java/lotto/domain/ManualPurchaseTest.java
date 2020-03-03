package lotto.domain;

import lotto.generator.NumberGenerator;
import lotto.generator.UserInputNumberGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ManualPurchaseTest {
    @Test
    void getManualPurchaseCountTest() {
        Payment payment = new Payment("4000");
        String purchaseCount = "4";
        ManualPurchaseCount manualPurchaseCount = new ManualPurchaseCount(purchaseCount, payment);
        List<String> manualNumbers = new ArrayList<>(Arrays.asList(
                "1,2,3,4,5,6",
                "11,12,13,14,15,16",
                "21,22,23,24,25,26",
                "31,32,33,34,35,36"
        ));
        ManualPurchase manualPurchase = new ManualPurchase(manualPurchaseCount, manualNumbers);
        assertThat(manualPurchase.getManualPurchaseCount())
                .isEqualTo(4);
    }

    @Test
    void getManualTicketsTest() {
        NumberGenerator numberGenerator = new UserInputNumberGenerator();
        Payment payment = new Payment("2000");
        String purchaseCount = "2";
        ManualPurchaseCount manualPurchaseCount = new ManualPurchaseCount(purchaseCount, payment);
        List<String> manualNumbers = new ArrayList<>(Arrays.asList(
                "1,2,3,4,5,6",
                "11,12,13,14,15,16"
        ));
        List<LottoTicket> lottoTickets = new ArrayList<>();
        List<LottoNumber> firstLottoNumbers = numberGenerator.generateNumbers("1,2,3,4,5,6");
        List<LottoNumber> secondLottoNumbers = numberGenerator.generateNumbers("11,12,13,14,15,16");

        lottoTickets.add(new LottoTicket(firstLottoNumbers));
        lottoTickets.add(new LottoTicket(secondLottoNumbers));

        ManualPurchase manualPurchase = new ManualPurchase(manualPurchaseCount, manualNumbers);
        assertThat(manualPurchase.getManualTickets())
                .isEqualTo(lottoTickets);
    }
}
