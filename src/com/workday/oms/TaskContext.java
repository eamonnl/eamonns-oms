package com.workday.oms;

import java.util.Map;
import java.util.Set;

public class TaskContext {

	private Map<String, Instance> instanceData;
	private Set<Instance> executingContext;
	
	public TaskContext(Map<String, Instance> instanceData) {
		super();
		this.instanceData = instanceData;
	}

	public Map<String, Instance> getInstanceData() {
		return instanceData;
	}

	public Instance getInstance(String instanceId) {
		return instanceData.get(instanceId);
	}
	
	public Set<Instance> getExecutingContext() {
		return executingContext;
	}

	public void setExecutingContext(Set<Instance> executingContext) {
		this.executingContext = executingContext;
	}

}
