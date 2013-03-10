package org.apache.commons.lang3.mutable;


/**
 * Extension to the MutableObject class to support templates.
 * 
 * @author Glenn Nagel
 * @param <T>
 */
public class MutableObjectGeneric<T extends Object> implements IMutableObjectGeneric<T> {
	private IMutableObjectDestructor<T>	destructor		= null;

	private IMutableObjectCreator<T>	creator			= null;

	private final MutableObject			mutableObject	= new MutableObject();


	public MutableObjectGeneric() {
		super();
	}


	public MutableObjectGeneric(final T value) {
		super();
		this.mutableObject.setValue(value);
	}


	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.lang.mutable.IMutableObjectGeneric#clearValue()
	 */
	@Override
	public void clearValue() {
		setValue(null);
	}


	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.lang.mutable.IMutableObjectGeneric#getValue()
	 */
	@Override
	public T getValue() {
		return getValueImpl();
	}


	@SuppressWarnings("unchecked")
	private T getValueImpl() {
		final Object value = mutableObject.getValue();
		return null == value ? null : (T) value;
	}


	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.lang.mutable.IMutableObjectGeneric#hasValue()
	 */
	@Override
	public boolean hasValue() {
		return null != getValueImpl();
	}


	@Override
	public void setCreator(final IMutableObjectCreator<T> creator) {
		this.creator = creator;
	}


	/*
	 * (non-Javadoc)
	 * @see
	 * org.apache.commons.lang.mutable.IMutableObjectGeneric#setDestructor(org
	 * .apache.commons.lang.mutable.IMutableObjectDestructor)
	 */
	@Override
	public void setDestructor(final IMutableObjectDestructor<T> destructor) {
		this.destructor = destructor;
	}


	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.lang.mutable.IMutableObjectGeneric#setValue(T)
	 */
	@Override
	public T setValue(final T value) {
		{
			final T src = getValueImpl();
			if (null != src && null != destructor) {
				destructor.onDestroy(src);
			}
		}

		mutableObject.setValue(value);
		if (null != value && null != creator) {
			creator.onCreate(value);
		}
		return value;
	}
}
