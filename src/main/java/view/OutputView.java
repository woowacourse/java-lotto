package view;

import dto.LottoDto;
import java.util.List;

public class OutputView {
    private static final String PURCHASE_COUNT_MESSAGE = "개를 구매하였습니다.";

    public void printPurchaseCount(int count) {
        System.out.printf("%d%s\n", count, PURCHASE_COUNT_MESSAGE);
    }

    public void printLottoNumbers(List<LottoDto> lottoDtos) {
        for (LottoDto lottoDto : lottoDtos) {
            List<Integer> numbers = lottoDto.getNumbers();
            System.out.println(numbers.toString());
        }
    }
}
