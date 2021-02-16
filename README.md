# java-lotto
로또 미션 진행을 위한 저장소

## 기능 요구 사항
- Lotto
    - [X] 로또 번호를 가지고 있는다.
    - [X] 로또 번호는 6개다.
    - [X] 로또 번호가 중복되면 안된다.
    
- LottoNumber
    - [X] 로또 번호는 1과 45사이의 숫자다.
    
- LottoGenerator
    - [X] 로또를 생성 (랜덤 + 수동)
    
- LottoSeller
    - [X] 가격에 맞는 수량의 로또를 제공
    - [ ] 입력한 가격이 1000원이 넘어야한다.

- Lottos, 산 로또 (나중에 또 생각해보기)
    - [ ] 손님이 산 로또.
    - [ ] 테스트일 때는 임의값 가능
    
- WinningLotto
    - 당첨 번호 입력
    - 보너스 볼
    
- LottoResult
    - 손님이 산 로또에서 당첨 통계를 갖고 있는 객체
    
- WinningRateCalculator
    - 손님이 산 로또의 수익률을 계산한다.
    
- LottoRank
    - **`3개 일치 (5000원)- 1개
      4개 일치 (50000원)- 0개
      5개 일치 (1500000원)- 0개
      5개 일치, 보너스 볼 일치(30000000원) - 0개
      6개 일치 (2000000000원)`**