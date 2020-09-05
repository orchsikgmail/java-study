package codingtest.jinhak;
import java.util.Stack;

class ProfitMargin {
	
	public static void main(String[] args) {
		ProfitMargin profitMargin = new ProfitMargin();
		
		int[] price = {4,1,4,7,6};
		int[] result = profitMargin.solution(price);
		for(int i : result) {
			System.out.print(i+" ");
		}
	}
	
	
   public int[] solution(int[] price) {

      Stack<Integer> beginIdxs= new Stack<>();
      int i=0;
      int [] terms = new int[price.length];

      beginIdxs.push(i);
      for(i=1; i<price.length; i++) {
         while(!beginIdxs.empty() && price[i]>price[beginIdxs.peek()]) {
            int beginIdx = beginIdxs.pop();
            terms[beginIdx] = i - beginIdx;
         }
         beginIdxs.push(i);
      }
      while(!beginIdxs.empty()) {
         int beginIdx = beginIdxs.pop();
         terms[beginIdx] = -1;
      }

      return terms;
   }
}
/* 문제 설명
 
1일부터 N일까지, 날짜별 주가가 순서대로 들어있는 배열이 있습니다. 이때, 주식을 구매한 후 차익을 얻기까지 날짜별로 최소 며칠씩 기다려야 하는지 알고 싶습니다.

예를 들어 N = 5일 때 1일부터 5일까지 각 날짜의 주가가 [4, 1, 4, 7, 6]인 경우,

1일에 주식을 4원에 산 후, 2일 혹은 3일에 주식을 판매하면 차익이 없으며, 4일에 주식을 판매하면 차익 3원을 얻습니다. 따라서 1일에 주식을 산 후, 차익을 얻기까지 최소 3일이 지나야 합니다.
마찬가지로 2일에 주식을 1원에 산 경우 하루가 지나면 차익을 얻으며, 3일에 주식을 4원에 산 경우에도 하루 뒤에 차익을 얻습니다. 그러나 4일, 5일에 각각 7원, 6원에 주식을 산 경우에는 차익을 얻을 수 있는 날이 없습니다.
1일부터 N일까지 날짜별로 주식의 가격이 순서대로 들어있는 배열 price가 매개변수로 주어질 때, 날짜별로 차익을 얻으려면 최소 며칠을 기다려야 하는지 알려주는 배열을 return 하도록 solution 함수를 완성해주세요. 단, N일까지 차익을 얻을 수 없는 경우에는 -1을 담으면 됩니다.

제한사항
price는 1일부터 N일까지 각 날짜의 주식가격이 순서대로 담겨있는 배열이며, 길이 N은 1 이상 500,000 이하의 자연수입니다.
price의 각 원소는 각 날짜의 주식 가격을 나타내며, 주식 가격은 1 이상 100,000,000 이하의 자연수입니다.
날짜별로 차익을 얻을 수 있는 가장 가까운 날까지 며칠이 걸리는지를 배열에 담아 return 하세요.
차익을 얻을 수 없는 날짜에는 -1을 담으면 됩니다.
입출력 예
price	result
[4,1,4,7,6]	[3,1,1,-1,-1]
[13,7,3,7,5,16,12]	[5,4,1,2,1,-1,-1]
입출력 예 설명
입출력 예 #1

1일에 주식을 구매하면 차익을 얻는 가장 가까운 날은 4일이므로 3일이 지나야 합니다.
2일에 주식을 구매하면 차익을 얻는 가장 가까운 날은 3일이므로 1일이 지나야 합니다.
3일에 주식을 구매하면 차익을 얻는 가장 가까운 날은 4일이므로 1일이 지나야 합니다.
4일, 5일에 주식을 구매하면 차익을 얻을 수 없습니다.
따라서 [3,1,1,-1,-1]을 return 하면 됩니다.

입출력 예 #2

1일, 2일에 주식을 구매하면 차익을 얻는 가장 가까운 날은 6일이므로 각각 5일, 4일이 지나야 합니다.
3일에 주식을 구매하면 차익을 얻는 가장 가까운 날은 4일이므로 1일이 지나야 합니다.
4일, 5일에 주식을 구매하면 차익을 얻는 가장 가까운 날은 6일이므로 각각 2일, 1일이 지나야 합니다.
6일, 7일에 주식을 구매하면 차익을 얻을 수 없습니다.
따라서 [5,4,1,2,1,-1,-1]을 return 하면 됩니다.
*/

