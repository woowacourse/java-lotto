# java-lotto
로또 미션 진행을 위한 저장소

## 로또 게임 구현 및 콘솔 입출력
- 로또 구입 금액 입력
  - 로또 1개 가격은 1,000원이다.
  - 입력 금액이 1,000원으로 떨어지지 않는 경우 살 수 있는 최대 개수로 구입한다.
  - [예외] 숫자가 아닌 경우
  - [예외] 자연수가 아닌 경우
  - [예외] 1000보다 작은 경우
- 수동으로 구매할 로또 개수 입력
- 수동으로 구매할 로또 번호 입력
  - 각 로또 번호는 줄바꿈으로 구분한다.
  - 로또 생성 규칙: 1 ~ 45 범위 숫자, 중복되지 않음.
  - [예외] 번호가 6개가 아닌 경우
  - [예외] 각 번호 중 숫자가 아닌 입력이 있는 경우
  - [예외] 숫자 범위가 1 ~ 45 를 넘어서는 경우
- 수동으로 구매한 로또를 제외한 후 로또 생성
  - 로또 생성 규칙은 위와 동일하다.
  - 랜덤으로 생성
- 지난 주 당첨 번호 입력
  - 수동 로또 입력 로또 생성 규칙 및 예외와 동일하다.
- 보너스볼 입력
  - 보너스볼은 로또 번호와 동일한 규칙을 가진다.
- 당첨 결과 출력
  - 당첨 로또와 일치하는 현황 출력
  - 수익률 계산하여 출력
  
## 로또 웹 UI 적용
- 콘솔 UI로 구현되어 있는 로또를 웹 UI로 동작하도록 구현한다.
- 웹 화면은 콘솔 UI와 같은 기능으로 구현하며, 각자의 힘으로 구현해 본다. 자신만의 독특한 UI도 환영한다.

## 로또 DB 적용
- 각 회차별로 사용자가 구매한 로또, 당첨 번호, 당첨 결과, 당첨 금액, 수익률을 조회할 수 있어야 한다.
- 현실에서는 매주 1회차가 진행되는데 이 로또는 로또 게임을 진행할 때마다 1회차가 증가하는 것으로 가정한다.

### 로또 DB 테이블
CREATE TABLE ROUND (
    lotto_round INT UNSIGNED NOT NULL,
    PRIMARY KEY (lotto_round)
);

CREATE TABLE LOTTO (
    lotto_round INT UNSIGNED NOT NULL,
    lotto VARCHAR(40) NOT NULL,
    FOREIGN KEY (lotto_round)
    REFERENCES ROUND(lotto_round) ON DELETE CASCADE
);

CREATE TABLE WINNINGLOTTO (
    lotto_round INT UNSIGNED NOT NULL,
    lotto VARCHAR(40) NOT NULL,
    bonus_ball TINYINT UNSIGNED NOT NULL,
    PRIMARY KEY (lotto_round),
    FOREIGN KEY (lotto_round)
    REFERENCES ROUND(lotto_round) ON DELETE CASCADE
);

CREATE TABLE LOTTORESULT (
    lotto_round INT UNSIGNED NOT NULL,
    first INT UNSIGNED,
    second INT UNSIGNED,
    third INT UNSIGNED,
    fourth INT UNSIGNED,
    fifth INT UNSIGNED,
    winning_amount VARCHAR(12) NOT NULL,
    earning_rate VARCHAR(12) NOT NULL,
    PRIMARY KEY (lotto_round),
    FOREIGN KEY (lotto_round)
    REFERENCES ROUND(lotto_round) ON DELETE CASCADE
);
