import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LottoController {

    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("구입금액을 입력해 주세요.");
        Price price = new Price(bufferedReader.readLine());
        LottoMachine lottoMachine = new LottoMachine(price);
        int ticket = lottoMachine.getTicket();
        System.out.println(ticket +"개를 구매했습니다.");
    }
}
