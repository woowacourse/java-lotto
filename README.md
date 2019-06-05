# java-lotto
## Step 1. Automatically Generate Lotto Games
### Function Requirements
1. get purchase amount from User and generate Lotto Games

### TODO
1. get purchase amount from User [View]
    - purchase amount should be 1000 won per a Lotto
2. generate Lotto Games [Domain]
    - {Exception} each numbers must be not duplicated
3. get last week winning number [View]
4. show winning statics [View]

## Step 2. Bonus Ball
### Function Requirements
1. get bonus number [domain]
    - {Exception} bonus number must not contained WinningLotto
2. show winning statics with bonus number

## Step 3. User Picked Lotto Numbers
### Function Requirements
1. get how many **not automatically generated** lottos
    - {Exception} must over 0
    - {Exception} must under ``GameCounts`` instance
2. get user picked lotto numbers
    - {Exception} input format
    - {Exception} duplicated number

## Step 4. Web UI Application
### Function Requirements
1. 콘솔 UI 로 구현되어 있는 로또를 웹 UI로 동작하도록 구현
