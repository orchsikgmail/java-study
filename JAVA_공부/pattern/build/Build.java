package interviews.java.pattern.build;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;

import interviews.java.pattern.build.Build.Pet.Animal;

/* ���� ������ �� �����?
	�ʵ尡 ���� ��ü�� ������ ���, �����ڸ� �̿��ϸ� �ٷ�� ��ư� �ް��� �� �ִ�.
	�پ��� ������ �����ڸ� '����'��� ������ ��ü(static class Builder)�� ���� ����.
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
		private final Date dateOfBirth; // ���û���
		private final String emailAddress; // ���û���

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
			private String petName = "�̸�����";  // ������ �⺻���� ���.
			private String ownerName;
			private String address;
			private String telephone;
			private Date dateOfBirth; // ���û���
			private String emailAddress; // ���û���
			
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
				
				// ���� �Ǵ� �����ڸ� ����� �� �ִ�.
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
				// .withPetName("happy") �⺻�� ���
				.withOwnerName("�ŵ���")
				.withAddress("805 10-4 ������")
				.withTelephone("010-0000-0000")
				.withEmailAddress("abc@naver.com")
				.build();
		
		System.out.println(pet.getPetName());
		// ����ó������ �׽�Ʈ�� ����ϴ� ���� Ȯ�� ����
	}

	@Test
	public void illegalBuild() {
		final Pet.Builder builder = new Pet.Builder();
		final Pet pet = builder
				.withAnimal(Animal.Dog)
				.withPetName("pica")
				.withOwnerName("�ŵ���")
				.build();
		// �ǵ��Ѵ�� ���ܹ߻�
	}
	
	
}
