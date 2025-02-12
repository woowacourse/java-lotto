package view;

import model.Lotto;
import model.LottoRepository;

public class OutputView {
    private static final String BUY_QUANTITY_PROMPT = "%d개를 구매했습니다.";
    public static void printRandomLotto(LottoRepository lottoRepository){
        for (Lotto lotto : lottoRepository.getLottos()) {
            System.out.println(lotto.printLotto());
        }
    }

    public static void printBuyQuantity(int quantity){
        System.out.println(String.format(BUY_QUANTITY_PROMPT, quantity));
    }


}
