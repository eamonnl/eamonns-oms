package com.workday.oms;

import java.util.Set;

/**
 * An abstract base-class for all methods providing implementations for common
 * methods.
 * 
 * Note: Methods are immutable
 */
public abstract class AbstractMethod {

	private String uniqueId;

	private Instance returnType;

	private String name;
	
	private Boolean statik;

	private Set<Instance> parms;

	public AbstractMethod(String uniqueId) {
		super();
		this.uniqueId = uniqueId;
	}

	public String getUniqueId() {
		return this.uniqueId;
	}

	public String getName() {
		return this.name;
	}

	public Instance getReturnType() {
		return this.returnType;
	}

	public Set<Instance> getParms() {
		return this.parms;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setReturnType(Instance returnType) {
		this.returnType = returnType;
	}

	public void setParms(Set<Instance> parms) {
		this.parms = parms;
	}

	public void setStatic(boolean statik) {
		this.statik = new Boolean(statik);
	}
	
	public boolean isStatic() {
		// TODO default to false?
		return statik.booleanValue();
	}
}
