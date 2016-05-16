package context_circuit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CircuitReader {
	private static CircuitReader instance = null;
	private String filePath;
	private List<String> fileList;
	private HashMap<String, String> nodeDescriptionMap;
	private HashMap<String, List<String>> edgeDescriptionMap;
	
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
		GenerateNodeHashMaps();
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
	 * It removes the whitespace, tabs and ; from the files.
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
	private void GenerateNodeHashMaps(){
		nodeDescriptionMap = new HashMap<String, String>();
		edgeDescriptionMap = new HashMap<String, List<String>>();
		Boolean secondHalf = false;
		
		for(String i : fileList){
			if(i.isEmpty()){
				secondHalf = true;
			} 
			else if(!i.isEmpty() && i.charAt(0) != '#' && !secondHalf){
				String key = i.substring(0, i.indexOf(':'));
				String value = i.substring(i.indexOf(':')+1);
				nodeDescriptionMap.put(key, value);
			}
			else if(!i.isEmpty() && i.charAt(0) != '#' && secondHalf){
				String key = i.substring(0, i.indexOf(':'));
				List<String> value = Arrays.asList(i.substring(i.indexOf(':')+1).split(","));
				edgeDescriptionMap.put(key, value);
			}
		}
	}
}
