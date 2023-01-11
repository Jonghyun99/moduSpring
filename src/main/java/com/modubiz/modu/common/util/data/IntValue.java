package com.modubiz.modu.common.util.data;

import java.io.Serializable;

/**
 * int value wrapper
 */
public class IntValue implements Serializable{
	/** serialVersionUID */
	private static final long	serialVersionUID	= 1L;

	/** int value */
	private int					value				= 0;

	/**
	 * constructor
	 */
	public IntValue(){
		this.value = 0;
	}

	public IntValue(int value){
		this.value = value;
	}

	/**
	 * sum
	 * @param sumValue
	 * @return int
	 */
	public int sum(int sumValue){
		value = value + sumValue;
		return value;
	}

	/**
	 * getInt
	 * @return int
	 */
	public int getInt(){
		return value;
	}
}