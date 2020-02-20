package lotto;

import lotto.domain.Lotties;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.PaidPrice;
import lotto.utils.LottoGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class Application {
    public static void main(String args[]){
        PaidPrice paidPrice = new PaidPrice(InputView.getPayment());
        OutputView.printLottoCount(paidPrice);

        Lotties lotties = new Lotties(LottoGenerator.createLotties(paidPrice));

        List<Lotto> lottoList = lotties.getLotties();
        for(int i=0;i<lottoList.size();i++){
            List<LottoNumber> nowLottoNumbers = lottoList.get(i).getLottoNumbers();
            for(int j=0;j<nowLottoNumbers.size();j++){
                System.out.print(lottoList.get(i).getLottoNumbers().get(j).getNumber() +" ");
            }
        }
    }
}
