package interviews.java.pattern.build;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;

import interviews.java.pattern.build.Build.Pet.Animal;

/* 빌드 패턴은 왜 사용해?
	필드가 많은 객체를 생성할 경우, 생성자를 이용하면 다루기 어렵고 햇갈릴 수 있다.
	다양한 조합의 생성자를 '빌더'라는 동반자 객체(static class Builder)를 만들어서 관리.
 */


@SuppressWarnings("unused")
class Build {
	
	
	public static class Pet{
		public static class Animal{
			public final static Animal Cat = null;
			public final static Animal Dog = null;
		}

		private final Animal animal;
		private final String petName;
		private final String ownerName;
		private final String address;
		private final String telephone;
		private final Date dateOfBirth; // 선택사항
		private final String emailAddress; // 선택사항

		public String getPetName() {
			return petName;
		}
		public Pet(Animal animal, String petName, String ownerName, 
				String address, String telephone,
				Date dateOfBirth, String emailAddress) {
			this.animal = animal;
			this.petName = petName;
			this.ownerName = ownerName;
			this.address = address;
			this.telephone = telephone;
			this.dateOfBirth = dateOfBirth;
			this.emailAddress = emailAddress;
		}


		public static class Builder {
			private Animal animal;
			private String petName = "이름없음";  // 빌더에 기본값을 줬다.
			private String ownerName;
			private String address;
			private String telephone;
			private Date dateOfBirth; // 선택사항
			private String emailAddress; // 선택사항
			
			public Builder withAnimal(final Animal animal) {
				this.animal = animal;
				return this;
			}
			public Builder withPetName(final String petName) {
				this.petName = petName;
				return this;
			}
			public Builder withOwnerName(final String ownerName) {
				this.ownerName = ownerName;
				return this;
			}
			public Builder withAddress(final String address) {
				this.address = address;
				return this;
			}
			public Builder withTelephone(final String telephone) {
				this.telephone = telephone;
				return this;
			}
			public Builder withDateOfBirth(final Date dateOfBirth) {
				this.dateOfBirth = dateOfBirth;
				return this;
			}
			public Builder withEmailAddress(final String emailAddress) {
				this.emailAddress = emailAddress;
				return this;
			}
			public Pet build() {
				if(petName==null || ownerName==null ||
						address==null || telephone==null) {
					throw new IllegalStateException("Cannot create Pet");
				}
				
				// 세터 또는 생성자를 사용할 수 있다.
				return new Pet(
						animal, petName, ownerName,
						address, telephone, dateOfBirth, emailAddress
						);
			}
		} // Builder
	} // Pet
	
	
	// Builder Test
	@Test
	public void legalBuild() {
		final Pet.Builder builder = new Pet.Builder();
		final Pet pet = builder
				.withAnimal(Animal.Cat)
				// .withPetName("happy") 기본값 사용
				.withOwnerName("신동엽")
				.withAddress("805 10-4 도원동")
				.withTelephone("010-0000-0000")
				.withEmailAddress("abc@naver.com")
				.build();
		
		System.out.println(pet.getPetName());
		// 예외처리없이 테스트가 통과하는 것을 확인 가능
	}

	@Test
	public void illegalBuild() {
		final Pet.Builder builder = new Pet.Builder();
		final Pet pet = builder
				.withAnimal(Animal.Dog)
				.withPetName("pica")
				.withOwnerName("신동엽")
				.build();
		// 의도한대로 예외발생
	}
	
	
}
