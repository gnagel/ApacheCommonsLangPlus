package org.apache.commons.lang3.mutable;


public interface IMutableObjectDestructor<T extends Object> {
	public void onDestroy(T value);
}
