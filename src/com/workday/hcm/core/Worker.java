package com.workday.hcm.core;

import java.util.Set;

public class Worker extends Person {

	private static Set<Worker> WORKER_INSTANCES;
	
	private String fullname;

	/**
	 * Get all the Worker instances in the tenant. This
	 * is probably a very bad idea!
	 */
	public Set<Worker> getAllWorkers() {
		return Worker.WORKER_INSTANCES;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
}
