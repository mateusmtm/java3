package interfaces;

import java.util.List;

import entities.TurmaEntity;

public interface ITurmaDao {
	
	public void insert(TurmaEntity turma);
	
	public List<TurmaEntity> findALl();
	
	public TurmaEntity findByID(Long id);

	public void update(TurmaEntity turma);

	public void delete(TurmaEntity turma);
	
}