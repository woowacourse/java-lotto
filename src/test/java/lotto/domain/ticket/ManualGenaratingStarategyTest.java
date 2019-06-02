package lotto.domain.ticket;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManualGenaratingStarategyTest {
    @Test
    public void 수동_번호_생성(){

        List<List<Integer>> numberList = Arrays.asList(Arrays.asList(1,2,3,4,5,6));
        int num =1;
        ManualGenaratingStarategy manualGenaratingStarategy = new ManualGenaratingStarategy(num,numberList);
        manualGenaratingStarategy.generateTickets();
    }

}