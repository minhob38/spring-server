# Spring
Spring은 OOP로 만들어진 Java Framework입니다.

## OOP
아래는 OOP(Objective-Oriented Programming)의 기본 원칙인 SOLID입니다.
### • SRP
- 단일책임 원칙 (Single Responsibility Principle)
- 하나의 class는 하나의 responsibility를 가집니다.  
(e.g: config class는 구현체 생성 및 연결, client class는 실행의 책임을 가집니다.)
### • OCP
- 개방/폐쇄 원칙 (Open/Close Principle)
- 확장에는 열려 있지만, 변경에는 닫혀있어야 합니다.  
(e.g: config class에서 다른 구현체를 client class에 넘겨주어, program을 다르게 실행시킬 수 있습니다.)
### • LSP
- 리스코프 치환 원칙 (Liskov Substitution Principle)
### • ISP
- 인터페이스 분리 원칙 (Interface Segregation Principle)
- 특정 client를 위한 interface가 여러개의 공통 interface보다 좋습니다.
### • DIP
- 의존관계역전 원칙 (Dependency Inversion Principle)
- 구현체가 아닌 추상화에 의존해야 합니다.  
(e.g: 인자로 구현체가 아닌 interface를 받음으로써, 구현체가 바뀌더라도 code 수정이 줄어듭니다.)

## Spring Container / Bean
### • Container
container는 spring에서 관리(DI/IOC)되는 bean들을 모아놓은 공간입니다.

### • Bean
bean은 spring container에서 관리하는 instance입니다.

### • DI / IOC
**- DI**  
어떤 class가 다른 class의 intance를 생성자 인자로 받을때 spring container가 instance를 만들어 생성자에 넣어주는 것을,  
의존관계주입(Dependency Injection)이라 합니다.  
**- IOC**  
program의 제어흐름을 실행되는 code가 아닌 바깥의 configuration code에서 관리하는 것을,  
제어의 역전(Inversion of Control)이라 합니다.


[//]: # (### • Service)

[//]: # (service는 해당 domain의 business logic을 가지고 있습니다.)

[//]: # ()
[//]: # (### • Repository)

[//]: # (repository는 해당 domain의 저장소&#40;database&#41;를 다루는 logic을 가지고 있습니다.)


[//]: # (// cmd + shitf + enter : semicolon까지 완성)

[//]: # (// psvm : public static void main 자동 완성)

[//]: # (// cmd + option + v)

[//]: # (// option + enter)
