package br.com.cadastro;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cadastro.dao.ClienteDao;
import br.com.cadastro.model.Cliente;
import br.com.cadastro.model.TipoBusca;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(HttpSession session) {
		
		ModelAndView mv = new ModelAndView("home");

		List<TipoBusca> listatipobusca = new ArrayList<>();

		try {

			String message = new ClienteDao().buscaTipos(listatipobusca);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

		String msg = "Pagina Home executada com sucesso!";
		System.out.println(msg);

		mv.addObject("msg", msg);
		
		
		session.setAttribute("listatipobusca", listatipobusca);

		return mv;
	}
	
	@RequestMapping(value = "/addCliente", method = RequestMethod.POST)
	public String addCliente(Cliente cliente, Model model) {
		
		
		
		System.out.println(cliente.toString());
		
		try {
			
			
			Map<String, String> retorno = new ClienteDao().cadastraAlteraCliente(cliente);
			
			
			if (!retorno.get("status").equalsIgnoreCase("N")){
				
				model.addAttribute("msg", retorno.get("message"));
				
				return "home";
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage()); 
		}
		
		
		return "redirect:/listarCliente";
	}
	
	@RequestMapping(value = "/listarCliente", method = RequestMethod.GET)
	public ModelAndView listarCliente() {
		
		ModelAndView mv = new ModelAndView("home");
		
		List<Cliente> listaCliente = new ArrayList<>();
		
		try {
			
			String message = new ClienteDao().listaCliente(listaCliente);

			mv.addObject("listaCliente",listaCliente);
			mv.addObject("msg", message);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage()); 
		}
		
		return mv;
	}

	@RequestMapping(value = "/editarCliente", method = RequestMethod.GET)
	public ModelAndView editarCliente(String id) {
		
		ModelAndView mv = new ModelAndView("novocliente");

		try {

			
			System.out.println("Id do cliente camada Controller.editarCliente.: "+ id);
			Cliente cliente = new Cliente();
			String message = new ClienteDao().buscaPorId(id, cliente );

			mv.addObject("msg", message);
			mv.addObject("cliente", cliente);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

		return mv;
	}
	
	@RequestMapping(value = "/deletarCliente", method = RequestMethod.GET)
	public String deletarCliente(Cliente cliente, Model model) {

		try {

			String message = new ClienteDao().excluirCliente(cliente);

			model.addAttribute("msg", message);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

		return "redirect:/listarCliente";
	}
	
	@RequestMapping(value = "/listarCliFiltrado", method = RequestMethod.GET)
	public ModelAndView listarCliFiltrado(String filtro, String busca) {

		System.out.println("codigo:"+ filtro+ " busca:"+ busca);
		
		ModelAndView mv = new ModelAndView("home");

		List<Cliente> listaCliente = new ArrayList<>();

		try {

			String message = new ClienteDao().listaClienteFiltrado(listaCliente, filtro, busca);

			mv.addObject("listaCliente", listaCliente);
			mv.addObject("msg", message);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

		return mv;
	}
		
	@RequestMapping(value = "/novoCliente", method = RequestMethod.GET)
	public ModelAndView novoCliente() {
		
		ModelAndView mv = new ModelAndView("novocliente");

		try {

			Cliente cliente = new Cliente();
			cliente.setId("0");
			String message = "Novo cliente solicitado.";

			mv.addObject("msg", message);
			mv.addObject("cliente", cliente);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

		return mv;
	}
}
