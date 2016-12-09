/**
 * 
 */
package hulva.luva.learn.corejava.thelistthing;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.PredicateUtils;
import org.junit.Test;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * @ClassName
 * @Description remove all null elements from a List
 * @author Hulva Luva.H
 * @date 2016年11月29日
 *
 */
public class RemoveNull {
	// Remove nulls from a List using plain Java
	@Test
	public void givenListContainsNulls_whenRemovingNullsWithPlainJava() {
		List<Integer> list = Lists.newArrayList(null, 1, 3, null);
		System.out.println(list);
		while (list.remove(null))
			;
		System.out.println(list);
	}

	@Test
	public void givenListContainsNulls_whenRemovingNullsWithPlainJavaAlternative_thenCorrect() {
		List<Integer> list = Lists.newArrayList(null, 1, null);
		System.out.println(list);
		list.removeAll(Collections.singleton(null));
		System.out.println(list);
	}

	// Remove nulls from a List using Google Guava
	@Test
	public void givenListContainsNulls_whenRemovingNullsWithGuavaV1() {
		List<Integer> list = Lists.newArrayList(null, 1, null);
		System.out.println(list);
		Iterables.removeIf(list, Predicates.isNull());
		System.out.println(list);
	}

	@Test
	public void givenListContainsNulls_whenRemovingNullsWithGuavaV2_thenCorrect() {
		List<Integer> list = Lists.newArrayList(null, 1, null, 2, 3);
		System.out.println(list);
		List<Integer> listWithoutNulls = Lists.newArrayList(Iterables.filter(
				list, Predicates.notNull()));
		System.out.println(list);
		System.out.println(listWithoutNulls);
	}

	// Remove nulls from a List using Apache Commons Collections
	@Test
	public void givenListContainsNulls_whenRemovingNullsWithCommonsCollections() {
		List<Integer> list = Lists.newArrayList(1, null, 3, null, 5, null);
		System.out.println(list);
		CollectionUtils.filter(list, PredicateUtils.notNullPredicate());
		System.out.println(list);
	}

	// Remove nulls from a List using Lambdas (Java 8)
	@Test
	public void givenListContainsNulls_whenFilteringParallel() {
		List<Integer> list = Lists
				.newArrayList(null, 1, null, 3, null, 5, null);
		List<Integer> listWithoutNulls = list.parallelStream()
				.filter(i -> i != null).collect(Collectors.toList());
		System.out.println(list);
		System.out.println(listWithoutNulls);
	}

	@Test
	public void givenListContainsNulls_whenFilteringSerial_thenCorrect() {
		List<Integer> list = Lists.newArrayList(null, 1, 2, null, 3, null);
		List<Integer> listWithoutNulls = list.stream().filter(i -> i != null)
				.collect(Collectors.toList());
		System.out.println(list);
		System.out.println(listWithoutNulls);
	}

	@Test
	public void givenListContainsNulls_whenRemovingNullsWithRemoveIf_thenCorrect() {
		List<Integer> listWithoutNulls = Lists.newArrayList(null, 1, 2, null,
				3, null);
		System.out.println(listWithoutNulls);
		listWithoutNulls.removeIf(p -> p == null);
		System.out.println(listWithoutNulls);
	}
}
