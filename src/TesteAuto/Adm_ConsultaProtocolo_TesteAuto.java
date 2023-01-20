package TesteAuto;




import org.junit.After;  
import org.junit.AfterClass;  
import org.junit.Before;  
import org.junit.BeforeClass;  
import org.junit.Test;

import Pagina.Adm_ConsultaProtocolo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Adm_ConsultaProtocolo_TesteAuto {  
  
    @BeforeClass  
    public static void setUpBeforeClass() throws Exception {  
        System.out.println("Antes de Iniciar o objeto");  
    }  
    @Before  
    public void setUp() throws Exception {  
        System.out.println("Antes de Iniciar a classe");  
    }  
  
    @Test  
    public void inicioTeste() throws IOException{  
        System.out.println("Classe: inicioTeste");  
        
        String test_url = "https://online.saovicente.sp.gov.br/pmsaovicente/websis/siapegov/administrativo/aten/aten_valida.php?consulta";
       // test_url = "https://online.saovicente.sp.gov.br/pmsaovicente/websis/siapegov/administrativo/gpro/protocolo_consulta.php";
       
        test_url = "REGISTRO";
        
        String[] arrNumProt = getNumProt();
        String[] arrDtaRef = getDtaRef();
        
        for (int  i=0;i<arrNumProt.length ; i++) {
			String test_url_ret = Adm_ConsultaProtocolo.Adm_ConsultaProtocolo_Registro(arrNumProt[i],arrDtaRef[i]);
			
     //   	String test_url_ret = "https://online.saovicente.sp.gov.br/pmsaovicente/websis/siapegov/administrativo/aten/aten_valida.php?consulta";;
        	GravarLog(arrNumProt[i],arrDtaRef[i], test_url_ret);
			System.out.println("Nmro Prot = " + arrNumProt[i] + " Data = " + arrDtaRef[i]);
	//		assertEquals(test_url,test_url_ret);
			
		}
        
      
     
    
       
      
    }  
    
	  
    @After  
    public void tearDown() throws Exception {  
        System.out.println("Depois de Finalizar a classe");  
    }  
  
    @AfterClass  
    public static void tearDownAfterClass() throws Exception {  
        System.out.println("Depois de finalizar o objeto");  
    }  
    private String[] getNumProt()
    {
    	String[] retNumProt={"0000054733","00042985", "0000025851","42985",
    			"25851",
    			"25854",
    			"23529",
    			"19812",
    			"19275",
    			"15866",
    			"16786",
    			"17898",
    			"15867",
    			"15868",
    			"49792",
    			"9730",
    			"52708",
    			"52051",
    			"52706",
    			"52707",
    			"53070",
    			"2424",
    			"52709",
    			"52710"} ;
    	
    	
    	return retNumProt;
    }
    private String[] getDtaRef()
    {
    	String[] retDtaRef= {"06/12/2022","14092022","01/06/2022","14.09.2022",
    			"01.06.2022",
    			"01.06.2022",
    			"19.05.2022",
    			"26.04.2022",
    			"25.04.2022",
    			"29.03.2022",
    			"04.04.2022",
    			"11.04.2022",
    			"29.03.2022",
    			"29.03.2022",
    			"03.11.2022",
    			"17.02.2022",
    			"22.11.2022",
    			"17.11.2022",
    			"22.11.2022",
    			"22.11.2022",
    			"23.11.2022",
    			"01.01.2000",
    			"22.11.2022",
    			"22.11.2022"};
    	
    	
    	return retDtaRef;
    }
    private  void GravarLog(String nr_Prot,String dt_Data,String sttRet) throws IOException
    {
        System.out.println(Adm_ConsultaProtocolo_TesteAuto.class.getSimpleName().toString()); 
    	
    	DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
    	
    	String strDataAtual =  dateFormat.format(Calendar.getInstance().getTime()).toString() ;
    	
    	
    	String folderName = "C:\\Users\\luizperez\\Documents\\Demandas Kelly\\Teste\\" + Adm_ConsultaProtocolo_TesteAuto.class.getSimpleName().toString() + "\\";
    	
    	String fileName = folderName + "logTeste" + strDataAtual + ".txt";
    	
    	System.out.println(fileName);
    	
    	String linhaLog;
    	
    	Path path = Paths.get(folderName);
		
		if(!Files.exists(path)) {
			
			Files.createDirectory(path);
			
		}
		
		File log = new File(fileName);
		
		if(!log.exists()) {
			
			log.createNewFile();
		
		}
        
    	if(sttRet == "REGISTRO")
    	{
    		linhaLog = "INFO:  Número do Protocolo : " + nr_Prot + " Data da Atuação : " + dt_Data;
    	}
    	else
    	{
    		linhaLog = "===================================> ERRO:  Número do Protocolo : " + nr_Prot + " Data da Atuação : " + dt_Data + " <=============================";
    	}
    	
    	FileWriter fw = new FileWriter(log, true);
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write(linhaLog);
		bw.newLine();

		bw.close();
		fw.close();
        
    }
  
}  
