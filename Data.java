public class Data {
	
	private short ano;
	private byte mes;
	private byte dia;
	
	public Data() {
		ano = 1;
		mes = 1;
		dia = 1;
	}

	public Data(int dia, int mes, int ano) {
		setAno((short) ano);
		setMes((byte) mes);
		setDia((byte) dia);
	}

	public short getAno() {
		return ano;
	}

	public void setAno(short ano) {
		if(!(ano > 0 && ano <= ultimoAno))
			throw new IllegalArgumentException("Ano inválido!");
		this.ano = ano;
	}

	public byte getMes() {
		return mes;
	}

	public void setMes(byte mes) {
		if(!(mes > 0 && mes <= ultimoMes))
			throw new IllegalArgumentException("Mês inválido!");
		this.mes = mes;
	}

	public byte getDia() {
		return dia;
	}

	public void setDia(byte dia) {
		if(!(dia > 0 && dia <= 31))
			throw new IllegalArgumentException("Dia inválido!");
		byte ultimoDiaMes = getUltimoDiaMes(mes, ano);
		if(dia > ultimoDiaMes)
			throw new IllegalArgumentException("Dia inválido! Esse mês tem "+ultimoDiaMes+" dias.");
		this.dia = dia;
	}

	private byte getUltimoDiaMes(byte mes, short ano) {
		byte ultimoDiaMes = 0;
		switch(mes) {
		case 2:
			if(verificarBissexto(ano))
				ultimoDiaMes = 29;
			else
				ultimoDiaMes = 28;
			break;
		case 1: case 3: case 5: case 7: case 8: case 10: case 12:
			ultimoDiaMes = 31;
			break;
		case 4: case 6: case 9: case 11:
			ultimoDiaMes = 30;
			break;
		}
		return ultimoDiaMes;
	}
	
	private boolean verificarBissexto(short ano) {
		if(ano % 4 == 0) {
			if(ano % 100 == 0) {
				return false;
			}
		}
		else {
			if(ano % 400 != 0)
				return false;
		}
		return true;	
	}
	
	public void incrementarAno() {
		incrementarAno(1);
	}
	
	public void incrementarAno(int quantidadeAnos) {
		byte dia = this.dia;
		short ano = this.ano;

		for(int i = 0; i < quantidadeAnos; i++) {
			ano = (short) (ano % 9999 + 1);
		}

		byte ultimoDiaMes = getUltimoDiaMes(mes, ano);
		if(mes == 2) {
			if(dia > ultimoDiaMes)
	        	dia = (byte) (dia % ultimoDiaMes);
		}

		this.dia = dia;
		this.ano = ano;
	}

	public void incrementarMes() {
		incrementarMes(1);
	}
	
	public void incrementarMes(int quantidadeMeses) {
		byte dia = this.dia;
		byte mes = this.mes;

		for(int i = 0; i < quantidadeMeses; i++) {
			mes = (byte) (mes % 12 + 1);
			if(mes == 1)
				incrementarAno(1);
		}

		byte ultimoDiaMes = getUltimoDiaMes(mes, ano);
		if(dia > ultimoDiaMes)      
        	dia = (byte) (dia % ultimoDiaMes);

		this.dia = dia;
		this.mes = mes;
	}

	public void incrementarDia() {
		incrementarDia(1);
	}
	
	public void incrementarDia(int quantidadeDias) {
		byte dia = this.dia;
		byte ultimoDiaMes = getUltimoDiaMes(mes, ano);

		for(int i = 0; i < quantidadeDias; i++) {
			dia = (byte) (dia % ultimoDiaMes + 1);
			if(dia == 1) {
				incrementarMes(1);
				ultimoDiaMes = getUltimoDiaMes(mes, ano);
			}							
		}

		this.dia = dia;
	}
	
	@Override
	public String toString() {
		return String.format("%02d/%02d/%04d", dia, mes, ano);
	}
	
}