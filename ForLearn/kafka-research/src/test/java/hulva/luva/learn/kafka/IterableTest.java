package hulva.luva.learn.kafka;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Hulva Luva.H from ECBD
 * @date 2017年4月7日
 * @description
 *
 */
public class IterableTest {

  /**
   * @param args
   */
  public static void main(String[] args) {
    List<String> stringList = new ArrayList<String>();
    stringList.add("hello=world");
    stringList.add("hello");
    stringList.add("ni=hao");
    System.out.println(stringList);
    Iterator<String> it = stringList.iterator();
    it.forEachRemaining(i -> {
      String item = it.next();
      if (item.split("=").length == 1)
        it.remove();
    });
    System.out.println(stringList);
  }

}
