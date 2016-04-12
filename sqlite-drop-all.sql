alter table maze_path drop constraint if exists fk_maze_path_room_from_id;
drop index if exists ix_maze_path_room_from_id;

alter table maze_path drop constraint if exists fk_maze_path_room_to_id;
drop index if exists ix_maze_path_room_to_id;

alter table maze_runner drop constraint if exists fk_maze_runner_room_id;
drop index if exists ix_maze_runner_room_id;

alter table room drop constraint if exists fk_room_layout_id;
drop index if exists ix_room_layout_id;

alter table save drop constraint if exists fk_save_layout_id;
drop index if exists ix_save_layout_id;

alter table save drop constraint if exists fk_save_player_id;
drop index if exists ix_save_player_id;

alter table save drop constraint if exists fk_save_maze_runner_id;
drop index if exists ix_save_maze_runner_id;

alter table save drop constraint if exists fk_save_wumpus_room_id;
drop index if exists ix_save_wumpus_room_id;

alter table save drop constraint if exists fk_save_good_bat_room_id;
drop index if exists ix_save_good_bat_room_id;

alter table save drop constraint if exists fk_save_bad_bat_room_id;
drop index if exists ix_save_bad_bat_room_id;

alter table save drop constraint if exists fk_save_pit_room_id;
drop index if exists ix_save_pit_room_id;

alter table save drop constraint if exists fk_save_treasure_room_id;
drop index if exists ix_save_treasure_room_id;

drop table if exists layout;

drop table if exists maze_path;

drop table if exists maze_runner;

drop table if exists player;

drop table if exists room;

drop table if exists save;

