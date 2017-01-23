/**
 * 
 */
package hulva.luva.learn.arithmetic.weight;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2017年1月14日
 *
 */
public class WeightRandom {

  static List<WeightCategory> categorys = new ArrayList<WeightCategory>();
  private static Random random = new Random();

  public static void initData() {
    WeightCategory wc1 = new WeightCategory("A", 25);
    WeightCategory wc2 = new WeightCategory("B", 35);
    WeightCategory wc3 = new WeightCategory("C", 40);
    categorys.add(wc1);
    categorys.add(wc2);
    categorys.add(wc3);
  }

  public static void main(String[] args) {
    initData();
    for (int i = 0; i < 10; i++) {

      random();
    }
  }

  private static void random() {
    Integer weightSum = 0;
    for (WeightCategory wc : categorys) {
      weightSum += wc.getWeight();
    }
    if (weightSum <= 0) {
      System.err.println("Error: weightSum=" + weightSum.toString());
      return;
    }
    Integer n = random.nextInt(weightSum); // n in [0, weightSum)
    Integer m = 0;
    for (WeightCategory wc : categorys) {
      if (m <= n && n < m + wc.getWeight()) {
        System.out.println("This Random Category is " + wc.getCategory());
        break;
      }
      m += wc.getWeight();
    }
  }
}
