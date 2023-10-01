package com.praticas.msusuario.util;

import java.lang.StackWalker.StackFrame;

public class LogUtils {

	private LogUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static String getCurrentMethodName() {
		return StackWalker.getInstance()
			.walk(s -> s.skip(1).findFirst())
			.map(StackFrame::getMethodName)
			.orElseGet(() -> "");
	}

	public static String getCallerMethodName() {
		return StackWalker.getInstance()
			.walk(s -> s.skip(2).findFirst())
			.map(StackFrame::getMethodName)
			.orElseGet(() -> "");
	}

}