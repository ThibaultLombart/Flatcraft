module fr.univartois.butinfo.r304.flatcraft {
  exports fr.univartois.butinfo.r304.flatcraft;
  exports fr.univartois.butinfo.r304.flatcraft.controller;
  exports fr.univartois.butinfo.r304.flatcraft.model;
  exports fr.univartois.butinfo.r304.flatcraft.model.craft;
  exports fr.univartois.butinfo.r304.flatcraft.model.decorator;
  exports fr.univartois.butinfo.r304.flatcraft.model.map;
  exports fr.univartois.butinfo.r304.flatcraft.model.movables;
  exports fr.univartois.butinfo.r304.flatcraft.model.resources;
  exports fr.univartois.butinfo.r304.flatcraft.model.resources.stateinventory;
  exports fr.univartois.butinfo.r304.flatcraft.view;
  
  
  requires transitive javafx.controls;
  requires javafx.fxml;
  requires javafx.graphics;
  
  
  opens fr.univartois.butinfo.r304.flatcraft.controller to javafx.fxml;
}
