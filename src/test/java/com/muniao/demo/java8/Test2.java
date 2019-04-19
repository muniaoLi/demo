package com.muniao.demo.java8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class Test2 {
	
	public static TestVO bin(TestVO v1, TestVO v2) {
		if(v1.getAge()>v2.getAge()) {
			return v1;
		} else {
			return v2;
		}
		
	}
	
	public Integer haveAge1() {
		return 9;
	}
	
	public static void main(String[] args) {
		List<TestVO> list = new ArrayList<>();
		
		list.add(new TestVO("wang", 22,20l));
		list.add(new TestVO("wang", 23,20l));
		list.add(new TestVO("zhao", 22,20l));
		list.add(new TestVO("zhao", 22,20l));
		list.add(new TestVO("zhao", 24,21l));
		list.add(new TestVO("li", 20,30l));
		list.add(new TestVO("li", 28,50l));
		
		Comparator<TestVO> ageCom = (v1,v2)->v1.getAge().compareTo(v2.getAge());
		
		Map<String, TestVO> mp =
		list.stream()
			.filter(v->v.name.length()>2)
			.collect(Collectors.groupingBy(TestVO::getName,Collectors.collectingAndThen(Collectors.minBy(ageCom), Optional::get)));
		
		System.out.println(mp);
		
		System.out.println(mp.values());
		
		//计算年龄之和 方法一
		int sum1 = list.stream().mapToInt(v->v.getAge()).reduce(Integer::sum).getAsInt();
		System.out.println(sum1);
		//计算年龄之和 方法二
		int sum2 = list.stream().mapToInt(v->v.getAge()).reduce((v1,v2)->v1+v2).getAsInt();
		System.out.println(sum2);
		//计算年龄之和 方法三
		int sum3 = list.stream().collect(Collectors.summingInt(v->v.getAge()));
		System.out.println(sum3);
		
		TreeSet<String> nameSet = list.stream().map(TestVO::getName).collect(Collectors.toCollection(TreeSet::new));
		System.out.println(nameSet);
		
		Set<String> nameSet2 = list.stream().map(TestVO::getName).collect(Collectors.toSet());
		System.out.println(nameSet2);
		
		
		
		
		Map<Boolean, List<TestVO>> m1 = list.stream().collect(Collectors.partitioningBy(v->v.getAge()>23));
		
		System.out.println(m1);
		
		/*list.stream().forEach(v->{
			if("wang".equals(v.getName())) {
				return;
			}
			v.setOpinion(50l); 
		});
		System.out.println(list);*/
		
		List<TestVO> skipList = list.stream().skip(2).collect(Collectors.toList());
		System.out.println(skipList);
		System.out.println(skipList.size());
		
		Map<String, Map<Long, Set<Integer>>> map9=
		list.stream().collect(Collectors.groupingBy(v->v.getName(),Collectors.groupingBy(v->v.getOpinion(),Collectors.mapping(v->v.getAge(), Collectors.toSet()))));
		System.out.println(map9);
		
		Map<String, Map<Long, Long>> map10 = 
		list.stream().collect(Collectors.groupingBy(v->v.getName(),Collectors.groupingBy(v->v.getOpinion(),Collectors.summingLong(v->v.getAge()))));
		System.out.println(map10);
		
		
		boolean allMatch = list.stream().allMatch(v->v.getAge()>25);
		System.out.println(allMatch);
		
		boolean anyMatch = list.stream().anyMatch(v->v.getAge()>25);
		System.out.println(anyMatch);
		
		boolean noneMatch = list.stream().noneMatch(v->v.getAge()<20);
		System.out.println(noneMatch);
		
		//Long size = list.stream().collect(Collectors.counting());
		Long size = list.stream().map(TestVO::haveAge1).count();
		System.out.println(size);
		
		BinaryOperator<TestVO>  op = (v1,v2)->v1.getAge()>v2.getAge()?v1:v2;
		
		TestVO maxV = 
		list.stream().reduce(Test2::bin).get();
		System.out.println("max--"+maxV);
		
		
		
		//根据姓名进行分组
		Map<String, List<TestVO>> map1 = 
		list.stream().collect(Collectors.groupingBy(v->v.getName()));
		System.out.println(map1);
		
		map1.forEach((key,value)->{
			System.out.println(key);
			System.out.println(value);
		});
		
		//根据姓名进行分组并取得每一组最大值
		Map<String, TestVO> map8 = 
		list.stream().collect(Collectors.groupingBy(v->v.getName(),
				Collectors.collectingAndThen(Collectors.reducing((v1,v2)->v1.getAge()>v2.getAge()?v1:v2), Optional::get)
				));
		
		list.stream().collect(Collectors.groupingBy(v->v.getName(),
				Collectors.collectingAndThen(Collectors.reducing((v1,v2)->v1.getAge()>v2.getAge()?v1:v2), Optional::get)
				));
		
		//
//		list.stream().collect(Collectors.groupingBy(v->v.getName(),
//				Collectors.collectingAndThen(Collections.sort(this, (v1,v2)->v1.getAge()-v2.getAge()), Collectors.toList())
//				));
		
		System.out.println("map8"+map8);
		
		Map<String, Optional<TestVO>> map81 =
		list.stream().collect(Collectors.groupingBy(v->v.getName(),Collectors.maxBy((v1,v2)->v1.getAge()-v2.getAge())));
		System.out.println("map81"+map81);
		
		//list.stream().reduce(TestVO::getName, (v1,v2)->v1>v2?v1:v2)
		
		Map<String, Set<Integer>> map6 = 
		list.stream().collect(Collectors.groupingBy(v->v.getName(),TreeMap::new,Collectors.mapping(v->v.getAge(), Collectors.toSet()) ));
		System.out.println("map6:  "+map6);
		
		Map<String, List<TestVO>> map2 = 
		list.stream().collect(Collectors.groupingBy(v->v.getName()+"-"+v.getAge()));
		System.out.println(map2);
		
		Map<String, Map<Integer, List<TestVO>>> map3 = 
		list.stream().collect(Collectors.groupingBy(v->v.getName(),Collectors.groupingBy(v->v.getAge())));
		System.out.println(map3);
		
		//分组计数
		Map<String, Long> map4 = 
		list.stream().collect(Collectors.groupingBy(v->v.getName(),Collectors.counting()));
		System.out.println(map4);
		
		//分组求和
		Map<String, Long> map5 = 
		list.stream().collect(Collectors.groupingBy(v->v.getName(),Collectors.summingLong(TestVO::getOpinion)));
		System.out.println("map5="+map5);
		
		Integer sum = 
		list.stream().map(v->v.getAge()).reduce((v1,v2)->v1+v2).get();
		System.out.println(sum);
		
		Integer max = 
				list.stream().map(v->v.getAge()).reduce(Integer::max).get();
		System.out.println(max);
		
		list.stream().map(v->v.getAge()).reduce((i, j) -> i > j ? j : i).ifPresent(System.out::println);
		
		final TestVO maxVO=
		list.stream().reduce((v1,v2)->(v1.getAge()-v2.getAge()>0)?v1:v2).get();
		System.out.println(maxVO);
		
	}
	
	public void func1(List<TestVO> list) {
		System.out.println(list);
		func2(list);
		System.out.println(list);
		
	}
	
	private void func2(List<TestVO> list) {
		List<TestVO> l = list.stream().filter(v->v.getAge()>20).collect(Collectors.toList());
		l.forEach(v->v.setOpinion(111l));
		list.add(new TestVO("zhang", 18,20l));
		
		//return list;
	}

}
