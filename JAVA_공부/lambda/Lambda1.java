package me.blacksheepbell;

/* 람다 맛보기
 * 람다 : 식별자 없이 실행가능한 함수
 * 익명함수로 생성, 코드 한줄에 함수를 써서 호출하는 방식.
 */

/* 람다식의 장점
 * 1. 코드간결
 * 2. 간결 -> 개발자의도 명확 -> 가독성 증대
 * 3. 시간단축
 * 4. 병렬프로그래밍에 용이
 */

/* 람다식의 단점
 * 1. 익명함수로 사용하기에 재사용 불가
 * 2. 디버깅 어려움
 * 3. 재사용이 많은 함수에 적용할 시 오히려 코드 더러워짐
 * 4. 재귀로 만들기에 부적합
 */
public class Lambda1 {

	// 예제 준비물 - 인터페이스
	@FunctionalInterface
	interface Say{
		int something(int a, int b);
	}

	// 예제 준비물 - 클래스
	class Person {
		public void hi(Say hoho) {
			int number = hoho.something(3, 4);
			System.out.println("Number is " + number);
		}
	}
	Person rin = new Person();

	// 람다식을 사용하지 않은 함수 호출
	public void noLambda() {
		rin.hi(new Say() {
			@Override
			public int something(int a, int b) {
				System.out.printf("parameter number is %d, %d %n", a, b);
				return 7;
			}
		});
	}

	// 람다식을 사용한 함수 호출
	public void yesLambda() {
		rin.hi((a,b) -> {
			System.out.printf("parameter number is %d, %d %n", a, b);
			return 7;
		});
	}

	// 실행
	public static void main(String[] args) {
		Lambda1 lambda = new Lambda1();
		lambda.noLambda();
		lambda.yesLambda();
	}

	
}
