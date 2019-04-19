package com.muniao.demo.java8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestVO implements Cloneable {

	String name;
	Integer age;
	Long opinion;
	
	public Integer haveAge1() {
		return this.getAge();
	}

	@Override
	public String toString() {
		return String.format("name:%s age:%d opinion:%d", name, age,opinion);
	}

	public int compareTo(TestVO vo) {
		return this.name.compareTo(vo.getName());
	}
	public TestVO(String name, Integer age) {
		this.name=name;
		this.age=age;
	}

	@Override
	public TestVO clone() {
		TestVO o = null;
		try {
			o = (TestVO) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

}
