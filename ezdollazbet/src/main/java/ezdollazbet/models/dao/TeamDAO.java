package ezdollazbet.models.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import ezdollazbet.models.Team;

public class TeamDAO {
	public static Team getTeamById(int id) throws SQLException {
		String statement = "SELECT * FROM teams Where TeamID ="  + id;
		ResultSet set = DBUtil.selectQuery(statement);
		Team team = new Team();
		while(set.next()) {
			team.setTeamId(set.getInt("TeamID"));
			team.setTeamName(set.getString("TeamName"));
			team.setCity(set.getString("City"));
		}
		
	return team;
	}
}
