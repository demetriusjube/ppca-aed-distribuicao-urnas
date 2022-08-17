package br.jus.tse.distribuicao_urnas.controller;

import br.jus.tse.distribuicao_urnas.domain.Veiculo;
import br.jus.tse.distribuicao_urnas.model.PlanoRotaDTO;
import br.jus.tse.distribuicao_urnas.repos.VeiculoRepository;
import br.jus.tse.distribuicao_urnas.service.PlanoRotaService;
import br.jus.tse.distribuicao_urnas.util.WebUtils;
import java.util.stream.Collectors;
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
@RequestMapping("/planoRotas")
public class PlanoRotaController {

    private final PlanoRotaService planoRotaService;
    private final VeiculoRepository veiculoRepository;

    public PlanoRotaController(final PlanoRotaService planoRotaService,
            final VeiculoRepository veiculoRepository) {
        this.planoRotaService = planoRotaService;
        this.veiculoRepository = veiculoRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("veiculoValues", veiculoRepository.findAll().stream().collect(
                Collectors.toMap(Veiculo::getId, Veiculo::getDescricao)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("planoRotas", planoRotaService.findAll());
        return "planoRota/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("planoRota") final PlanoRotaDTO planoRotaDTO) {
        return "planoRota/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("planoRota") @Valid final PlanoRotaDTO planoRotaDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "planoRota/add";
        }
        planoRotaService.create(planoRotaDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("planoRota.create.success"));
        return "redirect:/planoRotas";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("planoRota", planoRotaService.get(id));
        return "planoRota/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("planoRota") @Valid final PlanoRotaDTO planoRotaDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "planoRota/edit";
        }
        planoRotaService.update(id, planoRotaDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("planoRota.update.success"));
        return "redirect:/planoRotas";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        final String referencedWarning = planoRotaService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            planoRotaService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("planoRota.delete.success"));
        }
        return "redirect:/planoRotas";
    }

}
