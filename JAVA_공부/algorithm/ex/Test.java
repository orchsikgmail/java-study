package interviews.java.algorithm.ex;

public class Test {

	public String changeStr(String input) {
		String str = input;

		String[] arrStr = str.split(" ");
		String changStr ="";
		for(String s : arrStr) {
			char[] arrChar = s.toCharArray();

			for(int index=0; index<arrChar.length; index++) {
				switch(index%2) {
				case 1: if(arrChar[index]>='a' && arrChar[index]<='z') {
					arrChar[index] = (char)(arrChar[index]-32); 
					changStr += arrChar[index];
					break;
				} else {
					changStr += arrChar[index];
					break;
				}
				case 0: if(arrChar[index]>='A' && arrChar[index]<='Z') {
					arrChar[index] = (char)(arrChar[index]+32); 
					changStr += arrChar[index];
					break;
				} else {
					changStr += arrChar[index];
					break;
				}
				}
			}
			changStr += " ";
		}

		return changStr;
	}

	public static void main(String[] args) {
		Test t = new Test();
		String result = t.changeStr("hello world goodbye");
		System.out.println(result);
	}
}