package context_circuit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import exceptions.CustomException;
import misc.Link;

public class CircuitReader {
	private static CircuitReader instance = null;
	private String filePath;
	private List<String> fileList;
	private HashMap<String, String> nodeDescriptionMap;
	private HashMap<String, List<String>> edgeDescriptionMap;
	private HashMap<String, Link> links;
	
	private final String DoubleKeyCircuit = "ERROR: The name of the gate key has already been used.";
	private final String GateDoesNotExist = "ERROR: The gate does not exist in the input file.";
	private final String GateHasNoDescription = "ERROR: The gate does not have a description.";
	private final String GateIsNotConnected = "ERROR: The gate is not connected to another gate.";
	private final String CircuitHasACircularDependency = "ERROR: Circular dependency detected. Exiting...";

	private CircuitReader(){}
	
	public static CircuitReader getInstance(){
		if(instance == null){
			instance = new CircuitReader();
		}
		return instance;
	}
	
	/**
	 * This method is more or less an initialize method.
	 * The method calls the file and hashmap set methods.
	 * @param filePath
	 */
	public void getCircuitFromFile(String filePath){
		this.filePath = filePath;
		readFile();
		generateNodeHashMaps();
		checkForErrorsInHashMap();
		createLinkedList();
		checkForCircularDependencies();
	}
	
	public List<String> getFileList(){
		return fileList;
	}
	
	public HashMap<String, String> getNodeDescriptionMap(){
		return nodeDescriptionMap;
	}
	
	public HashMap<String, List<String>> getEdgeDescriptionMap(){
		return edgeDescriptionMap;
	}
	
	/**
	 * A method for reading out the txt files.
	 * It removes the tabs and ; from the files.
	 */
	private void readFile(){
		fileList = new ArrayList<String>();
		
		try {
			Files.lines(Paths.get(filePath)).forEach(l -> fileList.add(l.trim().replaceAll("\\t", "").replaceAll(";", "")));
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This method splits up the filelist in two hashmaps.
	 * A hashmap with the node descriptions
	 * And a hashmap with the edge descriptions
	 */
	private void generateNodeHashMaps(){
		nodeDescriptionMap = new HashMap<String, String>();
		edgeDescriptionMap = new HashMap<String, List<String>>();
		Boolean secondHalf = false;
		
		for(String i : fileList){
			if(i.isEmpty()){
				secondHalf = true;
			} 
			else if(!i.isEmpty() && i.charAt(0) != '#' && !secondHalf){
				String key = i.substring(0, i.indexOf(':')).trim();
				String value = i.substring(i.indexOf(':') + 1).trim();
				nodeDescriptionMap.put(key, value);
			}
			else if(!i.isEmpty() && i.charAt(0) != '#' && secondHalf){
				String key = i.substring(0, i.indexOf(':')).trim();
				
				checkForDoubleKeyCircuit(key);
				
				List<String> value = Arrays.asList(i.substring(i.indexOf(':')+1).trim().split(","));
				edgeDescriptionMap.put(key, value);
			}
		}
	}
	
	private void checkForDoubleKeyCircuit(String key){
		if(getEdgeDescriptionMap().containsKey(key)){
			tryAndCatchError(DoubleKeyCircuit);
		}
	}
	
	private void checkForErrorsInHashMap(){
		for(String key : getEdgeDescriptionMap().keySet()){
			checkIfGateHasNoDescription(key);
			checkIfGateIsNotConnected(key);
			checkIfGateDoesNotExist(key);
		}
	}
	
	private void checkIfGateHasNoDescription(String key){
		if(!getNodeDescriptionMap().containsKey(key)){
			tryAndCatchError(GateHasNoDescription);
		}
	}
	
	private void checkIfGateIsNotConnected(String key){
		List<String> list = getEdgeDescriptionMap().get(key);
		
		if(list.isEmpty()){
			tryAndCatchError(GateIsNotConnected);
		} else {
			int emptyStrings = 0;
			for(String i : list){
				if(i.isEmpty()){
					emptyStrings++;
				} else{
					break;
				}
			}
			if(emptyStrings >= list.size()){
				tryAndCatchError(GateIsNotConnected);
			}
		}
	}
	
	private void checkIfGateDoesNotExist(String key){
		for(String value : getEdgeDescriptionMap().get(key)){
			if(!getEdgeDescriptionMap().containsKey(value) && value.toUpperCase().contains("NODE")){
				tryAndCatchError(GateDoesNotExist);
			}
		}
	}
	
	private void createLinkedList() {
		// Create hashmap of Link names and Link objects.
		links = new HashMap<String, Link>();
		for (String key : getNodeDescriptionMap().keySet()) {
			links.put(key, new Link(key));
		}
		
		// Link links to each other.
		for (String key : getEdgeDescriptionMap().keySet()) {
			for (String val : getEdgeDescriptionMap().get(key)) {
				links.get(key).addNextLink(links.get(val));
			}
		}
	}
	
	private void checkForCircularDependencies() {
		// Check for circular dependency.
		try {
			for (String key : links.keySet()) {
				links.get(key).next();
			}
		}
		catch (StackOverflowError e) {
			System.out.println(CircuitHasACircularDependency);
			System.exit(0);
		}
	}
	
	private void tryAndCatchError(String error){
		try {
			throw new CustomException(error);
		} catch (CustomException e) {
			System.out.println(error + " Exiting...");
			System.exit(0);
		}
	}
}