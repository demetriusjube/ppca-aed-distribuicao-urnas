package br.jus.tse.distribuicao_urnas.controller;

import br.jus.tse.distribuicao_urnas.domain.Localizacao;
import br.jus.tse.distribuicao_urnas.model.DistanciaDTO;
import br.jus.tse.distribuicao_urnas.repos.LocalizacaoRepository;
import br.jus.tse.distribuicao_urnas.service.DistanciaService;
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
@RequestMapping("/distancias")
public class DistanciaController {

    private final DistanciaService distanciaService;
    private final LocalizacaoRepository localizacaoRepository;

    public DistanciaController(final DistanciaService distanciaService,
            final LocalizacaoRepository localizacaoRepository) {
        this.distanciaService = distanciaService;
        this.localizacaoRepository = localizacaoRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("origemValues", localizacaoRepository.findAll().stream().collect(
                Collectors.toMap(Localizacao::getId, Localizacao::getId)));
        model.addAttribute("destinoValues", localizacaoRepository.findAll().stream().collect(
                Collectors.toMap(Localizacao::getId, Localizacao::getId)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("distancias", distanciaService.findAll());
        return "distancia/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("distancia") final DistanciaDTO distanciaDTO) {
        return "distancia/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("distancia") @Valid final DistanciaDTO distanciaDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "distancia/add";
        }
        distanciaService.create(distanciaDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("distancia.create.success"));
        return "redirect:/distancias";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("distancia", distanciaService.get(id));
        return "distancia/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("distancia") @Valid final DistanciaDTO distanciaDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "distancia/edit";
        }
        distanciaService.update(id, distanciaDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("distancia.update.success"));
        return "redirect:/distancias";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        distanciaService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("distancia.delete.success"));
        return "redirect:/distancias";
    }

}
