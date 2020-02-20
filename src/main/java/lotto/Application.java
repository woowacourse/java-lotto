package lotto;

import lotto.domain.Lottos;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Payment;
import lotto.utils.LottoFactory;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class Application {
    public static void main(String args[]){
        Payment payment = new Payment(InputView.getPayment());
        OutputView.printLottoCount(payment);

        Lottos lottos = new Lottos(LottoFactory.createLottoList(payment));

        List<Lotto> lottoList = lottos.getLottos();
        for(int i=0;i<lottoList.size();i++){
            List<LottoNumber> nowLottoNumbers = lottoList.get(i).getLottoNumbers();
            for(int j=0;j<nowLottoNumbers.size();j++){
                System.out.print(lottoList.get(i).getLottoNumbers().get(j).getNumber() +" ");
            }
            System.out.println();
        }
    }
}
