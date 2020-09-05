package codingtest.jinhak;

import java.util.ArrayList;

class OverlapCheck {
	public static void main(String[] args) {
		OverlapCheck overlapCheck = new OverlapCheck();
		String[] teamIDs = {"world", "prog"};
		String[] additional = {"hello", "world", "code", "hello", "try", "code"};
		
		String[] result = overlapCheck.solution(teamIDs, additional);
		for(String string : result) {
			System.out.print(string+" ");
		}
	}
	
   public String[] solution(String[] teamIDs, String[] additional) {

      // ��ϵ� ���̵� ������ ����Ʈ
      ArrayList<String> beforeList = new ArrayList<>();
      // ������� ������ ����Ʈ
      ArrayList<String> answerList = new ArrayList<>();

      // teamIDs�迭�� ����Ʈ�� ����
      for(String name : teamIDs) {
         beforeList.add(name);
      }

      // additional�� �̸��� ���ؼ� ���ο� �̸��� ����
      for(String newName : additional) {
         if(!beforeList.contains(newName) && !answerList.contains(newName)) {
            answerList.add(newName);
         }
      }

      String[] answer = (String[])answerList.toArray(new String[answerList.size()]);
      return answer;
   }
}

/*
���α׷��� ��ȸ ���� ���� �߰��� �����Ϸ��� �մϴ�. �߰��� �����ϴ� ������ �� ���̵� ����ϱ� ���� �� �����κ��� ��ȸ�� ����� ���̵� ������� ��û�޾ҽ��ϴ�. ��, ������ ��ϵǾ��ִ� ���̵�� ����� �� ������, �� �� �̻��� ���� ���̵� ��û�� ���, ���� ��û�� ���� �켱���� �ֽ��ϴ�. ���� ��� �̹� ����� �Ϸ�� �� ���̵� ����� [world, prog], �߰��� ��� ��û�� ���� �� ���̵��� ������ [hello, world, code, hello, try, code]�� ��, �߰� ��� ���� ���̵� ��ϵǴ� ������ ������ �����ϴ�.

ù ��°�� ��û���� hello�� �� ���̵�� ��ϵ˴ϴ�.
�� ��°�� ��û���� world�� �̹� ��ϵ� ���̵��̹Ƿ� ��ϵ��� �ʽ��ϴ�.
�� ��°�� ��û���� code�� �� ���̵�� ��ϵ˴ϴ�.
�� ��°�� ��û���� hello�� �̹� ��ϵ� ���̵��̹Ƿ� ��ϵ��� �ʽ��ϴ�.
�ټ� ��°�� ��û���� try�� �� ���̵�� ��ϵ˴ϴ�.
���� ��°�� ��û���� code�� �̹� ��ϵ� ���̵��̹Ƿ� ��ϵ��� �ʽ��ϴ�.
���� �߰��� ��ϵǴ� ���̵�� ����� �Ϸ�� ������� [hello, code, try]�� �˴ϴ�.
������ ��ϵǾ��ִ� �� ���̵� ��� teamIDs �� �߰��� ��� ��û�� ���� �� ���̵� ��û���� ������� ����ִ� ��� additional�� �Ű������� �־��� ��, �߰� ��û���� ���̵� �� ����� �Ϸ�Ǵ� �� ���̵� ������� �ִ� �迭�� return �ϵ��� solution �Լ��� �ϼ����ּ���.

���ѻ���
teamIDs�� ������ ��ϵǾ��ִ� �� ���̵� ���ڿ� ���·� ����ִ� �迭�̸�, �迭�� ���̴� 1 �̻� 100,000 �����Դϴ�.
teamIDs�� �� ���Ҵ� �� ���̵� ��Ÿ����, �ߺ��� ���̵� ������� �ʽ��ϴ�.
additional�� �߰��� ��û���� �� ���̵� ù ��° ���Һ��� ��û���� ������� ����ִ� �迭�̸�, ���̴� 1 �̻� 200,000 �����Դϴ�.
additional�� �� ���Ҵ� �߰��� ��û���� �� ���̵� ��Ÿ���ϴ�.
��� �� ID�� ���̴� 1 �̻� 10 �����̸�, ���ĺ� �ҹ��ڷθ� �̷���� �ֽ��ϴ�.
additional�� ù ��° ���Һ��� ������� ���̵� ����� ��, �߰� ����� �Ϸ�Ǵ� ���̵���� ���� ��ϵ� ���̵� �տ� ������ �迭�� ��� return �ϸ� �˴ϴ�.
return �迭�� ���̰� �׻� 1 �̻��� ��츸 �Է����� �־����ϴ�. ��, �߰� ����� ��û�� ��� ���̵� ��� �����ϴ� ���� �Է����� �־����� �ʽ��ϴ�.
����� ��
teamIDs	additional	result
[world,prog]	[hello,world,code,hello,try,code]	[hello,code,try]
[abc,hq,xyz]	[hq,abc,pp,xy,pp,hq]	[pp,xy]
����� �� ����
����� �� #1
������ ���ÿ� �����ϴ�.

����� �� #2
additional�� ù ��° ���Һ��� ������� �����մϴ�.
hq�� abc�� �̹� ��ϵ� ���̵��̹Ƿ� ���� ��ϵ��� �ʽ��ϴ�.
�� ��°, �� ��° ������ pp, xy�� ���� �ƹ� ���� ������� �ʾ����Ƿ� ���� ��ϵ˴ϴ�.
���� pp�� ���ο� �� ���̵�� ��ϵǾ� �����Ƿ� �ټ� ��° ������ pp�� ���� ��ϵ��� �ʽ��ϴ�.
������ ������ hq�� �̹� ��ϵ� ���̵��̱� ������ ���� ��ϵ��� �ʽ��ϴ�.
���� �߰��� ��ϵǴ� ���̵�� ������� [pp,xy]�� �˴ϴ�.
*/