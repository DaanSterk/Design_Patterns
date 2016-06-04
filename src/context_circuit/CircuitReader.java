package context_circuit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import exceptions.CustomException;

public class CircuitReader {
	private static CircuitReader instance = null;
	private String filePath;
	private List<String> fileList;
	private HashMap<String, String> nodeDescriptionMap;
	private HashMap<String, List<String>> edgeDescriptionMap;
	
	private final String DoubleKeyCircuit = "ERROR: The name of the gate key has already been used.";
	private final String GateDoesNotExist = "ERROR: The gate does not exist in the input file.";
	private final String GateHasNoDescription = "ERROR: The gate does not have a description.";
	private final String GateIsNotConnected = "ERROR: The gate is not connected to another gate.";

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
			try {
				throw new CustomException(DoubleKeyCircuit);
			} catch (CustomException e) {
				e.printStackTrace();
				System.out.println(e);
				System.exit(0);
			}
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
			try {
				throw new CustomException(GateHasNoDescription);
			} catch (CustomException e) {
				e.printStackTrace();
				System.out.println(e);
				System.exit(0);
			}
		}
	}
	
	private void checkIfGateIsNotConnected(String key){
		List<String> list = getEdgeDescriptionMap().get(key);
		
		if(list.isEmpty()){
			try {
				throw new CustomException(GateIsNotConnected);
			} catch (CustomException e) {
				e.printStackTrace();
				System.out.println(e);
				System.exit(0);
			}
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
				try {
					throw new CustomException(GateIsNotConnected);
				} catch (CustomException e) {
					e.printStackTrace();
					System.out.println(e);
					System.exit(0);
				}
			}
		}
	}
	
	private void checkIfGateDoesNotExist(String key){
		for(String value : getEdgeDescriptionMap().get(key)){
			if(!getEdgeDescriptionMap().containsKey(value) && value.toUpperCase().contains("NODE")){
				try {
					throw new CustomException(GateDoesNotExist);
				} catch (CustomException e) {
					e.printStackTrace();
					System.out.println(e);
					System.exit(0);
				}
			}
		}
	}
}