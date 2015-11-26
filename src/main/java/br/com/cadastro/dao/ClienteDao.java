package br.com.cadastro.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.cadastro.model.Cliente;
import br.com.cadastro.model.TipoBusca;
import br.com.tdvframework.data.TdvCommand;
import br.com.tdvframework.data.TdvCommandFactory;
import br.com.tdvframework.data.TdvCommandParam;
import br.com.tdvframework.data.TdvConnectionFactory;
import br.com.tdvframework.data.TdvParamDirection;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.internal.OracleTypes;

public class ClienteDao {
	
	public String listaCliente(List<Cliente> listaClienteOut) throws Exception{
		
      ResultSet resultSet = null;
		 
	  //List<Cliente> listaCliente = new ArrayList<Cliente>();
	  
	  TdvConnectionFactory confac = new TdvConnectionFactory();
	  
	  OracleConnection connection = confac.getConnection("192.168.9.15", "1521", "tdp", "tdvadm", "aged12");
	  
	  System.out.println(connection);
	  
      TdvCommand command = new TdvCommandFactory(connection);
      
      List<TdvCommandParam> listapar = new ArrayList<TdvCommandParam>();
      
      listapar.add(new TdvCommandParam("pCursor",OracleTypes.CURSOR, TdvParamDirection.Out));
      listapar.add(new TdvCommandParam("pStatus",OracleTypes.CHAR, TdvParamDirection.Out));
      listapar.add(new TdvCommandParam("pMessage",OracleTypes.VARCHAR, TdvParamDirection.Out));
      
      Map<String, Object> executeProc = command.executeProc("tdvadm.pkg_glb_teste.sp_tmp_listacliente", listapar);
      
      
      
      if (!executeProc.get("pStatus").toString().equalsIgnoreCase("N")){
    	  
    	 throw new RuntimeException(executeProc.get("pMessage").toString());
    	  
      }
	  
      resultSet = (ResultSet) executeProc.get("pCursor");
      
      while (resultSet.next()){
    	  
    	  listaClienteOut.add(new Cliente(resultSet.getString("ID_CLIENTE"), resultSet.getString("NOME"),resultSet.getString("CPF"),resultSet.getString("PESO"),resultSet.getString("ALTURA"))); 
    	  
      }
      
      //listaClienteOut = listaCliente;
      
      return executeProc.get("pMessage").toString();
		
	}
	
    public Map<String, String> cadastraAlteraCliente(Cliente cliente) throws Exception{
    	
    	
    	System.out.println("Id do Cliente.: "+cliente.getId());
    	
    	Map<String, String> retorno = new HashMap<String, String>();
    	
    	TdvConnectionFactory confac = new TdvConnectionFactory();
   	 
   	    OracleConnection connection = confac.getConnection("192.168.9.15", "1521", "tdp", "tdvadm", "aged12");
   	  
   	    System.out.println(connection);
   	  
        TdvCommand command = new TdvCommandFactory(connection);
         
        List<TdvCommandParam> listapar = new ArrayList<TdvCommandParam>();
         
		String id = cliente.getId() == "" ? "0" : cliente.getId();
        
        listapar.add(new TdvCommandParam("pId",OracleTypes.NUMBER, id, TdvParamDirection.In));

        listapar.add(new TdvCommandParam("pNome",OracleTypes.VARCHAR, cliente.getNome(), TdvParamDirection.In));
        listapar.add(new TdvCommandParam("pCpf",OracleTypes.VARCHAR, cliente.getCpf(), TdvParamDirection.In));
        listapar.add(new TdvCommandParam("pPeso",OracleTypes.VARCHAR, cliente.getPeso(), TdvParamDirection.In));
        listapar.add(new TdvCommandParam("pAltura",OracleTypes.VARCHAR, cliente.getAltura(), TdvParamDirection.In));
        
        listapar.add(new TdvCommandParam("pStatus",OracleTypes.CHAR, TdvParamDirection.Out));
        listapar.add(new TdvCommandParam("pMessage",OracleTypes.VARCHAR, TdvParamDirection.Out));
         
        System.out.println("Antes de exucutar");
        Map<String, Object> executeProc = command.executeProc("tdvadm.pkg_glb_teste.Sp_Tmp_IncluirAlterarCliente", listapar);
        System.out.println("Despois de executar"); 
        
        System.out.println("pStatus.: "+executeProc.get("pStatus").toString());
         
        if (executeProc.get("pStatus").toString().equalsIgnoreCase("E")){
       	  
       	throw new RuntimeException(executeProc.get("pMessage").toString());
       	  
        }
   	  
        retorno.put("status",executeProc.get("pStatus").toString());
        retorno.put("message",executeProc.get("pMessage").toString());
        
        
        return retorno;
		
	}
	
    public String excluirCliente(Cliente cliente) throws Exception{
    	
    	
   	    TdvConnectionFactory confac = new TdvConnectionFactory();
   	 
   	    OracleConnection connection = confac.getConnection("192.168.9.15", "1521", "tdp", "tdvadm", "aged12");
   	  
   	    System.out.println(connection);
   	  
        TdvCommand command = new TdvCommandFactory(connection);
         
        List<TdvCommandParam> listapar = new ArrayList<TdvCommandParam>();
         
        listapar.add(new TdvCommandParam("pId",OracleTypes.NUMBER, cliente.getId(), TdvParamDirection.In));
        
        listapar.add(new TdvCommandParam("pStatus",OracleTypes.CHAR, TdvParamDirection.Out));
        listapar.add(new TdvCommandParam("pMessage",OracleTypes.VARCHAR, TdvParamDirection.Out));
         
        Map<String, Object> executeProc = command.executeProc("tdvadm.pkg_glb_teste.Sp_Tmp_ExcluiCliente", listapar);
         
         
        if (!executeProc.get("pStatus").toString().equalsIgnoreCase("N")){
       	  
       	throw new RuntimeException(executeProc.get("pMessage").toString());
       	  
        }
   	  
        return executeProc.get("pMessage").toString();
		
	}
    
    public String buscaPorId(String id, Cliente cliente) throws Exception{
    	
		
    	ResultSet resultSet = null;
    	
    	TdvConnectionFactory confac = new TdvConnectionFactory();

		OracleConnection connection = confac.getConnection("192.168.9.15", "1521", "tdp", "tdvadm", "aged12");

		System.out.println(connection);

		TdvCommand command = new TdvCommandFactory(connection);

		List<TdvCommandParam> listapar = new ArrayList<TdvCommandParam>();

		
		System.out.println("Id do cliente a ser consultado.: "+id);
		
		listapar.add(new TdvCommandParam("pId", OracleTypes.NUMBER, id, TdvParamDirection.In));
		listapar.add(new TdvCommandParam("pCursor",OracleTypes.CURSOR, TdvParamDirection.Out));
		listapar.add(new TdvCommandParam("pStatus", OracleTypes.CHAR, TdvParamDirection.Out));
		listapar.add(new TdvCommandParam("pMessage", OracleTypes.VARCHAR, TdvParamDirection.Out));

		Map<String, Object> executeProc = command.executeProc("tdvadm.pkg_glb_teste.Sp_Tmp_ListaClienteId", listapar);

		if (!executeProc.get("pStatus").toString().equalsIgnoreCase("N")) {

			throw new RuntimeException(executeProc.get("pMessage").toString());

		}
		
		resultSet = (ResultSet) executeProc.get("pCursor");
	      
	    while (resultSet.next()){
	    	  
	    	 cliente.setId(resultSet.getString("ID_CLIENTE")); 
	    	 cliente.setNome(resultSet.getString("NOME"));
	    	 cliente.setCpf(resultSet.getString("CPF"));
	    	 cliente.setPeso(resultSet.getString("PESO"));
	    	 cliente.setAltura(resultSet.getString("ALTURA"));
	    			 	    	  
	    }

		return executeProc.get("pMessage").toString();
    	
    }
    
	public String buscaTipos(List<TipoBusca> tipobusca) throws Exception{
		
		ResultSet resultSet = null;
		
		List<TipoBusca> retorno = new ArrayList<TipoBusca>();

		TdvConnectionFactory confac = new TdvConnectionFactory();

		OracleConnection connection = confac.getConnection("192.168.9.15", "1521", "tdp", "tdvadm", "aged12");

		System.out.println(connection);

		TdvCommand command = new TdvCommandFactory(connection);

		List<TdvCommandParam> listapar = new ArrayList<TdvCommandParam>();

		listapar.add(new TdvCommandParam("pCursor", OracleTypes.CURSOR, TdvParamDirection.Out));
		listapar.add(new TdvCommandParam("pStatus", OracleTypes.CHAR, TdvParamDirection.Out));
		listapar.add(new TdvCommandParam("pMessage", OracleTypes.VARCHAR, TdvParamDirection.Out));

		Map<String, Object> executeProc = command.executeProc("tdvadm.pkg_glb_teste.sp_tmp_listaTipoBusca", listapar);

		if (!executeProc.get("pStatus").toString().equalsIgnoreCase("N")) {

			throw new RuntimeException(executeProc.get("pMessage").toString());

		}

		resultSet = (ResultSet) executeProc.get("pCursor");

		while (resultSet.next()) {

			tipobusca.add(new TipoBusca(resultSet.getString("CODIGO"), resultSet.getString("DESCRICAO")));

		}	
		
		 return executeProc.get("pMessage").toString();
		
		
	}
    
	public String listaClienteFiltrado(List<Cliente> listaClienteOut, String cod, String busca) throws Exception{
		
	      ResultSet resultSet = null;
		  
		  TdvConnectionFactory confac = new TdvConnectionFactory();
		  
		  OracleConnection connection = confac.getConnection("192.168.9.15", "1521", "tdp", "tdvadm", "aged12");
		  
		  System.out.println(connection);
		  
	      TdvCommand command = new TdvCommandFactory(connection);
	      
	      List<TdvCommandParam> listapar = new ArrayList<TdvCommandParam>();
	      
	      listapar.add(new TdvCommandParam("pIdbusca", OracleTypes.VARCHAR, cod, TdvParamDirection.In));
	      listapar.add(new TdvCommandParam("pFiltro", OracleTypes.VARCHAR, busca, TdvParamDirection.In));
	      
	      listapar.add(new TdvCommandParam("pCursor",OracleTypes.CURSOR, TdvParamDirection.Out));
	      listapar.add(new TdvCommandParam("pStatus",OracleTypes.CHAR, TdvParamDirection.Out));
	      listapar.add(new TdvCommandParam("pMessage",OracleTypes.VARCHAR, TdvParamDirection.Out));
	      
	      Map<String, Object> executeProc = command.executeProc("tdvadm.pkg_glb_teste.Sp_Tmp_ListaClienteFiltrado", listapar);
	      
	      
	      
	      if (!executeProc.get("pStatus").toString().equalsIgnoreCase("N")){
	    	  
	    	 throw new RuntimeException(executeProc.get("pMessage").toString());
	    	  
	      }
		  
	      resultSet = (ResultSet) executeProc.get("pCursor");
	      
	      while (resultSet.next()){
	    	  
	    	  listaClienteOut.add(new Cliente(resultSet.getString("ID_CLIENTE"), resultSet.getString("NOME"),resultSet.getString("CPF"),resultSet.getString("PESO"),resultSet.getString("ALTURA"))); 
	    	  
	      }
	      
	      return executeProc.get("pMessage").toString();
			
		}
	
    public static void main(String[] args) throws Exception {
		
		//List<Cliente> listaCliente = new ArrayList<>();
		//String message = new ClienteDao().listaCliente(listaCliente);

		//System.out.println( "message = "+message );
		
		//for (Cliente cliente : listaCliente) {
			
		//	System.out.println(cliente);
			
		//}
		
		List<TipoBusca> tipos = new ArrayList<TipoBusca>();
		
		String message = new ClienteDao().buscaTipos(tipos);

		System.out.println( "message = "+ message );
		
	}
	
}
