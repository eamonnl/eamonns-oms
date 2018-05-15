package com.workday.oms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Lets pretend meta-data is stored on disk in CSV files that look like this:
 * 
 * 1$261,com.workday.oms.Class,Worker,,,393$7253
 * 393$7253,com.workday.oms.GetAllInstancesMethod,GetAllWorkers,yes,1$261, 
 *
 */
public class MetaDataLoader {

	private static Map<String, Instance> ID = new HashMap<String, Instance>();

	public static Map<String, Instance> loadMetaData(File csvMetadataFile) throws Exception {

		// TODO: As a Hack we are going to load the metadata twice so we don't
		// have to walk the graph and load instances in any particular order.

		BufferedReader br = null;
		String line = "";

		try {

			br = new BufferedReader(new FileReader(csvMetadataFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] instance = line.split(",");

				// Create an instance for every line - ignoring instances
				// already loaded
				Instance newInstance = MetaDataLoader.newInstance(instance);
				if (newInstance != null)
					ID.put(instance[0], MetaDataLoader.newInstance(instance));

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// Log the instance being loaded
		System.out.println("Loaded Instances: ");
		for (Iterator<Instance> iterator = ID.values().iterator(); iterator.hasNext();) {
			System.out.println("\t" + iterator.next());
		}
		
		return ID;
	}

	private static Instance newInstance(String[] instance) throws Exception {

		// If we have already loaded don't create another
		Object object = lookupInstance(instance[0]);

		if (object == null) {

			// Create Java instance
			java.lang.Class<?> clazz = java.lang.Class.forName(instance[1]);

			// Lets say all instances have a constructor that takes just the instance ID
			Constructor<?> ctor = clazz.getConstructor(String.class);

			// The first column in our data format is the instanceID
			object = ctor.newInstance(new Object[] { instance[0] });

		}

		// Now inject all the attributes, relationships and methods from the CSV

		if (object instanceof com.workday.oms.Class) {

			Class cl = (Class) object;
			cl.setName(instance[2]);
			cl.setAttributes(lookupInstances(instance[3].split(";")));
			cl.setRelationships(lookupInstances(instance[4].split(";")));
			cl.setMethods(lookupInstances(instance[5].split(";")));

		} else if (object instanceof com.workday.oms.Method) {

			Method m = (Method) object;
			m.setName(instance[2]);
			m.setStatic(Boolean.parseBoolean(instance[3]));
			m.setReturnType(lookupInstance(instance[4]));
			m.setParms(lookupInstances(instance[5].split(";")));

		}

		return (Instance) object;
	}

	private static Set<Instance> lookupInstances(String[] instanceIDs) {
		Set<Instance> instances = new HashSet<Instance>(instanceIDs.length);
		for (int i = 0; i < instanceIDs.length; i++) {
			instances.add(lookupInstance(instanceIDs[i]));
		}
		return instances;
	}

	private static Instance lookupInstance(String instanceID) {
		return ID.get(instanceID);
	}

}
