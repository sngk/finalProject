package com.sngk.lab.wumpus.controller;

import com.sngk.lab.wumpus.model.Edge;
import com.sngk.lab.wumpus.model.World;
import com.sngk.lab.wumpus.model.Layout;
import com.sngk.lab.wumpus.model.MazePath;
import com.sngk.lab.wumpus.model.Player;
import com.sngk.lab.wumpus.model.Save;
import com.sngk.lab.wumpus.model.service.LayoutService;
import com.sngk.lab.wumpus.model.service.PlayerService;
import com.sngk.lab.wumpus.model.service.SaveService;
import com.sngk.lab.wumpus.model.command.Command;
import com.sngk.lab.wumpus.model.command.Result;
import com.sngk.lab.wumpus.view.WorldView;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Stack;

/**
 * @author Andrew U. (SID: s0253796)
 * @version 30-Sep-2015
 * <p>
 * Controller or engine for our world.
 * This class process the user input, update the states of the models and renders the world
 */
@Singleton
public class Game {

	private boolean isUserChoosen;

	private boolean isStarted;

	private boolean isLoading;

	@Inject
	private LayoutService layoutService;

	@Inject
	private PlayerService playerService;

	@Inject
	private SaveService saveService;

	@Inject
	private WorldController worldController;

	@Inject
	private WorldView worldView;

	private World world;
	private Player player;

	public void play() {
		worldView.show();

		worldView.println(welcome());

		worldView.println("Enter your login: ");

		worldView.newGameListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isUserChoosen) {
					endGame();

					Layout layout = layoutService.findDefault();
					if (layout == null) {
						layout = layoutService.createDefault();
					}
					worldView.println("Creating world...");
					world = worldController.createWorld(player.getId(), layout.getId());

					worldView.println(start());
					worldView.println(describe());

					startGame();
				} else {
					worldView.println("Enter your login:");
				}
			}
		});

		worldView.loadGameListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isUserChoosen) {
					endGame();
					List<Save> saves = saveService.findAll(player.getId());
					for (Save save : saves) {
						worldView.println("Save: id = <" + save.getId() + "> name = <" + save.getName() + "> date = <" + save.getLastUpdate() + ">");
					}
					worldView.println("Choose your save id: ");

					loadGame();
				} else {
					worldView.println("Enter your login:");
				}
			}
		});

		worldView.saveGameListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isUserChoosen) {
					if (isStarted) {
						if (worldController.save()) {
							worldView.println("Your game has been successfully saved. To load it press Load Game button.\n");
							worldView.println(describe());
						} else {
							worldView.println("Game can not be saved.");
							worldView.println(describe());
						}
					} else {
						worldView.println("Start or load game");
					}
				} else {
					worldView.println("Enter your login:");
				}
			}
		});

		worldView.helpListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isUserChoosen) {
					if (isStarted) {
						worldView.showHelp();
					} else {
						worldView.println("Start or load game");
					}
				} else {
					worldView.println("Enter your login:");
				}
			}
		});

		worldView.commandListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent event) {
				worldView.print("> ");
				String s = worldView.readCommand();

				if(isUserChoosen) {
					if (isStarted) {
						Command c = Command.create(s);
						Result r = c.perform(worldController);
						switch (r) {
							case UNKNOWN:
								worldView.println("Invalid command. Please type \"help\" or \"?\" to see list of available commands.");
								break;
							case HELP:
								worldView.showHelp();
								break;
							case GAME_OVER:
								worldView.println("Game over");
								endGame();
								break;
							case WORLD_SAVED:
								worldView.println("Your game has been successfully saved. To load it press Load Game button\n");
								worldView.println(describe());
								break;
							case WORLD_NOT_SAVED:
								worldView.println("Game can not be saved.");
								worldView.println(describe());
								break;
							case WUMPUS_KILLED:
								worldView.println("The wumpus is dead");
								worldView.println("Congratulations! You won!");
								endGame();
								break;
							case PLAYER_DIED:
								if (world.mazeRunner.getHealth() >= 2) {
									Long pew = null;
									worldView.println("You've been killed. However, since you have 2 lives you will be\nredirected to your last saved position");
									world.mazeRunner.setHealth(world.mazeRunner.getHealth() - 1);
									worldView.println("Now you have " + world.mazeRunner.getHealth() + " life.");
									List<Save> saves = saveService.findAll(player.getId());
									for (Save save:saves) {
										pew = save.getId();
									}
									world = worldController.loadWorld(pew);
									worldView.println(describe());
								} else {
									worldView.println("You die! Game Over!");
									worldView.println("You've been eaten by scary wumpus. Sorry.");
									endGame();
								}
								break;
							case PLAYER_DIED_PIT:
								if (world.mazeRunner.getHealth() >= 2) {
									Long pew = null;
									worldView.println("You've been killed. However, since you have 2 lives you will be\nredirected to your last saved position");
									world.mazeRunner.setHealth(world.mazeRunner.getHealth() - 1);
									worldView.println("Now you have " + world.mazeRunner.getHealth() + " life.");List<Save> saves = saveService.findAll(player.getId());
									for (Save save:saves) {
										pew = save.getId();
									}
									world = worldController.loadWorld(pew);
									worldView.println(describe());
								} else {
									worldView.println("You're falling in a deep-deep pit.");
									worldView.println("Looks like you'll die. Sorry. \nGame over.");
									endGame();
								}
								break;
							case GOOD_BAT:
								worldView.println("Congratulations, you found a good bat.");
								worldView.println("Good bat says: Greetings hunter, the wumpus is in room: " + world.wumpusRoom.getLabel());
								worldView.println("Good bat says: Hope it'll help. Good luck in your journey.");
								worldView.println(describe());
								break;
							//
							case BAD_BAT:
								worldView.println("Oops! You found a bad bat it will redirect you closer to wumpus. Be careful!");
								Stack<MazePath> spbb = worldController.shortestPath(worldController.getRoom(world.wumpusRoom.getLabel()));
								while (!spbb.empty()) {
									Edge e = spbb.pop();
									world.mazeRunner.setRoom(worldController.getRoom(e.from()));
								}
								worldView.println(describe());
								break;
							case ROOM_ENTERED:
								worldView.println(describe());
								break;
							case TREASURE:
								world.mazeRunner.setHealth(world.mazeRunner.getHealth() + 1);
								worldView.println("Congrats! You found a treasure. It adds you an extra life. #of lives: " + world.mazeRunner.getHealth());
								worldView.println(describe());
								break;
							case WUMPUS_NOT_KILLED:
								worldView.println("The wumpus lives and is very stroppy");
								break;
							case ROOM_NOT_EXISTS:
								worldView.println("There is no room for " + c.target);
								break;
							case ROOM_NOT_ENTERED:
								worldView.println("Room " + c.target + " is not accessible from here");
								break;
							case PATH_FOUND:
								Stack<MazePath> sptr = worldController.shortestPath(worldController.getRoom(c.target));
								String path = "";
								while (!sptr.empty()) {
									Edge e = sptr.pop();
									path += "\t" + e.from() + " to " + e.to() + "\n";
								}
								worldView.println("The shortest path to " + c.target + " is:\n" + path);
								break;
						}
					} else {
						if (isLoading) {
							worldView.println("Loading world...");
							world = worldController.loadWorld(Long.parseLong(s));

							worldView.println(start());
							worldView.println(describe());

							startGame();
						} else {
							worldView.println("Start or load game");
						}
					}
				}  else {
					player = playerService.findOne(s);
					if (player == null) {
						player = playerService.createOne(s, s);
					}
					chooseUser();
					worldView.println("Start or load game.");
				}
			}
		});
	}

	public String welcome() {
		return "Welcome to Wumpus game!";
	}

	public void chooseUser() {
		isUserChoosen = true;
	}

	public void startGame() {
		isStarted = true;
	}

	public void loadGame() {
		isLoading = true;
	}

	public void endGame() {
		isStarted = false;
	}

	public String describe() {
		String s = "";
		s += "You are in room " + world.mazeRunner.getRoom().getLabel() + ".";
		s += "\nThere are exits to:";
		List<MazePath> ale = world.mazeRunner.getRoom().getMazePaths();
		for (MazePath mazePath : ale)
			s += "\n\t" + mazePath.getRoomTo().getLabel();
		s += "\nThe wumpus is " + world.distance(world.mazeRunner.getRoom(), world.wumpusRoom) + " rooms away from you";
		return s;
	}

	public String start() {
		return "In this world, only the following commands are recognised:\n" +
				Command.list();
	}
}