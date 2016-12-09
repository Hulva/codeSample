/**
 * 
 */
package newegg.mis.ec2.luva.learn.corejava.lambdas;

import hulva.luva.learn.corejava.lambdas.Human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author lh10
 *
 */
public class SortHumans {

	List<Human> humans = new ArrayList<Human>();

	@Before
	public void init() {
		humans.add(new Human("Luva.H", 23));
		humans.add(new Human("Hulva", 24));
		humans.add(new Human("Luva.H", 27));
	}

	@Test
	public void givenPreLambda_whenSortingEntitiesByName_thenCorrectlySorted() {

		Collections.sort(humans, new Comparator<Human>() {

			@Override
			public int compare(Human o1, Human o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});

		Assert.assertThat(humans.get(0),
				org.hamcrest.core.IsEqual.equalTo(new Human("Hulva", 24)));
	}

	@Test
	public void whenSortingEntitiesByName_thenCorrectlySorted() {

		// humans.sort((Human h1, Human h2) -> h1.getName().compareTo(h2.getName()));
		// 无类型定义
		// humans.sort((h1, h2) -> h1.getName().compareTo(h2.getName())); //
		// humans.sort(Human::compareByNameThenAge); 使用lambda表达式引用静态方法
		Collections.sort(humans, Comparator.comparing(Human::getName));
		Assert.assertThat(humans.get(0),
				org.hamcrest.core.IsEqual.equalTo(new Human("Hulva", 24)));

	}
	
	// 反转集合
	@Test
	public void whenSortingEntitiesByNameReversed_thenCorrectlySorted() {
		Comparator<Human> comparator = (h1, h2) -> h1.getName().compareTo(h2.getName());
		
		humans.sort(comparator.reversed());
		Assert.assertThat(humans.get(0),
				org.hamcrest.core.IsEqual.equalTo(new Human("Luva.H", 23)));
	}
	
	// 复杂情形
	@Test
	public void whenSortingEntitiesByName_thenCorrectlySorted_Multi() {
		System.out.println(humans.toString());
		/*humans.sort((lhs, rhs) -> {
			if (lhs.getName().equals(rhs.getName())) {
				return lhs.getAge() - rhs.getAge();
			}else {
				return lhs.getName().compareTo(rhs.getName());
			}
		});*/
		humans.sort(
				Comparator.comparing(Human::getName).thenComparing(Human::getAge)
				);
		System.out.println(humans);
		Assert.assertThat(humans.get(1),
				org.hamcrest.core.IsEqual.equalTo(new Human("Luva.H", 23)));
	}
}
