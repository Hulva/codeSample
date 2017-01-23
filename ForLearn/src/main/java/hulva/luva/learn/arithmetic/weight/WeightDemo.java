/**
 * 
 */
package hulva.luva.learn.arithmetic.weight;

import java.util.HashSet;
import java.util.Set;


/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2017年1月14日
 *
 */
public class WeightDemo {

  /**
   * @param args
   */
  public static void main(String[] args) {
    Set<Server> servers = new HashSet<Server>();
    servers.add(new Server("10.132.02.21", 10D));
    servers.add(new Server("11.132.02.21", 20D));
    servers.add(new Server("12.132.02.21", 30D));
    servers.add(new Server("13.132.02.21", 40D));
    for (int i = 0; i < 10; i++) {
      System.out.println(random(servers));
    }
  }

  static <E extends Weighter> E random(Iterable<? extends E> weighters) {
    double weightSum = 0;
    for (E weighter : weighters) {
      weightSum += weighter.getWeight();
    }
    double r = Math.random() * weightSum;
    double c = 0;
    for (E weighter : weighters) {
      c += weighter.getWeight();
      if (c >= r) {
        return weighter;
      }
    }
    throw new RuntimeException();
  }

}
