## 기능목록

#### 
전체적으로는 입력받은 금액으로 살 수 있는 만큼 로또를 구매 (자동)
그리고 구매한 로또들을 모아서 통계를 내는 역할

-----------------------------------
#### 세부사항
1. 로또
    - [x] Lotto 생성
        - [x] 자동 (랜덤으로)
        - [x] 수동 (정해진 숫자로)
    - [x] WinningLotto
        - [x] 당첨결과 알아내기
    - [x] 파싱하면서 확인 할 부분
        - [x] 숫자가 아닌 입력
        - [x] "," 외의 잘못된 구분자로 나눠진 입력
        - [x] [1, 45] 외의 숫자 입력
        - [x] 6개(로또에서 사용되는 숫자 개수)가 아닌 숫자들
        - [x] 중복 된 숫자 존재
2. 당첨 통계  
    - [x] 결과를 모아서 통계를 내는 기능
        - [x] Rank 당 당첨 횟수
        - [x] 수익률 계산
