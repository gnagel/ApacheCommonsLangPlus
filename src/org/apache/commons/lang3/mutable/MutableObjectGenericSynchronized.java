package org.apache.commons.lang3.mutable;


/**
 * Synchronized version of {@link MutableObjectGeneric}
 * 
 * @author Glenn Nagel
 * @param <T>
 */
public class MutableObjectGenericSynchronized<T> implements IMutableObjectGeneric<T> {
	private final IMutableObjectGeneric<T>	value	= new MutableObjectGeneric<T>();


	@Override
	public synchronized void clearValue() {
		value.clearValue();
	}


	@Override
	public synchronized T getValue() {
		return value.getValue();
	}


	@Override
	public synchronized boolean hasValue() {
		return value.hasValue();
	}


	@Override
	public void setCreator(final IMutableObjectCreator<T> creator) {
		value.setCreator(creator);
	}


	@Override
	public synchronized void setDestructor(final IMutableObjectDestructor<T> destructor) {
		value.setDestructor(destructor);
	}


	@Override
	public synchronized T setValue(final T value) {
		return this.value.setValue(value);
	}
}
