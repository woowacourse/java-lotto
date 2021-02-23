# java-lotto

로또 미션 진행을 위한 저장소

1. 로또 구매
    - [x] 사용자 입력 : 구입 금액
        - 최대 구입 가능 금액은 100만원으로 한정
        - 발생 가능한 예외 사항
            - [x] null 입력된 경우
            - [x] 숫자가 아닌 경우
            - [x] 1000원 이하의 금액이 입력된 경우
            - [x] 100만원 초과의 금액이 입력된 경우
            - [x] 1000원 단위의 금액이 입력되지 않은 경우
    - [x] 구입한 로또 개수 출력
    - [x] 사용자가 구입한 로또 티켓 번호 생성
        - 로또 번호는 1 ~ 45
            - [x] 번호 오름차순 정렬
        - 발생 가능한 예외 사항
            - [x] 한 로또 티켓에 중복된 번호가 있는 경우
            - [x] 한 로또 티켓의 사이즈가 6이 아닌 경우
            - [x] 로또 번호 범위를 벗어나는 숫자가 생길 경우
    - [x] 사용자가 구입한 로또 티켓 번호 출력

2. 당첨 번호 및 보너스 입력
    - [x] 사용자 입력 : 지난 주 당첨 번호
    - [x] 사용자 입력 : 보너스 번호
        - 로또 번호는 1 ~ 45
            - [x] 번호 오름차순 정렬
        - 발생 가능한 예외 사항
            - [x] null 입력된 경우
            - [x] 숫자가 아닌 경우
            - [x] 로또 번호가 부족한 경우 (당첨 번호 6 개, 보너스 번호 1 개)
            - [x] 중복된 번호가 있는 경우
            - [x] 로또 번호 범위를 벗어난 숫자인 경우

3. 당첨 통계 및 총 수익률 출력
    - [x] 당첨 통계 title 출력
    - [x] 당첨 금액별 로또 개수 출력
        1. 보너스 번호를 제외한 일치 개수 계산
        2. 5 개 일치할 경우, 보너스 번호 일치 여부 확인
    - [x] 총 수익률 계산
    - [x] 총 수익률 출력

4. 추가 기능 - 로또 수동 생성 기능
    - [ ] 수동으로 구매할 로또 개수 입력
        - 발생 가능한 예외 사항
            - [ ] 구입 금액으로 살 수 있는 숫자보다 많은 수를 입력
            - [ ] 양의 정수가 아닌 숫자 입력 
    - [ ] 수동으로 구매할 티켓 번호 입력
        - 발생 가능한 예외 사항 
            - [ ] 양의 정수가 아닌 경우
            - [ ] 1 - 45 사이의 정수값이 아닌 경우
            - [ ] 로또 번호가 부족한 경우 (당첨 번호 6개, 보너스 번호 1개)
            - [ ] 중복된 번호가 있는 경우
            - [ ] 수동 구매 수량과 일치하지 않도록 입력된 경우 (예: 수동 구매 5개 인데, 5개 보다 작거나 많이 입력할 경우)
    

## 추가 리팩토링

- [x] enum 적용할 객체 정하기 (ex: 로또 숫자, 당첨 통계 부분)
- [x] 숫자를 나눌 delimiter(,) 와 trim() 처리
- [x] 사용자로부터 받은 번호가 정렬되지 않은 경우
- [x] null이 아닌 enter가 입력된 경우 예외처리
- [x] 코딩 컨벤션 으로 static final 순서 수정
- [ ] LottoMachine 생성시 Generator 인자로 전달
- [x] LottoNumber에 캐시 자료구조 변경 고려
- [x] LottoSystem에 Getter 불변 객체 반환으로 수정
- [x] LottoTicket ValidateDuplicatedNumber 메소드 고민 필요
- [ ] InputView에서 숫자를 parseInt로 받는 것 고려 - 예외 처리 방식 변경
- [x] 1-45 리스트 Generator 내부에서 생성
- [ ] WinningNumbers validateSize 중복 검증 수정
- [ ] WinningNumbers/WinningResult 에 get 붙은 메소드 이름 고민
- [ ] Controller 필드에 변수 줄이기 
- [ ] Ranking 에 보너스 매칭 필드 없애도록 구현 수정 