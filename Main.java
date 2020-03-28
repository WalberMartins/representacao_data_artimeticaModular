import java.util.concurrent.TimeUnit;

class Main {

  public static void main(String[] args) {
  	Data data = new Data(31, 1, 9900);

//	data.incrementarDia();
//	data.incrementarMes();
	data.incrementarAno(120);
		
	System.out.println(data);
  }

}