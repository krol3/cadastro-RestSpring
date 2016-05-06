package cadastro.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cadastro.web.Gizmo;
import cadastro.web.GizmoChild;

@Controller
public class CadastroController {

	@RequestMapping("/cadastro")
	public String index(Model model) {
		// return "Cadastrar usuario from Spring Boot!";

		Gizmo gizmo = new Gizmo();
		gizmo.getChildren().add(new GizmoChild());
		model.addAttribute("gizmo", gizmo);
		return "index";
	}

	@RequestMapping("/cadastro/save")
	public String save(Gizmo gizmo) {
		System.out.println(gizmo.getField1());
		System.out.println(gizmo.getField2());
		for (GizmoChild child : gizmo.getChildren()) {
			System.out.println(child.getChildField1());
			System.out.println(child.getChildField2());
		}
		return "redirect:/cadastro";
	}
}
