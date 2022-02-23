package view;

import domain.LottoNumber;
import domain.LottoNumbers;
import java.util.List;

public class OutputView {
    public static void printLottoTickets(List<LottoNumbers> lottoTickets) {
        System.out.println("2개를 구매했습니다.");
        for(LottoNumbers lottoNumbers : lottoTickets){
            printLottoNumbers(lottoNumbers);
        }
    }

    private static void printLottoNumbers (LottoNumbers lottoNumbers){
        StringBuilder result = new StringBuilder("[");
        for (LottoNumber lottoNumber : lottoNumbers.get()) {
            result.append(lottoNumber.get()).append(", ");
        }
        result.delete(result.length() - 2, result.length()).append("]");
        System.out.println(result);
    }
}