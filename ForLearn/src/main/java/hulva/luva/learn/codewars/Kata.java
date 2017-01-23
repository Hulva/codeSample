/**
 * 
 */
package hulva.luva.learn.codewars;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2016年12月20日
 *
 */
public class Kata {

	public static void main(String[] args) {
		System.out.println(TripleDouble(451999277L, 41177722899L));
	}

	public static int TripleDouble(long num1, long num2) {
		char[] triple = Long.toString(num1).toCharArray();
		char[] doubleNum = Long.toString(num2).toCharArray();
		int isTriple = sameNumber(triple, 3, -1);
		int isDouble = isTriple != -1 ? sameNumber(doubleNum, 2, isTriple) : -1;
		return isTriple != -1 && isDouble != -1 && isDouble == isTriple ? 1 : 0;
	}

	private static int sameNumber(char[] charArr, int count, int theTrip) {
		int current = 0;
		int repeatNum;
		for (int i = 0; i < charArr.length; i++) {
			repeatNum = charArr[i];
			System.out.println(repeatNum);
			for (int j = i; j < charArr.length; j++) {
				if (charArr[i] == charArr[j]) {
					current++;
				}
			}
			if (current == count) {
				if (theTrip != -1 && theTrip == repeatNum) {
					return repeatNum;
				}
			}
			current = 0;
		}
		return -1;
	}

	public static int solution(int number) {
		int result = 0;
		for (int i = 3; i < number; i++) {
			if (i % 3 == 0) {
				result += i;
			} else if (i % 5 == 0) {
				result += i;
			}
		}
		return result;
	}

}
