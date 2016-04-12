package com.sngk.lab.wumpus;

import com.avaje.ebean.EbeanServer;
import com.google.inject.AbstractModule;
import com.sngk.lab.wumpus.controller.Game;
import com.sngk.lab.wumpus.model.service.*;
import com.sngk.lab.wumpus.model.service.impl.*;

/**
 * @author Andrew U. (SID: s0253796)
 * @version 30-Sep-2015
 * This class is necessary for using Services
 * However, this is used when you don't want to create new() instances of services every time.
 */
public class AppInjection extends AbstractModule {

	protected void configure() {

		//bind ebean orm
		bind(EbeanServer.class).toProvider(AppDatabaseProvider.class).asEagerSingleton();

		//bind application
		bind(Game.class).asEagerSingleton();
		bind(com.sngk.lab.wumpus.controller.WorldController.class).asEagerSingleton();
		bind(com.sngk.lab.wumpus.view.WorldView.class).asEagerSingleton();

		//bind others
		bind(LayoutService.class)
				.to(LayoutServiceImpl.class)
				.asEagerSingleton();
		bind(MazePathService.class)
				.to(MazePathServiceImpl.class)
				.asEagerSingleton();
		bind(PlayerService.class)
				.to(PlayerServiceImpl.class)
				.asEagerSingleton();
		bind(RoomService.class)
				.to(RoomServiceImpl.class)
				.asEagerSingleton();
		bind(SaveService.class)
				.to(SaveServiceImpl.class)
				.asEagerSingleton();
	}
}
