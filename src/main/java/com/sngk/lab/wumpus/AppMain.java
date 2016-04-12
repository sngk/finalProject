package com.sngk.lab.wumpus;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.sngk.lab.wumpus.controller.Game;
/**
 * @author Andrew U. (SID: s0253796)
 * @version 30-Sep-2015
 * Main class to run the application.
 */

public class AppMain {
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new AppInjection());
		Game game = injector.getInstance(Game.class);
		game.play();
	}

}
