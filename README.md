# java-lotto

로또 미션 저장소

# 입력

- [x]  구입 금액을 입력한다
    - [ ]  음수면 예외처리
    - [ ]  숫자가 아니면 예외처리
    - [ ]  1000원으로 안나눠지면 예외처리
- [ ]  지난 주 당첨번호를 입력한다
    - [ ]  콤마(,)를 구분자로 사용한다
    - [x]  숫자가 6개가 아니면 예외처리
    - [ ]  숫자가 아닌 값이 들어오면 예외처리
    - [x]  1~45 범위의 숫자가 아니면 예외처리
    - [x]  중복된 숫자면 예외처리
- [ ]  보너스 번호를 입력한다.
    - [ ]  앞선 당첨번호랑 중복되면 예외처리
    - [ ]  숫자가 아닌 값이 들어오면 예외처리
    - [ ]  1~45 범위의 숫자가 아니면 예외처리

---

# 도메인

### 로또 발행

- [x]  구입 금액을 로또 가격으로 나눈만큼 로또를 생성한다
- [x]  로또는 6개의 번호를 가진다
    - [x]  6개의 번호를 중복없이 1~45 랜덤으로 생성한다
        - [x]  생성된 번호를 오름차순 정렬한다

### 당첨 판단

- [ ]  당첨 통계를 생성한다
- [ ]  당첨 개수에 따른 상금은 다음과 같다

   ```toml
   3개 일치 (5000원)
   4개 일치 (50000원)
   5개 일치 (1500000원)
   5개 일치, 보너스 볼 일치(30000000원) 
   6개 일치 (2000000000원)
   ```

- [ ]  총 상금을 계산한다
- [ ]  수익률을 계산한다

---

# 출력

- [ ]  발행된 로또의 개수를 산출하여 출력한다
- [ ]  발행된 로또의 번호를 출력한다
- [ ]  당첨 통계를 출력한다