package ezdollazbet.models.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import ezdollazbet.models.Team;

public class TeamDAO {
	public static Team getTeamById(int id) throws SQLException {
		String statement = "SELECT * FROM teams Where TeamID = ? ";
		Map<Integer,String> arguments = new HashMap<Integer,String>();
		arguments.put(1,Integer.toString(id));
		ResultSet set = DBUtil.safeSelectQuery(statement, arguments);
		Team team = new Team();
		while(set.next()) {
			team.setTeamId(set.getInt("TeamID"));
			team.setTeamName(set.getString("TeamName"));
			team.setCity(set.getString("City"));
		}
		
	return team;
	}
}
