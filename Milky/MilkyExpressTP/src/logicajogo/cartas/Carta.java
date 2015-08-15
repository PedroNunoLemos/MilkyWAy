package logicajogo.cartas;

public abstract class Carta {
	
	protected String nome;

	public String getNome() {
		return nome;
	}

	public boolean validaNome(String nome){
		
		int res =0;
		
		res=nome.trim().compareToIgnoreCase(this.nome.trim());
		
		return (res==0);
	}
	
}
