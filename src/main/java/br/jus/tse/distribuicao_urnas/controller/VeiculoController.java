package br.jus.tse.distribuicao_urnas.controller;

import br.jus.tse.distribuicao_urnas.model.VeiculoDTO;
import br.jus.tse.distribuicao_urnas.service.VeiculoService;
import br.jus.tse.distribuicao_urnas.util.WebUtils;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(final VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("veiculos", veiculoService.findAll());
        return "veiculo/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("veiculo") final VeiculoDTO veiculoDTO) {
        return "veiculo/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("veiculo") @Valid final VeiculoDTO veiculoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "veiculo/add";
        }
        veiculoService.create(veiculoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("veiculo.create.success"));
        return "redirect:/veiculos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("veiculo", veiculoService.get(id));
        return "veiculo/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("veiculo") @Valid final VeiculoDTO veiculoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "veiculo/edit";
        }
        veiculoService.update(id, veiculoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("veiculo.update.success"));
        return "redirect:/veiculos";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        final String referencedWarning = veiculoService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            veiculoService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("veiculo.delete.success"));
        }
        return "redirect:/veiculos";
    }

}
