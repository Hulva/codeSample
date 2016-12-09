/**
 * 
 */
package hulva.luva.learn.corejava.thelistthing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * @ClassName
 * @Description clean up the duplicate elements from a List
 * @author Hulva Luva.H
 * @date 2016年11月29日
 *
 */
public class RemoveDuplicates {

	// Remove duplicates from a List using plain Java
	@Test
	public void givenListContainsDuplicates_whenRemovingDuplicatesWithPlainJava() {
		List<Integer> listWithDuplicates = Arrays
				.asList(1, 0, 3, 0, 2, 0, 1, 3);
		System.out.println(listWithDuplicates);
		List<Integer> listWithoutDuplicates = new ArrayList<>(new HashSet<>(
				listWithDuplicates));
		System.out.println(listWithoutDuplicates);
	}

	// Remove duplicates from a List using Guava
	@Test
	public void givenListContainsDuplicates_whenRemovingDuplicatesWithGuava() {
		List<Integer> listWithDuplicates = Lists.newArrayList(2, 4, 3, 1, 2, 3,
				4, 5);
		System.out.println(listWithDuplicates);
		List<Integer> listWithoutDuplicates = Lists.newArrayList(Sets
				.newHashSet(listWithDuplicates));
		System.out.println(listWithoutDuplicates);
	}

	// Remove duplicates form a List using Java 8 Lambdas, use the distinct()
	// method from the Stream API
	@Test
	public void givenListContainsDuplicates_whenRemovingDuplicatesWithJava8() {
		List<Integer> listWithDuplicates = Lists.newArrayList(1, 2, 3, 2, 5, 1,
				4);
		System.out.println(listWithDuplicates);
		List<Integer> listWithoutDuplicates = listWithDuplicates
				.parallelStream().distinct().collect(Collectors.toList());
		System.out.println(listWithoutDuplicates);
	}
}
