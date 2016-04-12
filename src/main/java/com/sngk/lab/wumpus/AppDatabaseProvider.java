package com.sngk.lab.wumpus;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.ServerConfig;
import com.google.inject.Provider;

/**
 * @author Andrew U. (SID: s0253796)
 * @version 30-Sep-2015
 * The class to work with the database configuration file ebean.properties
 */
public class AppDatabaseProvider implements Provider<EbeanServer> {

	public EbeanServer get() {
		ServerConfig config = new ServerConfig();
		config.setName("sqlite");
		// load configuration from ebean.properties
		config.loadFromProperties();
		config.setDefaultServer(true);
		config.setDdlGenerate(false);
		config.setDdlRun(false);
		// EbeanServer configured by ebean.properties
		return EbeanServerFactory.create(config);
	}
}
