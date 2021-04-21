SELECT PlayerID, t.TeamName AS Club, Position, CONCAT(FirstName,' ',Surname) AS Name, MarketValue AS 'Market value', Overall, Age
FROM ezdollazbet.players p
JOIN ezdollazbet.teams t
	ON p.TeamID = t.TeamID
 WHERE t.TeamID = 1
 ORDER BY Position