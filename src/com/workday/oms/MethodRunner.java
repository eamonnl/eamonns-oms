package com.workday.oms;

import java.util.Set;

public class MethodRunner {
	
	public Set<Instance> execute(final Method method, TaskContext context) {
		return method.execute(context);
	}
	
}
