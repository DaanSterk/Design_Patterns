package context_circuit;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import circuit_gates.GateNeutral;
import context_circuit.gates.Gate;

public class GateFactory {
	private static final HashMap<String, Gate> gates;
	private static List<Class> gateClassArray;
	
	static {
		gateClassArray = new ArrayList<Class>();
		try {
			gateClassArray = findClasses(new File(System.getProperty("user.dir") + "//src//circuit_gates"), "circuit_gates");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		gates = new HashMap<String, Gate>();
		
		for(Class gate : gateClassArray){
			try {
				gates.put(gate.getSimpleName(), (Gate) gate.newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Creates a new object that is a subtype of Gate.
	 * @param type
	 * @param name
	 * @return Gate
	 */
	public static final Gate create(final String type, final String name){
		if(gates.containsKey(name)){
			return gates.get(name).copy();
		} 
		else{
			return gates.get("GateNeutral").copy();
		}
		
//		final String message = String.format("Gate type '%s' was not found.", name);
//		throw new IllegalArgumentException(message);
	}
	
	/**
	 * Recursive method used to find all classes in a given directory and subdirs.
	 *
	 * @param directory   The base directory
	 * @param packageName The package name for classes found inside the base directory
	 * @return The classes
	 * @throws ClassNotFoundException
	 */
	private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
	    List<Class> classes = new ArrayList<Class>();
	    if (!directory.exists()) {
	        return classes;
	    }
	    File[] files = directory.listFiles();
	    for (File file : files) {
	        if (file.isDirectory()) {
	            assert !file.getName().contains(".");
	            classes.addAll(findClasses(file, packageName + "." + file.getName()));
	        } else if (file.getName().endsWith(".class")) {
	            classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
	        } else if (file.getName().endsWith(".java")) {
	            classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 5)));
	        }
	    }
	    return classes;
	}
}
