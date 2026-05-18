package com.example.tunisartisan.Controllers;

import com.example.tunisartisan.Entities.Service;
import com.example.tunisartisan.Services.ServiceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/services")
public class ServiceController {

	private final ServiceService serviceService;

	public ServiceController(ServiceService serviceService) {
		this.serviceService = serviceService;
	}

	@GetMapping
	public String index(Model model) {
		model.addAttribute("services", serviceService.getAllServices());
		return "service/service";
	}

	@GetMapping("/new")
	@PreAuthorize("hasRole('ADMIN') or hasRole('ARTISAN')")
	public String createForm(Model model) {
		model.addAttribute("service", new Service());
		return "service/create";
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN') or hasRole('ARTISAN')")
	public String create(Service service, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "service/create";
		}
		serviceService.saveService(service);
		redirectAttributes.addFlashAttribute("success", "Service cree avec succes.");
		return "redirect:/services";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
		Service service = serviceService.getServiceById(id);
		if (service == null) {
			redirectAttributes.addFlashAttribute("error", "Service introuvable.");
			return "redirect:/services";
		}
		model.addAttribute("service", service);
		return "service/show";
	}

	@GetMapping("/{id}/edit")
    @PreAuthorize("hasRole('ADMIN') or hasRole('ARTISAN')")
	public String editForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
		Service service = serviceService.getServiceById(id);
		if (service == null) {
			redirectAttributes.addFlashAttribute("error", "Service introuvable.");
			return "redirect:/services";
		}
		model.addAttribute("service", service);
		return "service/edit";
	}

	@PostMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('ARTISAN')")
	public String update(@PathVariable Long id, Service service, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "service/edit";
		}
		Service updated = serviceService.updateService(id, service);
		if (updated == null) {
			redirectAttributes.addFlashAttribute("error", "Service introuvable.");
			return "redirect:/services";
		}
		redirectAttributes.addFlashAttribute("success", "Service mis a jour avec succes.");
		return "redirect:/services";
	}

	@PostMapping("/{id}/delete")
    @PreAuthorize("hasRole('ADMIN') or hasRole('ARTISAN')")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		serviceService.deleteService(id);
		redirectAttributes.addFlashAttribute("success", "Service supprime avec succes.");
		return "redirect:/services";
	}
}
