SELECT CONCAT(Host,' - ',Guest) AS 'Match', CONCAT(HostGoals,':',GuestGoals) AS Result, MatchDay AS 'Date', MatchStatus AS 'Status'
FROM ezdollazbet.games
WHERE MatchStatus = 'trwa' OR MatchStatus = 'zakonczony' -- czy cos takiego