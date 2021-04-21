SELECT WorkerID, Surname, a.Login, a.Password
FROM ezdollazbet.workers w
JOIN ezdollazbet.accounts a
	ON w.Login = a.Login
ORDER BY WorkerID
