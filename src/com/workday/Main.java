package com.workday;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.workday.oms.Instance;
import com.workday.oms.MetaDataLoader;
import com.workday.oms.MethodRunner;
import com.workday.oms.TaskContext;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		// We have a static CSV file on the classpath instead of a DB to load metadata.
		File csvMetadataFile = new File("bin/super-tenant.metadata");
		
		Map<String, Instance> instanceData = MetaDataLoader.loadMetaData(csvMetadataFile);
		
		TaskContext context = new TaskContext(instanceData);
		
		MethodRunner mr = new MethodRunner();
		
		// Grab a specific method to execute - In our platform a Task (CT) might call this.
		com.workday.oms.Method allWorkersMethod = (com.workday.oms.Method) context.getInstance("393$7253");
		
		context.setExecutingContext(mr.execute(allWorkersMethod, context));
		
		// Lets just print our result as we don't have any elements yet!
		Set<Instance> workers = context.getExecutingContext();
		System.out.println(allWorkersMethod.toString() + " returned " + workers.size() + " workers.");
		System.out.println(String.join(",", workers.stream().map(o -> o.toString()).collect(Collectors.toList())));
	}

}
