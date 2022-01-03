package entities;

public class AlunoEntity extends BaseEntity {

	private static final long serialVersionUID = 8578662769938479960L;
	
	private Long id;
	private String nome;
	private String email;
	private TurmaEntity turma;
	
	public AlunoEntity() {
	}

	public AlunoEntity(Long id, String nome, String email, TurmaEntity turma) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.turma = turma;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TurmaEntity getTurma() {
		return turma;
	}

	public void setTurma(TurmaEntity turma) {
		this.turma = turma;
	}
	
}
