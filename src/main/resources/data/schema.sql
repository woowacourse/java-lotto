DROP TABLE IF EXISTS result;
DROP TABLE IF EXISTS bought_lotto;
DROP TABLE IF EXISTS winning_lotto;
DROP TABLE IF EXISTS lotto_game;

CREATE TABLE lotto_game (
	round integer primary key,
    money integer not null,
    number_of_manual integer not null
);

CREATE TABLE bought_lotto (
	id integer auto_increment primary key,
    num1 integer not null,
    num2 integer not null,
    num3 integer not null,
    num4 integer not null,
    num5 integer not null,
    num6 integer not null,
    round_id integer not null,
    foreign key (round_id) references lotto_game (round)
);

CREATE TABLE winning_lotto (
	id integer not null primary key,
	num1 integer not null,
    num2 integer not null,
    num3 integer not null,
    num4 integer not null,
    num5 integer not null,
    num6 integer not null,
    bonus_no integer not null,
    foreign key (id) references lotto_game (round)
);

CREATE TABLE result (
	id integer not null primary key,
	winning_money integer not null,
    `first` integer default 0,
    `second` integer default 0,
    `third` integer default 0,
    `fourth` integer default 0,
    `fifth` integer default 0,
    `none` integer default 0,
    foreign key (id) references lotto_game (round)
);
