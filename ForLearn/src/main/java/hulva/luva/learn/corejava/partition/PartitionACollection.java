/**
 * 
 */
package hulva.luva.learn.corejava.partition;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.collections4.ListUtils;
import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2016年11月29日
 *
 */
public class PartitionACollection {

	@Test
	public void givenList_whenPartitionIntoSublists_thenCorrect() {
		List<Integer> intList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);
		List<List<Integer>> subSets = Lists.partition(intList, 3);

		List<Integer> lastPartition = subSets.get(2);
		assertThat(subSets.size(), equalTo(3));
		assertThat(lastPartition, equalTo(Lists.newArrayList(7, 8)));
	}

	@Test
	public void givenListPartitioned_whenOriginalListIsModified_thenPartitionsChangeAsWell() {
		// Given
		List<Integer> intList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		List<List<Integer>> subSets = Lists.partition(intList, 3);

		// when
		intList.add(9);

		// Then
		List<Integer> lastPartition = subSets.get(2);
		assertThat(lastPartition, equalTo(Lists.newArrayList(7, 8, 9)));
	}

	@Test
	public void givenList_whenParitioningIntoNSublists_thenCorrect() {
		List<Integer> intList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);
		List<List<Integer>> subSets = ListUtils.partition(intList, 3);

		List<Integer> lastPartition = subSets.get(2);
		List<Integer> expectedLastPartition = Lists
				.<Integer> newArrayList(7, 8);
		assertThat(subSets.size(), equalTo(3));
		assertThat(lastPartition, equalTo(expectedLastPartition));
	}

	// In Java8
	@Test
	public void givenList_whenPartitioningIntoSublistsUsingPartitionBy_thenCorrect() {
		List<Integer> intList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);

		Map<Boolean, List<Integer>> groups = intList.stream().collect(
				Collectors.partitioningBy(s -> s > 6));
		List<List<Integer>> subSets = new ArrayList<List<Integer>>(
				groups.values());

		List<Integer> lastPartition = subSets.get(1);
		assertThat(subSets.size(), equalTo(2));
		assertThat(lastPartition, equalTo(Lists.newArrayList(7, 8)));
	}

	@Test
	public final void givenList_whenPartitioningIntoNSublistsUsingGroupBy_thenCorrect() {
		List<Integer> intList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);

		Map<Integer, List<Integer>> groups = intList.stream().collect(
				Collectors.groupingBy(s -> (s - 1) / 3));
		List<List<Integer>> subSets = new ArrayList<List<Integer>>(
				groups.values());

		List<Integer> lastPartition = subSets.get(2);
		assertThat(subSets.size(), equalTo(3));
		assertThat(lastPartition, equalTo(Lists.newArrayList(7, 8)));
	}
	
	@Test
	public void givenList_whenSplittingBySeparator_thenCorrect() {
	    List<Integer> intList = Lists.newArrayList(1, 2, 3, 0, 4, 5, 6, 0, 7, 8);
	 
	    int[] indexes = 
	      Stream.of(IntStream.of(-1), IntStream.range(0, intList.size())
	      .filter(i -> intList.get(i) == 0), IntStream.of(intList.size()))
	      .flatMapToInt(s -> s).toArray();
	    List<List<Integer>> subSets = 
	      IntStream.range(0, indexes.length - 1)
	               .mapToObj(i -> intList.subList(indexes[i] + 1, indexes[i + 1]))
	               .collect(Collectors.toList());
	 
	    List<Integer> lastPartition = subSets.get(2);
	    List<Integer> expectedLastPartition = Lists.<Integer> newArrayList(7, 8);
	    assertThat(subSets.size(), equalTo(3));
	    assertThat(lastPartition, equalTo(expectedLastPartition));
	}
}
