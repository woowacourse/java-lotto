package lotto.view;

import lotto.domain.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputView {
    
    private static final String PAY_AMOUNT_QUESTION = "구입금액을 입력해 주세요.";
    private static final String MANUAL_PURCHASE_COUNT_QUESTION = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String WINNING_LOTTO_NUMBER_QUESTION = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String BONUS_LOTTO_NUMBER_QUESTION = "보너스 볼을 입력해 주세요.";
    private static final String MANUAL_LOTTO_NUMBER_QUESTION = "수동으로 구매할 번호를 입력해 주세요.";
    
    private static final String DELIMITER = ",";
    
    private final Scanner scanner;
    
    public InputView() {
        scanner = new Scanner(System.in);
    }
    
    public PaymentAmount readPaymentAmount() {
        String input = read(PAY_AMOUNT_QUESTION);
        
        return PaymentAmount.from(input);
    }
    
    public PurchaseCount readPurchaseCount(PaymentAmount paymentAmount) {
        String input = read(MANUAL_PURCHASE_COUNT_QUESTION);
        
        return PurchaseCount.of(paymentAmount, input);
    }
    
    public Lottos readManualLotto(PurchaseCount purchaseCount) {
        String input = read(MANUAL_LOTTO_NUMBER_QUESTION);
    
        List<Lotto> lottos = Stream.generate(() -> readLotto(input))
              .limit(purchaseCount.getManualPurchaseCount())
              .collect(Collectors.toList());
        
        return new Lottos(lottos);
    }
    
    public Lotto readWinningLotto() {
        String input = read(WINNING_LOTTO_NUMBER_QUESTION);
    
        return readLotto(input);
    }
    
    private Lotto readLotto(String input) {
        Set<LottoNumber> lottoNumbers = Arrays.stream(input.split(DELIMITER))
                                              .map(LottoNumber::fromStringLottoNumber)
                                              .collect(Collectors.toSet());
        
        return Lotto.fromNumbers(lottoNumbers);
    }
    
    public LottoNumber readBonusNumber() {
        String input = read(BONUS_LOTTO_NUMBER_QUESTION);
        
        return LottoNumber.fromStringLottoNumber(input);
    }
    
    private String read(String question) {
        System.out.println(question);
        
        return scanner.nextLine();
    }
}
