package verus.common;

import static org.assertj.core.api.Assertions.assertThat;

public class Mock {

    private int time = 0;

    protected void call() {
        time++;
    }

    protected int calledTime() {
        return time;
    }

    public void verifyCalledOnce() {
        assertThat(calledTime() == 1).isTrue();
    }

    public void verifyCalledTimes(int time) {
        assertThat(calledTime()).isEqualTo(time);
    }

    public void verifyIsNotCalled() {
        assertThat(calledTime()).isEqualTo(0);
    }
}
