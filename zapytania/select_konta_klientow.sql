SELECT ClientID, Surname, a.Login, a.Password
FROM ezdollazbet.clients c
JOIN ezdollazbet.accounts a
	ON c.Login = a.Login
ORDER BY ClientID