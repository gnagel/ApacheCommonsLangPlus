/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.lang3.mutable;


/**
 * A mutable <code>long</code> wrapper.
 * 
 * @see Long
 * @since 2.1
 * @version $Id: MutableLong.java 618693 2008-02-05 16:33:29Z sebb $
 */
@SuppressWarnings({ "rawtypes" })
public class MutableLong extends Number implements Comparable, Mutable {

	/**
	 * Required for serialization support.
	 * 
	 * @see java.io.Serializable
	 */
	private static final long	serialVersionUID	= 62986528375L;

	/** The mutable value. */
	private long				value;


	/**
	 * Constructs a new MutableLong with the default value of zero.
	 */
	public MutableLong() {
		super();
	}


	/**
	 * Constructs a new MutableLong with the specified value.
	 * 
	 * @param value
	 *            a value.
	 */
	public MutableLong(final long value) {
		super();
		this.value = value;
	}


	/**
	 * Constructs a new MutableLong with the specified value.
	 * 
	 * @param value
	 *            a value.
	 * @throws NullPointerException
	 *             if the object is null
	 */
	public MutableLong(final Number value) {
		super();
		this.value = value.longValue();
	}


	// -----------------------------------------------------------------------
	/**
	 * Adds a value.
	 * 
	 * @param operand
	 *            the value to add
	 * @since Commons Lang 2.2
	 */
	public void add(final long operand) {
		value += operand;
	}


	/**
	 * Adds a value.
	 * 
	 * @param operand
	 *            the value to add
	 * @throws NullPointerException
	 *             if the object is null
	 * @since Commons Lang 2.2
	 */
	public void add(final Number operand) {
		value += operand.longValue();
	}


	/**
	 * Compares this mutable to another in ascending order.
	 * 
	 * @param obj
	 *            the mutable to compare to
	 * @return negative if this is less, zero if equal, positive if greater
	 * @throws ClassCastException
	 *             if the argument is not a MutableLong
	 */
	@Override
	public int compareTo(final Object obj) {
		final MutableLong other = (MutableLong) obj;
		final long anotherVal = other.value;
		return value < anotherVal ? -1 : value == anotherVal ? 0 : 1;
	}


	/**
	 * Decrements the value.
	 * 
	 * @since Commons Lang 2.2
	 */
	public void decrement() {
		value--;
	}


	/**
	 * Returns the value of this MutableLong as a double.
	 * 
	 * @return the numeric value represented by this object after conversion to
	 *         type double.
	 */
	@Override
	public double doubleValue() {
		return value;
	}


	// -----------------------------------------------------------------------
	/**
	 * Compares this object against the specified object. The result is
	 * <code>true</code> if and only if the argument is not <code>null</code>
	 * and is a <code>MutableLong</code> object that contains the same
	 * <code>long</code> value as this object.
	 * 
	 * @param obj
	 *            the object to compare with.
	 * @return <code>true</code> if the objects are the same; <code>false</code>
	 *         otherwise.
	 */
	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof MutableLong) {
			return value == ((MutableLong) obj).longValue();
		}
		return false;
	}


	/**
	 * Returns the value of this MutableLong as a float.
	 * 
	 * @return the numeric value represented by this object after conversion to
	 *         type float.
	 */
	@Override
	public float floatValue() {
		return value;
	}


	// -----------------------------------------------------------------------
	/**
	 * Gets the value as a Long instance.
	 * 
	 * @return the value as a Long
	 */
	@Override
	public Object getValue() {
		return new Long(value);
	}


	/**
	 * Returns a suitable hashcode for this mutable.
	 * 
	 * @return a suitable hashcode
	 */
	@Override
	public int hashCode() {
		return (int) (value ^ value >>> 32);
	}


	// -----------------------------------------------------------------------
	/**
	 * Increments the value.
	 * 
	 * @since Commons Lang 2.2
	 */
	public void increment() {
		value++;
	}


	// -----------------------------------------------------------------------
	// shortValue and bytValue rely on Number implementation
	/**
	 * Returns the value of this MutableLong as a int.
	 * 
	 * @return the numeric value represented by this object after conversion to
	 *         type int.
	 */
	@Override
	public int intValue() {
		return (int) value;
	}


	/**
	 * Returns the value of this MutableLong as a long.
	 * 
	 * @return the numeric value represented by this object after conversion to
	 *         type long.
	 */
	@Override
	public long longValue() {
		return value;
	}


	/**
	 * Sets the value.
	 * 
	 * @param value
	 *            the value to set
	 */
	public void setValue(final long value) {
		this.value = value;
	}


	/**
	 * Sets the value from any Number instance.
	 * 
	 * @param value
	 *            the value to set
	 * @throws NullPointerException
	 *             if the object is null
	 * @throws ClassCastException
	 *             if the type is not a {@link Number}
	 */
	@Override
	public void setValue(final Object value) {
		setValue(((Number) value).longValue());
	}


	/**
	 * Subtracts a value.
	 * 
	 * @param operand
	 *            the value to add
	 * @since Commons Lang 2.2
	 */
	public void subtract(final long operand) {
		value -= operand;
	}


	/**
	 * Subtracts a value.
	 * 
	 * @param operand
	 *            the value to add
	 * @throws NullPointerException
	 *             if the object is null
	 * @since Commons Lang 2.2
	 */
	public void subtract(final Number operand) {
		value -= operand.longValue();
	}


	// -----------------------------------------------------------------------
	/**
	 * Gets this mutable as an instance of Long.
	 * 
	 * @return a Long instance containing the value from this mutable
	 */
	public Long toLong() {
		return new Long(longValue());
	}


	/**
	 * Returns the String value of this mutable.
	 * 
	 * @return the mutable value as a string
	 */
	@Override
	public String toString() {
		return String.valueOf(value);
	}

}