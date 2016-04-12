create table layout (
  id                            integer primary key autoincrement not null,
  name                          varchar(255),
  creation_date                 timestamp not null,
  last_update                   timestamp not null,
  version                       integer not null
);

create table maze_path (
  id                            integer primary key autoincrement not null,
  room_from_id                  integer,
  room_to_id                    integer,
  distance                      integer,
  creation_date                 timestamp not null,
  last_update                   timestamp not null,
  version                       integer not null,
  foreign key (room_from_id)    references room (id),
  foreign key (room_to_id)      references room (id)
);

create table maze_runner (
  id                            integer primary key autoincrement not null,
  health                        integer,
  room_id                       integer,
  creation_date                 timestamp not null,
  last_update                   timestamp not null,
  version                       integer not null,
  foreign key (room_id)         references room (id)
);

create table player (
  id                            integer primary key autoincrement not null,
  name                          varchar(255),
  login                         varchar(255),
  creation_date                 timestamp not null,
  last_update                   timestamp not null,
  version                       integer not null
);

create table room (
  id                            integer primary key autoincrement not null,
  layout_id                     integer,
  label                         varchar(255),
  creation_date                 timestamp not null,
  last_update                   timestamp not null,
  version                       integer not null,
  foreign key (layout_id)       references layout (id)
);

create table save (
  id                             integer primary key autoincrement not null,
  layout_id                      integer,
  label                          varchar(255),
  player_id                      integer,
  maze_runner_id                 integer,
  wumpus_room_id                 integer,
  good_bat_room_id               integer,
  bad_bat_room_id                integer,
  pit_room_id                    integer,
  treasure_room_id               integer,
  creation_date                  timestamp not null,
  last_update                    timestamp not null,
  version                        integer not null,
  foreign key (layout_id)        references layout (id),
  foreign key (player_id)        references player (id),
  foreign key (maze_runner_id)   references maze_runner (id),
  foreign key (wumpus_room_id)   references room (id),
  foreign key (good_bat_room_id) references room (id),
  foreign key (bad_bat_room_id)  references room (id),
  foreign key (pit_room_id)      references room (id),
  foreign key (treasure_room_id) references room (id)
);

create index ix_maze_path_room_from_id on maze_path (room_from_id);

create index ix_maze_path_room_to_id on maze_path (room_to_id);

create index ix_maze_runner_room_id on maze_runner (room_id);

create index ix_room_layout_id on room (layout_id);

create index ix_save_layout_id on save (layout_id);

create index ix_save_player_id on save (player_id);

create index ix_save_maze_runner_id on save (maze_runner_id);

create index ix_save_wumpus_room_id on save (wumpus_room_id);

create index ix_save_good_bat_room_id on save (good_bat_room_id);

create index ix_save_bad_bat_room_id on save (bad_bat_room_id);

create index ix_save_pit_room_id on save (pit_room_id);

create index ix_save_treasure_room_id on save (treasure_room_id);

