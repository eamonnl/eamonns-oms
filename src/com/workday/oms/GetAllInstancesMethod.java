package com.workday.oms;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GetAllInstancesMethod extends AbstractMethod implements Method {

	public GetAllInstancesMethod(String uniqueId) {
		super(uniqueId);
	}

	@Override
	public Set<Instance> execute(TaskContext context) {
		
		Set<Instance> returnSet = new HashSet<Instance>();
		
		Collection<Instance> instanceData = context.getInstanceData().values();
		for (Iterator<Instance> iterator = instanceData.iterator(); iterator.hasNext();) {
			Instance i = iterator.next();
			
			if (i instanceof Class)
				returnSet.add(i);
		}
		
		return returnSet;
	}

	
	public String toString() {
		return "@" + this.getName() + "(GI), " + this.getUniqueId();
	}
}
