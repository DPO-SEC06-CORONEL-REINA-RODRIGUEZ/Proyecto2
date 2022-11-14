package model;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class GenericAlgorithms {

	
	
	public static String formatDate(Date fecha)
	{
	
		SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
		String stringDate = DateFor.format(fecha);
		
		return stringDate;
	}
	
	public static <K, V> void addToHashList(HashMap<K, ArrayList <V>> hash, K mapKey, V myItem) {
		ArrayList<V> itemsList = hash.get(mapKey);
	
	    // if list does not exist create it
	    if(itemsList == null) {
	         itemsList = new ArrayList<V>();
	         itemsList.add(myItem);
	         hash.put(mapKey, itemsList);
	    } else {
	        // add if item is not already in list
	        if(!itemsList.contains(myItem)) itemsList.add(myItem);
	    }
	}
	
	public static boolean revisarFile(File archivo, String espExt) throws Exception

	{
		boolean rta = false;
		if(archivo == null) {throw new Exception("Por favor seleccione todos los archivos solicitados");}
		String fileName = archivo.toString();
		int index = fileName.lastIndexOf('.');
	    if(index > 0) 
	    {
	      String extension = fileName.substring(index + 1);
	      if (espExt.equals(extension))
	      {
	    	  rta = true;
	      }
	      else
		    {
		    	throw new Exception("Extension de archivo incorrecta");
		    }
	    }
	    
	    	
	    
	    return rta;
	}
	
	public static boolean cadenaNoVacia(String checkCadena) throws Exception
	{
		boolean rta = true;
		if(checkCadena == null || checkCadena.equals("")) {rta = false; throw new Exception("Por favor ingrese un key no vacío");}
		return rta;
	}
	
	public static <V> boolean revisarArrayListVacio(ArrayList<V> lista) throws Exception
	{
		boolean rta = true;
		if(lista.isEmpty()) {rta=false;throw new Exception("Por favor seleccione elementos");}
		return rta;
		
	}
	
	public static <K,B,V> void addHashHash(HashMap<K,HashMap<B,V>> i, K key, B subkey, V value) {
		HashMap<B,V> hashI = i.get(key);
		if(hashI == null) 
		{
			hashI = new HashMap<>();
			hashI.put(subkey, value);
			i.put(key, hashI);
		}
		else
		{
			hashI.put(subkey,value);
		}
	}


	
	

	

}
