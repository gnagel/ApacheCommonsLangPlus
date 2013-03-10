package org.apache.commons.lang3.threads;


import java.lang.Thread.UncaughtExceptionHandler;


import org.apache.commons.lang3.mutable.IMutableObjectCreator;
import org.apache.commons.lang3.mutable.IMutableObjectDestructor;
import org.apache.commons.lang3.mutable.MutableObjectGenericSynchronized;


import android.app.Application;
import android.os.HandlerThread;
import android.os.Looper;


public final class HandlerThreadWrapper {
	private final MutableObjectGenericSynchronized<android.os.Handler>	handler	= new MutableObjectGenericSynchronized<android.os.Handler>();


	private final MutableObjectGenericSynchronized<HandlerThread>		thread	= new MutableObjectGenericSynchronized<HandlerThread>();


	public android.os.Handler getHandler() {
		return handler.getValue();
	}


	public HandlerThreadWrapper onCreate(final Application application, final UncaughtExceptionHandler listener, final android.os.Handler.Callback handlerCallback) {
		final String threadName = application.getClass().getSimpleName();

		thread.setCreator(new IMutableObjectCreator<HandlerThread>() {
			@Override
			public void onCreate(final HandlerThread value) {
				value.setUncaughtExceptionHandler(listener);
				value.start();
			}
		});

		thread.setDestructor(new IMutableObjectDestructor<HandlerThread>() {
			@Override
			public void onDestroy(final HandlerThread value) {
				if (null != value.getLooper()) {
					value.getLooper().quit();
				}
				value.interrupt();
			}
		});

		final Looper looper = thread.setValue(new HandlerThread(threadName)).getLooper();
		handler.setValue(new android.os.Handler(looper, handlerCallback));

		return this;
	}


	public HandlerThreadWrapper onDestroy() {
		thread.clearValue();
		handler.clearValue();

		return this;
	}
}
