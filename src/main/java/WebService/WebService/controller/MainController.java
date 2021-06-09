package WebService.WebService.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import WebService.WebService.utils.WebUtils;
 
@RestController
public class MainController {
 
	
	
	/*
	 * @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	 * public String welcomePage(Model model) { model.addAttribute("title",
	 * "Bem-vindo"); model.addAttribute("message", "Página inicial!"); return
	 * "welcomePage"; }
	 */
	  
	  @GetMapping("/userAccountInfo") 
	  public User Page(Principal principal) {
	  
	  User loginedUser = (User) ((Authentication) principal).getPrincipal();
	  
		/*
		 * String userInfo = WebUtils.toString(loginedUser);
		 * model.addAttribute("userInfo", userInfo);
		 */
	  
	  return loginedUser; 
	  }
	 
	  
	
	  @GetMapping("/login") 
	  public String loginPage(Model model) {
	  
	  return "Apenas administrador pode realizar essa ação"; 
	  }
	  
	  @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
	  public String logoutSuccessfulPage() { 
		  return "logout Successful"; 
		  }
	  
	/*
	 * @RequestMapping(value = "/userInfo", method = RequestMethod.GET) public
	 * String userInfo(Model model, Principal principal) {
	 * 
	 * // After user login successfully. String userName = principal.getName();
	 * 
	 * System.out.println("User Name: " + userName);
	 * 
	 * User loginedUser = (User) ((Authentication) principal).getPrincipal();
	 * 
	 * String userInfo = WebUtils.toString(loginedUser);
	 * model.addAttribute("userInfo", userInfo);
	 * 
	 * return "userInfoPage"; }
	 */
 
	
	  @RequestMapping(value = "/403") 
	  public String accessDenied(Principal principal) {
		  String message = "";
		  if (principal != null) { 
			  User loginedUser = (User) ((Authentication) principal).getPrincipal();
	  
			  String userInfo = WebUtils.toString(loginedUser);	  
			  message = "Olá " + principal.getName()+ ", você não tem permissão para realizar essa ação!";  
	  		}	  
	  return message; 
	  }
}
