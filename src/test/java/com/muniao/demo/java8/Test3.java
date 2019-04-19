package com.muniao.demo.java8;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test3 {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("1","2","3","4");
		System.out.println(list);

		Set<String> s1 = new HashSet<>(Arrays.asList("1","2","3","4","5"));
		Set<String> s2 = new HashSet<>(Arrays.asList("1","2","6"));
		Set<String> s3 = new HashSet<>(s1);
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(s3);
		
		
		System.out.println(s1.contains("1"));
		
		s3.removeAll(s2);
		System.out.println(s3);
		System.out.println(s1);

	}
}
