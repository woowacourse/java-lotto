# java-lotto

로또 미션 저장소

간단한 로또 발매 프로그램을 구현합니다.

## 기능 목록

- [x] 구입금액을 입력 받는다.
    - [x] `구입금액을 입력해 주세요.` 문구를 출력한다.
    - [x] `1,000원` 단위여야 한다.
    - [X] 음수일 수 없다.
- [x] 구매한 로또를 출력한다.
    - [x] `n개를 구매했습니다.` 문구를 출력한다.
    - [x] 로또 번호는 오름차순으로 정렬한다.
    - [x] 로또 번호는 대괄호로 감싸고 쉼표로 구분한다.
- [x] 지난 주 당첨 번호를 입력 받는다.
    - [x] `지난 주 당첨 번호를 입력해 주세요.` 문구를 출력한다.
    - [x] 1~45 사이의 수여야 한다.
    - [x] 6개의 중복되지 않는 수여야 한다.
- [x] 보너스 볼을 입력 받는다.
    - [x] `보너스 볼을 입력해 주세요.` 문구를 출력한다.
    - [x] 1~45 사이의 수여야 한다.
    - [x] 지난 주 당첨 번호와 중복되지 않는 수여야 한다.
- [x] 당첨 통계를 출력한다.
    - [x] 당첨 결과를 출력한다.
      - 꽝 : 당첨 숫자들 중 0 ~ 2개 일치 (0원)
      - 5등 : 당첨 숫자들 중 3개 일치 (50,000원) 
      - 4등 : 당첨 숫자들 중 4개 일치 (1,500,000원) 
      - 3등 : 당첨 숫자들 중 5개 일치, 보너스 볼 불일치 (1,500,000원) 
      - 2등 : 당첨 숫자들 중 5개 일치, 보너스 볼 일치 (30,000,000원) 
      - 1등 : 당첨 숫자들 중 6개 일치 (2,000,000,000원)
    - [x] 총 수익률을 출력한다.