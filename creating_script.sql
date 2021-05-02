DROP DATABASE IF EXISTS EzDollazBet;
CREATE DATABASE EzDollazBet; 
USE EzDollazBet;

SET NAMES utf8 ;
SET character_set_client = utf8mb4 ;

CREATE TABLE Teams (
	TeamID int(10) NOT NULL AUTO_INCREMENT,
    TeamName varchar(255) NOT NULL,
    City varchar(150) NOT NULL,
    PRIMARY KEY (TeamID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci; 

CREATE TABLE Players (
  PlayerID int(10) NOT NULL AUTO_INCREMENT,
  TeamID int(10) NOT NULL,
  Position varchar(255) NOT NULL,
  FirstName varchar(255) NOT NULL,
  Surname varchar(255) NOT NULL,
  MarketValue int(10) DEFAULT NULL,
  Overall int(10) DEFAULT NULL,
  Age int(10),
  PRIMARY KEY (PlayerID),
  KEY `FK_team_id` (`TeamID`),
  CONSTRAINT `FK_team_id` FOREIGN KEY (`TeamID`) REFERENCES `Teams` (`TeamID`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE TeamStats (
	TeamStatsID int(10) NOT NULL AUTO_INCREMENT, 
	TeamID int(10) NOT NULL,
	Games int(10) DEFAULT NULL,
	Wins int(10) DEFAULT NULL,
	Loses int(10) DEFAULT NULL,
	Draws int(10) DEFAULT NULL,
	Points int(10) DEFAULT NULL,
	PRIMARY KEY (TeamStatsID),
	KEY `FK_team_id` (`TeamID`),
	FOREIGN KEY (`TeamID`) REFERENCES `Teams` (`TeamID`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE Coaches (
	CoachID int(10) NOT NULL AUTO_INCREMENT,
	TeamID int(10) NOT NULL,
	FirstName varchar(255) NOT NULL,
	Surname varchar(255) NOT NULL,
	Age int(10) NOT NULL,
	PRIMARY KEY (CoachID),
    FOREIGN KEY (`TeamID`) REFERENCES `Teams` (`TeamID`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE Games (
	GameID int(10) NOT NULL AUTO_INCREMENT,
	Host int(10) NOT NULL,
	Guest int(10) NOT NULL,
	HostGoals int(10) DEFAULT NULL,
	GuestGoals int(10) DEFAULT NULL,
	MatchDay DATETIME DEFAULT NULL,
	MatchStatus varchar(255) NOT NULL,
	PRIMARY KEY (GameID),
    FOREIGN KEY (`Guest`) REFERENCES `Teams` (`TeamID`) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (`Host`) REFERENCES `Teams` (`TeamID`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE Bets (
	BetID int(10) NOT NULL AUTO_INCREMENT,
	GameID int(10) NOT NULL,
	BetType int(10) NOT NULL,
	BettingOdd float(10) NOT NULL,
	Score varchar(255) DEFAULT NULL,
	PRIMARY KEY (BetID),
	FOREIGN KEY (`GameID`) REFERENCES `Games` (`GameID`) ON DELETE RESTRICT ON UPDATE CASCADE
)  ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE Accounts (
	Login varchar(255) NOT NULL,
    Password varchar(255) NOT NULL,
    PRIMARY KEY (Login)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE Clients (
	ClientID int(10) NOT NULL AUTO_INCREMENT,
    Login varchar(255) NOT NULL,
	FirstName varchar(255) NOT NULL,
	Surname varchar(255) NOT NULL,
	Age int(10) NOT NULL,
    Balance float(10) DEFAULT 0.00,
	PRIMARY KEY (ClientID),
    FOREIGN KEY (`Login`) REFERENCES `Accounts` (`Login`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE BookedBets (
	BookedBetID int(10) NOT NULL AUTO_INCREMENT,
	ClientID int(10) NOT NULL,
	BetID int(10) NOT NULL,
	Stake float(10) NOT NULL,
	PRIMARY KEY (BookedBetID),
    FOREIGN KEY (`ClientID`) REFERENCES `Clients` (`ClientID`) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (`BetID`) REFERENCES `Bets` (`BetID`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE Workers (
	WorkerID int(10) NOT NULL,
    Login varchar(255) NOT NULL,
	FirstName varchar(255) NOT NULL,
	Surname varchar(255) NOT NULL,
	PRIMARY KEY (WorkerID),
    FOREIGN KEY (`Login`) REFERENCES `Accounts` (`Login`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO Teams (TeamID, TeamName, City) VALUES (1, 'Liverpool', 'Liverpool');
INSERT INTO Teams  (TeamID, TeamName, City) VALUES (2, 'Manchester City', 'Manchester');
INSERT INTO Teams  (TeamID, TeamName, City) VALUES(3, 'Manchester United', 'Manchester');
INSERT INTO Teams  (TeamID, TeamName, City) VALUES (4, 'Chelsea', 'Londyn');
INSERT INTO Teams  (TeamID, TeamName, City) VALUES (5, 'Leicester City', 'Leicester');
INSERT INTO Teams  (TeamID, TeamName, City) VALUES(6, 'Tottenham Hotspur', 'Londyn');
INSERT INTO Teams  (TeamID, TeamName, City) VALUES (7, 'Wolverhampton Wanderers', 'Wolverhampton');
INSERT INTO Teams  (TeamID, TeamName, City) VALUES(8, 'Arsenal', 'Londyn');
INSERT INTO Teams  (TeamID, TeamName, City) VALUES (9, 'Sheffield United', 'Sheffield');
INSERT INTO Teams  (TeamID, TeamName, City) VALUES(10, 'Burnley', 'Burnley');
INSERT INTO Teams  (TeamID, TeamName, City) VALUES (11, 'Southampton', 'Southampton');
INSERT INTO Teams  (TeamID, TeamName, City) VALUES (12, 'Everton', 'Liverpool');
INSERT INTO Teams  (TeamID, TeamName, City) VALUES (13, 'Newcastle United', 'Newcastle upon Tyne');
INSERT INTO Teams  (TeamID, TeamName, City) VALUES (14, 'Crystal Palace', 'Londyn');
INSERT INTO Teams  (TeamID, TeamName, City) VALUES (15, 'Brighton & Hove Albion', 'Brighton');
INSERT INTO Teams  (TeamID, TeamName, City) VALUES (16, 'West Ham United', 'Londyn');
INSERT INTO Teams  (TeamID, TeamName, City) VALUES (17, 'Aston Villa', 'Birmingham');
INSERT INTO Teams  (TeamID, TeamName, City) VALUES (18, 'Leeds United', 'Leeds');
INSERT INTO Teams  (TeamID, TeamName, City) VALUES (19, 'West Bromwich Albion', 'West Bromwich');
INSERT INTO Teams  (TeamID, TeamName, City) VALUES (20, 'Fulham', 'Londyn');

INSERT INTO Coaches (TeamID, FirstName, Surname, Age) VALUES (1, 'Juergen', 'Klopp', 53);
INSERT INTO Coaches (TeamID, FirstName, Surname, Age) VALUES (2, 'Pep', 'Guardiola', 50);
INSERT INTO Coaches (TeamID, FirstName, Surname, Age) VALUES (3, 'Ole', 'Gunnar Solskjaer', 48);
INSERT INTO Coaches (TeamID, FirstName, Surname, Age) VALUES (4, 'Thomas', 'Tuchel', 47);
INSERT INTO Coaches (TeamID, FirstName, Surname, Age) VALUES (5, 'Brendan', 'Rodgers', 48);
INSERT INTO Coaches (TeamID, FirstName, Surname, Age) VALUES (6, 'Jose', 'Mourinho', 58);
INSERT INTO Coaches (TeamID, FirstName, Surname, Age) VALUES(7, 'Nuno', 'Espirito Santo', 47);
INSERT INTO Coaches (TeamID, FirstName, Surname, Age) VALUES (8, 'Mikel', 'Arteta', 39);
INSERT INTO Coaches (TeamID, FirstName, Surname, Age) VALUES (9, 'Paul', 'Heckingbottom', 43);
INSERT INTO Coaches (TeamID, FirstName, Surname, Age) VALUES (10, 'Sean', 'Dyche', 49);
INSERT INTO Coaches (TeamID, FirstName, Surname, Age) VALUES (11, 'Ralph', 'Hasenhuttl', 53);
INSERT INTO Coaches (TeamID, FirstName, Surname, Age) VALUES (12, 'Carlo', 'Ancelotti', 61);
INSERT INTO Coaches (TeamID, FirstName, Surname, Age) VALUES (13, 'Steve', 'Bruce', 60);
INSERT INTO Coaches (TeamID, FirstName, Surname, Age) VALUES (14, 'Roy', 'Hodgson', 73);
INSERT INTO Coaches (TeamID, FirstName, Surname, Age) VALUES (15, 'Graham', 'Potter', 45);
INSERT INTO Coaches (TeamID, FirstName, Surname, Age) VALUES (16, 'David', 'Moyes', 57);
INSERT INTO Coaches (TeamID, FirstName, Surname, Age) VALUES (17, 'Dean', 'Smith', 50);
INSERT INTO Coaches (TeamID, FirstName, Surname, Age) VALUES (18, 'Marcelo', 'Bielsa', 65);
INSERT INTO Coaches (TeamID, FirstName, Surname, Age) VALUES (19, 'Sam', 'Allardyce', 66);
INSERT INTO Coaches (TeamID, FirstName, Surname, Age) VALUES (20, 'Scott', 'Parker', 40);
