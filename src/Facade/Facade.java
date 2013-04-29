package Facade;

import java.io.File;
import java.util.ArrayList;

import Base.Tags;
import Controller.Controller;

public class Facade {

	private static Facade facade;
	private Controller controller;

	private Facade() {
		controller = new Controller();
	}

	public static Facade getInstace() {
		if (facade == null) {
			facade = new Facade();
			return facade;
		}
		return facade;
	}

	public ArrayList<Tags> parserFileToTagsList(File disco) {
		return controller.parserFileToTagsList(disco);
	}

}
