package fr.univartois.butinfo.r304.flatcraft.model;

import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class Player {

	private IntegerProperty healthPoints;
	
	private IntegerProperty xpPoints;
	
	private ObservableMap<Resource, Integer> inventory = FXCollections.observableHashMap();

	
	
	
	
}
