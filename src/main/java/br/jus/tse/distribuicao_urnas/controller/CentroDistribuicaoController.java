package br.jus.tse.distribuicao_urnas.controller;

import br.jus.tse.distribuicao_urnas.domain.Localizacao;
import br.jus.tse.distribuicao_urnas.model.CentroDistribuicaoDTO;
import br.jus.tse.distribuicao_urnas.repos.LocalizacaoRepository;
import br.jus.tse.distribuicao_urnas.service.CentroDistribuicaoService;
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
@RequestMapping("/centroDistribuicaos")
public class CentroDistribuicaoController {

    private final CentroDistribuicaoService centroDistribuicaoService;
    private final LocalizacaoRepository localizacaoRepository;

    public CentroDistribuicaoController(final CentroDistribuicaoService centroDistribuicaoService,
            final LocalizacaoRepository localizacaoRepository) {
        this.centroDistribuicaoService = centroDistribuicaoService;
        this.localizacaoRepository = localizacaoRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("localizacaoValues", localizacaoRepository.findAll().stream().collect(
                Collectors.toMap(Localizacao::getId, Localizacao::getId)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("centroDistribuicaos", centroDistribuicaoService.findAll());
        return "centroDistribuicao/list";
    }

    @GetMapping("/add")
    public String add(
            @ModelAttribute("centroDistribuicao") final CentroDistribuicaoDTO centroDistribuicaoDTO) {
        return "centroDistribuicao/add";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("centroDistribuicao") @Valid final CentroDistribuicaoDTO centroDistribuicaoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "centroDistribuicao/add";
        }
        centroDistribuicaoService.create(centroDistribuicaoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("centroDistribuicao.create.success"));
        return "redirect:/centroDistribuicaos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("centroDistribuicao", centroDistribuicaoService.get(id));
        return "centroDistribuicao/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("centroDistribuicao") @Valid final CentroDistribuicaoDTO centroDistribuicaoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "centroDistribuicao/edit";
        }
        centroDistribuicaoService.update(id, centroDistribuicaoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("centroDistribuicao.update.success"));
        return "redirect:/centroDistribuicaos";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        final String referencedWarning = centroDistribuicaoService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            centroDistribuicaoService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("centroDistribuicao.delete.success"));
        }
        return "redirect:/centroDistribuicaos";
    }

}
