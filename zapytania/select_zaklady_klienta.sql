SELECT BetID, Stake
FROM ezdollazbet.bookedbets b
JOIN ezdollazbet.clients c
	ON b.ClientID = c.ClientID
WHERE b.ClientID = 1