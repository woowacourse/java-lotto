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
    - [X] 입력한 가격이 1000원이 넘어야한다.

- LottoGroup
    - [X] 여러 장의 로또의 정보를 담고 있는 일급 컬렉션 구현

- LottoRank
  - [X] 1등~꼴등까지 enum 으로 만들기
  - [X] matchCount, bonusMatch 를 이용해 등수를 가져오는 것 구현
      
- WinningLotto
    - [X] 당첨 번호 입력
    - [X] 보너스 볼
    
- LottoResult
    - [X] 손님이 산 로또에서 당첨 통계를 갖고 있는 객체
    - [X] 손님이 산 로또의 수익률을 계산한다.
    