SELECT CoachID, CONCAT(FirstName,' ',Surname) AS Name, t.TeamName AS Club, Age
FROM ezdollazbet.coaches c
JOIN ezdollazbet.teams t
	ON c.TeamID = t.TeamID