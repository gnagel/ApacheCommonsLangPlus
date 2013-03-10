package org.apache.commons.lang3.mutable;


public interface IMutableObjectCreator<T extends Object> {
	public void onCreate(T value);
}
