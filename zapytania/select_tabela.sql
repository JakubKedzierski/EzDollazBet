SELECT TeamName AS Club, ts.Games AS Played, ts.Wins AS Won, ts.Draws AS Drawn, ts.Loses AS Lost, ts.Points
FROM ezdollazbet.teams t
JOIN ezdollazbet.teamstats ts
	ON t.TeamID = ts.TeamID
-- WHERE t.TeamID = 1
ORDER BY ts.Points DESC