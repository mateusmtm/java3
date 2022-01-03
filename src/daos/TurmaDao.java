package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import entities.TurmaEntity;
import factories.ConnectionFactory;
import interfaces.ITurmaDao;

public class TurmaDao implements ITurmaDao {

private final Logger logger;
	
	private static final String INSERT_QUERY = "INSERT INTO tb_turmas(turma_descricao) VALUES(?)";
	private static final String FIND_ALl_QUERY = "SELECT * FROM tb_turmas";
	private static final String FIND_BY_ID_QUERY = "SELECT * FROM tb_turmas WHERE turma_id = ?";
	private static final String UPDATE_QUERY = "UPDATE tb_turmas SET turma_descricao = ? WHERE turma_id = ?";
	private static final String DELETE_QUERY = "DELETE FROM tb_turmas WHERE turma_id = ?";
	
	public TurmaDao() {
		this.logger = Logger.getLogger(getClass());
	}
	
	@Override
	public void insert(TurmaEntity turma) {
		Connection conn;
		PreparedStatement ps;
		try {
			logger.info("Saving record...");
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(INSERT_QUERY);
			ps.setString(1, turma.getDescricao().trim().toUpperCase());
			ps.executeUpdate();
			logger.info(String.format("QUERY: %s", INSERT_QUERY));
			logger.info("Record successfully saved.");
		} catch(Exception e) {
			logger.error(e);
		}
	}
	
	@Override
	public List<TurmaEntity> findALl() {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		List<TurmaEntity> turmas = null;
		try {
			logger.info("Searching records...");
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(FIND_ALl_QUERY);
			rs = ps.executeQuery();
			logger.info(String.format("QUERY: %s", FIND_ALl_QUERY));
			if(rs.first())  {
				turmas = new ArrayList<TurmaEntity>();
				do {
					TurmaEntity turma = new TurmaEntity();
					turma.setId(rs.getLong("turma_id"));
					turma.setDescricao(rs.getString("turma_descricao"));
					turmas.add(turma);
				} while(rs.next());
			}
			logger.info("Search successfully done.");
		} catch(Exception e) {
			logger.error(e);
		}
		return turmas;
	}
	
	@Override
	public TurmaEntity findByID(Long id) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		TurmaEntity turma = null;
		try {
			logger.info("Searching records...");
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(FIND_BY_ID_QUERY);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			logger.info(String.format("QUERY: %s", FIND_BY_ID_QUERY));
			if(rs.first())  {
				turma = new TurmaEntity();
				turma.setId(rs.getLong("turma_id"));
				turma.setDescricao(rs.getString("turma_descricao"));
			}
			logger.info("Search successfully done.");
		} catch(Exception e) {
			logger.error(e);
		}
		return turma;
	}
	
	@Override
	public void update(TurmaEntity turma) {
		Connection conn;
		PreparedStatement ps;
		try {
			logger.info("Updating record...");
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(UPDATE_QUERY);
			ps.setString(1, turma.getDescricao().trim().toUpperCase());
			ps.setLong(2, turma.getId());
			ps.executeUpdate();
			logger.info(String.format("QUERY: %s", UPDATE_QUERY));
			logger.info("Record successfully updated.");
		} catch(Exception e) {
			logger.error(e);
		}
	}
	
	@Override
	public void delete(TurmaEntity turma) {
		Connection conn;
		PreparedStatement ps;
		try {
			logger.info("Deleting record...");
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(DELETE_QUERY);
			ps.setLong(1, turma.getId());
			ps.executeUpdate();
			logger.info(String.format("QUERY: %s", DELETE_QUERY));
			logger.info("Record successfully deleted.");
		} catch(Exception e) {
			logger.error(e);
		}
	}
}
