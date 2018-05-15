package com.workday.oms;

import java.util.Set;

public class Class implements Instance {

	private String uniqueId;

	private String name;

	private Set<Instance> attributes;
	private Set<Instance> relationships;
	private Set<Instance> methods;

	public Class(String uniqueId) {
		super();
		this.uniqueId = uniqueId;
	}

	public String getName() {
		return name;
	}

	public Set<Instance> getMethods() {
		return methods;
	}

	public Set<Instance> getAttributes() {
		return attributes;
	}

	public Set<Instance> getRelationships() {
		return relationships;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAttributes(Set<Instance> attributes) {
		this.attributes = attributes;
	}

	public void setRelationships(Set<Instance> relationships) {
		this.relationships = relationships;
	}

	public void setMethods(Set<Instance> methods) {
		this.methods = methods;
	}

	@Override
	public String getUniqueId() {
		return this.uniqueId;
	}

	public String toString() {
		return this.getName() + ", " + this.getUniqueId();
	}

}
