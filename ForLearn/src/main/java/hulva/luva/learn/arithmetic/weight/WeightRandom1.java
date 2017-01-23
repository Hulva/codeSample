/**
 * 
 */
package hulva.luva.learn.arithmetic.weight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2017年1月14日
 *
 */
public class WeightRandom1 {

  static List<WeightCategory> categorys = new ArrayList<WeightCategory>();
  private static int i = -1;
  private static int cw = 0;

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
      System.out.println(random());
    }
  }

  private static WeightCategory random() {
    int[] iArr = new int[categorys.size()];
    for (int i = 0; i < categorys.size(); i++) {
      iArr[i] = categorys.get(i).getWeight();
    }
    while (true) {
      i = (i + 1) % categorys.size();
      if (i == 0) {
        cw = cw - ngcd(iArr, categorys.size());
        if (cw <= 0) {
          cw = max(categorys);
          if (cw == 0)
            return null;
        }
      }
      if (categorys.get(i).getWeight() >= cw)
        return categorys.get(i);
    }
  }

  // List 中 Weight 最大的值
  private static int max(List<WeightCategory> categorys2) {
    Collections.sort(categorys2, new Comparator<WeightCategory>() {

      @Override
      public int compare(WeightCategory o1, WeightCategory o2) {
        if (o1.getWeight() == o2.getWeight()) {
          return 0;
        }
        if (o1.getWeight() > o2.getWeight()) {
          return 1;
        } else {
          return -1;
        }
      }

    });
    return categorys2.get(0).getWeight();
  }

  private static int gcd(int a, int b) {
    if (a == 0)
      return b;
    if (b == 0)
      return a;
    if (a % 2 == 0 && b % 2 == 0)
      return 2 * gcd(a >> 1, b >> 1);
    else if (a % 2 == 0)
      return gcd(a >> 1, b);
    else if (b % 2 == 0)
      return gcd(a, b >> 1);
    else
      return gcd(Math.abs(a - b), Math.min(a, b));
  }

  private static int ngcd(int[] iArr, int index) {
    if (index == 1)
      return iArr[0];

    return gcd(iArr[index - 1], ngcd(iArr, index - 1));
  }
}
