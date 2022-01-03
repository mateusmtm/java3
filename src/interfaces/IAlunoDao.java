package interfaces;

import java.util.List;

import entities.AlunoEntity;

public interface IAlunoDao {
	
	public void insert(AlunoEntity aluno);
	
	public List<AlunoEntity> findALl();
	
	public AlunoEntity findByID(Long id);

	public void update(AlunoEntity aluno);

	public void delete(AlunoEntity aluno);
	
}