package com.workday.oms;

import java.util.Set;

public interface Method extends Instance {

	public String getName();
	
	public Instance getReturnType();
	
	public Set<Instance> getParms();
	
	public boolean isStatic();
	
	public Set<Instance> execute(TaskContext context);
	
	
	// Setters for loading the metadata - could use reflection to keep instances immutable
	
	public void setName(String name);
	public void setReturnType(Instance returnType);
	public void setParms(Set<Instance> parms);
	public void setStatic(boolean statik);
}
