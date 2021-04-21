SELECT g.MatchDay AS Date, CONCAT(g.Host,' - ',g.Guest) AS 'Match', BetType AS 'Type', BettingOdd AS Odd
FROM ezdollazbet.bets b
JOIN ezdollazbet.games g
	ON b.GameID = g.GameID
WHERE g.MatchStatus = 'nierozpoczety' -- czy cos takiego
	-- AND b.GameID = 1 -- dla konkretnego meczu