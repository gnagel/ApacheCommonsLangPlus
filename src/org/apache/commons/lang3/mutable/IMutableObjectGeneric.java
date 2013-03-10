package org.apache.commons.lang3.mutable;


/**
 * Interface for the {@link MutableObjectGeneric} class. This is a simple typed
 * wrapper around an object using the Apache {@link MutableObject}. There are
 * two flavors of this interface: {@link MutableObjectGeneric} and
 * {@link MutableObjectGenericSynchronized}. The are exactly the same, except
 * {@link MutableObjectGenericSynchronized} is thread safe.
 * 
 * @author Glenn Nagel
 * @param <T>
 */
public interface IMutableObjectGeneric<T extends Object> {

	/**
	 * Clear the saved value. This is an alias for passing <code>null</code> to
	 * {@link #setValue(Object)}.
	 */
	public abstract void clearValue();


	/**
	 * Get the value. Return <code>null</code> if there is no value.
	 * 
	 * @return
	 */
	public abstract T getValue();


	/**
	 * Is the value non-<code>null</code>?
	 * 
	 * @return
	 */
	public abstract boolean hasValue();


	/**
	 * Set the creator to be called when a value is removed. This will only be
	 * called if the current value is non-<code>null</code>.
	 * 
	 * @example <code>
	 * 	IMutableObjectGeneric<String> wrapper = new MutableObjectGeneric<String>();
	 *  wrapper.setCreator(new IMutableObjectCreatoror<String>() {
	 *    public void onCreate(String value) { throw new {@link RuntimeException}(value); } 
	 *  } );
	 *  
	 *  // The creator will not be called:
	 *  wrapper.clearValue();
	 *  wrapper.setValue(null);
	 *  
	 *  // The creator will be called:
	 *  wrapper.setValue("Hello World"); // Throws the exception from the creator
	 *  wrapper.setValue("Goodbye world"); // Throws the exception from the creator
	 * </code>
	 * @param destructor
	 */
	public abstract void setCreator(final IMutableObjectCreator<T> creator);


	/**
	 * Set the destructor to be called when a value is removed. This will only
	 * be called if the current value is non-<code>null</code>.
	 * 
	 * @example <code>
	 * 	IMutableObjectGeneric<String> wrapper = new MutableObjectGeneric<String>();
	 *  wrapper.setDestructor(new IMutableObjectDestructor<String>() {
	 *    public void onDestroy(String value) { throw new {@link RuntimeException}(value); } 
	 *  } );
	 *  
	 *  // The destructor will not be called:
	 *  wrapper.clearValue();
	 *  wrapper.setValue(null);
	 *  wrapper.setValue("Hello World");
	 *  
	 *  // The destructor will be called:
	 *  wrapper.setValue("Goodbye world"); // Throws the exception from the destructor
	 *  wrapper.setValue(null); // Throws the exception from the destructor
	 * </code>
	 * @param destructor
	 */
	public abstract void setDestructor(final IMutableObjectDestructor<T> destructor);


	/**
	 * Set the value. IF the current value is non-<code>null</code> and a
	 * destructor has been set, then the destructor will be triggered.
	 * 
	 * @param value
	 * @return Returns the input value for easily chaining methods
	 */
	public abstract T setValue(final T value);
}
